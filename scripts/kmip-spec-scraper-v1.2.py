#!/usr/bin/env python3
"""
KMIP 1.2 Specification Scraper
Parses docs/kmip-spec/v1.x/kmip-spec-v1.2-os.html and produces:
  docs/kmip-spec/v1.x/scraped/
    tags.md           - All TTLV tags with hex values
    enumerations.md   - All enumerations with their values
    bit-masks.md      - All bit masks with their values
    structures.md     - All structures (Base Objects, Message Contents, etc.)
    managed-objects.md- Managed object types with fields
    attributes.md     - All KMIP attributes with data types
    operations.md     - All operations with request/response payload fields
    README.md         - Index and summary counts
"""

import re
import os
from html.parser import HTMLParser
from typing import Optional

# ---------------------------------------------------------------------------
# HTML utility
# ---------------------------------------------------------------------------

def strip_tags(html: str) -> str:
    """Remove HTML tags and normalize whitespace."""
    text = re.sub(r'<[^>]+>', ' ', html)
    text = text.replace('\xa0', ' ').replace('&nbsp;', ' ')
    text = re.sub(r'\s+', ' ', text)
    return text.strip()


def parse_tables_in_chunk(html: str):
    """
    Parse all <table> elements in html.
    Returns list of tables, each a list of rows, each a list of cell texts.
    """
    tables = []
    for table_m in re.finditer(r'<table\b[^>]*>(.*?)</table>', html, re.DOTALL | re.IGNORECASE):
        table_html = table_m.group(1)
        rows = []
        for row_m in re.finditer(r'<tr\b[^>]*>(.*?)</tr>', table_html, re.DOTALL | re.IGNORECASE):
            row_html = row_m.group(1)
            cells = []
            for cell_m in re.finditer(r'<t[hd]\b[^>]*>(.*?)</t[hd]>', row_html, re.DOTALL | re.IGNORECASE):
                cells.append(strip_tags(cell_m.group(1)))
            if cells:
                rows.append(cells)
        if rows:
            tables.append(rows)
    return tables


def get_section_html(content: str, start_pos: int, end_pos: int) -> str:
    return content[start_pos:end_pos]


# ---------------------------------------------------------------------------
# Section index builder
# ---------------------------------------------------------------------------

class SectionIndexer(HTMLParser):
    """Collects all heading positions with their text and level."""

    def __init__(self):
        super().__init__()
        self.sections = []  # (level, text, byte_offset)
        self._cur_tag = None
        self._cur_text = ''
        self._cur_offset = 0

    def handle_starttag(self, tag, attrs):
        if tag in ('h1', 'h2', 'h3', 'h4', 'h5'):
            self._cur_tag = tag
            self._cur_text = ''
            self._cur_offset = self.getpos()[0]  # line number proxy

    def handle_data(self, data):
        if self._cur_tag:
            self._cur_text += data

    def handle_endtag(self, tag):
        if tag == self._cur_tag:
            text = self._cur_text.strip().replace('\xa0', ' ')
            text = re.sub(r'\s+', ' ', text)
            if text:
                self.sections.append((tag, text, self._cur_offset))
            self._cur_tag = None


def build_section_byte_index(content: str):
    """Return list of (heading_level, heading_text, byte_start_of_heading_tag)."""
    sections = []
    for m in re.finditer(r'<(h[1-5])\b[^>]*>(.*?)</\1>', content, re.DOTALL | re.IGNORECASE):
        level = m.group(1)
        text = strip_tags(m.group(2))
        text = re.sub(r'\s+', ' ', text).strip()
        if text:
            sections.append((level, text, m.start(), m.end()))
    return sections


# ---------------------------------------------------------------------------
# Table parser helpers
# ---------------------------------------------------------------------------

def md_table(headers: list, rows: list) -> str:
    """Render a markdown table."""
    if not rows:
        return '_No entries_\n'
    col_widths = [max(len(h), max((len(str(r[i])) if i < len(r) else 0) for r in rows))
                  for i, h in enumerate(headers)]
    lines = []

    def row_str(cells):
        padded = [str(cells[i]).ljust(col_widths[i]) if i < len(cells) else ' ' * col_widths[i]
                  for i in range(len(headers))]
        return '| ' + ' | '.join(padded) + ' |'

    lines.append(row_str(headers))
    lines.append('|' + '|'.join('-' * (w + 2) for w in col_widths) + '|')
    for r in rows:
        lines.append(row_str(r))
    return '\n'.join(lines) + '\n'


