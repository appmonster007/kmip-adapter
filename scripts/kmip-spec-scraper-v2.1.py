#!/usr/bin/env python3
"""
KMIP 2.1 Specification Scraper
Parses docs/kmip-spec/v2.x/kmip-spec-v2.1-os.html and produces:
  docs/kmip-spec/v2.x/scraped/
    tags.md                  - All TTLV tags with hex values (§11.56)
    enumerations.md          - All enumerations with symbolic values (§11, excl. §11.56)
    bit-masks.md             - All bit mask value names (§12)
    managed-objects.md       - Managed object types with fields (§2)
    structures.md            - Object data structures + attribute data structures
                               + operations data structures + messages + message data structures
                               (§3, §5, §7, §8, §9)
    attributes.md            - All KMIP attributes with data types and sub-fields (§4)
    operations.md            - All operations with request/response payload fields (§6)
    README.md                - Index and summary counts
"""

import re
import os

# ---------------------------------------------------------------------------
# HTML utilities
# ---------------------------------------------------------------------------

def strip_tags(html: str) -> str:
    text = re.sub(r'<[^>]+>', ' ', html)
    text = text.replace('\xa0', ' ').replace('&nbsp;', ' ').replace('\x92', "'")
    return re.sub(r'\s+', ' ', text).strip()


def parse_tr_cells(row_html: str) -> list[str]:
    cells = []
    for m in re.finditer(r'<t[dh]\b[^>]*>(.*?)</t[dh]>', row_html, re.DOTALL | re.IGNORECASE):
        cells.append(strip_tags(m.group(1)))
    return cells


def get_rows_in_chunk(chunk: str) -> list[list[str]]:
    """Return all table rows (as cell lists) found anywhere in chunk."""
    rows = []
    for m in re.finditer(r'<tr\b[^>]*>(.*?)</tr>', chunk, re.DOTALL | re.IGNORECASE):
        cells = parse_tr_cells(m.group(1))
        if cells:
            rows.append(cells)
    return rows


# ---------------------------------------------------------------------------
# Section index
# ---------------------------------------------------------------------------

def build_section_index(content: str) -> list[tuple]:
    """Return list of (level, text, tag_start, tag_end) for all h1–h5."""
    sections = []
    for m in re.finditer(r'<(h[1-5])\b[^>]*>(.*?)</\1>', content, re.DOTALL | re.IGNORECASE):
        level = m.group(1)
        text = strip_tags(m.group(2))
        text = re.sub(r'\s+', ' ', text).strip()
        if text:
            sections.append((level, text, m.start(), m.end()))
    return sections


class Finder:
    """Lightweight section lookup on the pre-built index."""

    RANK = {'h1': 1, 'h2': 2, 'h3': 3, 'h4': 4, 'h5': 5}

    def __init__(self, sections: list[tuple], content: str):
        self.sections = sections
        self.content = content

    def find(self, pattern: str, level: str = None) -> int:
        """Return index of first matching section, or -1."""
        for i, (lvl, text, start, end) in enumerate(self.sections):
            if (level is None or lvl == level) and re.search(pattern, text, re.IGNORECASE):
                return i
        return -1

    def body_range(self, idx: int) -> tuple[int, int]:
        """Return (body_start, body_end) for sections[idx]."""
        if idx < 0:
            return 0, 0
        lvl, text, start, end = self.sections[idx]
        cur_rank = self.RANK.get(lvl, 5)
        body_start = end
        for j in range(idx + 1, len(self.sections)):
            if self.RANK.get(self.sections[j][0], 5) <= cur_rank:
                return body_start, self.sections[j][2]
        return body_start, len(self.content)

    def section_range(self, pattern: str, level: str = None) -> tuple[int, int]:
        return self.body_range(self.find(pattern, level))

    def h2_sections_in_range(self, start: int, end: int) -> list[tuple]:
        """Return all h2 sections whose tag starts in [start, end)."""
        return [(i, lvl, text, s, e)
                for i, (lvl, text, s, e) in enumerate(self.sections)
                if lvl == 'h2' and start <= s < end]


