#!/usr/bin/env python3
"""
KMIP spec scraper — collects enumerations and tags from all version HTML specs,
then produces an exhaustive markdown document at docs/kmip-pending-implementation.md.

Supported spec files (relative to this script's directory):
  v1.x/kmip-spec-v1.2-os.html   (h5 for enum headings)
  v1.x/kmip-spec-v1.3-os.html   (h5 for enum headings)
  v1.x/kmip-spec-v1.4-os.html   (h5 for enum headings)
  v2.x/kmip-spec-v2.0-os.html   (h2 for enum headings)
  v2.x/kmip-spec-v2.1-os.html   (h2 for enum headings)
  v3.x/kmip-spec-v3.0-os.html   (h2 for enum headings)

Run:
  python3 docs/kmip-spec/scrape_kmip_all_versions.py
"""

import re
import sys
import json
from pathlib import Path
from typing import Optional, Dict, List, Tuple
from bs4 import BeautifulSoup, Tag, NavigableString

# ---------------------------------------------------------------------------
# Configuration
# ---------------------------------------------------------------------------

SPEC_DIR = Path(__file__).parent
REPO_ROOT = SPEC_DIR.parent.parent
OUT_MD = REPO_ROOT / "docs" / "kmip-pending-implementation.md"
OUT_JSON = SPEC_DIR / "kmip-all-versions-data.json"

SPECS = [
    {"version": "1.2", "label": "V1_2",
     "file": SPEC_DIR / "v1.x" / "kmip-spec-v1.2-os.html",
     "encoding": "windows-1252", "enum_heading_tag": "h5"},
    {"version": "1.3", "label": "V1_3",
     "file": SPEC_DIR / "v1.x" / "kmip-spec-v1.3-os.html",
     "encoding": "windows-1252", "enum_heading_tag": "h5"},
    {"version": "1.4", "label": "V1_4",
     "file": SPEC_DIR / "v1.x" / "kmip-spec-v1.4-os.html",
     "encoding": "windows-1252", "enum_heading_tag": "h5"},
    {"version": "2.0", "label": "V2_0",
     "file": SPEC_DIR / "v2.x" / "kmip-spec-v2.0-os.html",
     "encoding": "windows-1252", "enum_heading_tag": "h2"},
    {"version": "2.1", "label": "V2_1",
     "file": SPEC_DIR / "v2.x" / "kmip-spec-v2.1-os.html",
     "encoding": "windows-1252", "enum_heading_tag": "h2"},
    {"version": "3.0", "label": "V3_0",
     "file": SPEC_DIR / "v3.x" / "kmip-spec-v3.0-os.html",
     "encoding": "utf-8", "enum_heading_tag": "h2"},
]

ALL_VERSIONS = [s["version"] for s in SPECS]
HEADING_TAGS = ["h1", "h2", "h3", "h4", "h5", "h6"]

# ---------------------------------------------------------------------------
# Helpers
# ---------------------------------------------------------------------------

def clean(text: str) -> str:
    return re.sub(r"\s+", " ", (text or "")).strip()


def strip_section_number(text: str) -> str:
    """Remove leading section numbers like '11.1', '9.1.3.2.1' etc."""
    return re.sub(r"^[\d.]+\s+", "", text).strip()


def hex_val(text: str) -> Optional[str]:
    """Normalise a hex value string → '0x00000001', or return None."""
    t = clean(text).upper()
    t = re.sub(r"[^0-9A-Fa-f]", "", t.replace("0X", ""))
    if t and 4 <= len(t) <= 8:
        return "0x" + t.upper().zfill(8)
    return None


def is_reserved(name: str) -> bool:
    n = clean(name).lower()
    return (n.startswith("(reserved)")
            or n.startswith("(unused)")
            or n.startswith("reserved")
            or n == "")


def find_next_tables(element: Tag, max_tables: int = 3) -> List[Tag]:
    """
    Walk forward siblings (including content nested in <div> wrappers)
    and return up to max_tables <table> elements found before the next heading.
    """
    found = []
    sibling = element.find_next_sibling()
    while sibling and len(found) < max_tables:
        if isinstance(sibling, Tag):
            if sibling.name in HEADING_TAGS:
                break  # stop at next heading
            if sibling.name == "table":
                found.append(sibling)
            else:
                # look inside divs and other wrappers
                nested = sibling.find_all("table", recursive=True)
                found.extend(nested[:max_tables - len(found)])
        sibling = sibling.find_next_sibling()
    return found


def table_to_rows(table: Tag) -> List[List[str]]:
    rows = []
    for tr in table.find_all("tr"):
        cells = [clean(td.get_text()) for td in tr.find_all(["td", "th"])]
        if any(c for c in cells):
            rows.append(cells)
    return rows


