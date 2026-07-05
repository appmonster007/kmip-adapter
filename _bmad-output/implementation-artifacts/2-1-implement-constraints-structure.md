# Story 2.1: Implement Constraints Structure

## 1. Story Requirements

**As a library consumer,**
I want the `Constraints` structure (tag `0x420162`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model object-level constraints used in GetConstraints and SetConstraints operations.

### 1.1. Acceptance Criteria

**Given** a `Constraints` instance constructed with spec-defined child fields (consult KMIP v2.1 §2 for exact fields before implementing)
**When** serialized to TTLV, JSON, and XML
**Then** the tag `0x420162` appears with all child fields in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** the result equals the original `Constraints` (round-trip lossless for all three codecs)

**Given** `Constraints` is used with `KmipSpec.V1_2`
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (V2_1+ only)

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Constraints` appears in all three codec service files

## 2. Developer Context

### 2.1. Technical Requirements

- The `Constraints` class must be a new structure.
- It must have the KMIP tag `0x420162`.
- It is only supported from KMIP v2.1 onwards.

### 2.2. Architecture Compliance

- The new class `Constraints` should be created in the `src/main/java/org/purpleBean/kmip/model/core/structure/` directory.
- It must implement the `KmipStructure` interface from the `org.purpleBean.kmip.api` package.
- The class should be immutable, using `@Value` or final fields with a builder.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/structure/Constraints.java`: The new Java class for the `Constraints` structure.
- `src/test/java/org/purpleBean/kmip/model/core/structure/ConstraintsTest.java`: The test suite for the `Constraints` class.
- `src/main/resources/META-INF/services/org.purplebean.kmip.codec.ttlv.KmipTtlvCodec`: Add an entry for `org.purpleBean.kmip.model.core.structure.Constraints`.
- `src/main/resources/META-INF/services/org.purplebean.kmip.codec.json.KmipJsonCodec`: Add an entry for `org.purpleBean.kmip.model.core.structure.Constraints`.
- `src/main/resources/META-INF/services/org.purplebean.kmip.codec.xml.KmipXmlCodec`: Add an entry for `org.purpleBean.kmip.model.core.structure.Constraints`.

### 2.5. Testing Requirements

- A full round-trip serialization test suite (`KmipSerializationTestSuite`) must be created for the `Constraints` class.
- The test suite must cover TTLV, JSON, and XML codecs.
- The test must verify that the class is not supported for KMIP versions before 2.1.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
