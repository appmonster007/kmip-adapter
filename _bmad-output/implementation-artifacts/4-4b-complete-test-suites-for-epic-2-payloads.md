# Story 4.4b: Complete Test Suites for Epic 2 Payloads

## 1. Story Requirements

**As a developer,**
I want full `KmipSerializationTestSuite` test suites for all payloads implemented in Epic 2 (GetConstraints, SetConstraints, SetDefaults, QueryAsyncRequests request+response, Poll, Pkcs11 request+response, Import, Export request+response, Process request+response — 11 payloads),
So that Epic 2 payload implementations are verified correct and regressions are detected.

### 1.1. Acceptance Criteria

**Given** each of the 11 Epic 2 payloads now has a full implementation
**When** its test suite is upgraded with concrete test data (minimum: one minimal instance, one fully-populated instance per payload)
**Then** TTLV, JSON, and XML serialization tests all pass for every payload

**Given** any Epic 2 test still has `// TODO validateComponents` annotations
**When** this story is complete
**Then** all such annotations are resolved

**Given** Epic 2 test suites run
**When** JaCoCo coverage is measured for Epic 2 classes
**Then** line coverage is ≥90% for all Epic 2 payload classes

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the test suites for the Epic 2 payloads.

The `generate.sh` script's test generation capabilities will be used to create or update the test suites for the following operations:
-   GetConstraints
-   SetConstraints
-   SetDefaults
-   QueryAsyncRequests
-   Poll
-   Pkcs11
-   Import
-   Export
-   Process

For each operation, the script will be run with the appropriate parameters to generate a comprehensive `KmipSerializationTestSuite`. For example:

```bash
./scripts/generators/generate.sh structure --name GetConstraints --version <version> --with-tests
```

This will ensure that:
-   Each payload has a full test suite with concrete test data.
-   All `// TODO` annotations are resolved.
-   The line coverage for all Epic 2 payload classes meets the ≥90% requirement.
