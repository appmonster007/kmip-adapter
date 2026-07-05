# Story 2.11: Clarify and Implement Process Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `ProcessOpRequestPayload` and `ProcessOpResponsePayload` implemented per KMIP v2.1 §6.33,
So that I can model KMIP cryptographic processing operations.

### 1.1. Acceptance Criteria

**Given** the KMIP v2.1 §6.33 spec is consulted using the `kmip-architect` agent
**When** the field list for Process request/response is confirmed and documented
**Then** an implementation design is reviewed before any code is written

**Given** the design is confirmed
**When** both payloads are implemented
**Then** `getValue()` returns the correct field arrays and all three codec round-trips pass

**Given** the skeletal test suites
**When** test data is filled in
**Then** all three codec tests pass

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the `Process` operation payloads.

Run the following command to generate the `Process` operation components:

```bash
./scripts/generators/generate.sh structure --name Process --version 2.1
```

This command will create or update the `ProcessOpRequestPayload` and `ProcessOpResponsePayload` classes, ensuring they are implemented according to the KMIP v2.1 specification and meet all acceptance criteria.
