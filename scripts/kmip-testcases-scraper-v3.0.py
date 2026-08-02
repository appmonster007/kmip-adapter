#!/usr/bin/env python3
"""
KMIP 3.0 Test-Case Scraper
==========================

Downloads every profile test-case XML file published under
    https://docs.oasis-open.org/kmip/kmip-profiles/v3.0/csd01/test-cases/
into the workspace under
    docs/kmip-spec/v3.x/kmip-testcases/v3.0/csd01/test-cases/

The remote layout mirrors the OASIS site:

    test-cases/
        kmip-v3.0/
            mandatory/*.xml    (95 files)
            optional/*.xml     ( 7 files)
        mandatory/*.xml        (byte-identical duplicate of kmip-v3.0/mandatory/)
        optional/*.xml         (byte-identical duplicate of kmip-v3.0/optional/)

By default we mirror only the nested ``kmip-v3.0/`` tree (the top-level
``mandatory``/``optional`` folders on OASIS are byte-identical duplicates and
would just waste disk). Pass ``--include-duplicates`` to fetch those too.

Usage
-----
    python scripts/kmip-testcases-scraper-v3.0.py [--include-duplicates] [--force]

The script is idempotent: existing files are skipped unless ``--force`` is
given.  Run it from the repository root.
"""

from __future__ import annotations

import argparse
import os
import re
import sys
import urllib.request
from urllib.error import HTTPError, URLError

BASE_URL = "https://docs.oasis-open.org/kmip/kmip-profiles/v3.0/csd01/test-cases/"
LOCAL_ROOT = os.path.join(
    "docs", "kmip-spec", "v3.x", "kmip-testcases", "v3.0", "csd01", "test-cases"
)

# Relative paths under BASE_URL that hold the XML test cases.
NESTED_DIRS = ["kmip-v3.0/mandatory/", "kmip-v3.0/optional/"]
DUPLICATE_DIRS = ["mandatory/", "optional/"]

XML_HREF_RE = re.compile(r'href="([^"/?#]+\.xml)"', re.IGNORECASE)

USER_AGENT = "kmip-adapter-testcase-scraper/1.0 (+https://github.com/)"


def http_get(url: str) -> bytes:
    req = urllib.request.Request(url, headers={"User-Agent": USER_AGENT})
    with urllib.request.urlopen(req, timeout=30) as resp:
        return resp.read()


def list_xml_files(dir_url: str) -> list[str]:
    """Fetch an OASIS directory index and return the XML filenames it lists."""
    html = http_get(dir_url).decode("utf-8", errors="replace")
    return sorted(set(XML_HREF_RE.findall(html)))


def download_file(url: str, dest_path: str, force: bool) -> str:
    """Download ``url`` to ``dest_path``. Returns 'downloaded' or 'skipped'."""
    if os.path.exists(dest_path) and not force:
        return "skipped"
    os.makedirs(os.path.dirname(dest_path), exist_ok=True)
    data = http_get(url)
    # Atomic-ish write to avoid partial files on interruption.
    tmp = dest_path + ".part"
    with open(tmp, "wb") as fh:
        fh.write(data)
    os.replace(tmp, dest_path)
    return "downloaded"


def sync_dir(rel_dir: str, force: bool) -> tuple[int, int, int]:
    """Mirror one remote directory into the local tree.

    Returns (count_total, count_downloaded, count_skipped).
    """
    remote = BASE_URL + rel_dir
    # rel_dir uses forward slashes; convert to OS-native for local path.
    local_dir = os.path.join(LOCAL_ROOT, *rel_dir.strip("/").split("/"))
    os.makedirs(local_dir, exist_ok=True)

    try:
        files = list_xml_files(remote)
    except (HTTPError, URLError) as exc:  # pragma: no cover - network
        print(f"  ERROR listing {remote}: {exc}", file=sys.stderr)
        return (0, 0, 0)

    print(f"  {rel_dir}: {len(files)} XML files")
    downloaded = skipped = 0
    for name in files:
        url = remote + name
        dest = os.path.join(local_dir, name)
        try:
            status = download_file(url, dest, force)
        except (HTTPError, URLError) as exc:  # pragma: no cover - network
            print(f"    FAIL {name}: {exc}", file=sys.stderr)
            continue
        if status == "downloaded":
            downloaded += 1
        else:
            skipped += 1
    print(f"    -> downloaded={downloaded} skipped={skipped}")
    return (len(files), downloaded, skipped)


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__.splitlines()[1])
    parser.add_argument(
        "--include-duplicates",
        action="store_true",
        help="Also mirror the top-level mandatory/ and optional/ folders "
        "(byte-identical to the kmip-v3.0/ nested copies).",
    )
    parser.add_argument(
        "--force",
        action="store_true",
        help="Re-download files even if they already exist locally.",
    )
    args = parser.parse_args()

    dirs = list(NESTED_DIRS)
    if args.include_duplicates:
        dirs += DUPLICATE_DIRS

    print(f"KMIP 3.0 test-case scraper")
    print(f"  base: {BASE_URL}")
    print(f"  dest: {LOCAL_ROOT}")
    print()

    totals = [0, 0, 0]
    for rel in dirs:
        t, d, s = sync_dir(rel, args.force)
        totals[0] += t
        totals[1] += d
        totals[2] += s

    print()
    print(
        f"Done. total_listed={totals[0]} downloaded={totals[1]} skipped={totals[2]}"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
