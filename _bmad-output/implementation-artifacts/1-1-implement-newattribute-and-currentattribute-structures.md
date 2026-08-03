---
status: "in progress"
epic: 1
story: "1.1"
baseline_commit: 987847ec04756a638dca2f747d462cf4c9253e75
---

# Story 1.1: Implement `NewAttribute` and `CurrentAttribute` Structures

## Story

As a library consumer,
I want `NewAttribute` (tag `0x42013D`) and `CurrentAttribute` (tag `0x42013C`) structures implemented with TTLV/JSON/XML codecs,
So that I can model attribute-carrying KMIP structures used in SetAttribute and AdjustAttribute operations.

## Acceptance Criteria

**Given** a `NewAttribute` instance is constructed with a valid inner `Attribute` child
**When** serialized to TTLV, JSON, and XML under `KmipSpec.V2_1` context
**Then** the output contains the correct tag `0x42013D` with the inner attribute correctly nested in spec-defined order

**Given** the TTLV/JSON/XML output from the above
**When** deserialized back to a `NewAttribute` instance
**Then** the result equals the original instance (round-trip lossless)

**Given** `CurrentAttribute` is constructed and serialized/deserialized under the same conditions
**When** round-trip is performed for all three codecs
**Then** the result is lossless and tag `0x42013C` is correct

**Given** either structure is created within a `KmipSpec.V1_2` context
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (both are V2_1+ only)

**Given** both implementations are complete
**When** `META-INF/services` is audited for all three codec interfaces
**Then** both `NewAttribute` and `CurrentAttribute` appear in TTLV, JSON, and XML service files

## Revised Tasks / Subtasks

- [ ] **Task 1: Generate Code using `bmad-generate-kmip-code` skill**
    - [ ] Invoke `bmad-generate-kmip-code` for `NewAttribute`.
    - [ ] Invoke `bmad-generate-kmip-code` for `CurrentAttribute`.
    - This will generate the model, serializers, deserializers, and test stubs for both structures.

- [ ] **Task 2: Review and Refine Generated Code**
    - [ ] **Model:** Verify `NewAttribute.java` and `CurrentAttribute.java` in `src/main/java/org/purplebean/kmip/model/v2x1/structure/` correctly wrap a single `Attribute` child and have the correct supported versions.
    - [ ] **SPI Registration:** Confirm that the `bmad-generate-kmip-code` skill has correctly registered `NewAttribute` and `CurrentAttribute` in the `META-INF/services` files.

- [ ] **Task 3: Complete and Verify Tests**
    - [ ] **Implement Test Logic:** Fill in the `createDefault()` and `createVariant()` methods in the generated test files.
        - `NewAttributeTtlvTest.java`
        - `NewAttributeJsonTest.java`
        - `NewAttributeXmlTest.java`
        - `CurrentAttributeTtlvTest.java`
        - `CurrentAttributeJsonTest.java`
        - `CurrentAttributeXmlTest.java`
    - [ ] **Run Tests:** Execute the tests to verify round-trip equality for all three codecs.

- [ ] **Task 4: Final Verification**
    - [ ] Run the full test suite (`mvn test`) and verify no regressions.

## Dev Notes

### Package Location
- New structures: `src/main/java/org/purplebean/kmip/model/v2x1/structure/`
- New tests (Ttlv): `src/test/java/org/purplebean/kmip/codec/ttlv/model/v2x1/structure/`
- New tests (Json): `src/test/java/org/purplebean/kmip/codec/json/model/v2x1/structure/`
- New tests (Xml): `src/test/java/org/purplebean/kmip/codec/xml/model/v2x1/structure/`

### Key Tags (already defined in `KmipTag.java`)
- `KmipTag.Standard.NEW_ATTRIBUTE` → 0x42013D, supported from V2_1
- `KmipTag.Standard.CURRENT_ATTRIBUTE` → 0x42013C, supported from V2_1

### Spec Notes
- Both structures are wrappers around a single `Attribute` child — consult KMIP v2.1 spec §4.4 and §4.47 for exact field definition
- Both are V2_1+ only — `supportedVersions` must NOT include V1_2, V1_3, V1_4, V2_0

### Downstream Dependency
Story 1.3 (SetAttribute/AdjustAttribute payloads) depends on both `NewAttribute` and `CurrentAttribute`. This story must be complete before starting 1.3.
