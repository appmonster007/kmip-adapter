#!/usr/bin/env python3
"""
KMIP Objects scraper — collects managed objects, data structures, attributes,
and operation payloads from KMIP spec HTML files (currently targets v3.0).

Outputs:
  docs/kmip-spec/kmip-objects-v3.0.json   — structured JSON of all objects

Run:
  python3 docs/kmip-spec/scrape_kmip_objects.py
"""

import re
import sys
import json
from pathlib import Path
from typing import Optional, Dict, List, Tuple, Any
from bs4 import BeautifulSoup, Tag, NavigableString

# ---------------------------------------------------------------------------
# Paths
# ---------------------------------------------------------------------------

SPEC_DIR = Path(__file__).parent
REPO_ROOT = SPEC_DIR.parent.parent
SPEC_V3 = SPEC_DIR / "v3.x" / "kmip-spec-v3.0-os.html"
OUT_JSON = SPEC_DIR / "kmip-objects-v3.0.json"

HEADING_TAGS = ["h1", "h2", "h3", "h4", "h5", "h6"]

# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def clean(text: str) -> str:
    return re.sub(r"\s+", " ", (text or "")).strip()


def strip_section(text: str) -> str:
    """Remove leading section numbers like '4.1', '6.1.3' etc."""
    return re.sub(r"^[\d.]+\s*", "", text).strip()


def section_number(text: str) -> str:
    """Extract the leading section number from a heading text."""
    m = re.match(r"^([\d.]+)", text.strip())
    return m.group(1).rstrip(".") if m else ""


def table_rows(table: Tag) -> List[List[str]]:
    """Return table rows as lists of cleaned cell strings."""
    rows = []
    for tr in table.find_all("tr"):
        cells = [clean(td.get_text()) for td in tr.find_all(["td", "th"])]
        if any(c for c in cells):
            rows.append(cells)
    return rows


def header_key(rows: List[List[str]]) -> Tuple[str, ...]:
    """Return the first row as a normalised tuple for pattern matching."""
    if not rows:
        return ()
    return tuple(re.sub(r"\s+", " ", c).strip() for c in rows[0])


def siblings_until_heading(start: Tag, stop_level: int) -> List[Tag]:
    """Collect element siblings after *start* until a heading of level <= stop_level."""
    elems = []
    cur = start.next_sibling
    while cur:
        if isinstance(cur, Tag):
            if cur.name and re.match(r"h[1-6]", cur.name):
                lvl = int(cur.name[1])
                if lvl <= stop_level:
                    break
            elems.append(cur)
        cur = cur.next_sibling
    return elems


def first_para(elems: List[Tag]) -> str:
    """Return text of the first <p> element, if any."""
    for e in elems:
        if isinstance(e, Tag) and e.name == "p":
            txt = clean(e.get_text())
            if txt and len(txt) > 10:
                return txt
    return ""


def find_tables(elems: List[Tag]) -> List[Tag]:
    """Collect all <table> elements from a list of siblings."""
    tables = []
    for e in elems:
        if isinstance(e, Tag):
            if e.name == "table":
                tables.append(e)
            else:
                tables.extend(e.find_all("table", recursive=True))
    return tables


# ---------------------------------------------------------------------------
# Table parsers
# ---------------------------------------------------------------------------

def parse_structure_table(rows: List[List[str]]) -> List[Dict[str, str]]:
    """
    Parse an Object|Item/Encoding/REQUIRED table into a list of field dicts.
    Header row is consumed; row 1 may be the structure-name root row (skip it
    when the Encoding cell is 'Structure' and REQUIRED is empty).
    """
    if len(rows) < 2:
        return []
    fields = []
    # Determine column indices from header
    hdr = [c.lower() for c in rows[0]]
    ni = next((i for i, h in enumerate(hdr) if h in ("object", "item")), 0)
    ei = next((i for i, h in enumerate(hdr) if h == "encoding"), 1)
    ri = next((i for i, h in enumerate(hdr) if h == "required"), None)

    for row in rows[1:]:
        if len(row) <= max(ni, ei):
            continue
        name = row[ni]
        enc = row[ei]
        req = row[ri] if ri is not None and ri < len(row) else ""
        # Skip the root structure row (Encoding = "Structure", name = structure title)
        if enc.lower() == "structure" and (not req or req.strip() == ""):
            continue
        if not name or name.lower() in ("object", "item", "encoding", "required"):
            continue
        entry: Dict[str, str] = {"field": name, "encoding": enc}
        if req:
            entry["required"] = req
        fields.append(entry)
    return fields


