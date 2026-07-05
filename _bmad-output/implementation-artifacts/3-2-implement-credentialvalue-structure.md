# Story 3.2: Implement CredentialValue Structure

## 1. Story Requirements

**As a library consumer,**
I want `CredentialValue` implemented for all KMIP credential subtypes (UsernameAndPassword, Device, Attestation, HashedPassword, OTP, Ticket, Certificate, Password),
So that I can model the credential payload used in CreateCredential operations.

### 1.1. Acceptance Criteria

**Given** a `CredentialValue` of each supported `CredentialType`
**When** serialized to TTLV, JSON, and XML with the appropriate `KmipSpec` context
**Then** the credential-type-specific fields render correctly per spec

**Given** the serialized output
**When** deserialized with the same `CredentialType` context
**Then** the correct value type is restored and round-trip is lossless

**Given** existing credential sub-types (HashedPasswordCredential, OtpCredential, PasswordCredential from commit `2e639ce8`) are mapped to `CredentialValue`
**When** the dispatch model is designed
**Then** no new structure duplication occurs — existing types are reused

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the `CredentialValue` structure.
- This structure will act as a wrapper for different credential types.
- Reuse existing credential-related classes where possible.

### 2.2. Architecture Compliance

- Create a new class in `src/main/java/org/purpleBean/kmip/model/core/structure/`.
- Implement `KmipStructure`.
- Design a dispatch model to handle the different `CredentialType` values.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/structure/CredentialValue.java`
- Corresponding test file.
- Update `META-INF/services` for all codecs.

### 2.5. Testing Requirements

- Create a full round-trip serialization test suite for `CredentialValue`.
- The test suite must cover all supported `CredentialType` values.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
