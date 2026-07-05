# Story 4.1: Implement ItemType Enumeration

## 1. Story Requirements

**As a library consumer,**
I want the `ItemType` enumeration (v2.0+) implemented with all spec-defined values,
So that I can use the last missing KMIP enumeration without gaps.

### 1.1. Acceptance Criteria

**Given** `ItemType` class exists in `model/core/enumeration/` with all v2.0 values (Boolean, BigInteger, ByteString, DateTime, DateTimeExtended, Enumeration, Integer, Interval, LongInteger, TextString) and v3.0 additions (Identifier, NameReference, Reference)
**When** each value's `supportedVersions` is checked
**Then** v2.0 values include `V2_0`, `V2_1`, `V3_0` and v3.0-only values include only `V3_0`

**Given** `ItemType.isSupportedFor(KmipSpec.V1_2)` is called on any value
**Then** it returns `false` (V2_0+ only)

**Given** an `ItemType` value is serialized to JSON and XML
**When** deserialized
**Then** the correct enum value is restored

**Given** the implementation is complete and `META-INF/services` is audited
**Then** `ItemType` appears in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the `ItemType` enumeration.
- This is a v2.0+ enumeration.

### 2.2. Architecture Compliance

- Create a new class in `src/main/java/org/purpleBean/kmip/model/core/enumeration/`.
- The class must follow the existing pattern for enumerations.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/enumeration/ItemType.java`
- Corresponding test file.
- Update `META-INF/services` for all codecs.

### 2.5. Testing Requirements

- Create a full round-trip serialization test suite for `ItemType`.
- The test must cover JSON and XML deserialization from string values.
- The test must verify the `supportedVersions` for each value.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