# ---------------------------------------------------------------------------
# Core scraping functions
# ---------------------------------------------------------------------------

def scrape_tags(content: str, tags_start: int, tags_end: int) -> list:
    """Parse the Tags table (section 9.1.3.1). Returns list of (name, tag_value)."""
    chunk = get_section_html(content, tags_start, tags_end)
    entries = []
    for table in parse_tables_in_chunk(chunk):
        for row in table:
            if len(row) >= 2:
                name = row[0].strip()
                val = row[1].strip()
                if name and val and name not in ('Object', 'Tag', 'Tag Value',
                                                  '(Unused)', 'Extensions 8xxxxxxx'):
                    if re.search(r'[0-9A-Fa-f]{4,}', val):
                        entries.append((name, val))
    return entries


def scrape_enumerations(content: str, enum_start: int, enum_end: int) -> list:
    """
    Parse the Enumerations section (9.1.3.2).
    Each sub-section is one enumeration type.
    Returns list of dicts: {name, values: [(name, hex_val), ...]}
    """
    chunk = get_section_html(content, enum_start, enum_end)
    enumerations = []

    # Find each h5 sub-section
    h5_positions = [(m.start(), m.end()) for m in
                    re.finditer(r'<h5\b[^>]*>.*?</h5>', chunk, re.DOTALL | re.IGNORECASE)]

    for i, (h5_start, h5_end) in enumerate(h5_positions):
        enum_name_raw = strip_tags(chunk[h5_start:h5_end])
        # Extract just the name part (strip any leading section number like "9.1.3.2.N ")
        enum_name = re.sub(r'^\d+(\.\d+)+\s*', '', enum_name_raw)
        enum_name = re.sub(r'\s+Enumeration\s*$', '', enum_name, flags=re.IGNORECASE).strip()
        # Also strip trailing anchor text (Ref_enum_xxx)
        enum_name = re.sub(r'\s+Ref\s+\S+$', '', enum_name).strip()

        # Section body is between this h5 and the next
        body_start = h5_end
        body_end = h5_positions[i + 1][0] if i + 1 < len(h5_positions) else len(chunk)
        body = chunk[body_start:body_end]

        values = []
        for table in parse_tables_in_chunk(body):
            for row in table:
                if len(row) >= 2:
                    vname = row[0].strip()
                    vval = row[1].strip()
                    if vname and vval and vname not in ('Name', 'Value', 'Extensions'):
                        if re.search(r'[0-9A-Fa-f]{6,8}', vval):
                            values.append((vname, vval))

        if enum_name and values:
            enumerations.append({'name': enum_name, 'values': values})

    return enumerations


def scrape_bit_masks(content: str, mask_start: int, mask_end: int) -> list:
    """
    Parse Bit Masks section (9.1.3.3).
    Returns list of dicts: {name, values: [(name, hex_val), ...]}
    """
    chunk = get_section_html(content, mask_start, mask_end)
    masks = []

    h5_positions = [(m.start(), m.end()) for m in
                    re.finditer(r'<h5\b[^>]*>.*?</h5>', chunk, re.DOTALL | re.IGNORECASE)]

    for i, (h5_start, h5_end) in enumerate(h5_positions):
        name_raw = strip_tags(chunk[h5_start:h5_end])
        name = re.sub(r'^\d+(\.\d+)+\s*', '', name_raw).strip()
        name = re.sub(r'\s+Ref\s+\S+$', '', name).strip()

        body_start = h5_end
        body_end = h5_positions[i + 1][0] if i + 1 < len(h5_positions) else len(chunk)
        body = chunk[body_start:body_end]

        values = []
        for table in parse_tables_in_chunk(body):
            for row in table:
                if len(row) >= 2:
                    vname = row[0].strip()
                    vval = row[1].strip()
                    if vname and vval and vname not in ('Name', 'Value', 'Extensions'):
                        if re.search(r'[0-9A-Fa-f]{4,}', vval):
                            values.append((vname, vval))

        if name and values:
            masks.append({'name': name, 'values': values})

    return masks


