#!/usr/bin/env python3
"""
KMIP v2.x HTML Table Extractor

This script extracts enumeration tables from KMIP v2.x HTML files and converts
them to Markdown and CSV formats, modeled after the v1.x extractor.

It looks for sections with h2 headers whose titles include the word
"Enumeration" and then finds the next table (possibly inside a wrapping div)
with Name/Value columns. It outputs a Markdown file with each section and its
parsed table, and a CSV file with rows of:
- enumeration_category
- enumeration_name
- value
"""

import os
import re
import csv
import argparse
from typing import List, Dict, Tuple
from bs4 import BeautifulSoup


def extract_text_content(node) -> str:
    """Extract clean text content from an HTML element, removing extra whitespace."""
    if node is None:
        return ""
    text = node.get_text(strip=True)
    return re.sub(r"\s+", " ", text)


def parse_table_to_markdown(table_element, table_type: str = 'enumeration') -> Tuple[str, List[Dict]]:
    """Convert an HTML table to Markdown and collect CSV rows.

    Returns (markdown, csv_rows) where csv_rows has dicts with 'name' and 'value'.
    """
    if not table_element:
        return "", []

    rows = table_element.find_all('tr')
    if not rows:
        return "", []

    markdown_lines: List[str] = []
    headers: List[str] = []
    header_row_idx = -1

    # Attempt to locate a header row (cells with silver background, typical in KMIP docs)
    for i, row in enumerate(rows):
        cells = row.find_all(['th', 'td'])
        if len(cells) >= 2:
            first_cell_style = cells[0].get('style', '')
            second_cell_style = cells[1].get('style', '')
            if 'background:silver' in first_cell_style or 'background:silver' in second_cell_style:
                header_row_idx = i
                for cell in cells:
                    headers.append(extract_text_content(cell) or '')
                break

    # Fallback default headers for typical 2-col enumeration tables
    if header_row_idx == -1:
        for i, row in enumerate(rows):
            cells = row.find_all(['td', 'th'])
            if len(cells) == 2:
                header_row_idx = i - 1
                headers = ['Name', 'Value']
                break
    if not headers:
        headers = ['Name', 'Value']

    # Enforce only Name/Value tables; skip Name/Description and others
    norm_headers = [re.sub(r'\s+', ' ', h).strip().lower() for h in headers]
    # If there are more than 2 header cells, or they are not exactly ['name','value'], skip
    if not (len(norm_headers) == 2 and norm_headers[0] == 'name' and norm_headers[1] == 'value'):
        return "", []

    markdown_lines.append('| ' + ' | '.join(headers) + ' |')
    markdown_lines.append('| ' + ' | '.join(['---'] * len(headers)) + ' |')

    csv_rows: List[Dict] = []
    for i, row in enumerate(rows):
        if i <= header_row_idx:
            continue
        cells = row.find_all(['td', 'th'])
        if not cells:
            continue
        if len(cells) == 1 and cells[0].get('colspan'):
            continue

        cell_texts: List[str] = []
        for cell in cells:
            cell_text = extract_text_content(cell).replace('|', '\\|')
            cell_texts.append(cell_text)

        if not any(cell_texts):
            continue

        # Only accept rows that have exactly two cells for Name/Value
        if len(cell_texts) != 2:
            continue

        markdown_lines.append('| ' + ' | '.join(cell_texts) + ' |')
        csv_rows.append({
            'name': cell_texts[0],
            'value': cell_texts[1]
        })

    return '\n'.join(markdown_lines), csv_rows


def clean_category_name(category: str) -> str:
    """Clean up category name by removing common suffixes like 'Enumeration', 'Values'."""
    if not category:
        return category
    suffixes = ['Enumeration', 'Value', 'Values']
    for suffix in suffixes:
        if category.endswith(suffix):
            category = category[:-len(suffix)]
    category = re.sub(r'[\s-]+$', '', category)
    return category.strip()


def extract_section_title(header_el) -> str:
    """Extract a clean title from an h2 (or general header) element."""
    title = extract_text_content(header_el)
    # Remove leading numbering like '11.64'
    title = re.sub(r'^[\d.]+\s*', '', title)
    return title.strip()


