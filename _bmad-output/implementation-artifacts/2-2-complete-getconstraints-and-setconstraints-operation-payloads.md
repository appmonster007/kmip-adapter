# Story 2.2: Complete GetConstraints and SetConstraints Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `GetConstraintsOpResponsePayload` and `SetConstraintsOpRequestPayload` fully implemented,
So that I can model KMIP object constraint management operations.

**Depends on:** Story 2.1 (`Constraints`)

### 1.1. Acceptance Criteria

**Given** `GetConstraintsOpResponsePayload` with a `UniqueIdentifier` and `Constraints` field
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.27

**Given** `SetConstraintsOpRequestPayload` with `UniqueIdentifier` and `Constraints`
**When** serialized to all three codecs and deserialized
**Then** all fields are preserved in round-trip

**Given** both payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** TTLV, JSON, and XML tests all pass

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the operation payloads.

### GetConstraints Operation

Run the following command to generate the `GetConstraints` operation components:

```bash
./scripts/generators/generate.sh structure --name GetConstraints --version 2.1
```

This will create or update the necessary request and response payload classes.

### SetConstraints Operation

Run the following command to generate the `SetConstraints` operation components:

```bash
./scripts/generators/generate.sh structure --name SetConstraints --version 2.1
```

This will create or update the necessary request and response payload classes.

These commands will ensure that the `GetConstraintsOpResponsePayload` and `SetConstraintsOpRequestPayload` are correctly implemented and all acceptance criteria are met.