def parse_payload_table(rows: List[List[str]]) -> List[Dict[str, str]]:
    """
    Parse a Request Payload / Response Payload table.
    Row 0: ['Request Payload'] or ['Response Payload'] (spanning)
    Row 1: ['Item', 'REQUIRED', 'Description']
    Row 2+: data rows
    """
    if len(rows) < 3:
        return []
    # Row 1 should be the header
    hdr = [c.lower() for c in rows[1]]
    ii = next((i for i, h in enumerate(hdr) if h == "item"), 0)
    ri = next((i for i, h in enumerate(hdr) if h == "required"), 1)
    di = next((i for i, h in enumerate(hdr) if h == "description"), None)

    fields = []
    for row in rows[2:]:
        if not row or not row[ii]:
            continue
        name = row[ii]
        if name.lower() in ("item", "required", "description"):
            continue
        req = row[ri] if ri < len(row) else ""
        desc = row[di] if di is not None and di < len(row) else ""
        entry: Dict[str, str] = {"item": name, "required": req}
        if desc:
            entry["description"] = desc
        fields.append(entry)
    return fields


def parse_required_attr_table(rows: List[List[str]]) -> List[Dict[str, str]]:
    """
    Parse an Attribute|REQUIRED table (required attrs for an object type).
    """
    if len(rows) < 2:
        return []
    hdr = [c.lower() for c in rows[0]]
    ai = next((i for i, h in enumerate(hdr) if "attribute" in h), 0)
    ri = next((i for i, h in enumerate(hdr) if "required" in h), 1)
    attrs = []
    for row in rows[1:]:
        if not row or len(row) <= max(ai, ri):
            continue
        name = row[ai]
        req = row[ri]
        if not name or name.lower() in ("attribute", "required"):
            continue
        attrs.append({"name": name, "required": req})
    return attrs


def parse_attribute_rules(rows: List[List[str]]) -> Dict[str, str]:
    """
    Parse an attribute-rules two-column table:
      SHALL always have a value | No
      Initially set by          | Server or Client
      ...
    """
    rule_keys = {
        "shall always have a value": "shall_always_have_value",
        "initially set by": "initially_set_by",
        "modifiable by server": "modifiable_by_server",
        "modifiable by client": "modifiable_by_client",
        "deletable by client": "deletable_by_client",
        "multiple instances permitted": "multiple_instances_permitted",
        "when implicitly set": "when_implicitly_set",
        "applies to object types": "applies_to_object_types",
    }
    rules: Dict[str, str] = {}
    for row in rows:
        if len(row) < 2:
            continue
        key_raw = row[0].lower().strip()
        val = row[1]
        for k, mapped in rule_keys.items():
            if key_raw.startswith(k):
                rules[mapped] = val
                break
    return rules


def parse_item_encoding_table(rows: List[List[str]]) -> List[Dict[str, str]]:
    """
    Parse an Item|Encoding table (attribute type hint / simple structure).
    """
    if len(rows) < 2:
        return []
    fields = []
    for row in rows[1:]:
        if len(row) < 2:
            continue
        name, enc = row[0], row[1]
        if not name or name.lower() in ("item", "encoding", "object"):
            continue
        fields.append({"item": name, "encoding": enc})
    return fields


def parse_message_structure_table(rows: List[List[str]]) -> List[Dict[str, str]]:
    """
    Parse spanning-header message tables used in Section 8:
      Row 0: ['Request Header'] or similar (spanning title)
      Row 1: ['Object', 'REQUIRED in Message', 'Comment']
      Row 2+: data rows (skip root-structure row where Comment='Structure')
    """
    if len(rows) < 3:
        return []
    hdr = [c.lower() for c in rows[1]]
    ni = next((i for i, h in enumerate(hdr) if h in ("object", "item")), 0)
    ri = next((i for i, h in enumerate(hdr) if "required" in h), 1)
    ci = next((i for i, h in enumerate(hdr) if h == "comment"), None)
    fields = []
    for row in rows[2:]:
        if len(row) <= max(ni, ri):
            continue
        name = row[ni]
        req = row[ri]
        comment = row[ci] if ci is not None and ci < len(row) else ""
        if not name or name.lower() in ("object", "item"):
            continue
        if comment.strip().lower() == "structure":
            continue
        entry: Dict[str, str] = {"field": name, "required": req}
        if comment:
            entry["comment"] = comment
        fields.append(entry)
    return fields


