# Story 4.4a: Complete Test Suites for Epic 1 Payloads

## 1. Story Requirements

**As a developer,**
I want full `KmipSerializationTestSuite` test suites for all payloads implemented in Epic 1 (Login, Logout, DelegatedLogin, SetAttribute, AdjustAttribute — 7 payloads),
So that Epic 1 payload implementations are verified correct and regressions are detected.

### 1.1. Acceptance Criteria

**Given** each of the 7 Epic 1 payloads now has a full implementation
**When** its test suite is upgraded with concrete test data (minimum: one minimal instance, one fully-populated instance per payload)
**Then** TTLV, JSON, and XML serialization tests all pass for every payload

**Given** any Epic 1 test still has `// TODO validateComponents` annotations
**When** this story is complete
**Then** all such annotations are resolved — either with actual validation logic or with a justified removal

**Given** Epic 1 test suites run
**When** JaCoCo coverage is measured for Epic 1 classes
**Then** line coverage is ≥90% for all Epic 1 payload classes

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the test suites for the Epic 1 payloads.

The `generate.sh` script's test generation capabilities will be used to create or update the test suites for the following operations:
-   Login
-   Logout
-   DelegatedLogin
-   SetAttribute
-   AdjustAttribute

For each operation, the script will be run with the appropriate parameters to generate a comprehensive `KmipSerializationTestSuite`. For example:

```bash
./scripts/generators/generate.sh structure --name Login --version <version> --with-tests
```

This will ensure that:
-   Each payload has a full test suite with concrete test data.
-   All `// TODO` annotations are resolved.
-   The line coverage for all Epic 1 payload classes meets the ≥90% requirement.
