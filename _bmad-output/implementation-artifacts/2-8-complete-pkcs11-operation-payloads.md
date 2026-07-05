# Story 2.8: Complete Pkcs11 Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `Pkcs11OpRequestPayload` and `Pkcs11OpResponsePayload` fully implemented,
So that I can model KMIP PKCS#11 passthrough operations.

**Depends on:** Story 2.7 (`Pkcs11Interface`)

### 1.1. Acceptance Criteria

**Given** `Pkcs11OpRequestPayload` constructed with a `Pkcs11Interface` field
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.32

**Given** `Pkcs11OpResponsePayload` with its response fields including `Pkcs11Interface`
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** both payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass

## 2. Developer Context

### 2.1. Technical Requirements

- Complete the implementation of the `Pkcs11OpRequestPayload` and `Pkcs11OpResponsePayload` classes.
- These payloads depend on the `Pkcs11Interface` structure from story 2.7.

### 2.2. Architecture Compliance

- The classes to be modified are existing stubs.
- The implementation should follow the existing patterns for operation payloads.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- **Files to modify:**
    - `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/Pkcs11OpRequestPayload.java`
    - `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/Pkcs11OpResponsePayload.java`
- **Tests to update:**
    - The corresponding test classes for the above payloads.

### 2.5. Testing Requirements

- Update the existing skeletal test suites for both payloads.
- Fill in concrete test data.
- Ensure all TTLV, JSON, and XML serialization tests pass.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