def parse_error_table(rows: List[List[str]]) -> List[Dict[str, str]]:
    """Parse a Result Status | Result Reason error table."""
    if len(rows) < 2:
        return []
    errors = []
    for row in rows[1:]:
        if len(row) < 2:
            continue
        status, reasons = row[0], row[1]
        if not status or not reasons:
            continue
        if status.lower() in ("result status", "result_status"):
            continue
        errors.append({"result_status": status, "result_reasons": reasons})
    return errors


# ---------------------------------------------------------------------------
# Section walkers
# ---------------------------------------------------------------------------

def walk_section(soup: BeautifulSoup,
                 h1_pattern: str,
                 subsection_tag: str,
                 stop_on_h1: bool = True) -> List[Dict[str, Any]]:
    """
    Find the h1 matching *h1_pattern*, then walk its h2 (or h3) children.
    Returns a list of dicts with: name, section, description, tables_raw.
    """
    h1 = None
    for h in soup.find_all("h1"):
        if re.search(h1_pattern, h.get_text(), re.IGNORECASE):
            h1 = h
            break
    if not h1:
        return []

    results = []
    for sub in h1.find_all_next(subsection_tag):
        # Stop when we exit the parent h1's scope
        if stop_on_h1:
            parent_h1 = sub.find_previous("h1")
            if parent_h1 and parent_h1 is not h1:
                break
        sec_text = clean(sub.get_text())
        sec_num = section_number(sec_text)
        sec_name = strip_section(sec_text)
        if not sec_name:
            continue

        # Collect body until the next sibling of equal or higher level
        sub_level = int(sub.name[1])
        body_elems = siblings_until_heading(sub, sub_level)
        desc = first_para(body_elems)
        tables = find_tables(body_elems)

        results.append({
            "name": sec_name,
            "section": sec_num,
            "description": desc,
            "_tables": tables,
        })
    return results


# ---------------------------------------------------------------------------
# Main section extractors
# ---------------------------------------------------------------------------

def extract_objects(soup: BeautifulSoup) -> Dict[str, List[Dict]]:
    """Section 2: Objects (System + User)."""
    system_objects: List[Dict] = []
    user_objects: List[Dict] = []

    h1 = None
    for h in soup.find_all("h1"):
        if re.search(r"^2\s*Objects", h.get_text(strip=True)):
            h1 = h
            break
    if not h1:
        return {"system": [], "user": []}

    current_category = None
    for h2 in h1.find_all_next("h2"):
        if h2.find_previous("h1") is not h1:
            break
        txt = clean(h2.get_text())
        if re.search(r"system\s+objects?", txt, re.IGNORECASE):
            current_category = "system"
        elif re.search(r"user\s+objects?", txt, re.IGNORECASE):
            current_category = "user"

        for h3 in h2.find_all_next("h3"):
            if h3.find_previous("h2") is not h2:
                break
            sec_text = clean(h3.get_text())
            sec_num = section_number(sec_text)
            sec_name = strip_section(sec_text)
            if not sec_name:
                continue

            body = siblings_until_heading(h3, 3)
            desc = first_para(body)
            tables = find_tables(body)

            obj: Dict[str, Any] = {
                "name": sec_name,
                "section": sec_num,
                "description": desc,
                "required_attributes": [],
                "structure": [],
            }

            for t in tables:
                rows = table_rows(t)
                hk = header_key(rows)
                if hk and hk[0].lower() == "attribute":
                    obj["required_attributes"] = parse_required_attr_table(rows)
                elif hk and hk[0].lower() in ("object", "item") and len(hk) >= 2 and "encoding" in hk[1].lower():
                    obj["structure"] = parse_structure_table(rows)

            target = system_objects if current_category == "system" else user_objects
            target.append(obj)

    return {"system": system_objects, "user": user_objects}


def extract_data_structures(soup: BeautifulSoup, section_pattern: str, section_h1_num: str) -> List[Dict]:
    """Generic extractor for sections 3, 5, 7, 9 (data structure sections)."""
    h1 = None
    for h in soup.find_all("h1"):
        txt = h.get_text(strip=True)
        num = section_number(txt)
        if num == section_h1_num:
            h1 = h
            break
    if not h1:
        return []

    results = []
    for h2 in h1.find_all_next("h2"):
        if h2.find_previous("h1") is not h1:
            break
        sec_text = clean(h2.get_text())
        sec_num = section_number(sec_text)
        sec_name = strip_section(sec_text)
        if not sec_name:
            continue

        body = siblings_until_heading(h2, 2)
        desc = first_para(body)
        tables = find_tables(body)

        entry: Dict[str, Any] = {
            "name": sec_name,
            "section": sec_num,
            "description": desc,
            "structure": [],
            "encoding": None,
        }

        for t in tables:
            rows = table_rows(t)
            hk = header_key(rows)
            if not hk:
                continue
            h0 = hk[0].lower()
            if h0 in ("object", "item") and len(hk) >= 2 and "encoding" in hk[1].lower():
                parsed = parse_structure_table(rows)
                if parsed:
                    entry["structure"] = parsed
                    break
            elif h0 == "item" and len(hk) == 2 and hk[1].lower() == "encoding":
                # Simple Item|Encoding table (single-field attribute)
                parsed2 = parse_item_encoding_table(rows)
                if parsed2 and not entry["structure"]:
                    if len(parsed2) == 1:
                        entry["encoding"] = parsed2[0]["encoding"]
                    else:
                        entry["structure"] = parsed2

        results.append(entry)
    return results


