# Story 3.6: Implement RngParameters, RandomNumberGenerator, and ProtectionStorageMasks Structures

## 1. Story Requirements

**As a library consumer,**
I want `RngParameters` (tag `0x4200D0`), `RandomNumberGenerator` (tag `0x4200D3`), and `ProtectionStorageMasks` (tag `0x420146`) implemented,
So that I can model and parse RNG capability and storage protection data in Query responses and object attributes.

### 1.1. Acceptance Criteria

**Given** an `RngParameters` instance with `RngAlgorithm`, `RngMode`, and optional fields
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `RandomNumberGenerator` wrapping one or more `RngParameters` children
**When** serialized to all three codecs
**Then** nesting is correct and round-trip is lossless

**Given** a `ProtectionStorageMasks` instance
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** all implementations are complete
**When** `META-INF/services` is audited
**Then** all new classes appear in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the following structures:
    - `RngParameters` (tag `0x4200D0`)
    - `RandomNumberGenerator` (tag `0x4200D3`)
    - `ProtectionStorageMasks` (tag `0x420146`)

### 2.2. Architecture Compliance

- Create new classes in `src/main/java/org/purpleBean/kmip/model/core/structure/`.
- All classes must implement `KmipStructure`.
- Ensure immutability.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- Create new Java files for each of the three structures.
- Create corresponding test files.
- Update `META-INF/services` for all codecs.

### 2.5. Testing Requirements

- Create full round-trip serialization test suites for each of the three structures.
- Tests must cover TTLV, JSON, and XML.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
