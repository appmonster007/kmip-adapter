# Story 4.2: Audit and Fix All Enumeration Description Strings for XML/JSON Deserialization

## 1. Story Requirements

**As a library consumer,**
I want every KMIP enumeration description string across all enumeration classes audited against the OASIS spec text and corrected where mismatches exist,
So that deserialization from any spec-compliant XML/JSON document succeeds without case, spacing, or punctuation mismatches — for every enumeration in the library.

### 1.1. Acceptance Criteria

**Given** the full set of enumeration classes in `model/core/enumeration/` (all ~50 enumeration types across v1.2–v3.0)
**When** each value's description string is systematically compared against the OASIS KMIP specification text using `docs/kmip-spec/chunks/enumerations/` as the authoritative source
**Then** a complete change list is produced showing every mismatch (enumeration class, value name, current string, spec string) before any edits are applied

**Given** the change list is reviewed and approved
**When** corrections are applied to all mismatching description strings
**Then** every description string exactly matches the corresponding spec text (same casing, spacing, punctuation, and abbreviation)

**Given** a spec-compliant JSON document containing enum values as their spec-defined string labels
**When** deserialized using the Jackson JSON codec for every enumeration type
**Then** the correct `Value` instance is returned with no `UnknownValue` fallbacks

**Given** the same documents in XML format
**When** deserialized
**Then** the correct `Value` instance is returned for every enumeration type

**Given** all corrections are applied
**When** the full test suite runs
**Then** no test regressions are introduced and any previously-failing deserialization round-trips now pass

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to audit and correct all enumeration description strings.

The `comprehensive_enum_sync.py` script, which is part of the `bmad-generate-kmip-code` skill, will be used to perform this task.

Run the following command to synchronize the enumeration files with the specification data:

```bash
python3 scripts/comprehensive_enum_sync.py --csv <path_to_spec_csv> --write
```

This command will:
1.  Compare the description strings in all enumeration classes against the authoritative specification data.
2.  Generate a change list of all mismatches.
3.  Apply the corrections to the enumeration classes.

This will ensure that all enumeration description strings are spec-compliant and that XML/JSON deserialization works correctly, fulfilling all acceptance criteria.