# ---------------------------------------------------------------------------
# Markdown helpers
# ---------------------------------------------------------------------------

def md_table(headers: list, rows: list) -> str:
    if not rows:
        return '_No entries_\n'
    col_count = len(headers)
    col_widths = [len(h) for h in headers]
    for row in rows:
        for i, cell in enumerate(row[:col_count]):
            col_widths[i] = max(col_widths[i], len(str(cell)))

    def fmt_row(cells):
        padded = []
        for i in range(col_count):
            val = str(cells[i]) if i < len(cells) else ''
            padded.append(val.ljust(col_widths[i]))
        return '| ' + ' | '.join(padded) + ' |'

    lines = [fmt_row(headers), '|' + '|'.join('-' * (w + 2) for w in col_widths) + '|']
    for row in rows:
        lines.append(fmt_row(row))
    return '\n'.join(lines) + '\n'


# ---------------------------------------------------------------------------
# Tags  (§11.56 Tag Enumeration)
# ---------------------------------------------------------------------------

def scrape_tags(content: str, finder: Finder) -> list[tuple]:
    idx = finder.find(r'11\.56\s+Tag\s+Enumeration', level='h2')
    start, end = finder.body_range(idx)
    chunk = content[start:end]

    entries = []
    for row in get_rows_in_chunk(chunk):
        if len(row) < 2:
            continue
        name, val = row[0].strip(), row[1].strip()
        skip = {'Tag Name', 'Name', 'Value', 'Tag', '(Unused)', 'Extensions 54xxxxxx'}
        if name in skip or not val:
            continue
        # Tag value is hex like 420001
        if re.search(r'[0-9A-Fa-f]{4,}', val):
            entries.append((name, val))
    return entries


# ---------------------------------------------------------------------------
# Enumerations  (§11, each h2, excluding §11.56)
# ---------------------------------------------------------------------------

def _parse_tables(chunk: str) -> list[list[list[str]]]:
    """Return list of tables; each table is a list of rows; each row is a list of cell texts."""
    tables = []
    for tbl_m in re.finditer(r'<table\b[^>]*>.*?</table>', chunk, re.DOTALL | re.IGNORECASE):
        rows = []
        for row_m in re.finditer(r'<tr\b[^>]*>(.*?)</tr>', tbl_m.group(0), re.DOTALL | re.IGNORECASE):
            cells = parse_tr_cells(row_m.group(1))
            if cells:
                rows.append(cells)
        if rows:
            tables.append(rows)
    return tables


def scrape_enumerations(content: str, finder: Finder) -> list[dict]:
    """
    Each enumeration section has one or two tables:
      - A "Value | Description" table (human-readable, present for some enums)
      - A hex-value table with the enum name as a merged header then "Name | Value" rows

    Prefer the "Value | Description" table. Fall back to the "Name | Value" hex table
    (extracting just the symbolic name column, ignoring the hex value).
    """
    enum_sec_start, enum_sec_end = finder.section_range(r'^11\s+Enumerations', level='h1')
    h2_secs = finder.h2_sections_in_range(enum_sec_start, enum_sec_end)

    skip_vals = {'Value', 'Description', 'Name', 'Extensions', 'Tag Name', 'Hex Value'}

    enumerations = []
    for i, lvl, heading, h2_start, h2_end in h2_secs:
        if re.search(r'Tag\s+Enumeration', heading, re.IGNORECASE):
            continue

        m = re.match(r'^11\.\d+\s+(.+?)\s+Enumeration$', heading, re.IGNORECASE)
        enum_name = m.group(1).strip() if m else re.sub(r'^11\.\d+\s*', '', heading).strip()

        body_start, body_end = finder.body_range(i)
        chunk = content[body_start:body_end]

        tables = _parse_tables(chunk)

        # Find the "Value | Description" table (first column header == "Value")
        desc_table = next(
            (t for t in tables if t and t[0] and t[0][0].strip().lower() == 'value'),
            None)

        # Find the "Name | Value" hex table (first data row has "Name" | "Value" as headers,
        # typically row 1 after a merged-header row 0)
        hex_table = next(
            (t for t in tables
             if t and len(t) >= 2
             and t[1][0].strip().lower() == 'name'
             and len(t[1]) >= 2 and t[1][1].strip().lower() == 'value'),
            None)

        values = []
        if desc_table:
            # Use symbolic names + descriptions from the "Value | Description" table
            for row in desc_table[1:]:  # skip header row
                val = row[0].strip()
                if val and val not in skip_vals:
                    desc = row[1].strip() if len(row) > 1 else ''
                    values.append((val, desc[:120]))
        elif hex_table:
            # Fall back: extract just the symbolic name from the hex table
            for row in hex_table[2:]:  # skip merged-header + "Name | Value" header
                val = row[0].strip()
                if val and val not in skip_vals:
                    values.append((val, ''))

        if enum_name and values:
            enumerations.append({'name': enum_name, 'values': values})

    return enumerations