def parse_enum_table(rows: List[List[str]]) -> List[Dict]:
    """
    Given table rows, extract Name+Value pairs for an enumeration.
    Returns list of {name, value} dicts.
    """
    if not rows:
        return []
    # Find Name and Value column indices
    header = [c.lower() for c in rows[0]]
    ni = next((i for i, h in enumerate(header) if h == "name"), None)
    vi = next((i for i, h in enumerate(header) if h == "value"), None)
    if ni is None or vi is None:
        # Try without header — sometimes first row is a section title, second is real header
        if len(rows) > 1:
            header2 = [c.lower() for c in rows[1]]
            ni = next((i for i, h in enumerate(header2) if h == "name"), None)
            vi = next((i for i, h in enumerate(header2) if h == "value"), None)
            if ni is None or vi is None:
                # Last resort: assume col0=Name, col1=Value
                ni, vi = 0, 1
            start = 2
        else:
            return []
    else:
        start = 1

    values = []
    for row in rows[start:]:
        if len(row) <= max(ni, vi):
            continue
        name = row[ni]
        val = row[vi]
        if is_reserved(name):
            continue
        if name.lower() in ("name", "value", "extensions"):
            continue
        v = hex_val(val) or val
        values.append({"name": name, "value": v})
    return values


# ---------------------------------------------------------------------------
# Per-spec data extraction
# ---------------------------------------------------------------------------

class SpecData:
    def __init__(self, version: str, label: str):
        self.version = version
        self.label = label
        # enum_name → {values: [{name, value}]}
        self.enumerations: Dict[str, Dict] = {}
        # tag_name → {tag, reserved}
        self.tags: Dict[str, Dict] = {}

    def to_dict(self):
        return {
            "version": self.version,
            "label": self.label,
            "enumerations": self.enumerations,
            "tags": self.tags,
        }


def parse_spec(cfg: dict) -> SpecData:
    path: Path = cfg["file"]
    version: str = cfg["version"]
    label: str = cfg["label"]
    encoding: str = cfg.get("encoding", "utf-8")
    enum_htag: str = cfg.get("enum_heading_tag", "h2")

    sd = SpecData(version, label)

    if not path.exists():
        print(f"  [SKIP] {path.name} not found", file=sys.stderr)
        return sd

    print(f"  Parsing v{version} from {path.name} …")
    try:
        raw = path.read_bytes().decode(encoding, errors="replace")
    except Exception as e:
        print(f"  [ERROR] {e}", file=sys.stderr)
        return sd

    soup = BeautifulSoup(raw, "html.parser")

    # ------------------------------------------------------------------ #
    # 1. ENUMERATIONS                                                       #
    # ------------------------------------------------------------------ #
    # Find all headings at the enumeration level that mention "Enumeration"
    enum_headings = [
        h for h in soup.find_all(enum_htag)
        if re.search(r"enumeration", h.get_text(), re.IGNORECASE)
        and not re.search(r"^(tag enumeration|tag values|tags?)\s*$",
                          strip_section_number(h.get_text()), re.IGNORECASE)
    ]

    # Also check one level down (in case the doc nests further)
    all_htags = HEADING_TAGS
    if not enum_headings:
        for ht in all_htags:
            enum_headings = [
                h for h in soup.find_all(ht)
                if re.search(r"enumeration", h.get_text(), re.IGNORECASE)
                and not re.search(r"^(tag enumeration|tag values)\s*$",
                                  strip_section_number(h.get_text()), re.IGNORECASE)
            ]
            if enum_headings:
                enum_htag = ht
                break

    for heading in enum_headings:
        raw_text = clean(heading.get_text())
        enum_name = strip_section_number(raw_text)
        # Remove trailing "Enumeration" word and clean up
        enum_name = re.sub(r"\s*[Ee]numeration\s*$", "", enum_name).strip()
        # Some headings say "XYZ Enumerations" (plural)
        enum_name = re.sub(r"\s*[Ee]numerations\s*$", "", enum_name).strip()
        if not enum_name or len(enum_name) > 80:
            continue

        tables = find_next_tables(heading, max_tables=4)
        for table in tables:
            rows = table_to_rows(table)
            vals = parse_enum_table(rows)
            if vals:
                if enum_name not in sd.enumerations:
                    sd.enumerations[enum_name] = {"values": []}
                existing = {v["name"] for v in sd.enumerations[enum_name]["values"]}
                for v in vals:
                    if v["name"] not in existing:
                        sd.enumerations[enum_name]["values"].append(v)
                        existing.add(v["name"])
                break  # first table with data wins

    # ------------------------------------------------------------------ #
    # 2. TAGS                                                               #
    # ------------------------------------------------------------------ #
    # Find the Tag Values / Tag Enumeration section
    tag_headings = []
    for ht in all_htags:
        tag_headings = [
            h for h in soup.find_all(ht)
            if re.search(r"(tag values|tag enumeration|appendix.*tag)", h.get_text(), re.IGNORECASE)
            or re.search(r"^[\d.]+\s+Tag\s+[Ee]numeration", h.get_text())
        ]
        if tag_headings:
            break

    for th in tag_headings:
        tables = find_next_tables(th, max_tables=5)
        for table in tables:
            rows = table_to_rows(table)
            if not rows:
                continue
            # Identify columns: look for "Name"/"Object"/"Tag" and value columns
            header = [c.lower() for c in rows[0]]
            ni = next((i for i, h in enumerate(header)
                        if h in ("name", "object", "tag name")), None)
            vi = next((i for i, h in enumerate(header)
                        if h in ("value", "tag value", "tag")), None)
            if ni is None:
                ni = 0
            if vi is None:
                vi = 1
            start = 1 if ni == 0 or vi == 1 else 0
            for row in rows[start:]:
                if len(row) <= max(ni, vi):
                    continue
                name_cell = row[ni]
                tag_cell = row[vi]
                if not name_cell or name_cell.lower() in ("name", "object", "tag", "tag name", "value"):
                    continue
                reserved = is_reserved(name_cell)
                tag_hex = hex_val(tag_cell) or tag_cell
                key = name_cell if not reserved else f"(Reserved:{tag_hex})"
                sd.tags[key] = {"tag": tag_hex, "reserved": reserved}

    return sd


