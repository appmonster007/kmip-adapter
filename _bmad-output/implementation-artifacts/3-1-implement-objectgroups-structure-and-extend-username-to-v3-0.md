# Story 3.1: Implement ObjectGroups Structure and Extend Username to v3.0

## 1. Story Requirements

**As a library consumer,**
I want `ObjectGroups` (tag `0x420166`, v2.1+) implemented and `Username.supportedVersions` extended to include `V3_0`,
So that I can model v3.0 user and group management operations.

### 1.1. Acceptance Criteria

**Given** an `ObjectGroups` instance with its child field(s)
**When** serialized to TTLV, JSON, and XML
**Then** tag `0x420166` appears and child fields are correctly nested

**Given** the serialized output
**When** deserialized
**Then** round-trip is lossless

**Given** `Username.isSupportedFor(KmipSpec.V3_0)` is called after the version extension
**Then** it returns `true`

**Given** `Username.isSupportedFor(KmipSpec.V1_2)` is called (existing behavior must be preserved)
**Then** it returns `true` (V1_2 support must not be removed)

**Given** `ObjectGroups` is complete and `META-INF/services` is audited
**Then** it appears in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the `ObjectGroups` structure (tag `0x420166`, v2.1+).
- Extend the `Username` class to support KMIP v3.0.

### 2.2. Architecture Compliance

- `ObjectGroups` should be a new class in `src/main/java/org/purpleBean/kmip/model/core/structure/`.
- It must implement `KmipStructure`.
- The `Username` class is an existing class that needs modification.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/structure/ObjectGroups.java`
- `src/main/java/org/purpleBean/kmip/model/core/type/Username.java` (to be modified)
- Corresponding test files.
- Update `META-INF/services` for `ObjectGroups`.

### 2.5. Testing Requirements

- Create a full round-trip serialization test suite for `ObjectGroups`.
- Update the tests for `Username` to include v3.0 checks.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