# ---------------------------------------------------------------------------
# Bit Masks  (§12)
# ---------------------------------------------------------------------------

def scrape_bit_masks(content: str, finder: Finder) -> list[dict]:
    mask_sec_start, mask_sec_end = finder.section_range(r'^12\s+Bit\s+Masks', level='h1')
    h2_secs = finder.h2_sections_in_range(mask_sec_start, mask_sec_end)

    skip_vals = {'Value', 'Description', 'Name', 'Mask', 'Valid KMIP Server Operation'}

    masks = []
    for i, lvl, heading, h2_start, h2_end in h2_secs:
        mask_name = re.sub(r'^12\.\d+\s*', '', heading).strip()

        body_start, body_end = finder.body_range(i)
        chunk = content[body_start:body_end]

        tables = _parse_tables(chunk)
        # Bit mask tables: first column is "Value", second is "Description"
        tbl = next(
            (t for t in tables if t and t[0] and t[0][0].strip().lower() == 'value'),
            tables[0] if tables else None)

        values = []
        if tbl:
            for row in tbl[1:]:  # skip header
                val = row[0].strip()
                if val and val not in skip_vals:
                    desc = row[1].strip() if len(row) > 1 else ''
                    values.append((val, desc[:120]))

        if mask_name and values:
            masks.append({'name': mask_name, 'values': values})

    return masks


# ---------------------------------------------------------------------------
# Generic structure scraper  (Object | Encoding | REQUIRED tables)
# ---------------------------------------------------------------------------

def _extract_struct_fields(chunk: str) -> list[tuple]:
    """
    Find "Item/Object | Encoding | REQUIRED" tables in chunk.
    Returns list of (field_name, encoding, required).
    """
    fields = []
    skip_names = {'object', 'item', 'field', 'name', 'encoding', 'required', 'type', ''}
    for row in get_rows_in_chunk(chunk):
        if len(row) < 2:
            continue
        header_text = ' '.join(row).lower()
        # Identify header rows
        if any(h in header_text for h in ('object', 'encoding', 'required', 'item')):
            cols = [c.lower() for c in row]
            if ('encoding' in cols[1] or 'type' in cols[1] if len(cols) > 1 else False):
                continue  # This is a header row itself
        fname = row[0].strip()
        fenc  = row[1].strip() if len(row) > 1 else ''
        freq  = row[2].strip() if len(row) > 2 else ''

        if fname.lower() in skip_names or fenc.lower() in ('encoding', 'type'):
            continue
        # Skip top-level parent row (row whose name == the structure name itself)
        # indicated by encoding == "Structure" and empty required
        fields.append((fname, fenc, freq))
    return fields


def scrape_h2_structures(content: str, finder: Finder,
                          sec_start: int, sec_end: int,
                          sec_pattern: str) -> list[dict]:
    """Scrape all h2 sections in [sec_start, sec_end) as structure definitions."""
    h2_secs = finder.h2_sections_in_range(sec_start, sec_end)
    structs = []
    for i, lvl, heading, h2_start, h2_end in h2_secs:
        if not re.match(sec_pattern, heading):
            continue
        body_start, body_end = finder.body_range(i)
        chunk = content[body_start:body_end]
        fields = _extract_struct_fields(chunk)
        structs.append({'name': heading, 'fields': fields})
    return structs