def extract_attributes(soup: BeautifulSoup) -> List[Dict]:
    """Section 4: Object Attributes."""
    h1 = None
    for h in soup.find_all("h1"):
        txt = h.get_text(strip=True)
        if section_number(txt) == "4":
            h1 = h
            break
    if not h1:
        return []

    results = []
    for h2 in h1.find_all_next("h2"):
        if h2.find_previous("h1") is not h1:
            break
        sec_text = clean(h2.get_text())
        sec_num = section_number(sec_text)
        sec_name = strip_section(sec_text)
        if not sec_name:
            continue

        body = siblings_until_heading(h2, 2)
        desc = first_para(body)
        tables = find_tables(body)

        entry: Dict[str, Any] = {
            "name": sec_name,
            "section": sec_num,
            "description": desc,
            "encoding": None,
            "structure": [],
            "rules": {},
        }

        for t in tables:
            rows = table_rows(t)
            hk = header_key(rows)
            if not hk:
                continue
            h0 = hk[0].lower()

            if h0 == "item" and len(hk) == 2 and hk[1].lower() == "encoding":
                parsed = parse_item_encoding_table(rows)
                if parsed:
                    if len(parsed) == 1:
                        entry["encoding"] = parsed[0]["encoding"]
                    else:
                        entry["structure"] = parsed

            elif h0 in ("object", "item") and len(hk) >= 2 and "encoding" in hk[1].lower():
                parsed2 = parse_structure_table(rows)
                if parsed2:
                    entry["structure"] = parsed2

            elif h0.startswith("shall always have"):
                rules = parse_attribute_rules(rows)
                # The first row IS a rule row itself
                rules_from_first = parse_attribute_rules([list(hk)])
                entry["rules"].update(rules_from_first)
                entry["rules"].update(rules)

        results.append(entry)
    return results


def extract_operations(soup: BeautifulSoup) -> Dict[str, List[Dict]]:
    """Section 6: Operations."""
    h1 = None
    for h in soup.find_all("h1"):
        txt = h.get_text(strip=True)
        if section_number(txt) == "6":
            h1 = h
            break
    if not h1:
        return {"client_to_server": [], "server_to_client": []}

    c2s: List[Dict] = []
    s2c: List[Dict] = []
    current_list = c2s

    for h2 in h1.find_all_next("h2"):
        if h2.find_previous("h1") is not h1:
            break
        h2_txt = clean(h2.get_text())
        if re.search(r"server.to.client", h2_txt, re.IGNORECASE):
            current_list = s2c

        for h3 in h2.find_all_next("h3"):
            if h3.find_previous("h2") is not h2:
                break
            sec_text = clean(h3.get_text())
            sec_num = section_number(sec_text)
            sec_name = strip_section(sec_text)
            if not sec_name:
                continue
            # Skip error-handling sub-sections
            if re.search(r"error\s+handling", sec_name, re.IGNORECASE):
                continue

            body = siblings_until_heading(h3, 3)
            desc = first_para(body)
            tables = find_tables(body)

            op: Dict[str, Any] = {
                "name": sec_name,
                "section": sec_num,
                "description": desc,
                "request_payload": [],
                "response_payload": [],
                "errors": [],
            }

            for t in tables:
                rows = table_rows(t)
                hk = header_key(rows)
                if not hk:
                    continue
                h0 = hk[0].lower()
                if h0 in ("request payload", "reponse payload", "message payload"):
                    op["request_payload"] = parse_payload_table(rows)
                elif h0 in ("response payload",):
                    op["response_payload"] = parse_payload_table(rows)
                elif h0 in ("result status", "result_status"):
                    op["errors"] = parse_error_table(rows)

            current_list.append(op)

    return {"client_to_server": c2s, "server_to_client": s2c}


