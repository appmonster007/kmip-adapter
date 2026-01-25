#!/usr/bin/env python3
"""
Script to scrape KMIP messages from HTML files.
It extracts sections under "2 KMIP Test Cases" (starting with "2"),
creates a nested directory structure based on headers,
and saves XML messages into individual files.
"""

import argparse
import os
import re
import html
import sys

# Configuration
OUTPUT_DIR_NAME = 'test-cases-messages'

def get_script_dir():
    return os.path.dirname(os.path.abspath(__file__))

def clean_filename(name):
    # Remove invalid characters for filenames
    name = re.sub(r'[\\/*?:"<>|]', '', name)
    # Replace spaces and other separators with underscores
    name = re.sub(r'\s+', '_', name)
    return name

def clean_text(text):
    # Remove HTML tags
    text = re.sub(r'<[^>]+>', '', text)
    # Decode HTML entities
    text = html.unescape(text)
    # Replace non-breaking spaces
    text = text.replace('\xa0', ' ')
    return text.strip()

def get_level(tag, attrs):
    tag = tag.lower()
    if tag == 'h1': return 1
    if tag == 'h2': return 2
    if tag == 'h3': return 3
    if tag == 'h4': return 4
    if tag == 'h5': return 5
    if tag == 'h6': return 6
    if tag == 'p' and 'Heading1WP' in attrs: return 1
    return None

def process_file(input_path, output_root):
    if not os.path.exists(input_path):
        print(f"Error: Input file not found at {input_path}")
        return

    print(f"Reading {input_path}...")
    with open(input_path, 'r', encoding='utf-8', errors='ignore') as f:
        content = f.read()

    # Regex to find headers and content paragraphs
    # We look for h1-h6 and p tags.
    # We capture: 1=tag, 2=attributes, 3=content
    pattern = re.compile(r'<(h[1-6]|p)\b([^>]*)>(.*?)</\1>', re.IGNORECASE | re.DOTALL)
    
    matches = []
    for m in pattern.finditer(content):
        matches.append((m.start(), m.group(1), m.group(2), m.group(3)))
    
    # Sort matches by position (though finditer usually yields in order)
    matches.sort(key=lambda x: x[0])
    
    # Stack of (level, dirname)
    stack = []
    
    # Buffer: path_tuple -> list of text lines
    content_map = {}

    current_path = ()
    
    print("Parsing HTML content...")
    for _, tag, attrs, text_content in matches:
        tag = tag.lower()
        
        # Check if it is a header
        level = get_level(tag, attrs)

        if level is not None:
            # It is a header
            text = clean_text(text_content)
            if not text:
                continue

            # Pop from stack if we are going up or staying at same level
            while stack and stack[-1][0] >= level:
                stack.pop()

            dirname = clean_filename(text)
            stack.append((level, dirname))

            current_path = tuple(d for l, d in stack)

        elif tag == 'p' and 'KMIPXMLCELL' in attrs:
            # It is content
            if not stack:
                continue

            # Filter: Only collect if under "2 KMIP Test Cases"
            # We assume the root section (level 1 or top of stack) starts with "2"
            root_dir = stack[0][1]
            if not root_dir.startswith('2'):
                continue

            text = clean_text(text_content)
            if current_path not in content_map:
                content_map[current_path] = []
            content_map[current_path].append(text)

    print(f"Found {len(content_map)} sections with content under '2...'.")
    
    total_files = 0
    for path_tuple, lines in content_map.items():
        full_text = "\n".join(lines)
        
        # Find XML messages
        xml_matches = re.finditer(r'<(RequestMessage|ResponseMessage)>.*?</\1>', full_text, re.DOTALL)
        
        count = 0
        for match in xml_matches:
            count += 1
            xml_content = match.group(0)
            root_tag = match.group(1)

            # Construct target directory
            target_dir = os.path.join(output_root, *path_tuple)
            if not os.path.exists(target_dir):
                os.makedirs(target_dir)
            
            # Filename: LeafSection_Count_Type.xml
            leaf_name = path_tuple[-1]
            filename = f"{leaf_name}_{count}_{root_tag}.xml"
            filepath = os.path.join(target_dir, filename)
            
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(xml_content)
            total_files += 1
            
    print(f"Saved {total_files} files from {input_path}")

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Scrape KMIP messages from HTML.')
    parser.add_argument('files', metavar='F', type=str, nargs='+',
                        help='HTML files to process.')
    args = parser.parse_args()

    script_dir = get_script_dir()
    output_dir = os.path.join(script_dir, OUTPUT_DIR_NAME)

    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for f in args.files:
        process_file(f, output_dir)
