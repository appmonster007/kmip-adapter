# Story 4.3: Audit supportedVersions for All Version-Removed Enum Values

## 1. Story Requirements

**As a library consumer,**
I want all enumeration values removed or reserved in later KMIP versions to correctly return `false` from `isSupportedFor()` for those versions,
So that the library refuses to use deprecated values in wrong version contexts.

### 1.1. Acceptance Criteria

**Given** the complete list of version-removed values from `docs/kmip-pending-implementation.md` §4 (e.g., `Undo` removed from `BatchErrorContinuationOption` in v2.0, `Canceled` removed from `CancellationResult` in v2.0, `Template` removed from `ObjectType` in v2.0, etc.)
**When** `isSupportedFor()` is called for the removing version on each affected value
**Then** it returns `false`

**Given** a value re-added in a later version (e.g., `PGP` in `CertificateType`: removed v2.0, re-added v2.1)
**When** `isSupportedFor()` is called for v1.4, v2.0, and v2.1
**Then** it returns `true`, `false`, `true` respectively

**Given** the audit is complete
**When** all existing tests run
**Then** no regressions are introduced

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to audit and correct the `supportedVersions` for all enumeration values.

The `audit_supported_versions.py` script, which is part of the `bmad-generate-kmip-code` skill, will be used to perform this task.

Run the following command to audit the `supportedVersions` of all enumeration classes:

```bash
python3 scripts/audit_supported_versions.py
```

This command will:
1.  Audit the `supportedVersions` set in each KMIP enumeration class against the specification data.
2.  Identify and report any discrepancies, including values that were removed or re-added in later KMIP versions.
3.  Apply the necessary corrections to the `supportedVersions` field in the enumeration classes.

This will ensure that the `isSupportedFor()` method behaves correctly for all enumeration values across all KMIP versions, fulfilling all acceptance criteria.
