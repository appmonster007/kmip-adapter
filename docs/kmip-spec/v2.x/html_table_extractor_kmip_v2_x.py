#!/usr/bin/env python3
"""
KMIP v2.x HTML Table Extractor

Extracts enumeration tables from KMIP v2.x HTML files and converts them to
Markdown (Name | Hex | Description) and CSV formats.

Each enumeration section in v2.x has two tables:
  Table A: Value | Description   (prose description per value)
  Table B: Name  | Value (hex)   (numeric codes)

This script merges both tables into the unified output format.
"""

import os
import re
import csv
import argparse
from typing import List, Dict, Tuple, Optional
from bs4 import BeautifulSoup


def extract_text_content(node) -> str:
    if node is None:
        return ""
    text = node.get_text(strip=True)
    return re.sub(r"\s+", " ", text)


def table_to_rows(table_element) -> List[List[str]]:
    rows = []
    for tr in table_element.find_all("tr"):
        cells = [extract_text_content(td).replace("|", "\\|")
                 for td in tr.find_all(["td", "th"])]
        if any(c for c in cells):
            rows.append(cells)
    return rows


def parse_hex_table(rows: List[List[str]]) -> List[Dict]:
    """Extract [{name, hex}] from a 'Name | Value' table."""
    if not rows:
        return []
    header = [c.lower() for c in rows[0]]
    ni = next((i for i, h in enumerate(header) if h == "name"), None)
    vi = next((i for i, h in enumerate(header) if h == "value"), None)
    if ni is None or vi is None:
        return []
    result = []
    for row in rows[1:]:
        if len(row) <= max(ni, vi):
            continue
        name = row[ni].strip()
        val  = row[vi].strip()
        if not name or name.lower() in ("name", "value", "extensions"):
            continue
        if name.lower().startswith("(reserved") or name.lower().startswith("reserved"):
            continue
        # Normalise hex: pad to 8 digits, add 0x prefix
        clean = re.sub(r"[^0-9A-Fa-f]", "", val.upper().replace("0X", ""))
        hex_val = ("0x" + clean.upper().zfill(8)) if (4 <= len(clean) <= 8) else val
        result.append({"name": name, "hex": hex_val})
    return result


def parse_desc_table(rows: List[List[str]]) -> Dict[str, str]:
    """Extract {value_name → description} from a 'Value | Description' table."""
    if not rows:
        return {}
    header = [c.lower() for c in rows[0]]
    vi = next((i for i, h in enumerate(header) if h in ("value", "name")), None)
    di = next((i for i, h in enumerate(header) if "desc" in h), None)
    if vi is None or di is None:
        return {}
    result: Dict[str, str] = {}
    for row in rows[1:]:
        if len(row) <= max(vi, di):
            continue
        name = row[vi].strip()
        desc = row[di].strip()
        if name and name.lower() not in ("value", "name", "extensions"):
            result[name] = desc
    return result


def clean_category_name(category: str) -> str:
    for suffix in ("Enumeration", "Value", "Values"):
        if category.endswith(suffix):
            category = category[: -len(suffix)]
    return re.sub(r"[\s-]+$", "", category).strip()


def extract_section_title(header_el) -> str:
    title = extract_text_content(header_el)
    title = re.sub(r"^[\d.]+\s*", "", title)
    return title.strip()


def find_enumeration_sections_v2(soup: BeautifulSoup) -> List[Dict]:
    """Find all enumeration sections via H2 headers."""
    HEADING_TAGS = ["h1", "h2", "h3", "h4", "h5", "h6"]
    sections: List[Dict] = []
    h2_elements = soup.find_all("h2")

    for idx, h2 in enumerate(h2_elements):
        title = extract_section_title(h2)
        if not title or "enumeration" not in title.lower():
            continue

        end = h2_elements[idx + 1] if idx + 1 < len(h2_elements) else None
        tables: List = []
        description = ""

        current = h2.next_sibling
        steps = 0
        while current and current is not end and steps < 2000:
            steps += 1
            if hasattr(current, "name") and current.name:
                if current.name in HEADING_TAGS:
                    break
                if current.name == "p":
                    text = extract_text_content(current)
                    if not description and text and len(text) < 500:
                        description = text
                if current.name == "table":
                    tables.append(current)
                elif current.name in ("div", "section", "center"):
                    tables.extend(current.find_all("table"))
            current = current.next_sibling

        sections.append({
            "title": title,
            "description": description,
            "tables": tables,
        })

    return sections