def scrape_structure_section(content: str, sec_start: int, sec_end: int, sec_name: str) -> list:
    """
    Parse a section containing structure definitions (like section 2.1, 2.2, 6, 7).
    Returns list of dicts: {name, section_num, fields: [(field_name, encoding, required)]}
    """
    chunk = get_section_html(content, sec_start, sec_end)
    structures = []

    # Find all h3 and h4 sub-headings (individual structure definitions)
    sub_headings = list(re.finditer(r'<(h[34])\b[^>]*>(.*?)</\1>', chunk, re.DOTALL | re.IGNORECASE))

    for i, m in enumerate(sub_headings):
        heading_text = strip_tags(m.group(2))
        heading_text = re.sub(r'\s+', ' ', heading_text).strip()

        # Skip intro headings that aren't structures
        if re.match(r'^\d+(\.\d+)*\s*(Transparent Key Structures|Template-Attribute Structures|'
                    r'Operations outside|Default Operation|Base Objects|Managed Objects|'
                    r'Message Format|Message Structure)$', heading_text, re.IGNORECASE):
            continue

        body_start = m.end()
        body_end = sub_headings[i + 1].start() if i + 1 < len(sub_headings) else len(chunk)
        body = chunk[body_start:body_end]

        fields = []
        for table in parse_tables_in_chunk(body):
            # Look for tables with Object/Encoding/REQUIRED pattern
            if len(table) < 2:
                continue
            header_row = table[0]
            header_text = ' '.join(header_row).lower()
            if not any(kw in header_text for kw in ('encoding', 'object', 'type')):
                continue

            # Determine columns
            cols = [c.lower() for c in header_row]
            obj_col = next((i for i, c in enumerate(cols) if 'object' in c or 'item' in c or 'field' in c), 0)
            enc_col = next((i for i, c in enumerate(cols) if 'encoding' in c or 'type' in c), 1)
            req_col = next((i for i, c in enumerate(cols) if 'required' in c or 'req' in c), 2 if len(cols) > 2 else -1)

            for row in table[1:]:
                if len(row) <= obj_col:
                    continue
                fname = row[obj_col].strip()
                fenc = row[enc_col].strip() if enc_col < len(row) else ''
                freq = row[req_col].strip() if req_col >= 0 and req_col < len(row) else ''

                # Skip header-like rows
                if fname.lower() in ('object', 'item', 'field', 'name', '', 'structure'):
                    continue
                if fname == heading_text.split()[-1]:
                    # Top-level "parent" row, skip
                    continue

                fields.append((fname, fenc, freq))

        if heading_text:
            structures.append({
                'name': heading_text,
                'fields': fields
            })

    return structures


def scrape_attributes_section(content: str, sec_start: int, sec_end: int) -> list:
    """
    Parse section 3 (Attributes). Each h2 is one attribute.
    Returns list of dicts: {name, section_num, data_type, notes}
    """
    chunk = get_section_html(content, sec_start, sec_end)
    attributes = []

    sub_headings = list(re.finditer(r'<h2\b[^>]*>(.*?)</h2>', chunk, re.DOTALL | re.IGNORECASE))

    for i, m in enumerate(sub_headings):
        heading_text = strip_tags(m.group(1))
        heading_text = re.sub(r'\s+', ' ', heading_text).strip()

        # Skip non-attribute headings
        m2 = re.match(r'^(3\.\d+)\s+(.+)$', heading_text)
        if not m2:
            continue
        sec_num = m2.group(1)
        attr_name = m2.group(2).strip()

        body_start = m.end()
        body_end = sub_headings[i + 1].start() if i + 1 < len(sub_headings) else len(chunk)
        body = chunk[body_start:body_end]

        # Extract data type from first table or first paragraph
        data_type = ''
        fields = []

        for table in parse_tables_in_chunk(body):
            if len(table) < 2:
                continue
            header_row = table[0]
            header_text = ' '.join(header_row).lower()
            if not any(kw in header_text for kw in ('encoding', 'type', 'object')):
                continue
            cols = [c.lower() for c in header_row]
            enc_col = next((i for i, c in enumerate(cols) if 'encoding' in c or 'type' in c), 1)
            obj_col = next((i for i, c in enumerate(cols) if 'object' in c or 'item' in c or 'attribute' in c.lower()), 0)

            for row in table[1:]:
                fname = row[obj_col].strip() if obj_col < len(row) else ''
                ftype = row[enc_col].strip() if enc_col < len(row) else ''
                if fname and ftype and fname.lower() not in ('object', 'item', 'name'):
                    if fname.lower() == attr_name.lower():
                        data_type = ftype
                    else:
                        fields.append((fname, ftype))

        if not data_type:
            # Try to detect from body text
            body_text = strip_tags(body)
            for dtype in ('Structure', 'Text String', 'Integer', 'Long Integer',
                          'Enumeration', 'Byte String', 'Boolean', 'Date-Time', 'Interval', 'Big Integer'):
                if dtype.lower() in body_text.lower()[:500]:
                    data_type = dtype
                    break

        attributes.append({
            'section': sec_num,
            'name': attr_name,
            'data_type': data_type,
            'fields': fields
        })

    return attributes