# ---------------------------------------------------------------------------
# Cross-version diff analysis
# ---------------------------------------------------------------------------

def analyse_all(all_data: List[SpecData]) -> Dict:
    # Master enumerations: enum_name → {values: {val_name → {hex, versions[]}}, versions[]}
    enum_master: Dict[str, Dict] = {}

    for sd in all_data:
        for ename, edata in sd.enumerations.items():
            if ename not in enum_master:
                enum_master[ename] = {"values": {}, "versions": []}
            if sd.version not in enum_master[ename]["versions"]:
                enum_master[ename]["versions"].append(sd.version)
            for v in edata.get("values", []):
                vname = v["name"]
                if vname not in enum_master[ename]["values"]:
                    enum_master[ename]["values"][vname] = {"hex": v["value"], "versions": []}
                if sd.version not in enum_master[ename]["values"][vname]["versions"]:
                    enum_master[ename]["values"][vname]["versions"].append(sd.version)

    # Master tags: tag_name → {tag_hex, active_in[], reserved_in[]}
    tag_master: Dict[str, Dict] = {}
    for sd in all_data:
        for tname, tdata in sd.tags.items():
            if tname not in tag_master:
                tag_master[tname] = {"tag": tdata["tag"], "active_in": [], "reserved_in": []}
            if tdata["reserved"]:
                tag_master[tname]["reserved_in"].append(sd.version)
            else:
                tag_master[tname]["active_in"].append(sd.version)

    # Per-version diffs (incremental from previous)
    new_in: Dict[str, Dict] = {v: {"enumerations": [], "enum_values": {}, "tags": []} for v in ALL_VERSIONS}
    removed_in: Dict[str, Dict] = {v: {"enum_values": {}, "tags": []} for v in ALL_VERSIONS}

    prev_enums: set = set()
    prev_enum_values: Dict[str, set] = {}
    prev_active_tags: set = set()

    for sd in all_data:
        cur_enums = set(sd.enumerations.keys())
        new_in[sd.version]["enumerations"] = sorted(cur_enums - prev_enums)

        for ename in cur_enums | prev_enums:
            cur_vals = {v["name"] for v in sd.enumerations.get(ename, {}).get("values", [])}
            prev_vals = prev_enum_values.get(ename, set())
            added = sorted(cur_vals - prev_vals)
            removed = sorted(prev_vals - cur_vals)
            if added:
                new_in[sd.version]["enum_values"][ename] = added
            if removed:
                removed_in[sd.version]["enum_values"][ename] = removed

        cur_active = {n for n, d in sd.tags.items() if not d["reserved"]}
        new_in[sd.version]["tags"] = sorted(cur_active - prev_active_tags)
        removed_in[sd.version]["tags"] = sorted(prev_active_tags - cur_active)

        prev_enums = cur_enums
        for ename in cur_enums:
            prev_enum_values[ename] = {v["name"] for v in sd.enumerations[ename].get("values", [])}
        prev_active_tags = cur_active

    return {
        "enum_master": enum_master,
        "tag_master": tag_master,
        "new_in": new_in,
        "removed_in": removed_in,
    }


# ---------------------------------------------------------------------------
# Implemented-in-project inventory
# ---------------------------------------------------------------------------