# ---------------------------------------------------------------------------
# Attributes  (§4)
# ---------------------------------------------------------------------------

def scrape_attributes(content: str, finder: Finder) -> list[dict]:
    attr_sec_start, attr_sec_end = finder.section_range(
        r'^4\s+Object\s+Attributes', level='h1')
    h2_secs = finder.h2_sections_in_range(attr_sec_start, attr_sec_end)

    attributes = []
    for i, lvl, heading, h2_start, h2_end in h2_secs:
        m = re.match(r'^(4\.\d+)\s+(.+)$', heading)
        if not m:
            continue
        sec_num = m.group(1)
        attr_name = m.group(2).strip()

        body_start, body_end = finder.body_range(i)
        chunk = content[body_start:body_end]

        # Determine data type and sub-fields from tables
        data_type = ''
        sub_fields = []
        metadata = {}

        all_rows = get_rows_in_chunk(chunk)
        # Attribute tables in v2.1:
        # - A 2-col "Item | Encoding" table with the attribute's type in row 1,
        #   followed by metadata rows (SHALL always have a value, Initially set by, …)
        # - Optionally a 3-col "Item | Encoding | REQUIRED" table for sub-fields
        meta_keys = {
            'shall always have a value', 'initially set by', 'modifiable by server',
            'modifiable by client', 'deletable by client', 'multiple instances permitted',
            'when implicitly set', 'applies to object types',
        }

        for row in all_rows:
            if not row:
                continue
            col0 = row[0].strip()
            col1 = row[1].strip() if len(row) > 1 else ''
            col2 = row[2].strip() if len(row) > 2 else ''
            col0_low = col0.lower()

            # Header rows
            if col0_low in ('item', 'object', 'field', 'encoding', 'value', 'description'):
                continue

            # Metadata rows (2-col, key in col0)
            if col0_low in meta_keys:
                metadata[col0] = col1
                continue

            # Primary attribute type row (matches attribute name)
            if col0_low == attr_name.lower() or col0_low == 'unique identifier':
                if not data_type:
                    data_type = col1
                continue

            # Skip top-level structure-name rows (encoding=Structure, no required)
            if col1.lower() == 'structure' and not col2:
                continue

            # Sub-field rows (3-col "item | encoding | required")
            if col1:
                sub_fields.append((col0, col1, col2))

        if not data_type:
            # Fallback: scan body text
            body_text = strip_tags(chunk)
            for dtype in ('Structure', 'Text String', 'Integer', 'Long Integer',
                          'Enumeration', 'Byte String', 'Boolean', 'Date-Time',
                          'Interval', 'Big Integer', 'Date-Time Extended'):
                if dtype.lower() in body_text.lower()[:600]:
                    data_type = dtype
                    break

        attributes.append({
            'section': sec_num,
            'name': attr_name,
            'data_type': data_type,
            'fields': sub_fields,
            'metadata': metadata,
        })

    return attributes


# ---------------------------------------------------------------------------
# Operations  (§6)
# ---------------------------------------------------------------------------