def scrape_operations(content: str, sections: list, op_sections_range) -> list:
    """
    Parse operation sections (4.x Client-to-Server, 5.x Server-to-Client).
    Returns list of dicts: {name, section_num, request_fields, response_fields}

    KMIP operation tables have this structure:
      Row 0: merged header "Request Payload" or "Response Payload"
      Row 1: column headers "Object" | "REQUIRED" | "Description"
      Rows 2+: field data
    """
    start_idx, end_idx = op_sections_range
    chunk = get_section_html(content, start_idx, end_idx)
    operations = []

    # Each h2 is one operation
    sub_headings = list(re.finditer(r'<h2\b[^>]*>(.*?)</h2>', chunk, re.DOTALL | re.IGNORECASE))

    def extract_op_fields_from_body(body: str):
        """
        Find all tables in body. A table whose first row contains 'Request Payload'
        or 'Response Payload' in the merged header is an op payload table.
        Returns (request_fields, response_fields) where each is list of
        (field_name, required, description).
        """
        req_fields = []
        res_fields = []

        # Find each table with its byte position in body
        for table_m in re.finditer(r'<table\b[^>]*>(.*?)</table>', body, re.DOTALL | re.IGNORECASE):
            table_html = table_m.group(0)
            table_start = table_m.start()

            # Get all rows
            rows = []
            for row_m in re.finditer(r'<tr\b[^>]*>(.*?)</tr>', table_html, re.DOTALL | re.IGNORECASE):
                cells = []
                for cell_m in re.finditer(r'<t[hd]\b[^>]*>(.*?)</t[hd]>', row_m.group(1), re.DOTALL | re.IGNORECASE):
                    cells.append(strip_tags(cell_m.group(1)))
                if cells:
                    rows.append(cells)

            if len(rows) < 2:
                continue

            # Check if first row is a payload header (merged cell "Request Payload" etc.)
            first_row_text = ' '.join(rows[0]).lower()
            is_request = 'request payload' in first_row_text
            is_response = 'response payload' in first_row_text

            if not (is_request or is_response):
                # Try to infer from context before this table
                before = body[:table_start].lower()
                last_req = before.rfind('request payload')
                last_res = before.rfind('response payload')
                if last_req < 0 and last_res < 0:
                    continue
                is_response = last_res > last_req
                is_request = not is_response

                # Find data rows — assume header is first row
                header_row = rows[0]
                data_rows = rows[1:]
            else:
                # Skip the merged header row + column header row
                data_rows = rows[2:] if len(rows) > 2 else []

            # Column mapping: Object | REQUIRED | Description
            # (header may be row 1 with "Object", "REQUIRED", "Description")
            fields_out = req_fields if is_request else res_fields
            for row in data_rows:
                if len(row) < 1:
                    continue
                fname = row[0].strip()
                freq = row[1].strip() if len(row) > 1 else ''
                fdesc = row[2].strip() if len(row) > 2 else ''
                # Skip header-like rows
                if fname.lower() in ('object', 'item', 'required', 'description', ''):
                    continue
                # Clean up field name (may have "see 3.x" suffix)
                fname = re.sub(r',?\s*see\s+[\d\.]+$', '', fname, flags=re.IGNORECASE).strip()
                fname = re.sub(r'\s+', ' ', fname)
                fields_out.append((fname, freq, fdesc[:80]))

        return req_fields, res_fields

    for i, m in enumerate(sub_headings):
        heading_text = strip_tags(m.group(1))
        heading_text = re.sub(r'\s+', ' ', heading_text).strip()

        m2 = re.match(r'^(\d+\.\d+)\s+(.+)$', heading_text)
        if not m2:
            continue
        sec_num = m2.group(1)
        op_name = m2.group(2).strip()

        body_start = m.end()
        body_end = sub_headings[i + 1].start() if i + 1 < len(sub_headings) else len(chunk)
        body = chunk[body_start:body_end]

        request_fields, response_fields = extract_op_fields_from_body(body)

        operations.append({
            'section': sec_num,
            'name': op_name,
            'request_fields': request_fields,
            'response_fields': response_fields
        })

    return operations