IMPLEMENTED_ENUMS = {
    "Adjustment Type", "Alternative Name Type", "Asynchronous Indicator",
    "Attestation Type", "Batch Error Continuation Option", "Block Cipher Mode",
    "Cancellation Result", "Certificate Request Type", "Certificate Type",
    "Client Registration Method", "Credential Type", "Cryptographic Algorithm",
    "Data",  # implemented as `DataEnumeration` (Lombok @Data name collision); coexists with DataByteString on tag 0x4200C2 via different encodingType
    "Deactivation Reason Code", "Derivation Method", "Destroy Action",
    "Digital Signature Algorithm", "DRBG Algorithm", "Encoding Option",
    "Endpoint Role", "Ephemeral", "FIPS186 Variation", "Hashing Algorithm",
    "Interop Function", "Key Compression Type", "Key Format Type",
    "Key Role Type", "Key Value Location Type", "Key Wrap Type", "Link Type",
    "Mask Generator", "Name Type", "NIST Key Type", "Object Class",
    "Object Group Member", "Object Type", "Opaque Data Type", "Operation",
    "OTP Algorithm", "Padding Method", "Processing Stage", "Profile Name",
    "Protection Level", "Put Function", "Query Function", "Recommended Curve",
    "Result Reason", "Result Status", "Revocation Reason Code", "RNG Algorithm",
    "RNG Mode", "Rotate Name Type", "Secret Data Type", "Shredding Algorithm",
    "Split Key Method", "Split Key Polynomial", "State", "Ticket Type",
    "Unique Identifier",  # implemented as enumeration/UniqueIdentifier coexisting with type/UniqueIdentifier (different encodingType) per AsynchronousIndicator precedent
    "Unwrap Mode", "Usage Limits Unit", "Validation Authority Type",
    "Validation Type", "Validity Indicator", "Wrapping Method",
    # NOTE: "Item Type" intentionally omitted — the spec's Item Type Enumeration is
    # the TTLV item-type discriminator and is implemented as `org.purpleBean.kmip.api.EncodingType`
    # (an untagged Java enum). There is no separate KmipEnumeration class for it.
}

def _norm(name: str) -> str:
    return re.sub(r"\s+", " ", name.strip().lower())

IMPL_NORM: set = {_norm(e) for e in IMPLEMENTED_ENUMS}

IMPLEMENTED_OPS_V1_2 = {
    "Activate", "Add Attribute", "Archive", "Cancel", "Certify", "Check",
    "Create Key Pair", "Create", "Create Split Key", "Decrypt",
    "Delete Attribute", "Derive Key", "Destroy", "Discover Versions",
    "Encrypt", "Get Attribute List", "Get Attributes", "Get",
    "Get Usage Allocation", "Hash", "Join Split Key", "Locate", "MAC",
    "MAC Verify", "Modify Attribute", "Notify", "Obtain Lease",
    "Put", "Query", "Re-key Key Pair", "Re-key", "Re-certify", "Recover",
    "Register", "Revoke", "RNG Retrieve", "RNG Seed", "Sign",
    "Signature Verify", "Validate",
}

# Operations where only one direction (request XOR response) is implemented.
# Map: op_name → {"req": bool, "res": bool}
PARTIAL_OPS_V1_2: Dict[str, Dict[str, bool]] = {
    # (Poll response implemented 2026-06-21 — moved out of this map.)
}

# v1.4 / v2.0 / v2.1 / v3.0 operations whose request+response payloads are now
# present as stubs (registered against the operation in PAYLOAD_REGISTRY) but
# may carry empty `getValue()` / minimal field design pending OASIS-spec follow-up.
# These flip §6.2 rows from ❌ to ✅.
IMPLEMENTED_OPS_LATER = {
    "Import", "Export",
    "Log", "Login", "Logout", "Delegated Login", "Adjust Attribute",
    "Set Attribute", "Set Endpoint Role", "PKCS#11", "Interop", "Re-Provision",
    "Set Defaults", "Set Constraints", "Get Constraints",
    "Query Asynchronous Requests", "Process", "Ping",
    "Create Group", "Obliterate", "Create User", "Create Credential", "Deactivate",
    "Poll",  # response now implemented
}

