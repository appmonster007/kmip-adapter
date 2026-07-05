# Story 3.7: Wire Capability Structures into Query Response Payload

## 1. Story Requirements

**As a library consumer,**
I want the existing `QueryOpResponsePayload` updated to include all capability and profile structures from Stories 3.4a, 3.4b, 3.5, and 3.6,
So that I can parse complete KMIP v2.1+ Query responses with full server capability information.

**Depends on:** Stories 3.4, 3.5, 3.6

### 1.1. Acceptance Criteria

**Given** a full `QueryOpResponsePayload` with all optional capability fields populated (`CapabilityInformation`, `ProfileInformation`, `ClusterInfo`, `ValidationInformation`, `RandomNumberGenerator`, `ProtectionStorageMasks`)
**When** serialized to TTLV, JSON, and XML
**Then** all fields appear in spec-defined order (KMIP v2.1 §6.26)

**Given** the serialized output
**When** deserialized
**Then** all nested capability structures are fully reconstructed and the result equals the original

**Given** a minimal `QueryOpResponsePayload` with only required fields
**When** serialized
**Then** optional capability fields are absent from the output (not serialized as null/empty)

## 2. Developer Context

### 2.1. Technical Requirements

- Update the `QueryOpResponsePayload` to include the new capability and profile structures.

### 2.2. Architecture Compliance

- The class to be modified is an existing stub.
- The implementation should follow the existing patterns for operation payloads.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- **File to modify:**
    - `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/QueryOpResponsePayload.java`
- **Test to update:**
    - The corresponding test class for the above payload.

### 2.5. Testing Requirements

- Update the existing skeletal test suite for the payload.
- Fill in concrete test data, including a fully populated instance with all capability structures.
- Ensure all TTLV, JSON, and XML serialization tests pass.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