def extract_messages(soup: BeautifulSoup) -> List[Dict]:
    """Sections 8 + 9: Messages and Message Data Structures."""
    results = []
    for sec_num_str in ("8", "9"):
        h1 = None
        for h in soup.find_all("h1"):
            txt = h.get_text(strip=True)
            if section_number(txt) == sec_num_str:
                h1 = h
                break
        if not h1:
            continue

        # h2 and h3 sub-sections
        for sub in h1.find_all_next(["h2", "h3"]):
            if sub.find_previous("h1") is not h1:
                break
            sec_text = clean(sub.get_text())
            sec_num = section_number(sec_text)
            sec_name = strip_section(sec_text)
            if not sec_name:
                continue

            sub_level = int(sub.name[1])
            body = siblings_until_heading(sub, sub_level)
            desc = first_para(body)
            tables = find_tables(body)

            entry: Dict[str, Any] = {
                "name": sec_name,
                "section": sec_num,
                "description": desc,
                "structure": [],
            }

            for t in tables:
                rows = table_rows(t)
                hk = header_key(rows)
                if not hk:
                    continue
                h0 = hk[0].lower()

                # Standard Object|Encoding|REQUIRED table
                if h0 in ("object", "item") and len(hk) >= 2 and "encoding" in hk[1].lower():
                    parsed = parse_structure_table(rows)
                    if parsed:
                        entry["structure"] = parsed
                        break

                # Section 8 spanning-header tables: ['Request Header'], ['Response Header'], etc.
                # Row 1 will be ['Object', 'REQUIRED in Message', 'Comment']
                if len(rows) >= 2:
                    hdr2 = [c.lower() for c in rows[1]] if len(rows) > 1 else []
                    if (hdr2 and hdr2[0] in ("object", "item")
                            and any("required" in h for h in hdr2)):
                        parsed2 = parse_message_structure_table(rows)
                        if parsed2:
                            entry["structure"] = parsed2
                            break

            results.append(entry)
    return results


# ---------------------------------------------------------------------------
# Summary / stats
# ---------------------------------------------------------------------------

def summarise(data: Dict) -> None:
    objs = data.get("objects", {})
    print(f"  Objects           : {len(objs.get('system', []))} system, {len(objs.get('user', []))} user")
    print(f"  Object structures : {len(data.get('object_data_structures', []))}")
    print(f"  Attributes        : {len(data.get('attributes', []))}")
    print(f"  Attr structures   : {len(data.get('attribute_data_structures', []))}")
    ops = data.get("operations", {})
    print(f"  Operations        : {len(ops.get('client_to_server', []))} c2s, {len(ops.get('server_to_client', []))} s2c")
    print(f"  Op structures     : {len(data.get('operations_data_structures', []))}")
    print(f"  Messages          : {len(data.get('messages', []))}")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    print("KMIP objects scraper starting…\n")

    if not SPEC_V3.exists():
        print(f"ERROR: spec file not found: {SPEC_V3}", file=sys.stderr)
        sys.exit(1)

    print(f"  Parsing {SPEC_V3.name} …")
    raw = SPEC_V3.read_bytes().decode("utf-8", errors="replace")
    soup = BeautifulSoup(raw, "html.parser")

    print("  Extracting objects (§2)…")
    objects = extract_objects(soup)

    print("  Extracting object data structures (§3)…")
    obj_structures = extract_data_structures(soup, r"Object Data Structures", "3")

    print("  Extracting attributes (§4)…")
    attributes = extract_attributes(soup)

    print("  Extracting attribute data structures (§5)…")
    attr_structures = extract_data_structures(soup, r"Attribute Data Structures", "5")

    print("  Extracting operations (§6)…")
    operations = extract_operations(soup)

    print("  Extracting operations data structures (§7)…")
    op_structures = extract_data_structures(soup, r"Operations Data Structures", "7")

    print("  Extracting messages (§8–9)…")
    messages = extract_messages(soup)

    data = {
        "version": "3.0",
        "objects": objects,
        "object_data_structures": obj_structures,
        "attributes": attributes,
        "attribute_data_structures": attr_structures,
        "operations": operations,
        "operations_data_structures": op_structures,
        "messages": messages,
    }

    print(f"\nSaving JSON to {OUT_JSON} …")
    with open(OUT_JSON, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=2, ensure_ascii=False, default=str)

    print("\nDone.\n")
    summarise(data)
    print(f"\n  Output: {OUT_JSON}")


if __name__ == "__main__":
    main()