def merge_enum_section(tables) -> Tuple[List[Dict], Dict[str, str]]:
    """
    Given the tables in one enum section, return (hex_rows, desc_map).
    hex_rows: [{name, hex}]
    desc_map: {name → description}
    """
    hex_rows: List[Dict] = []
    desc_map: Dict[str, str] = {}
    for tbl in tables:
        rows = table_to_rows(tbl)
        if not hex_rows:
            hex_rows = parse_hex_table(rows)
        if not desc_map:
            desc_map = parse_desc_table(rows)
        if hex_rows and desc_map:
            break
    return hex_rows, desc_map


def convert_sections_to_markdown(sections: List[Dict]) -> Tuple[str, List[Dict]]:
    markdown_blocks: List[str] = []
    all_csv: List[Dict] = []

    for section in sections:
        title = section["title"]
        markdown_blocks.append(f"## {title}")
        markdown_blocks.append("")
        if section.get("description"):
            markdown_blocks.append(section["description"])
            markdown_blocks.append("")

        hex_rows, desc_map = merge_enum_section(section.get("tables", []))

        if hex_rows:
            markdown_blocks.append("| Name | Hex | Description |")
            markdown_blocks.append("| --- | --- | --- |")
            clean_cat = clean_category_name(title)
            for row in hex_rows:
                desc = desc_map.get(row["name"], "")
                markdown_blocks.append(f"| {row['name']} | {row['hex']} | {desc} |")
                all_csv.append({
                    "category": clean_cat,
                    "name": row["name"],
                    "value": row["hex"],
                    "description": desc,
                })

        markdown_blocks.append("")
        markdown_blocks.append("---")
        markdown_blocks.append("")

    return "\n".join(markdown_blocks), all_csv


def process_html_file(input_file: str, output_file: Optional[str] = None) -> None:
    if not os.path.exists(input_file):
        raise FileNotFoundError(f"Input file not found: {input_file}")

    with open(input_file, "rb") as f:
        raw = f.read()

    html = None
    for enc in ("utf-8", "windows-1252", "latin-1"):
        try:
            html = raw.decode(enc)
            break
        except UnicodeDecodeError:
            continue
    if html is None:
        html = raw.decode("utf-8", errors="replace")

    soup = BeautifulSoup(html, "html.parser")
    enum_sections = find_enumeration_sections_v2(soup)
    if not enum_sections:
        print("No enumeration sections found.")
        return

    print(f"Found {len(enum_sections)} enumeration sections:")
    for s in enum_sections:
        print(f"  - {s['title']}")

    base_name = os.path.splitext(input_file)[0]
    if output_file is None:
        output_file = f"{base_name}_extracted.md"

    md_content, csv_data = convert_sections_to_markdown(enum_sections)

    with open(output_file, "w", encoding="utf-8") as f:
        f.write("# KMIP v2.x Specification Enumerations\n\n")
        f.write("Extracted from HTML specification document.\n\n")
        f.write(md_content)

    csv_file = f"{base_name}_enumerations.csv"
    if csv_data:
        with open(csv_file, "w", newline="", encoding="utf-8") as f:
            writer = csv.DictWriter(
                f, fieldnames=["enumeration_category", "enumeration_name", "value", "description"]
            )
            writer.writeheader()
            for row in csv_data:
                writer.writerow({
                    "enumeration_category": row.get("category", ""),
                    "enumeration_name": row.get("name", ""),
                    "value": row.get("value", ""),
                    "description": row.get("description", ""),
                })
        print(f"CSV data written to: {csv_file}")

    print(f"Markdown output written to: {output_file}")


def main():
    parser = argparse.ArgumentParser(
        description="Extract KMIP v2.x enumeration tables (Name | Hex | Description) from HTML"
    )
    parser.add_argument("input_file", help="Path to the KMIP v2.x HTML file")
    parser.add_argument("-o", "--output", help="Output Markdown file path")
    args = parser.parse_args()

    try:
        process_html_file(args.input_file, args.output)
    except Exception as e:
        print(f"Error: {e}")
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
