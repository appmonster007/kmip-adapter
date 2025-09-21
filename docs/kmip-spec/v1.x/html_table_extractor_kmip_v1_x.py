#!/usr/bin/env python3
"""
HTML Table Extractor

This script extracts enumeration tables from HTML files and converts them to markdown format.
It specifically looks for sections with h5 headers followed by tables with Name/Value columns.
"""

import os
import re
import csv
import argparse
from typing import List, Dict, Optional, Tuple
from bs4 import BeautifulSoup, element


def extract_text_content(element) -> str:
    """Extract clean text content from an HTML element, removing extra whitespace."""
    if element is None:
        return ""
    
    # Get text and clean it up
    text = element.get_text(strip=True)
    # Remove extra whitespace and normalize
    text = re.sub(r'\s+', ' ', text)
    return text


def parse_table_to_markdown(table_element, table_type='enumeration') -> Tuple[str, List[Dict]]:
    """Convert an HTML table to markdown format.
    
    Args:
        table_element: The BeautifulSoup table element to parse
        table_type: Type of table ('enumeration' or 'tag_values')
        
    Returns:
        Tuple of (markdown_string, csv_rows) where csv_rows is a list of dicts with 'name' and 'value' keys
    """
    csv_rows = []
    if not table_element:
        return ""
    
    rows = table_element.find_all('tr')
    if not rows:
        return ""
    
    markdown_lines = []
    headers = []
    header_row_idx = -1
    
    if table_type == 'tag_values':
        # Special handling for Tag Values table
        headers = ['Object', 'Tag Value']
        header_row_idx = 1  # The header is the second row (0-based index)
    else:
        # Find the actual header row - look for rows with silver background and 2 columns
        for i, row in enumerate(rows):
            cells = row.find_all(['th', 'td'])
            if len(cells) >= 2:  # Must have at least 2 columns
                # Check if cells have silver background (header styling)
                first_cell_style = cells[0].get('style', '')
                second_cell_style = cells[1].get('style', '')
                
                if 'background:silver' in first_cell_style or 'background:silver' in second_cell_style:
                    header_row_idx = i
                    # Extract header text
                    for cell in cells:
                        header_text = extract_text_content(cell)
                        headers.append(header_text)
                    break
        
        # If no header found, use default Name/Value headers for 2-column tables
        if header_row_idx == -1:
            # Look for the first row with 2 columns
            for i, row in enumerate(rows):
                cells = row.find_all(['td', 'th'])
                if len(cells) == 2:
                    # Skip title rows (those with colspan)
                    if not (len(cells) == 1 and cells[0].get('colspan')):
                        headers = ['Name', 'Value']
                        header_row_idx = i - 1  # Start processing from this row
                        break
        
        if not headers:
            headers = ['Name', 'Value']  # Default headers
    
    # Create markdown header row
    markdown_lines.append('| ' + ' | '.join(headers) + ' |')
    # Create separator row
    markdown_lines.append('| ' + ' | '.join(['---'] * len(headers)) + ' |')
    
    # Process data rows
    for i, row in enumerate(rows):
        if i <= header_row_idx:  # Skip header and any rows before it
            continue
            
        cells = row.find_all(['td', 'th'])
        if not cells:
            continue
            
        # Skip rows that span all columns (title rows)
        if len(cells) == 1 and cells[0].get('colspan'):
            continue
        
        # For tag values table, skip the header row with silver background
        if table_type == 'tag_values' and cells and 'background:silver' in cells[0].get('style', ''):
            continue
            
        cell_texts = []
        for cell in cells:
            cell_text = extract_text_content(cell)
            # Clean up the tag value (remove extra spaces and newlines)
            if table_type == 'tag_values' and len(cell_texts) == 1:  # Second column (Tag Value)
                cell_text = ' '.join(cell_text.split())  # Normalize whitespace
            # Escape pipe characters in cell content
            cell_text = cell_text.replace('|', '\\|')
            cell_texts.append(cell_text)
        
        # Skip empty rows
        if not any(cell_texts):
            continue
        
        # Pad with empty cells if needed
        while len(cell_texts) < len(headers):
            cell_texts.append('')
        
        # Only process rows with the expected number of columns
        if len(cell_texts) <= len(headers):
            markdown_lines.append('| ' + ' | '.join(cell_texts) + ' |')
            
            # Add to CSV rows if this is a data row (not a header or separator)
            if i > header_row_idx and len(cell_texts) >= 2:
                # For tag values, use the specific column mapping
                if table_type == 'tag_values':
                    csv_rows.append({
                        'name': cell_texts[0],  # Object
                        'value': cell_texts[1]   # Tag Value
                    })
                # For enumerations, use the first two columns as name/value
                elif table_type == 'enumeration' and len(headers) >= 2:
                    csv_rows.append({
                        'name': cell_texts[0],  # Name
                        'value': cell_texts[1]   # Value
                    })
    
    return '\n'.join(markdown_lines), csv_rows


