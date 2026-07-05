# Story 2.10: Complete Import and Export Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `ImportOpRequestPayload`, `ExportOpRequestPayload`, and `ExportOpResponsePayload` fully implemented,
So that I can model KMIP managed object import and export flows.

**Depends on:** Story 2.9 (`ManagedObject`, `KeyWrappingData`)

### 1.1. Acceptance Criteria

**Given** `ImportOpRequestPayload` with `UniqueIdentifier`, `ObjectType`, optional `ReplaceExisting`, optional `Attributes`, and a `ManagedObject`
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v1.4 §6.20

**Given** `ExportOpResponsePayload` with a `ManagedObject` and optional `KeyWrappingData`
**When** serialized to all three codecs and deserialized
**Then** the correct `ManagedObject` subtype is restored and round-trip is lossless

**Given** `ExportOpRequestPayload` fields confirmed from KMIP v1.4 §6.21
**When** `getValue()` is implemented
**Then** it returns the correct field array

**Given** all three payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass for each payload

## 2. Developer Context

### 2.1. Technical Requirements

- Complete the implementation of the `ImportOpRequestPayload`, `ExportOpRequestPayload`, and `ExportOpResponsePayload` classes.
- These payloads depend on the `ManagedObject` hierarchy and `KeyWrappingData` structure from story 2.9.

### 2.2. Architecture Compliance

- The classes to be modified are existing stubs.
- The implementation should follow the existing patterns for operation payloads.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- **Files to modify:**
    - `src/main/java/org/purpleBean/kmip/model/v1_4/structure/request/payload/ImportOpRequestPayload.java`
    - `src/main/java/org/purpleBean/kmip/model/v1_4/structure/request/payload/ExportOpRequestPayload.java`
    - `src/main/java/org/purpleBean/kmip/model/v1_4/structure/response/payload/ExportOpResponsePayload.java`
- **Tests to update:**
    - The corresponding test classes for the above payloads.

### 2.5. Testing Requirements

- Update the existing skeletal test suites for all three payloads.
- Fill in concrete test data.
- Ensure all TTLV, JSON, and XML serialization tests pass.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
