# Story 3.4a: Implement Capability Sub-Structures

## 1. Story Requirements

**As a library consumer,**
I want `AttestationCapability`, `AsynchronousCapability`, `BatchContinueCapability`, `BatchUndoCapability`, and `QuantumSafeCapability` implemented with TTLV/JSON/XML codecs,
So that the building blocks for `CapabilityInformation` are available for composing full server capability responses.

### 1.1. Acceptance Criteria

**Given** each of the five capability sub-structures is implemented with its spec-defined fields (consult KMIP v2.1 spec for each structure's field list before implementing)
**When** each is serialized to TTLV, JSON, and XML and deserialized
**Then** round-trip is lossless for each sub-structure independently

**Given** `AttestationCapability.isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (all five are V2_1+ only)

**Given** all five implementations are complete and `META-INF/services` is audited
**Then** all five classes appear in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the following five capability sub-structures:
    - `AttestationCapability`
    - `AsynchronousCapability`
    - `BatchContinueCapability`
    - `BatchUndoCapability`
    - `QuantumSafeCapability`
- All are v2.1+.

### 2.2. Architecture Compliance

- Create new classes in `src/main/java/org/purpleBean/kmip/model/core/structure/`.
- All classes must implement `KmipStructure`.
- Ensure immutability.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- Create new Java files for each of the five structures.
- Create corresponding test files.
- Update `META-INF/services` for all codecs.

### 2.5. Testing Requirements

- Create full round-trip serialization test suites for each of the five structures.
- Tests must cover TTLV, JSON, and XML.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