def clean_category_name(category: str) -> str:
    """Clean up category name by removing common suffixes like 'Enumeration', 'Values', etc."""
    if not category:
        return category
    
    # Remove common suffixes
    suffixes = ['Enumeration', 'Value', 'Values']
    for suffix in suffixes:
        if category.endswith(suffix):
            category = category[:-len(suffix)]
    
    # Clean up any extra whitespace or dashes
    category = re.sub(r'[\s-]+$', '', category)
    return category.strip()


def extract_section_title(h5_element) -> str:
    """Extract clean title from h5 element."""
    if not h5_element:
        return ""
    
    # Get all text content, including from nested <a> tags
    title = extract_text_content(h5_element)
    
    # Clean up common patterns
    title = re.sub(r'^[\d.]+\s*', '', title)  # Remove leading numbers
    title = title.strip()
    
    return title


def find_tag_values_section(soup: BeautifulSoup) -> Dict:
    """Find and extract the Tag Values section."""
    # Look for the h4 with 'Tags' in it
    h4_elements = soup.find_all('h4')
    for h4 in h4_elements:
        if 'Tags' in extract_text_content(h4):
            # Found the Tags section, now look for the table
            current = h4.next_sibling
            while current:
                if hasattr(current, 'name'):
                    if current.name == 'p' and 'MsoBodyText' in current.get('class', []):
                        # This is the description
                        description = extract_text_content(current)
                    elif current.name == 'table':
                        # Found the table
                        return {
                            'title': 'Tag Values',
                            'description': description,
                            'table': current,
                            'type': 'tag_values'
                        }
                current = current.next_sibling
    return None


def find_enumeration_sections(soup: BeautifulSoup) -> List[Dict]:
    """Find all enumeration sections with h5 headers and tables."""
    sections = []
    
    # Find all h5 elements
    h5_elements = soup.find_all('h5')
    
    for h5 in h5_elements:
        title = extract_section_title(h5)
        
        # Skip if title doesn't look like an enumeration
        if not title or 'enumeration' not in title.lower():
            continue
        
        # Look for the next table after this h5
        current = h5.next_sibling
        table = None
        description = ""
        
        # Search for table within reasonable distance
        search_count = 0
        while current and search_count < 20:
            if hasattr(current, 'name'):
                if current.name == 'table':
                    table = current
                    break
                elif current.name == 'div':
                    # Look for table inside div
                    table_in_div = current.find('table')
                    if table_in_div:
                        table = table_in_div
                        break
                elif current.name == 'p' and current.get('class') and 'Ref' in current.get('class'):
                    # This might be a description paragraph
                    desc_text = extract_text_content(current)
                    if desc_text and len(desc_text) < 500:  # Reasonable description length
                        description = desc_text
            
            current = current.next_sibling
            search_count += 1
        
        if table:
            sections.append({
                'title': title,
                'description': description,
                'table': table,
                'h5_element': h5,
                'type': 'enumeration'
            })
    
    return sections


