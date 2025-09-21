#!/usr/bin/env python3
"""
KMIP Enumerations Comparator

Reads enumeration CSVs from KMIP v1.2, v2.1, and v3.0 and produces a merged CSV
showing whether each (category, name, value) triple exists in each version.

- Matching is case-insensitive for comparison.
- Output preserves original casing/spacing from the first occurrence encountered.
- All rows from all files are included (set union of rows).

Default input paths:
- docs/kmip-spec/v1.x/sections/kmip-spec-enumerations-v1.2-os_enumerations.csv
- docs/kmip-spec/v2.x/sections/kmip-spec-enumerations-v2.1-os_enumerations.csv
- docs/kmip-spec/v3.x/sections/kmip-spec-enumerations-v3.0-os_enumerations.csv

Default output path:
- docs/kmip-spec/kmip-enumerations-comparison.csv
"""

import os
import csv
import argparse
from typing import Dict, Tuple


def norm(s: str) -> str:
    if s is None:
        return ''
    return ' '.join(s.strip().lower().split())


def read_csv_to_set(path: str) -> Tuple[set, Dict[str, Tuple[str, str, str]]]:
    """Read a CSV with columns: enumeration_category, enumeration_name, value
    Returns a set of normalized keys and a map from normalized key to original triplet
    (first occurrence only) to preserve casing in output when needed.
    """
    present = set()
    originals: Dict[str, Tuple[str, str, str]] = {}
    if not os.path.exists(path):
        return present, originals

    with open(path, newline='', encoding='utf-8') as f:
        reader = csv.DictReader(f)
        for row in reader:
            cat = row.get('enumeration_category', '')
            name = row.get('enumeration_name', '')
            val = row.get('value', '')
            key = f"{norm(cat)}||{norm(name)}||{norm(val)}"
            present.add(key)
            if key not in originals:
                originals[key] = (cat, name, val)
    return present, originals


def merge_versions(v1_path: str, v2_path: str, v3_path: str, out_path: str) -> None:
    v1_set, v1_orig = read_csv_to_set(v1_path)
    v2_set, v2_orig = read_csv_to_set(v2_path)
    v3_set, v3_orig = read_csv_to_set(v3_path)

    all_keys = set().union(v1_set, v2_set, v3_set)

    # Build a unified originals map preferring earliest version occurrence order: v1 -> v2 -> v3
    originals: Dict[str, Tuple[str, str, str]] = {}
    for key in all_keys:
        if key in v1_orig:
            originals[key] = v1_orig[key]
        elif key in v2_orig:
            originals[key] = v2_orig[key]
        elif key in v3_orig:
            originals[key] = v3_orig[key]
        else:
            # Should not happen, but safeguard
            cat, name, val = key.split('||')
            originals[key] = (cat, name, val)

    # Sort rows for stable output: by category, then name, then value (case-insensitive)
    def sort_key(k: str):
        c, n, v = originals[k]
        return (norm(c), norm(n), norm(v))

    sorted_keys = sorted(all_keys, key=sort_key)

    os.makedirs(os.path.dirname(out_path), exist_ok=True)
    with open(out_path, 'w', newline='', encoding='utf-8') as f:
        writer = csv.DictWriter(
            f,
            fieldnames=[
                'enumeration_category', 'enumeration_name', 'value',
                'v1_2', 'v2_1', 'v3_0'
            ]
        )
        writer.writeheader()
        for key in sorted_keys:
            cat, name, val = originals[key]
            writer.writerow({
                'enumeration_category': cat,
                'enumeration_name': name,
                'value': val,
                'v1_2': 'Y' if key in v1_set else 'N',
                'v2_1': 'Y' if key in v2_set else 'N',
                'v3_0': 'Y' if key in v3_set else 'N',
            })


def main():
    parser = argparse.ArgumentParser(description='Compare KMIP enumeration CSVs across versions and produce a merged presence matrix.')
    parser.add_argument('--v1', default='docs/kmip-spec/v1.x/sections/kmip-spec-enumerations-v1.2-os_enumerations.csv', help='Path to v1.2 enumerations CSV')
    parser.add_argument('--v2', default='docs/kmip-spec/v2.x/sections/kmip-spec-enumerations-v2.1-os_enumerations.csv', help='Path to v2.1 enumerations CSV')
    parser.add_argument('--v3', default='docs/kmip-spec/v3.x/sections/kmip-spec-enumerations-v3.0-os_enumerations.csv', help='Path to v3.0 enumerations CSV')
    parser.add_argument('-o', '--out', default='docs/kmip-spec/kmip-enumerations-comparison.csv', help='Output CSV path')
    args = parser.parse_args()

    merge_versions(args.v1, args.v2, args.v3, args.out)
    print(f"Merged CSV written to: {args.out}")


if __name__ == '__main__':
    main()
