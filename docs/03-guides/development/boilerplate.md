# KMIP Boilerplate: Overview and Checklist

Use these copy‑ready boilerplates when adding new KMIP types. Each guide contains the full implementation, codecs (JSON/XML/TTLV), module registrations, and tests.

Quick links:
- Enumeration: `docs/03-guides/development/boilerplate-enum.md` (FooEnum)
- Attribute: `docs/03-guides/development/boilerplate-attribute.md` (FooDataType + ActivationDate - KmipDataType vs KmipAttribute)
- Structure: `docs/03-guides/development/boilerplate-structure.md` (FooStructure)

Related guides:
- Quick Start: `docs/03-guides/quick-start-new-types.md`
- Testing Guide: `docs/03-guides/testing.md`

Common building blocks referenced in all boilerplates:
- Interfaces: `KmipDataType`, `KmipAttribute`, `KmipStructure`
- Context/Spec: `KmipContext` (thread‑local), `KmipSpec`
- Tags/Encoding: `KmipTag`, `EncodingType`
- Codec modules: `KmipJsonModule`, `KmipXmlModule`, `KmipTtlvModule`, `TtlvMapper`

Recommended flow:
1) Open the Foo* guide that matches your type (FooEnum, FooDataType, FooStructure).
2) Copy the class and the matching serializer/deserializer code blocks.
3) Register the codecs using ServiceLoader by listing your providers in the correct `META-INF/services` files (see below). No code edits are needed in the `Kmip*Module` classes.
4) Copy the provided tests and run `mvn test`.
5) Adjust tags (`KmipTag.Standard.*` or custom `KmipTag.register(...)`) and field names/types as needed.

## Build & Test Checklist

- Implementation
  - [ ] Class implements the correct interface
  - [ ] Required fields marked `@NonNull`; builder validates spec via `KmipContext.getSpec()`
  - [ ] `getKmipTag()` and `getEncodingType()` return correct values
  - [ ] Structures: `getValues()` ordered and nullable fields handled conditionally

- Serialization
  - [ ] JSON/XML/TTLV serializers and deserializers created
  - [ ] Registered via ServiceLoader in the correct `META-INF/services` file(s):
    - JSON
      - `META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer`
      - `META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer`
    - XML
      - `META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer`
      - `META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer`
    - TTLV
      - `META-INF/services/org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer`
      - `META-INF/services/org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer`

- Tests
  - [ ] Unit tests for construction and validation
  - [ ] JSON/XML/TTLV round‑trip tests
  - [ ] UnsupportedVersion cases covered

See the Foo* boilerplates for exact code you can paste, including example provider entries for all three formats.

---

## New Object Checklist (copy/paste and track)

- Core implementation
  - [ ] Implement the correct interface (`KmipDataType`, `KmipAttribute`, or `KmipStructure`)
  - [ ] Required fields are `@NonNull`; builder or constructor validates inputs
  - [ ] `getKmipTag()` returns the correct tag (`KmipTag.Standard.*` or custom `KmipTag.register(...)`)
  - [ ] `getEncodingType()` returns the correct `EncodingType`
  - [ ] Structures: `getValues()` ordered; optional children added conditionally
  - [ ] `isSupportedFor(KmipSpec)` implemented using child support and tag support
  - [ ] Implement using `@Builder` pattern with custom validation in `build()` method. 

- Codecs (create all three unless format is intentionally unsupported)
  - [ ] JSON serializer/deserializer
  - [ ] XML serializer/deserializer
  - [ ] TTLV serializer/deserializer
  - [ ] All serializers validate spec via `KmipContext.getSpec()`
  - [ ] Deserializers validate shape (tag/type/value) and enforce required fields

- Module registration
  - [ ] JSON: `KmipJsonModule` auto-loads providers from ServiceLoader
  - [ ] XML: `KmipXmlModule` auto-loads providers from ServiceLoader
  - [ ] TTLV: `KmipTtlvModule` auto-loads providers from ServiceLoader

- Tests (see Foo* guides for copy‑ready classes)
  - [ ] Unit tests for construction/validation, equals/hash
  - [ ] JSON/XML/TTLV round‑trip tests
  - [ ] UnsupportedVersion contexts fail as expected
  - [ ] Structure tests validate child ordering and nullability semantics

- Context and versioning
  - [ ] Tests set `KmipContext.setSpec(...)` and always clear in finally
  - [ ] Runtime code paths access spec via `KmipContext.getSpec()` (no global state leakage)

- Tagging and naming
  - [ ] Define `KmipTag.Standard` entries or register custom tags with unique values/descriptions
  - Align element names with tag descriptions for XML
 
Pointers
- Enumeration: `docs/03-guides/development/boilerplate-enum.md`
- Attribute: `docs/03-guides/development/boilerplate-attribute.md`
- Structure: `docs/03-guides/development/boilerplate-structure.md`