# ---------------------------------------------------------------------------
# Markdown writers
# ---------------------------------------------------------------------------

def write_tags_md(tags: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write('# KMIP 1.2 — TTLV Tags\n\n')
        f.write(f'Total: **{len(tags)}** tags\n\n')
        f.write(md_table(['Tag Name', 'Hex Value'], tags))


def write_enumerations_md(enumerations: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write('# KMIP 1.2 — Enumerations\n\n')
        f.write(f'Total: **{len(enumerations)}** enumeration types\n\n')
        for enum in enumerations:
            f.write(f'## {enum["name"]}\n\n')
            f.write(md_table(['Value Name', 'Hex'], enum['values']))
            f.write('\n')


def write_bit_masks_md(masks: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write('# KMIP 1.2 — Bit Masks\n\n')
        f.write(f'Total: **{len(masks)}** bit mask types\n\n')
        for mask in masks:
            f.write(f'## {mask["name"]}\n\n')
            f.write(md_table(['Bit Name', 'Hex'], mask['values']))
            f.write('\n')


def write_structures_md(structures: list, title: str, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP 1.2 — {title}\n\n')
        f.write(f'Total: **{len(structures)}** structures\n\n')
        for s in structures:
            f.write(f'## {s["name"]}\n\n')
            if s['fields']:
                f.write(md_table(['Field', 'Encoding / Type', 'Required'], s['fields']))
            else:
                f.write('_No fields extracted (see spec for definition)_\n')
            f.write('\n')


def write_attributes_md(attributes: list, out_path: str):
    with open(out_path, 'w') as f:
        f.write('# KMIP 1.2 — Attributes\n\n')
        f.write(f'Total: **{len(attributes)}** attributes\n\n')

        # Summary table
        summary_rows = []
        for a in attributes:
            dt = a['data_type'] if a['data_type'] else '—'
            has_fields = 'Yes' if a['fields'] else ''
            summary_rows.append((f"{a['section']} {a['name']}", dt, has_fields))
        f.write('## Summary\n\n')
        f.write(md_table(['Attribute', 'Data Type', 'Is Structure'], summary_rows))
        f.write('\n')

        f.write('## Details\n\n')
        for a in attributes:
            f.write(f'### {a["section"]} {a["name"]}\n\n')
            f.write(f'**Data Type:** {a["data_type"] or "—"}\n\n')
            if a['fields']:
                f.write(md_table(['Field', 'Encoding / Type'], a['fields']))
                f.write('\n')


def write_operations_md(operations: list, title: str, out_path: str):
    with open(out_path, 'w') as f:
        f.write(f'# KMIP 1.2 — {title}\n\n')
        f.write(f'Total: **{len(operations)}** operations\n\n')
        for op in operations:
            f.write(f'## {op["section"]} {op["name"]}\n\n')

            f.write('### Request Payload\n\n')
            if op['request_fields']:
                f.write(md_table(['Field', 'Required', 'Description'], op['request_fields']))
            else:
                f.write('_No fields (empty or not scraped)_\n')
            f.write('\n')

            f.write('### Response Payload\n\n')
            if op['response_fields']:
                f.write(md_table(['Field', 'Required', 'Description'], op['response_fields']))
            else:
                f.write('_No fields (empty or not scraped)_\n')
            f.write('\n')


def write_readme(out_dir: str, counts: dict):
    with open(os.path.join(out_dir, 'README.md'), 'w') as f:
        f.write('# KMIP 1.2 Specification — Scraped Reference\n\n')
        f.write('Generated from `docs/kmip-spec/v1.x/kmip-spec-v1.2-os.html`\n\n')
        f.write('## Files\n\n')
        f.write('| File | Contents | Count |\n')
        f.write('|------|----------|-------|\n')
        for fname, desc, count in [
            ('tags.md', 'TTLV Tags (section 9.1.3.1)', counts.get('tags', 0)),
            ('enumerations.md', 'Enumerations (section 9.1.3.2)', counts.get('enums', 0)),
            ('bit-masks.md', 'Bit Masks (section 9.1.3.3)', counts.get('masks', 0)),
            ('attributes.md', 'KMIP Attributes (section 3)', counts.get('attrs', 0)),
            ('structures.md', 'Base Objects & Structures (sections 2.1, 6, 7)', counts.get('structs', 0)),
            ('managed-objects.md', 'Managed Objects (section 2.2)', counts.get('managed', 0)),
            ('operations.md', 'Operations — Request/Response (sections 4, 5)', counts.get('ops', 0)),
        ]:
            f.write(f'| [{fname}]({fname}) | {desc} | {count} |\n')


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    spec_path = 'docs/kmip-spec/v1.x/kmip-spec-v1.2-os.html'
    out_dir = 'docs/kmip-spec/v1.x/scraped'
    os.makedirs(out_dir, exist_ok=True)

    print(f'Reading {spec_path}...')
    content = open(spec_path, encoding='latin-1').read()
    print(f'  File size: {len(content):,} bytes')

    # Build section byte index
    print('Building section index...')
    sections = build_section_byte_index(content)
    print(f'  Found {len(sections)} headings')

    def find_section(pattern, level=None):
        """Find first section matching regex pattern, optionally filtered by heading level."""
        for i, (lvl, text, start, end) in enumerate(sections):
            if (level is None or lvl == level) and re.search(pattern, text, re.IGNORECASE):
                return i, start
        return -1, -1

    def section_range(pattern, level=None):
        """Return (body_start, body_end) for a matching section."""
        idx, h_start = find_section(pattern, level)
        if idx == -1:
            return 0, 0
        body_start = sections[idx][3]  # end of heading tag
        # End is start of next same-or-higher level heading
        cur_level = sections[idx][0]
        level_rank = {'h1': 1, 'h2': 2, 'h3': 3, 'h4': 4, 'h5': 5}
        cur_rank = level_rank.get(cur_level, 3)
        for j in range(idx + 1, len(sections)):
            next_rank = level_rank.get(sections[j][0], 3)
            if next_rank <= cur_rank:
                return body_start, sections[j][2]
        return body_start, len(content)

    # Locate key sections by byte position
    def h4_pos(pattern):
        for lvl, text, start, end in sections:
            if lvl == 'h4' and re.search(pattern, text, re.IGNORECASE):
                return start, end
        return 0, 0

    tags_h_start, tags_h_end = h4_pos(r'9\.1\.3\.1.*Tags')
    enum_h_start, enum_h_end = h4_pos(r'9\.1\.3\.2.*Enum')
    mask_h_start, mask_h_end = h4_pos(r'9\.1\.3\.3.*Bit')

    # --- Tags ---
    print('Scraping tags...')
    tags = scrape_tags(content, tags_h_end, enum_h_start)
    print(f'  Found {len(tags)} tags')
    write_tags_md(tags, os.path.join(out_dir, 'tags.md'))

    # --- Enumerations ---
    print('Scraping enumerations...')
    enumerations = scrape_enumerations(content, enum_h_end, mask_h_start)
    print(f'  Found {len(enumerations)} enumeration types')
    write_enumerations_md(enumerations, os.path.join(out_dir, 'enumerations.md'))

    # --- Bit Masks ---
    print('Scraping bit masks...')
    masks = scrape_bit_masks(content, mask_h_end, len(content))
    print(f'  Found {len(masks)} bit mask types')
    write_bit_masks_md(masks, os.path.join(out_dir, 'bit-masks.md'))

    # --- Section 2.1: Base Objects & Structures ---
    print('Scraping base objects (section 2.1)...')
    base_start, base_end = section_range(r'^2\s.*Objects$', level='h1')
    managed_h_start, _ = find_section(r'^2\.2\s+Managed Objects', level='h2')
    managed_h_start_pos = sections[managed_h_start][2] if managed_h_start >= 0 else base_end

    base_structs = scrape_structure_section(content, base_start, managed_h_start_pos, 'Base Objects')
    print(f'  Found {len(base_structs)} base object structures')

    # Section 2.2: Managed Objects
    print('Scraping managed objects (section 2.2)...')
    attrs_h_start, _ = find_section(r'^3\s.*Attributes', level='h1')
    attrs_h_pos = sections[attrs_h_start][2] if attrs_h_start >= 0 else base_end
    managed_structs = scrape_structure_section(content, managed_h_start_pos, attrs_h_pos, 'Managed Objects')
    print(f'  Found {len(managed_structs)} managed object types')

    # Section 6: Message Contents
    print('Scraping message contents (section 6)...')
    msg_contents_start, msg_contents_end = section_range(r'^6\s.*Message Contents', level='h1')
    msg_format_start, _ = find_section(r'^7\s.*Message Format', level='h1')
    msg_format_pos = sections[msg_format_start][2] if msg_format_start >= 0 else msg_contents_end

    msg_contents_structs = scrape_structure_section(content, msg_contents_start, msg_format_pos, 'Message Contents')
    msg_format_structs_end_h, _ = find_section(r'^8\s.*Authentication', level='h1')
    msg_format_end_pos = sections[msg_format_structs_end_h][2] if msg_format_structs_end_h >= 0 else len(content)
    msg_format_structs = scrape_structure_section(content, msg_format_pos, msg_format_end_pos, 'Message Format')

    all_structs = base_structs + msg_contents_structs + msg_format_structs
    print(f'  Found {len(all_structs)} total structures')
    write_structures_md(all_structs, 'Structures (Base Objects & Message Contents)', os.path.join(out_dir, 'structures.md'))
    write_structures_md(managed_structs, 'Managed Objects', os.path.join(out_dir, 'managed-objects.md'))

    # --- Section 3: Attributes ---
    print('Scraping attributes (section 3)...')
    ops_h_start, _ = find_section(r'^4\s.*Client', level='h1')
    ops_start_pos = sections[ops_h_start][2] if ops_h_start >= 0 else 0
    attributes = scrape_attributes_section(content, attrs_h_pos, ops_start_pos)
    print(f'  Found {len(attributes)} attributes')
    write_attributes_md(attributes, os.path.join(out_dir, 'attributes.md'))

    # --- Sections 4 + 5: Operations ---
    print('Scraping operations (sections 4 + 5)...')
    s2s_h_start, _ = find_section(r'^5\s.*Server-to-Client', level='h1')
    s2s_start_pos = sections[s2s_h_start][2] if s2s_h_start >= 0 else 0
    msg_h_start, _ = find_section(r'^6\s.*Message Contents', level='h1')
    msg_start_pos = sections[msg_h_start][2] if msg_h_start >= 0 else 0

    client_ops = scrape_operations(content, sections, (ops_start_pos, s2s_start_pos))
    server_ops = scrape_operations(content, sections, (s2s_start_pos, msg_start_pos))
    all_ops = client_ops + server_ops
    print(f'  Found {len(client_ops)} client-to-server ops, {len(server_ops)} server-to-client ops')
    write_operations_md(all_ops, 'Operations (Sections 4 & 5)', os.path.join(out_dir, 'operations.md'))

    # --- README ---
    counts = {
        'tags': len(tags),
        'enums': len(enumerations),
        'masks': len(masks),
        'attrs': len(attributes),
        'structs': len(all_structs),
        'managed': len(managed_structs),
        'ops': len(all_ops),
    }
    write_readme(out_dir, counts)

    print('\nDone! Output:')
    for fname in sorted(os.listdir(out_dir)):
        fpath = os.path.join(out_dir, fname)
        size = os.path.getsize(fpath)
        print(f'  {fname:40s} {size:>8,} bytes')

    print(f'\nSummary:')
    for k, v in counts.items():
        print(f'  {k:20s}: {v}')


if __name__ == '__main__':
    main()
