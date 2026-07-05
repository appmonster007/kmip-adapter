# Story 2.6: Complete QueryAsyncRequests and Poll Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `QueryAsynchronousRequestsOpRequestPayload`, `QueryAsynchronousRequestsOpResponsePayload`, and `PollOpResponsePayload` fully implemented,
So that I can model KMIP asynchronous operation tracking flows.

**Depends on:** Story 2.5 (`AsynchronousCorrelationValue`)

### 1.1. Acceptance Criteria

**Given** `QueryAsynchronousRequestsOpRequestPayload` with one or more `AsynchronousCorrelationValue` fields
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.31

**Given** `QueryAsynchronousRequestsOpResponsePayload` with response fields
**When** serialized to all three codecs and deserialized
**Then** all correlation values are preserved in order (round-trip lossless)

**Given** `PollOpResponsePayload` with its response fields
**When** serialized and deserialized
**Then** round-trip is lossless

**Given** all three payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass for each payload

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the operation payloads.

### QueryAsynchronousRequests Operation

Run the following command to generate the `QueryAsynchronousRequests` operation components:

```bash
./scripts/generators/generate.sh structure --name QueryAsynchronousRequests --version 2.1
```

### Poll Operation

Run the following command to generate the `Poll` operation components:

```bash
./scripts/generators/generate.sh structure --name Poll --version 2.1
```

These commands will create or update the necessary request and response payload classes, ensuring all acceptance criteria are met.
