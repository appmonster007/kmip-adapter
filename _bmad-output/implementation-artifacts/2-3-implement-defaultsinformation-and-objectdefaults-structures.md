# Story 2.3: Implement DefaultsInformation and ObjectDefaults Structures

## 1. Story Requirements

**As a library consumer,**
I want `DefaultsInformation` (tag `0x420157`, v2.1+) and its child `ObjectDefaults` (tag `0x420158`, v2.1+) implemented,
So that I can model default server attribute settings used in SetDefaults operations.

### 1.1. Acceptance Criteria

**Given** an `ObjectDefaults` instance with required fields (ObjectType and default attributes)
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `DefaultsInformation` instance containing one or more `ObjectDefaults` children
**When** serialized to TTLV, JSON, and XML
**Then** the nesting is correct — `ObjectDefaults` children appear within `DefaultsInformation` in spec order

**Given** the deserialized result
**When** compared to the original
**Then** all `ObjectDefaults` children are equal (collection round-trip preserved)

**Given** both implementations are complete
**When** `META-INF/services` is audited
**Then** both classes appear in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the `DefaultsInformation` and `ObjectDefaults` structures.
- `DefaultsInformation` has tag `0x420157` and is v2.1+.
- `ObjectDefaults` has tag `0x420158` and is v2.1+.

### 2.2. Architecture Compliance

- Create new classes in `src/main/java/org/purpleBean/kmip/model/core/structure/`.
- Both classes must implement `KmipStructure`.
- Ensure immutability.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/structure/DefaultsInformation.java`
- `src/main/java/org/purpleBean/kmip/model/core/structure/ObjectDefaults.java`
- Corresponding test files.
- Update `META-INF/services` for all codecs.

### 2.5. Testing Requirements

- Create full round-trip serialization test suites for both classes.
- Tests must cover TTLV, JSON, and XML.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