def find_enumeration_sections_v2(soup: BeautifulSoup) -> List[Dict]:
    """Find all enumeration sections in v2.x docs using H2 headers.

    For each enumeration H2, collect all tables within the section (until the next H2),
    allowing later logic to choose the correct Name/Value table.
    """
    sections: List[Dict] = []
    h2_elements = soup.find_all('h2')

    for idx, h2 in enumerate(h2_elements):
        title = extract_section_title(h2)
        if not title or 'enumeration' not in title.lower():
            continue

        # Identify the boundary: until the next H2
        end = None
        if idx + 1 < len(h2_elements):
            end = h2_elements[idx + 1]

        description = ''
        tables: List = []

        # Traverse siblings from this h2 up to (but not including) the next h2
        current = h2.next_sibling
        steps = 0
        while current and current is not end and steps < 2000:
            steps += 1
            # If we hit the next h2 due to tree organization, break
            if hasattr(current, 'name') and current.name == 'h2':
                break

            # Collect short paragraph as description if present
            if hasattr(current, 'name') and current.name == 'p':
                text = extract_text_content(current)
                if not description and text and len(text) < 500:
                    description = text

            # Collect tables directly or within wrappers
            if hasattr(current, 'name') and current.name == 'table':
                tables.append(current)
            elif hasattr(current, 'name') and current.name in ('div', 'section', 'center'):
                tables.extend(current.find_all('table'))

            current = current.next_sibling

        sections.append({
            'title': title,
            'description': description,
            'tables': tables,
            'type': 'enumeration'
        })

    return sections


def convert_sections_to_markdown(sections: List[Dict]) -> Tuple[str, List[Dict]]:
    markdown_blocks: List[str] = []
    all_csv: List[Dict] = []

    for section in sections:
        title = section['title']
        markdown_blocks.append(f"## {title}")
        markdown_blocks.append("")
        if section.get('description'):
            markdown_blocks.append(section['description'])
            markdown_blocks.append("")

        # Try each candidate table within this section until a valid Name/Value table is parsed
        table_md = ''
        csv_rows: List[Dict] = []
        for tbl in section.get('tables', []):
            md, rows = parse_table_to_markdown(tbl, section.get('type', 'enumeration'))
            if md and rows:
                table_md = md
                csv_rows = rows
                break

        if table_md:
            markdown_blocks.append(table_md)
            clean_cat = clean_category_name(title)
            for row in csv_rows:
                row['category'] = clean_cat
                all_csv.append(row)
        # If no suitable table found, do not print an error marker; continue gracefully
        markdown_blocks.append("")
        markdown_blocks.append("---")
        markdown_blocks.append("")

    return '\n'.join(markdown_blocks), all_csv


def process_html_file(input_file: str, output_file: str = None) -> None:
    if not os.path.exists(input_file):
        raise FileNotFoundError(f"Input file not found: {input_file}")

    # Read bytes and attempt sensible decodings (v2.1 docs often use Windows-1252)
    with open(input_file, 'rb') as f:
        raw = f.read()

    html = None
    for enc in ('utf-8', 'windows-1252', 'latin-1'):
        try:
            html = raw.decode(enc)
            break
        except UnicodeDecodeError:
            continue
    if html is None:
        # Fallback: replace errors using utf-8 to avoid crash
        html = raw.decode('utf-8', errors='replace')

    soup = BeautifulSoup(html, 'html.parser')

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

    with open(output_file, 'w', encoding='utf-8') as f:
        f.write("# KMIP v2.x Specification Enumerations\n\n")
        f.write("Extracted from HTML specification document.\n\n")
        f.write(md_content)

    csv_file = f"{base_name}_enumerations.csv"
    if csv_data:
        with open(csv_file, 'w', newline='', encoding='utf-8') as f:
            writer = csv.DictWriter(f, fieldnames=['enumeration_category', 'enumeration_name', 'value'])
            writer.writeheader()
            for row in csv_data:
                writer.writerow({
                    'enumeration_category': row.get('category', ''),
                    'enumeration_name': row.get('name', ''),
                    'value': row.get('value', ''),
                })
        print(f"CSV data written to: {csv_file}")

    print(f"Markdown output written to: {output_file}")


def main():
    parser = argparse.ArgumentParser(description="Extract KMIP v2.x enumeration tables from HTML and output Markdown + CSV")
    parser.add_argument('input_file', help='Path to the KMIP v2.x HTML file')
    parser.add_argument('-o', '--output', help='Output Markdown file path (default: <input>_extracted.md)')
    args = parser.parse_args()

    try:
        process_html_file(args.input_file, args.output)
    except Exception as e:
        print(f"Error: {e}")
        return 1
    return 0


if __name__ == '__main__':
    raise SystemExit(main())
