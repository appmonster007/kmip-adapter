---
name: bmad-generate-kmip-code
description: "Generates and manages KMIP data types, enums, and structures using the project's generator and audit scripts."
---

# KMIP Code Generation and Management

**Goal:** Generate, manage, and audit KMIP data types, enumerations, and structures using the project's specialized scripts.

**Your Role:** KMIP code generation and management assistant.

## Execution

This skill leverages the scripts in the `scripts` directory to perform code generation and maintenance tasks.

### 1. Code Generation (`generate.sh`)

The `scripts/generators/generate.sh` script is used to scaffold new KMIP entities.

**Usage:**

`./scripts/generators/generate.sh <entity_type> [options] <Name>`

**Entity Types:**

*   **`enum`**: Generate a KMIP enumeration.
*   **`datatype`**: Generate a KMIP data type.
*   **`structure`**: Generate a KMIP structure.

For detailed options for each entity type, use the `--help` flag:

`./scripts/generators/generate.sh <entity_type> --help`

### 2. Specification Chunking (`chunk_kmip_spec.py`)

The `scripts/chunk_kmip_spec.py` script chunks the large KMIP specification files into smaller, more manageable pieces.

**Usage:**

`python3 scripts/chunk_kmip_spec.py`

### 3. Enumeration Synchronization (`comprehensive_enum_sync.py`)

The `scripts/comprehensive_enum_sync.py` script synchronizes the KMIP enumeration files with the specification data from a CSV file.

**Usage:**

`python3 scripts/comprehensive_enum_sync.py --csv <path_to_csv> --write`

### 4. Version Auditing (`audit_supported_versions.py`)

The `scripts/audit_supported_versions.py` script audits the `supportedVersions` set in each KMIP enumeration class against the specification data.

**Usage:**

`python3 scripts/audit_supported_versions.py`