IMPLEMENTED_STRUCTURES = {
    "Alternative Name", "Application Specific Information",
    "Attestation Credential", "Attribute", "Authentication", "Certificate",
    "Certificate Identifier", "Certificate Issuer", "Certificate Subject",
    "Common Template Attribute", "Credential", "Cryptographic Domain Parameters",
    "Cryptographic Parameters", "Custom Attribute", "Derivation Parameters",
    "Device Credential", "Digest", "Encryption Key Information",
    "Extension Information", "Key Block", "Key Material Structure",
    "Key Value Location", "Key Value Structure", "Key Wrapping Data",
    "Key Wrapping Specification", "Link", "MAC Signature Key Information",
    "Message Extension", "Name", "Nonce", "Opaque Object", "PGP Key",
    "Private Key", "Private Key Template Attribute", "Protocol Version",
    "Public Key", "Public Key Template Attribute", "Revocation Reason",
    "Secret Data", "Server Information", "Split Key", "Symmetric Key",
    "Template", "Template Attribute", "Transparent DH Private Key",
    "Transparent DH Public Key", "Transparent DSA Private Key",
    "Transparent DSA Public Key", "Transparent ECDH Private Key",
    "Transparent ECDH Public Key", "Transparent ECDSA Private Key",
    "Transparent ECDSA Public Key", "Transparent ECMQV Private Key",
    "Transparent ECMQV Public Key", "Transparent RSA Private Key",
    "Transparent RSA Public Key", "Transparent Symmetric Key",
    "Usage Limits", "Username And Password", "Vendor Extension",
    "X.509 Certificate Identifier", "X.509 Certificate Issuer",
    "X.509 Certificate Subject",
    "Transparent EC Private Key", "Transparent EC Public Key",
    # v3.0 structures added 2026-06-21 (stubs — minimal getValue, field-level
    # design pending OASIS-spec follow-up for polymorphic typed-link fields and
    # credential variants).
    "PasswordCredential", "OtpCredential", "HashedPasswordCredential",
    "DeactivationReason", "CredentialInformation",
    "CertificateLink", "ChildLink", "DerivationObjectLink", "DerivedObjectLink",
    "NextLink", "ParentLink", "Pkcs12CertificateLink", "Pkcs12PasswordLink",
    "PreviousLink", "PrivateKeyLink", "PublicKeyLink", "ReplacedObjectLink",
    "ReplacementObjectLink", "WrappingKeyLink",
}


# ---------------------------------------------------------------------------
# Markdown generation
# ---------------------------------------------------------------------------

def _ver_badges(versions: List[str]) -> str:
    return " ".join(f"`v{v}`" for v in versions)


