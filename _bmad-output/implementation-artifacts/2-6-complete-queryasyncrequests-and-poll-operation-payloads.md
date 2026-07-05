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

## 2. Developer Context

### 2.1. Technical Requirements

- Complete the implementation of the `QueryAsynchronousRequestsOpRequestPayload`, `QueryAsynchronousRequestsOpResponsePayload`, and `PollOpResponsePayload` classes.
- These payloads depend on the `AsynchronousCorrelationValue` type from story 2.5.

### 2.2. Architecture Compliance

- The classes to be modified are existing stubs.
- The implementation should follow the existing patterns for operation payloads.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- **Files to modify:**
    - `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/QueryAsynchronousRequestsOpRequestPayload.java`
    - `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/QueryAsynchronousRequestsOpResponsePayload.java`
    - `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/PollOpResponsePayload.java`
- **Tests to update:**
    - The corresponding test classes for the above payloads.

### 2.5. Testing Requirements

- Update the existing skeletal test suites for all three payloads.
- Fill in concrete test data.
- Ensure all TTLV, JSON, and XML serialization tests pass.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
