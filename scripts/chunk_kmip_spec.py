#!/usr/bin/env python3
"""
Chunk large KMIP reference files into small, semantically-meaningful slices so
that Claude Code agents/skills can `Read` only the slice they need instead of
loading the 575 KB JSON or 140 KB markdown into context.

Produces under `docs/kmip-spec/chunks/`:

    chunks/
      index.md                       # Human/agent index of every chunk
      enumerations/
        <enum-slug>.json             # One JSON file per spec enumeration
        <enum-slug>.md               # Human-readable per-enum coverage
      tags/
        <tag-hex>.json               # One file per spec tag
      pending/
        01-enumeration-status.md     # docs/kmip-pending-implementation.md split
        02-missing-enumeration-classes.md
        ...
      operations/
        <op-slug>.md                 # One file per operation (request/response status)

Re-run after each scrape or pending-doc regeneration. The chunker is fast
(under a second) and idempotent.

Usage:
    python3 scripts/chunk_kmip_spec.py                # rebuild all chunks
    python3 scripts/chunk_kmip_spec.py --dry-run      # show what would be written
"""

from __future__ import annotations
import argparse
import json
import pathlib
import re
import shutil

REPO = pathlib.Path(__file__).resolve().parent.parent
JSON_PATH = REPO / "docs/kmip-spec/kmip-all-versions-data.json"
PENDING_PATH = REPO / "docs/kmip-pending-implementation.md"
OUT_DIR = REPO / "docs/kmip-spec/chunks"


def slugify(name: str) -> str:
    s = re.sub(r"[^A-Za-z0-9]+", "-", name).strip("-").lower()
    return s or "unnamed"


def chunk_enumerations(data: dict, out: pathlib.Path, written: list[str]) -> None:
    """One file per spec enumeration aggregating its presence + values across all versions."""
    enums_dir = out / "enumerations"
    enums_dir.mkdir(parents=True, exist_ok=True)

    # Collect: enum_name -> {version -> values list}
    per_enum: dict[str, dict[str, list]] = {}
    for vd in data["versions"]:
        v = vd["version"]
        for name, body in vd.get("enumerations", {}).items():
            per_enum.setdefault(name, {})[v] = body.get("values", [])

    for name, by_version in sorted(per_enum.items()):
        slug = slugify(name)
        spec_versions = sorted(by_version.keys())
        # Union all values across versions (a value present in any version of this enum)
        all_values: dict[str, dict] = {}
        for v, vals in by_version.items():
            for item in vals:
                key = item.get("name") + "|" + str(item.get("value"))
                all_values.setdefault(key, {
                    "name": item.get("name"),
                    "value": item.get("value"),
                    "versions": [],
                })
                all_values[key]["versions"].append(v)
        for entry in all_values.values():
            entry["versions"].sort()

        jbody = {
            "name": name,
            "spec_versions": spec_versions,
            "introduced_in": spec_versions[0] if spec_versions else None,
            "values": sorted(all_values.values(), key=lambda x: x["value"] or ""),
        }
        json_path = enums_dir / f"{slug}.json"
        json_path.write_text(json.dumps(jbody, indent=2))
        written.append(str(json_path.relative_to(REPO)))

        # Companion markdown for quick human read
        md = [f"# {name}", "",
              f"- **Spec versions**: {' '.join(f'`v{v}`' for v in spec_versions)}",
              f"- **Introduced**: v{spec_versions[0]}" if spec_versions else "",
              "",
              "| Name | Value | Versions |",
              "|---|---|---|"]
        for e in jbody["values"]:
            vs = " ".join(f"`v{v}`" for v in e["versions"])
            md.append(f"| {e['name']} | `{e['value']}` | {vs} |")
        md_path = enums_dir / f"{slug}.md"
        md_path.write_text("\n".join(md) + "\n")
        written.append(str(md_path.relative_to(REPO)))


def chunk_tags(data: dict, out: pathlib.Path, written: list[str]) -> None:
    """One file per tag (by hex) listing all versions it appears in + reserved flag."""
    tags_dir = out / "tags"
    tags_dir.mkdir(parents=True, exist_ok=True)

    # Collect: tag_name -> {version -> {tag_hex, reserved}}
    per_tag: dict[str, dict[str, dict]] = {}
    for vd in data["versions"]:
        v = vd["version"]
        for tag_name, body in vd.get("tags", {}).items():
            per_tag.setdefault(tag_name, {})[v] = body

    # Index tags by hex when known
    index_rows = []
    for tag_name, by_version in sorted(per_tag.items()):
        # Pick a representative hex (first non-null)
        hex_tag = None
        reserved_any = False
        for v in sorted(by_version):
            t = by_version[v].get("tag", "")
            if isinstance(t, str) and t.startswith("0x"):
                hex_tag = t
            if by_version[v].get("reserved"):
                reserved_any = True
        slug = slugify(tag_name)
        out_body = {
            "name": tag_name,
            "hex": hex_tag,
            "appears_in": sorted(by_version.keys()),
            "reserved_in_some_version": reserved_any,
            "per_version": {v: by_version[v] for v in sorted(by_version)},
        }
        json_path = tags_dir / f"{slug}.json"
        json_path.write_text(json.dumps(out_body, indent=2))
        written.append(str(json_path.relative_to(REPO)))
        index_rows.append((tag_name, hex_tag or "—", " ".join(f"v{v}" for v in sorted(by_version)),
                           "🚫 reserved-in-some-version" if reserved_any else ""))

    # Write index for tags
    idx = ["# Tag chunks index", "",
           "| Tag | Hex | Versions | Flags |", "|---|---|---|---|"]
    for row in index_rows:
        idx.append("| " + " | ".join(row) + " |")
    (tags_dir / "index.md").write_text("\n".join(idx) + "\n")
    written.append(str((tags_dir / "index.md").relative_to(REPO)))