def scrape_operations(content: str, finder: Finder) -> list[dict]:
    ops_sec_start, ops_sec_end = finder.section_range(r'^6\s+Operations', level='h1')
    chunk_full = content[ops_sec_start:ops_sec_end]

    # Operations are h3 sections; h4 sections are error-handling sub-sections
    op_headings = list(re.finditer(
        r'<h3\b[^>]*>(.*?)</h3>', chunk_full, re.DOTALL | re.IGNORECASE))

    def extract_payload_fields(body: str):
        req_fields, res_fields = [], []
        for table_m in re.finditer(
                r'<table\b[^>]*>.*?</table>', body, re.DOTALL | re.IGNORECASE):
            table_html = table_m.group(0)
            rows = []
            for row_m in re.finditer(r'<tr\b[^>]*>(.*?)</tr>', table_html,
                                     re.DOTALL | re.IGNORECASE):
                cells = parse_tr_cells(row_m.group(1))
                if cells:
                    rows.append(cells)
            if len(rows) < 2:
                continue
            first = ' '.join(rows[0]).lower()
            is_request  = 'request payload'  in first
            is_response = 'response payload' in first
            if not (is_request or is_response):
                continue
            # rows[0] = merged "Request/Response Payload" header
            # rows[1] = column headers "Item | REQUIRED | Description"
            data_rows = rows[2:]
            target = req_fields if is_request else res_fields
            skip = {'object', 'item', 'required', 'description', 'field', ''}
            for row in data_rows:
                fname = row[0].strip()
                freq  = row[1].strip() if len(row) > 1 else ''
                fdesc = row[2].strip() if len(row) > 2 else ''
                if fname.lower() in skip:
                    continue
                fname = re.sub(r',?\s*see\s+[\d\.]+$', '', fname, re.IGNORECASE).strip()
                target.append((fname, freq, fdesc[:120]))
        return req_fields, res_fields

    operations = []
    for i, m in enumerate(op_headings):
        heading = strip_tags(m.group(1))
        heading = re.sub(r'\s+', ' ', heading).strip()

        # Only include numbered operation headings, skip "Error Handling" sub-headings
        op_m = re.match(r'^(6\.\d+\.\d+)\s+(.+)$', heading)
        if not op_m or 'error handling' in heading.lower():
            continue

        sec_num = op_m.group(1)
        op_name = op_m.group(2).strip()

        body_start = m.end()
        body_end = op_headings[i + 1].start() if i + 1 < len(op_headings) else len(chunk_full)
        body = chunk_full[body_start:body_end]

        req_fields, res_fields = extract_payload_fields(body)
        operations.append({
            'section': sec_num,
            'name': op_name,
            'request_fields': req_fields,
            'response_fields': res_fields,
        })

    return operations


# ---------------------------------------------------------------------------
# Markdown writers
# ---------------------------------------------------------------------------

VERSION = '2.1'


