# Story 2.9: Audit ManagedObject Hierarchy and Implement KeyWrappingData Structure

## 1. Story Requirements

**As a library consumer,**
I want a clear audit of how `ManagedObject` and its subtypes are currently implemented in the codebase, existing gaps identified against the KMIP spec, and `KeyWrappingData` (structure, v1.2+) implemented,
So that I can model managed cryptographic objects and their wrapping metadata correctly in Import/Export operations.

### 1.1. Acceptance Criteria

**Given** the existing codebase is audited for any existing `ManagedObject` abstract type and all concrete subtypes (SymmetricKey, PrivateKey, PublicKey, Certificate, SecretData, OpaqueObject, SplitKey)
**When** the audit is complete
**Then** a clear inventory exists: which subtypes exist, which are partial, which are missing, and what the current virtual dispatch mechanism is (if any)

**Given** the audit results and the KMIP spec §2.2 definition of each ManagedObject subtype
**When** gaps are identified between spec and implementation
**Then** only the subtypes strictly required by `ImportOpRequestPayload` and `ExportOpResponsePayload` are implemented or completed in this story; remaining gaps are documented as follow-on work

**Given** the implementation follows the existing virtual type pattern discovered in the audit (do not invent a new pattern)
**When** a concrete `ManagedObject` subtype is serialized to all three codecs
**Then** the type-discriminating tag renders correctly per KMIP spec §2.2 and round-trip deserialization restores the correct subtype

**Given** a `KeyWrappingData` instance with `WrappingMethod` (required) and optional fields (EncryptionKeyInformation, MACSignatureKeyInformation, MACSignature, IVCounterNonce, EncodingOption)
**When** serialized to TTLV, JSON, and XML
**Then** all fields appear in spec-defined order (KMIP spec §2.1.5)
**And** deserialization produces an equal instance

**Given** all implementations are complete and `META-INF/services` is audited
**Then** every new or modified class appears in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- Audit the `ManagedObject` hierarchy.
- Implement the `KeyWrappingData` structure (v1.2+).

### 2.2. Architecture Compliance

- Follow existing patterns for `ManagedObject` subtypes.
- `KeyWrappingData` should be a new structure in `src/main/java/org/purpleBean/kmip/model/core/structure/`.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/structure/KeyWrappingData.java`
- Corresponding test file.
- Update `META-INF/services` for all codecs.
- Potentially modify existing `ManagedObject` subtype classes.

### 2.5. Testing Requirements

- Create a full round-trip serialization test suite for `KeyWrappingData`.
- Update tests for any modified `ManagedObject` subtypes.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