def generate_markdown(all_data: List[SpecData], analysis: Dict) -> str:
    enum_master = analysis["enum_master"]
    tag_master = analysis["tag_master"]
    new_in = analysis["new_in"]
    removed_in = analysis["removed_in"]

    L: List[str] = []
    add = L.append

    add("# KMIP Pending Implementation — Exhaustive Reference\n")
    add(
        "_Auto-generated by `docs/kmip-spec/scrape_kmip_all_versions.py`. "
        "Re-run after adding spec HTML files or changing project source._\n"
    )
    add(f"**Versions covered**: {', '.join(ALL_VERSIONS)}\n")
    add("---\n")

    # ---- 1. Enumeration Status -----------------------------------------
    add("## 1. Enumeration Status\n")
    add("| Enumeration | Spec Versions | In Project |")
    add("|---|---|---|")
    for ename in sorted(enum_master.keys()):
        edata = enum_master[ename]
        vbadges = _ver_badges(edata["versions"])
        status = "✅" if _norm(ename) in IMPL_NORM else "❌ **MISSING**"
        add(f"| {ename} | {vbadges} | {status} |")
    add("")

    # ---- 2. Missing enumerations summary --------------------------------
    missing = [e for e in sorted(enum_master.keys()) if _norm(e) not in IMPL_NORM]
    add("## 2. Missing Enumeration Classes\n")
    if missing:
        add("These enumeration types defined in the KMIP spec have **no** Java class in `model/core/enumeration/`.\n")
        add("| Enumeration | Introduced | All Values |")
        add("|---|---|---|")
        for ename in missing:
            edata = enum_master[ename]
            intro = edata["versions"][0] if edata["versions"] else "?"
            vals = edata.get("values", {})
            items = sorted(vals.items(), key=lambda x: x[1].get("hex", "")) if isinstance(vals, dict) else []
            val_str = ", ".join(f"{n} (`{d['hex']}`)" for n, d in items)
            add(f"| **{ename}** | v{intro} | {val_str[:200]}{'…' if len(val_str) > 200 else ''} |")
    else:
        add("_All enumeration classes are implemented._\n")
    add("")

    # ---- 3. New enumerations per version --------------------------------
    add("## 3. New Enumerations Introduced Per Version\n")
    for ver in ALL_VERSIONS:
        new_enums = new_in[ver]["enumerations"]
        if not new_enums:
            continue
        add(f"### v{ver}\n")
        add("| Enumeration | Implemented? |")
        add("|---|---|")
        for ename in sorted(new_enums):
            impl = "✅" if _norm(ename) in IMPL_NORM else "❌"
            add(f"| {ename} | {impl} |")
        add("")

    # ---- 4. New / removed enum values per version -----------------------
    add("## 4. Enumeration Value Changes Per Version\n")
    for ver in ALL_VERSIONS:
        added_vals = new_in[ver]["enum_values"]
        removed_vals = removed_in[ver]["enum_values"]
        if not added_vals and not removed_vals:
            continue
        add(f"### v{ver}\n")
        if added_vals:
            add("**Added values:**\n")
            add("| Enumeration | Impl? | New Values |")
            add("|---|---|---|")
            for ename in sorted(added_vals.keys()):
                impl = "✅" if _norm(ename) in IMPL_NORM else "❌"
                add(f"| {ename} | {impl} | {', '.join(added_vals[ename])} |")
        if removed_vals:
            add("\n**Removed / reserved values:**\n")
            add("| Enumeration | Removed Values |")
            add("|---|---|")
            for ename in sorted(removed_vals.keys()):
                add(f"| {ename} | {', '.join(removed_vals[ename])} |")
        add("")

    # ---- 5. Tag changes per version -------------------------------------
    add("## 5. Tag Changes Per Version\n")
    for ver in ALL_VERSIONS:
        new_tags = [t for t in new_in[ver]["tags"] if not t.startswith("(Reserved")]
        rem_tags = [t for t in removed_in[ver]["tags"] if not t.startswith("(Reserved")]
        if not new_tags and not rem_tags:
            continue
        add(f"### v{ver}\n")
        if new_tags:
            add(f"**New tags ({len(new_tags)}):**\n")
            add("| Tag Name | Tag Hex |")
            add("|---|---|")
            for tname in sorted(new_tags):
                thex = tag_master.get(tname, {}).get("tag", "?")
                add(f"| {tname} | `{thex}` |")
        if rem_tags:
            add(f"\n**Tags made Reserved — breaking changes ({len(rem_tags)}):**\n")
            add("| Tag Name |")
            add("|---|")
            for tname in sorted(rem_tags):
                add(f"| {tname} |")
        add("")

    # ---- 6. Missing operation payloads ----------------------------------
    add("## 6. Missing Operation Request/Response Payloads\n")

    op_enum_data = enum_master.get("Operation", {})
    op_values = op_enum_data.get("values", {})

    if isinstance(op_values, dict):
        op_list = [(n, d) for n, d in op_values.items()]
    else:
        op_list = [(v["name"], {"hex": v["value"], "versions": []}) for v in op_values]

    # Partial v1.2 ops (one side missing)
    if PARTIAL_OPS_V1_2:
        add("### 6.1 Partially Implemented (v1.2 operations)\n")
        add("| Operation | Hex | Request | Response |")
        add("|---|---|---|---|")
        for op_name, sides in sorted(PARTIAL_OPS_V1_2.items()):
            req = "✅" if sides["req"] else "❌"
            res = "✅" if sides["res"] else "❌"
            hex_val_str = next(
                (d.get("hex", "?") for n, d in op_list if n == op_name), "?"
            )
            add(f"| {op_name} | `{hex_val_str}` | {req} | {res} |")
        add("")

    add("### 6.2 Fully Missing (no model classes yet)\n")
    add("| Operation | Hex | Introduced | Request | Response |")
    add("|---|---|---|---|---|")
    for op_name, odata in sorted(op_list, key=lambda x: x[1].get("hex", "")):
        versions = odata.get("versions", [])
        introduced = versions[0] if versions else "?"
        if op_name in IMPLEMENTED_OPS_V1_2 or op_name in PARTIAL_OPS_V1_2:
            continue
        if op_name in IMPLEMENTED_OPS_LATER:
            continue
        if introduced == "1.2":
            continue
        status = "❌"
        add(f"| {op_name} | `{odata['hex']}` | v{introduced} | {status} | {status} |")
    add("")
    if IMPLEMENTED_OPS_LATER:
        add("### 6.3 Stub Payloads (registered, field-level design pending)\n")
        add("Request and response payload classes exist and register against the operation in")
        add("`RequestPayloadStructure.PAYLOAD_REGISTRY` / `ResponsePayloadStructure.PAYLOAD_REGISTRY`,")
        add("but `getValue()` returns empty — actual field layout from OASIS spec still to be wired in.\n")
        add("| Operation | Hex | Introduced |")
        add("|---|---|---|")
        for op_name in sorted(IMPLEMENTED_OPS_LATER):
            odata = next((d for n, d in op_list if n == op_name), {})
            versions = odata.get("versions", [])
            introduced = versions[0] if versions else "?"
            add(f"| {op_name} | `{odata.get('hex', '?')}` | v{introduced} |")
        add("")

    # ---- 7. Missing and changed structures ------------------------------
    add("## 7. Missing and Changed Structures\n")

    add("### 7.1 Missing v3.0 Structures\n")
    add("| Structure | Tag | Description | Implemented? |")
    add("|---|---|---|---|")
    V3_MISSING = [
        ("PasswordCredential",      "0x4201A1", "Password credential type — fields: Password Salt, Password Salt Algorithm, Salted Password"),
        ("OtpCredential",           "0x4201A7", "One-Time Password credential — fields: OTP Algorithm/Digest/Serial/Seed/Interval/Digits/Counter"),
        ("HashedPasswordCredential","0x4201AF", "Hashed password credential — fields: Hashed Username Password, Hashed Password Username"),
        ("DeactivationReason",      "0x4201B8", "Deactivation structure — fields: Deactivation Message, Deactivation Reason Code"),
        ("CredentialInformation",   "0x4201B2", "Credential metadata aggregate structure"),
        ("CertificateLink",         "0x420190", "Typed link replacing generic Link"),
        ("ChildLink",               "0x420191", "Typed link replacing generic Link"),
        ("DerivationObjectLink",    "0x420192", "Typed link replacing generic Link"),
        ("DerivedObjectLink",       "0x420193", "Typed link replacing generic Link"),
        ("NextLink",                "0x420194", "Typed link replacing generic Link"),
        ("ParentLink",              "0x420195", "Typed link replacing generic Link"),
        ("Pkcs12CertificateLink",   "0x420196", "Typed link replacing generic Link"),
        ("Pkcs12PasswordLink",      "0x420197", "Typed link replacing generic Link"),
        ("PreviousLink",            "0x420198", "Typed link replacing generic Link"),
        ("PrivateKeyLink",          "0x420199", "Typed link replacing generic Link"),
        ("PublicKeyLink",           "0x42019A", "Typed link replacing generic Link"),
        ("ReplacedObjectLink",      "0x42019B", "Typed link replacing generic Link"),
        ("ReplacementObjectLink",   "0x42019C", "Typed link replacing generic Link"),
        ("WrappingKeyLink",         "0x42019D", "Typed link replacing generic Link"),
    ]
    for name, tags, desc in V3_MISSING:
        impl = "✅" if name in IMPLEMENTED_STRUCTURES else "❌"
        add(f"| {name} | `{tags}` | {desc} | {impl} |")
    add("")

    add("### 7.2 Structural Breaking Changes by Version\n")
    add("| Object | Version | Change |")
    add("|---|---|---|")
    STRUCTURAL_CHANGES = [
        ("Custom Attribute (42002D)",  "v2.0",
         "Tag became **Reserved**. Replaced by Vendor Extension. `CustomAttribute.java` must not emit for v2.0+."),
        ("Attribute Index (420009)",   "v2.0",
         "Tag became **Reserved**. Was an integer attribute qualifier in v1.x."),
        ("Common Template-Attribute (42001F)", "v2.0",
         "Tag became **Reserved**. Replaced by Common Attributes (420126)."),
        ("Link (42004A/B/C)",          "v3.0",
         "Generic `Link` + `Link Type` + `Linked Object Identifier` tags all **Reserved**. "
         "Replaced by 14 typed link structures at 0x420190–0x42019D."),
        ("Name sub-structure",         "v3.0",
         "`Name Type` (420054) and `Name Value` (420055) tags **Reserved**. "
         "Name (420053) carries the string value directly."),
        ("Batch Count (42000D)",       "v3.0",
         "Tag **Reserved**. Must not appear in v3.0 Request Headers."),
        ("Batch Order Option (420010)","v3.0",
         "Tag **Reserved**. Must not appear in v3.0 Request Headers."),
        ("Unique Batch Item ID (420093)","v3.0",
         "Tag **Reserved**. Must not appear in v3.0 Batch Items."),
        ("Object Group (420056)",      "v3.0",
         "Tag **Reserved**. Replaced by Object Groups aggregate (420166)."),
        ("Template ObjectType (0x6)",  "v3.0",
         "ObjectType value `Template` is **Reserved** (removed) in v3.0."),
        ("Credential structure",       "v2.0→v3.0",
         "New credential value types per version: v2.0 adds OneTimePassword/HashedPassword/Ticket; "
         "v3.0 adds Password/Certificate — requires new credential value structure classes."),
        ("Certificate Subject / Issuer","v2.0",
         "Old CertificateSubject (42001A) and CertificateIssuer (420015) tags became **Reserved**. "
         "Replaced by CertificateSubject CN/O/OU/… and Issuer CN/O/OU/… sub-tags (0x420108–0x42011F)."),
    ]
    for name, ver, change in STRUCTURAL_CHANGES:
        add(f"| **{name}** | {ver} | {change} |")
    add("")

    # ---- 8. Version tagging gaps ----------------------------------------
    add("## 8. Version Tagging Notes (V1_3, V1_4, V2_0)\n")
    add(
        "No enumeration value in the project references `KmipSpec.V1_3`, "
        "`KmipSpec.V1_4`, or `KmipSpec.V2_0`. The diff tables below "
        "(from spec parsing) show what changed in these versions.\n"
    )
    for ver in ["1.3", "1.4", "2.0"]:
        prev_idx = ALL_VERSIONS.index(ver) - 1
        prev_ver = ALL_VERSIONS[prev_idx]
        add(f"### v{ver} — changes relative to v{prev_ver}\n")
        added_enums = new_in[ver]["enumerations"]
        added_vals  = new_in[ver]["enum_values"]
        removed_vals = removed_in[ver]["enum_values"]
        new_tags = [t for t in new_in[ver]["tags"] if not t.startswith("(Reserved")]
        rem_tags = [t for t in removed_in[ver]["tags"] if not t.startswith("(Reserved")]
        if added_enums:
            add(f"**New enumerations**: {', '.join(sorted(added_enums))}\n")
        if added_vals:
            add("**New enumeration values:**\n")
            add("| Enumeration | New Values |")
            add("|---|---|")
            for ename in sorted(added_vals.keys()):
                impl = "✅" if _norm(ename) in IMPL_NORM else "❌ not impl"
                add(f"| {ename} ({impl}) | {', '.join(added_vals[ename])} |")
            add("")
        if removed_vals:
            add("**Removed enumeration values:**\n")
            add("| Enumeration | Removed Values |")
            add("|---|---|")
            for ename in sorted(removed_vals.keys()):
                add(f"| {ename} | {', '.join(removed_vals[ename])} |")
            add("")
        if new_tags:
            add(f"**New tags**: {len(new_tags)} — see Section 5.\n")
        if rem_tags:
            add(f"**Tags made Reserved**: {', '.join(sorted(rem_tags))}\n")
        if not (added_enums or added_vals or removed_vals or new_tags or rem_tags):
            add("_No differences detected from spec parsing for this version._\n")

    # ---- 9. Full enumeration reference ----------------------------------
    add("## 9. Full Enumeration Reference (All Versions)\n")
    add(
        "Complete value tables for every enumeration parsed across all versions. "
        "⚠️ on a value means it was not present in the final version (`v3.0`).\n"
    )

    for ename in sorted(enum_master.keys()):
        edata = enum_master[ename]
        impl = "✅" if _norm(ename) in IMPL_NORM else "❌ **NOT IMPLEMENTED**"
        add(f"### {ename}  {impl}  —  {_ver_badges(edata['versions'])}\n")
        add("| Value Name | Hex | First Seen | Last Seen |")
        add("|---|---|---|---|")
        vals = edata.get("values", {})
        items = sorted(vals.items(), key=lambda x: x[1].get("hex", "")) if isinstance(vals, dict) else []
        for vname, vdata in items:
            vlist = vdata.get("versions", [])
            first = vlist[0] if vlist else "?"
            last  = vlist[-1] if vlist else "?"
            gone  = " ⚠️" if vlist and vlist[-1] != ALL_VERSIONS[-1] else ""
            add(f"| {vname} | `{vdata['hex']}` | v{first} | v{last}{gone} |")
        add("")

    add("---\n")
    add("_End of document._\n")
    return "\n".join(L)


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    print("KMIP spec scraper starting…\n")
    all_data: List[SpecData] = []
    for cfg in SPECS:
        sd = parse_spec(cfg)
        all_data.append(sd)
        print(f"    v{sd.version}: {len(sd.enumerations)} enumerations, {len(sd.tags)} tags")

    print("\nAnalysing cross-version diffs…")
    analysis = analyse_all(all_data)

    print(f"Saving JSON to {OUT_JSON}")
    with open(OUT_JSON, "w", encoding="utf-8") as f:
        json.dump({"versions": [sd.to_dict() for sd in all_data],
                   "new_in": analysis["new_in"],
                   "removed_in": analysis["removed_in"]},
                  f, indent=2, default=str)

    print(f"Writing markdown to {OUT_MD} …")
    md = generate_markdown(all_data, analysis)
    OUT_MD.write_text(md, encoding="utf-8")

    n_missing = sum(1 for e in analysis["enum_master"] if _norm(e) not in IMPL_NORM)
    total = len(analysis["enum_master"])
    tag_total = len(analysis["tag_master"])
    print(f"\nDone.")
    print(f"  Enumerations parsed : {total}  (missing in project: {n_missing})")
    print(f"  Tags parsed         : {tag_total}")
    print(f"  Output              : {OUT_MD}")


if __name__ == "__main__":
    main()