def convert_sections_to_markdown(sections: List[Dict]) -> Tuple[str, List[Dict]]:
    """Convert all sections to markdown format and collect CSV data.
    
    Returns:
        Tuple of (markdown_content, all_csv_rows) where all_csv_rows is a list of dicts
        with 'category', 'name', and 'value' keys
    """
    markdown_content = []
    all_csv_rows = []
    
    for i, section in enumerate(sections, 1):
        section_title = section['title']
        markdown_content.append(f"## {section_title}")
        markdown_content.append("")
        
        if 'description' in section and section['description']:
            markdown_content.append(section['description'])
            markdown_content.append("")
        
        table_type = section.get('type', 'enumeration')
        table_md, csv_rows = parse_table_to_markdown(section['table'], table_type)
        
        if table_md:
            markdown_content.append(table_md)
            
            # Add cleaned section title as category to each CSV row
            clean_category = clean_category_name(section_title)
            for row in csv_rows:
                row['category'] = clean_category
                all_csv_rows.append(row)
        else:
            markdown_content.append("*Table could not be parsed*")
        
        markdown_content.append("")
        markdown_content.append("---")
        markdown_content.append("")
    
    return '\n'.join(markdown_content), all_csv_rows


def process_html_file(input_file: str, output_file: str = None) -> None:
    """Process HTML file and extract tables."""
    
    if not os.path.exists(input_file):
        raise FileNotFoundError(f"Input file not found: {input_file}")
    
    # Read HTML file
    with open(input_file, 'r', encoding='utf-8') as f:
        html_content = f.read()
    
    # Parse HTML
    soup = BeautifulSoup(html_content, 'html.parser')
    
    # Extract Tag Values section
    tag_values_section = find_tag_values_section(soup)
    
    # Extract enumeration sections
    enum_sections = find_enumeration_sections(soup)
    
    if not enum_sections and not tag_values_section:
        print("No relevant sections found in the HTML file.")
        return
    
    # Combine all sections
    all_sections = []
    
    # Add Tag Values section first if found
    if tag_values_section:
        print("Found Tag Values section")
        all_sections.append(tag_values_section)
    
    # Add enumeration sections
    if enum_sections:
        print(f"\nFound {len(enum_sections)} enumeration sections:")
        for section in enum_sections:
            print(f"  - {section['title']}")
        all_sections.extend(enum_sections)
    
    # Convert to markdown and get CSV data
    base_name = os.path.splitext(input_file)[0]
    
    # Generate markdown
    markdown_content, csv_data = convert_sections_to_markdown(all_sections)
    
    # Determine output files
    if output_file is None:
        output_file = f"{base_name}_extracted.md"
    
    # Write markdown file
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write("# KMIP Specification Data\n\n")
        f.write("Extracted from HTML specification document.\n\n")
        f.write(markdown_content)
    
    # Write CSV file
    csv_file = f"{base_name}_enumerations.csv"
    if csv_data:
        with open(csv_file, 'w', newline='', encoding='utf-8') as f:
            # Define the field names in the order we want them in the CSV
            fieldnames = ['enumeration_category', 'enumeration_name', 'value']
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            
            # Write header
            writer.writeheader()
            
            # Write data rows
            for row in csv_data:
                writer.writerow({
                    'enumeration_category': row.get('category', ''),
                    'enumeration_name': row.get('name', ''),
                    'value': row.get('value', '')
                })
        
        print(f"CSV data written to: {csv_file}")
    
    print(f"\nMarkdown output written to: {output_file}")


def main():
    parser = argparse.ArgumentParser(
        description="Extract enumeration tables from HTML files and convert to markdown"
    )
    parser.add_argument(
        'input_file',
        help='Path to the input HTML file'
    )
    parser.add_argument(
        '-o', '--output',
        help='Output markdown file path (default: input_file_enumerations.md)'
    )
    
    args = parser.parse_args()
    
    try:
        process_html_file(args.input_file, args.output)
    except Exception as e:
        print(f"Error: {e}")
        return 1
    
    return 0


if __name__ == "__main__":
    exit(main())