def write_tags_md(tags: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP {VERSION} — TTLV Tags\n\n')
        f.write(f'Total: **{len(tags)}** tags\n\n')
        f.write(md_table(['Tag Name', 'Hex Value'], tags))


def write_enumerations_md(enumerations: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP {VERSION} — Enumerations\n\n')
        f.write(f'Total: **{len(enumerations)}** enumeration types\n\n')
        for enum in enumerations:
            f.write(f'## {enum["name"]}\n\n')
            f.write(md_table(['Value', 'Description'], enum['values']))
            f.write('\n')


def write_bit_masks_md(masks: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP {VERSION} — Bit Masks\n\n')
        f.write(f'Total: **{len(masks)}** bit mask types\n\n')
        for mask in masks:
            f.write(f'## {mask["name"]}\n\n')
            f.write(md_table(['Value', 'Description'], mask['values']))
            f.write('\n')


def write_structures_md(structures: list, title: str, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP {VERSION} — {title}\n\n')
        f.write(f'Total: **{len(structures)}** structures\n\n')
        for s in structures:
            f.write(f'## {s["name"]}\n\n')
            if s['fields']:
                # Filter header-only rows (encoding == 'Structure' with empty required is top-level)
                data_fields = [(fn, fe, fr) for fn, fe, fr in s['fields']
                               if not (fe.lower() == 'structure' and not fr)]
                if data_fields:
                    f.write(md_table(['Field', 'Encoding / Type', 'Required'], data_fields))
                else:
                    f.write('_No sub-fields (see spec for definition)_\n')
            else:
                f.write('_No fields extracted (see spec for definition)_\n')
            f.write('\n')


def write_managed_objects_md(managed: list, out_path: str):
    write_structures_md(managed, 'Managed Objects', out_path)


def write_attributes_md(attributes: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP {VERSION} — Object Attributes\n\n')
        f.write(f'Total: **{len(attributes)}** attributes\n\n')

        summary_rows = []
        for a in attributes:
            dt = a['data_type'] or '—'
            is_struct = 'Yes' if a['fields'] else ''
            summary_rows.append((f"{a['section']} {a['name']}", dt, is_struct))
        f.write('## Summary\n\n')
        f.write(md_table(['Attribute', 'Data Type', 'Is Structure'], summary_rows))
        f.write('\n')

        f.write('## Details\n\n')
        for a in attributes:
            f.write(f'### {a["section"]} {a["name"]}\n\n')
            f.write(f'**Data Type:** {a["data_type"] or "—"}\n\n')
            if a['metadata']:
                for k, v in a['metadata'].items():
                    f.write(f'- **{k}:** {v}\n')
                f.write('\n')
            if a['fields']:
                f.write(md_table(['Field', 'Encoding / Type', 'Required'], a['fields']))
                f.write('\n')


def write_operations_md(operations: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP {VERSION} — Operations\n\n')
        f.write(f'Total: **{len(operations)}** operations\n\n')
        for op in operations:
            f.write(f'## {op["section"]} {op["name"]}\n\n')

            f.write('### Request Payload\n\n')
            if op['request_fields']:
                f.write(md_table(['Field', 'Required', 'Description'], op['request_fields']))
            else:
                f.write('_No fields (empty payload per spec)_\n')
            f.write('\n')

            f.write('### Response Payload\n\n')
            if op['response_fields']:
                f.write(md_table(['Field', 'Required', 'Description'], op['response_fields']))
            else:
                f.write('_No fields (empty payload per spec)_\n')
            f.write('\n')


def write_readme(out_dir: str, counts: dict):
    with open(os.path.join(out_dir, 'README.md'), 'w') as f:
        f.write(f'# KMIP {VERSION} Specification — Scraped Reference\n\n')
        f.write('Generated from `docs/kmip-spec/v2.x/kmip-spec-v2.1-os.html`\n\n')
        f.write('## Files\n\n')
        f.write('| File | Contents | Count |\n')
        f.write('|------|----------|-------|\n')
        rows = [
            ('tags.md',           f'TTLV Tags (§11.56)',                       counts.get('tags', 0)),
            ('enumerations.md',   f'Enumerations (§11, excl. §11.56)',         counts.get('enums', 0)),
            ('bit-masks.md',      f'Bit Masks (§12)',                           counts.get('masks', 0)),
            ('managed-objects.md',f'Managed Objects (§2)',                     counts.get('managed', 0)),
            ('structures.md',     f'Data Structures (§3, §5, §7, §8, §9)',     counts.get('structs', 0)),
            ('attributes.md',     f'Object Attributes (§4)',                   counts.get('attrs', 0)),
            ('operations.md',     f'Operations — Request/Response (§6)',        counts.get('ops', 0)),
        ]
        for fname, desc, count in rows:
            f.write(f'| [{fname}]({fname}) | {desc} | {count} |\n')


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    spec_path = 'docs/kmip-spec/v2.x/kmip-spec-v2.1-os.html'
    out_dir   = 'docs/kmip-spec/v2.x/scraped'
    os.makedirs(out_dir, exist_ok=True)

    print(f'Reading {spec_path}...')
    content = open(spec_path, encoding='latin-1').read()
    print(f'  File size: {len(content):,} bytes')

    print('Building section index...')
    sections = build_section_index(content)
    finder = Finder(sections, content)
    print(f'  Found {len(sections)} headings')

    # ---- Tags (§11.56) ----
    print('Scraping tags (§11.56)...')
    tags = scrape_tags(content, finder)
    print(f'  Found {len(tags)} tags')
    write_tags_md(tags, os.path.join(out_dir, 'tags.md'))

    # ---- Enumerations (§11, excl. §11.56) ----
    print('Scraping enumerations (§11)...')
    enumerations = scrape_enumerations(content, finder)
    print(f'  Found {len(enumerations)} enumeration types')
    write_enumerations_md(enumerations, os.path.join(out_dir, 'enumerations.md'))

    # ---- Bit Masks (§12) ----
    print('Scraping bit masks (§12)...')
    bit_masks = scrape_bit_masks(content, finder)
    print(f'  Found {len(bit_masks)} bit mask types')
    write_bit_masks_md(bit_masks, os.path.join(out_dir, 'bit-masks.md'))

    # ---- Managed Objects (§2) ----
    print('Scraping managed objects (§2)...')
    managed_start, managed_end = finder.section_range(r'^2\s+Objects', level='h1')
    managed = scrape_h2_structures(content, finder, managed_start, managed_end,
                                   r'^2\.\d+\s+')
    print(f'  Found {len(managed)} managed object types')
    write_managed_objects_md(managed, os.path.join(out_dir, 'managed-objects.md'))

    # ---- Data Structures: §3 Object Data Structures ----
    print('Scraping object data structures (§3)...')
    s3_start, s3_end = finder.section_range(r'^3\s+Object\s+Data\s+Structures', level='h1')
    structs_s3 = scrape_h2_structures(content, finder, s3_start, s3_end, r'^3\.\d+\s+')
    print(f'  §3: {len(structs_s3)} structures')

    # ---- §5 Attribute Data Structures ----
    print('Scraping attribute data structures (§5)...')
    s5_start, s5_end = finder.section_range(r'^5\s+Attribute\s+Data\s+Structures', level='h1')
    structs_s5 = scrape_h2_structures(content, finder, s5_start, s5_end, r'^5\.\d+\s+')
    print(f'  §5: {len(structs_s5)} structures')

    # ---- §7 Operations Data Structures ----
    print('Scraping operations data structures (§7)...')
    s7_start, s7_end = finder.section_range(r'^7\s+Operations\s+Data\s+Structures', level='h1')
    structs_s7 = scrape_h2_structures(content, finder, s7_start, s7_end, r'^7\.\d+\s+')
    print(f'  §7: {len(structs_s7)} structures')

    # ---- §8 Messages ----
    print('Scraping messages (§8)...')
    s8_start, s8_end = finder.section_range(r'^8\s+Messages', level='h1')
    structs_s8 = scrape_h2_structures(content, finder, s8_start, s8_end, r'^8\.\d+\s+')
    print(f'  §8: {len(structs_s8)} structures')

    # ---- §9 Message Data Structures ----
    print('Scraping message data structures (§9)...')
    s9_start, s9_end = finder.section_range(r'^9\s+Message\s+Data\s+Structures', level='h1')
    structs_s9 = scrape_h2_structures(content, finder, s9_start, s9_end, r'^9\.\d+\s+')
    print(f'  §9: {len(structs_s9)} structures')

    all_structs = structs_s3 + structs_s5 + structs_s7 + structs_s8 + structs_s9
    print(f'  Total structures: {len(all_structs)}')
    write_structures_md(all_structs, 'Data Structures (§3, §5, §7, §8, §9)',
                        os.path.join(out_dir, 'structures.md'))

    # ---- Attributes (§4) ----
    print('Scraping attributes (§4)...')
    attributes = scrape_attributes(content, finder)
    print(f'  Found {len(attributes)} attributes')
    write_attributes_md(attributes, os.path.join(out_dir, 'attributes.md'))

    # ---- Operations (§6) ----
    print('Scraping operations (§6)...')
    operations = scrape_operations(content, finder)
    c2s = [o for o in operations if o['section'].startswith('6.1')]
    s2c = [o for o in operations if o['section'].startswith('6.2')]
    print(f'  Found {len(c2s)} client-to-server, {len(s2c)} server-to-client ops')
    write_operations_md(operations, os.path.join(out_dir, 'operations.md'))

    # ---- README ----
    counts = {
        'tags':    len(tags),
        'enums':   len(enumerations),
        'masks':   len(bit_masks),
        'managed': len(managed),
        'structs': len(all_structs),
        'attrs':   len(attributes),
        'ops':     len(operations),
    }
    write_readme(out_dir, counts)

    print('\nDone! Output:')
    for fname in sorted(os.listdir(out_dir)):
        fpath = os.path.join(out_dir, fname)
        size = os.path.getsize(fpath)
        print(f'  {fname:45s} {size:>8,} bytes')

    print('\nSummary:')
    for k, v in counts.items():
        print(f'  {k:20s}: {v}')


if __name__ == '__main__':
    main()
