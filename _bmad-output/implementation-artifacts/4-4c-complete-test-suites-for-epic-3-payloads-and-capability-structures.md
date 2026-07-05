# Story 4.4c: Complete Test Suites for Epic 3 Payloads and Capability Structures

## 1. Story Requirements

**As a developer,**
I want full `KmipSerializationTestSuite` test suites for all payloads and structures implemented in Epic 3 (CreateUser, CreateGroup, CreateCredential — 3 payloads; CapabilityInformation + sub-structures, ProfileInformation, ClusterInfo, ValidationInformation, RngParameters, RandomNumberGenerator, ProtectionStorageMasks — capability structures),
So that Epic 3 implementations are verified correct and the full Query response round-trip is validated end-to-end.

### 1.1. Acceptance Criteria

**Given** each of the 3 Epic 3 payloads and all Epic 3 capability structures now have full implementations
**When** test suites are upgraded with concrete test data
**Then** TTLV, JSON, and XML serialization tests all pass for every class

**Given** the full `QueryOpResponsePayload` (Story 3.7) with all capability fields populated
**When** serialized and deserialized across all three codecs
**Then** end-to-end round-trip is lossless — this is the integration-level acceptance test for Epic 3

**Given** all Epic 3 test suites run
**When** JaCoCo coverage is measured
**Then** line coverage is ≥90% across all Epic 3 classes

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the test suites for the Epic 3 payloads and capability structures.

The `generate.sh` script's test generation capabilities will be used to create or update the test suites for the following:
-   **Payloads:** CreateUser, CreateGroup, CreateCredential
-   **Capability Structures:** CapabilityInformation and its sub-structures, ProfileInformation, ClusterInfo, ValidationInformation, RngParameters, RandomNumberGenerator, ProtectionStorageMasks

For each item, the script will be run with the appropriate parameters to generate a comprehensive `KmipSerializationTestSuite`. For example:

```bash
./scripts/generators/generate.sh structure --name CreateUser --version <version> --with-tests
./scripts/generators/generate.sh structure --name CapabilityInformation --version <version> --with-tests
```

This will ensure that:
-   Each payload and structure has a full test suite with concrete test data.
-   The integration-level test for `QueryOpResponsePayload` is created and passes.
-   The line coverage for all Epic 3 classes meets the ≥90% requirement.