def chunk_pending_doc(out: pathlib.Path, written: list[str]) -> None:
    """Split kmip-pending-implementation.md by top-level (## N.) section."""
    if not PENDING_PATH.exists():
        return
    text = PENDING_PATH.read_text()
    sections_dir = out / "pending"
    sections_dir.mkdir(parents=True, exist_ok=True)

    # Split on top-level "## " headings
    parts = re.split(r"(?m)^(?=## \d)", text)
    header = parts[0]  # everything before §1
    (sections_dir / "00-header.md").write_text(header.strip() + "\n")
    written.append(str((sections_dir / "00-header.md").relative_to(REPO)))

    for i, body in enumerate(parts[1:], start=1):
        # First line is "## N. Title"
        first = body.splitlines()[0]
        m = re.match(r"^## (\d+)\.\s*(.+)$", first)
        if not m:
            continue
        num, title = m.group(1), m.group(2).strip()
        slug = f"{int(num):02d}-{slugify(title)}"
        path = sections_dir / f"{slug}.md"
        path.write_text(body.rstrip() + "\n")
        written.append(str(path.relative_to(REPO)))


def chunk_operations(out: pathlib.Path, written: list[str]) -> None:
    """Extract per-operation status from §6 of kmip-pending-implementation.md."""
    if not PENDING_PATH.exists():
        return
    text = PENDING_PATH.read_text()
    # Find §6
    m = re.search(r"^## 6\.[^\n]*\n(.*?)(?=^## \d)", text, re.MULTILINE | re.DOTALL)
    if not m:
        return
    sec6 = m.group(1)
    ops_dir = out / "operations"
    ops_dir.mkdir(parents=True, exist_ok=True)

    rows = re.findall(r"\| ([A-Z][^|]+?) \| `(0x[0-9A-Fa-f]+)` \|(?: (v[0-9.]+) \|)? (✅|❌) \| (✅|❌) \|", sec6)
    for op, hex_tag, version, req, resp in rows:
        slug = slugify(op)
        body = (
            f"# Operation: {op}\n\n"
            f"- **Tag**: `{hex_tag}`\n"
            f"- **Introduced**: {version or '(see spec)'}\n"
            f"- **RequestPayload implemented**: {req}\n"
            f"- **ResponsePayload implemented**: {resp}\n"
        )
        path = ops_dir / f"{slug}.md"
        path.write_text(body)
        written.append(str(path.relative_to(REPO)))


def write_index(out: pathlib.Path, written: list[str]) -> None:
    lines = [
        "# KMIP spec chunks index",
        "",
        "Generated by `scripts/chunk_kmip_spec.py`. Re-run after each scrape or",
        "pending-doc regeneration.",
        "",
        f"**Total chunks**: {len(written)}",
        "",
        "## How to use",
        "",
        "Agents and skills should prefer reading these small per-entity files over the",
        "large source files (`kmip-all-versions-data.json` is 575 KB,",
        "`kmip-pending-implementation.md` is 140 KB).",
        "",
        "- Looking up a single enum's spec entry → `enumerations/<slug>.json` (~1–5 KB)",
        "- Looking up a tag's coverage / reserved status → `tags/<slug>.json` (~1 KB)",
        "- Looking up implementation gap for one section → `pending/NN-<topic>.md` (~5–20 KB)",
        "- Looking up one operation's status → `operations/<slug>.md` (~300 B)",
        "",
        "## Subdirectories",
        "",
        "- `enumerations/` — One JSON+MD pair per spec enumeration (~65 enums)",
        "- `tags/` — One JSON per KMIP tag",
        "- `pending/` — `docs/kmip-pending-implementation.md` split by `## N.` section",
        "- `operations/` — One MD per operation extracted from §6",
        "",
    ]
    (out / "index.md").write_text("\n".join(lines))


def main():
    ap = argparse.ArgumentParser(description=__doc__)
    ap.add_argument("--dry-run", action="store_true",
                    help="list output paths but write nothing")
    ap.add_argument("--clean", action="store_true",
                    help="wipe chunks/ before writing (default: overwrite in place)")
    args = ap.parse_args()

    data = json.loads(JSON_PATH.read_text())
    if args.clean and OUT_DIR.exists():
        shutil.rmtree(OUT_DIR)
    OUT_DIR.mkdir(parents=True, exist_ok=True)

    written: list[str] = []

    if args.dry_run:
        # Just count what would be produced
        enum_count = sum(1 for vd in data["versions"] for _ in vd.get("enumerations", {})) \
                     // max(1, len(data["versions"]))
        print(f"Would write ~{enum_count} enumeration chunks under {OUT_DIR}/enumerations/")
        return

    chunk_enumerations(data, OUT_DIR, written)
    chunk_tags(data, OUT_DIR, written)
    chunk_pending_doc(OUT_DIR, written)
    chunk_operations(OUT_DIR, written)
    write_index(OUT_DIR, written)
    written.append(str((OUT_DIR / "index.md").relative_to(REPO)))

    print(f"Wrote {len(written)} chunk files under {OUT_DIR.relative_to(REPO)}/")
    # Show breakdown by subdir
    from collections import Counter
    by_dir = Counter(pathlib.Path(p).parts[2] if len(pathlib.Path(p).parts) > 3 else "root"
                     for p in written)
    for d, n in sorted(by_dir.items()):
        print(f"  {d}/: {n} files")


if __name__ == "__main__":
    main()
