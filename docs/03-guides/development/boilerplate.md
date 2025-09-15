# KMIP Boilerplate: Overview and Checklist

Use these copy‑ready boilerplates when adding new KMIP types. Each guide contains the full implementation, codecs (JSON/XML/TTLV), module registrations, and tests.

Quick links:
- Enumeration: `docs/03-guides/development/boilerplate-enum.md` (FooDemoEnum)
- Attribute: `docs/03-guides/development/boilerplate-attribute.md` (FooDemoAttribute)
- Structure: `docs/03-guides/development/boilerplate-structure.md` (FooDemoStructure)

Related guides:
- Quick Start: `docs/03-guides/quick-start-new-types.md`
- Testing Guide: `docs/03-guides/testing.md`

Common building blocks referenced in all boilerplates:
- Interfaces: `KmipDataType`, `KmipAttribute`, `KmipStructure`
- Context/Spec: `KmipContext` (thread‑local), `KmipSpec`
- Tags/Encoding: `KmipTag`, `EncodingType`
- Codec modules: `KmipJsonModule`, `KmipXmlModule`, `KmipTtlvModule`, `TtlvMapper`

Recommended flow:
1) Open the FooDemo* guide that matches your type.
2) Copy the class and the matching serializer/deserializer code blocks.
3) Register the codecs in the respective modules (snippets included in each guide).
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
  - [ ] Registered via `addSerializer`/`addDeserializer` in `KmipJsonModule`, `KmipXmlModule`, `KmipTtlvModule`

- Tests
  - [ ] Unit tests for construction and validation
  - [ ] JSON/XML/TTLV round‑trip tests
  - [ ] UnsupportedVersion cases covered

See the FooDemo* boilerplates for exact code you can paste.

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
  - [ ] `KmipJsonModule`: `addSerializer` + `addDeserializer`
  - [ ] `KmipXmlModule`: `addSerializer` + `addDeserializer`
  - [ ] `KmipTtlvModule`: `addSerializer` + `addDeserializer`

- Tests (see FooDemo* guides for copy‑ready classes)
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
