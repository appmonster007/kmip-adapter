---
status: in-progress
baseline_commit: c0a1dc3d2ae6b5075f639fb2700513e12df8a2dd
epic: 1
story: "1.3"
depends_on: "1.1"
---

# Story 1.3: Complete `SetAttribute` and `AdjustAttribute` Operation Payloads

## Story

As a library consumer,
I want `SetAttributeOpRequestPayload`, `AdjustAttributeOpRequestPayload`, and `AdjustAttributeOpResponsePayload` fully implemented,
So that I can model KMIP attribute management operations end-to-end.

**Depends on:** Story 1.1 (`NewAttribute`, `CurrentAttribute` must be complete first)

## Acceptance Criteria

**Given** `SetAttributeOpRequestPayload` constructed with a `UniqueIdentifier` and `NewAttribute`
**When** `getValue()` is called
**Then** it returns `[uniqueIdentifier, newAttribute]` in spec-defined field order (KMIP v2.1 §6.16)

**Given** `AdjustAttributeOpRequestPayload` constructed with `UniqueIdentifier`, `CurrentAttribute`, and `AdjustmentType`
**When** `getValue()` is called
**Then** it returns all three fields in spec-defined order

**Given** `AdjustAttributeOpResponsePayload` constructed with `UniqueIdentifier` and `NewAttribute`
**When** `getValue()` is called
**Then** it returns the correct field array per spec

**Given** all three payloads are serialized to TTLV, JSON, and XML and then deserialized
**When** round-trip is performed
**Then** all fields are preserved exactly and the result equals the original

**Given** existing skeletal test suites for all three payloads
**When** `createDefault()` and `createVariant()` are filled with real test data
**Then** all three codec serialization tests pass with zero failures

## Tasks / Subtasks

- [x] Task 1: Complete `SetAttributeOpRequestPayload`
  - [x] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/SetAttributeOpRequestPayload.java`
  - [x] Add required field: `@NonNull UniqueIdentifier uniqueIdentifier`
  - [x] Add required field: `@NonNull NewAttribute newAttribute` (from Story 1.1)
  - [x] Update `@Builder` constructor to accept both fields and call `validate()`
  - [x] Update `of(List<KmipDataType> values)` to stream-group and build with both fields
  - [x] Update `getValue()` to return `[uniqueIdentifier, newAttribute]` per spec order
  - [x] Update `isSupported()` to check child fields as well
  - [x] Update Javadoc to reflect actual fields (remove "stub" language)
- [x] Task 2: Complete `AdjustAttributeOpRequestPayload`
  - [x] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayload.java`
  - [x] Add required field: `@NonNull UniqueIdentifier uniqueIdentifier`
  - [x] Add required field: `@NonNull CurrentAttribute currentAttribute` (from Story 1.1)
  - [x] Add required field: `@NonNull AdjustmentType adjustmentType`
  - [x] Update builder, `of()` factory, `getValue()`, and `isSupported()` accordingly
  - [x] Update Javadoc
- [x] Task 3: Complete `AdjustAttributeOpResponsePayload`
  - [x] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayload.java`
  - [x] Add required field: `@NonNull UniqueIdentifier uniqueIdentifier`
  - [x] Add required field: `@NonNull NewAttribute newAttribute` (check spec — response returns the new value)
  - [x] Update builder, `of()` factory, `getValue()`, and `isSupported()`
  - [x] Update Javadoc
- [x] Task 4: Update test suites for `SetAttributeOpRequestPayload`
  - [x] Edit existing test stubs:
    - `src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadJsonTest.java`
    - `src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadTtlvTest.java` (if exists; create if not)
    - `src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadXmlTest.java` (if exists; create if not)
  - [x] Fill `createDefault()` with a real `UniqueIdentifier` and `NewAttribute` instance
  - [x] Fill `createVariant()` with a different `NewAttribute` value
- [x] Task 5: Update test suites for `AdjustAttributeOpRequestPayload`
  - [x] Locate or create Ttlv, Json, Xml test classes
  - [x] Fill `createDefault()` with real `UniqueIdentifier`, `CurrentAttribute`, `AdjustmentType` instances
  - [x] Fill `createVariant()` with different `AdjustmentType` value
- [x] Task 6: Update test suites for `AdjustAttributeOpResponsePayload`
  - [x] Locate or create Ttlv, Json, Xml test classes
  - [x] Fill `createDefault()` and `createVariant()` with real test data
- [x] Task 7: Run full test suite and verify no regressions
  - [x] `mvn test` passes clean

## Dev Notes

### Existing Stub Files (already registered in codec)
These files exist as stubs with empty `getValue()` — add fields directly:
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/SetAttributeOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayload.java`

Note: `SetAttributeOpResponsePayload` is **already complete** (has `UniqueIdentifier` field). Do not modify it.

### Implementation Pattern — Filling Stubs
See `SetAttributeOpResponsePayload` as the reference for a completed payload with fields:
```
src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/SetAttributeOpResponsePayload.java
```
Key changes from stub to full implementation:
1. Add `@NonNull` field declarations before the static block
2. Update `@Builder` constructor parameters
3. Update `of(List<KmipDataType> values)` to group by tag and extract each field
4. Update `getValue()` to return all non-null fields in spec order
5. Update `isSupported()` to chain child field checks

### Field Types
- `UniqueIdentifier`: `org.purpleBean.kmip.model.core.type.UniqueIdentifier`
- `NewAttribute`: `org.purpleBean.kmip.model.v2_1.structure.NewAttribute` (Story 1.1)
- `CurrentAttribute`: `org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute` (Story 1.1)
- `AdjustmentType`: `org.purpleBean.kmip.model.core.enumeration.AdjustmentType` (already exists)

### KMIP Spec Field Order (v2.1)
- **SetAttributeOpRequest**: UniqueIdentifier (optional per spec, check §6.16), NewAttribute (required)
- **AdjustAttributeOpRequest**: UniqueIdentifier (optional), CurrentAttribute (required), AdjustmentType (required) — verify exact order in spec
- **AdjustAttributeOpResponse**: UniqueIdentifier (required), NewAttribute (required) — verify in spec

### Existing Tests
Some JSON test stubs already exist (see `SetAttributeOpRequestPayloadJsonTest.java`) — they have empty `createDefault()` returning `builder().build()`. These need real test data after payload is fully implemented.

### SPI Already Registered
All three payloads are already registered in META-INF/services (the stubs register themselves on class load). No SPI changes needed for this story.

## Dev Agent Record

### Implementation Plan

Followed the story spec exactly. For each of the 3 model payloads, replaced the empty stub with proper field declarations, `@Builder` constructor with `@NonNull` enforcement, `of(List<KmipDataType>)` factory using instanceof dispatch, `getValue()` returning all non-null fields in spec order via Stream, and `isSupported()` chaining to child field checks. Also implemented all 9 stub deserializers (JSON/TTLV/XML × 3 payloads) and updated 9 codec round-trip tests + 3 model domain tests + 3 benchmark subjects. Added `setupDefaultSpec() = V2_1` to the 9 codec tests because `CryptographicAlgorithm` is only registered in the KmipDataType registry for versioned specs (not UnknownVersion), making deserialization via `getKmipDataTypeClass()` fail in UnknownVersion context.

### Debug Log

- Initial test run: 18 failures — all "Round-trip serialization failed" in JSON/TTLV/XML tests for all 3 payloads.
- Root cause: `NewAttributeJsonDeserializer.setValue()` calls `getKmipDataTypeClass(tag, encodingType)` which looks up the registry for current spec. In UnknownVersion, CryptographicAlgorithm is not registered (static block skips UnknownVersion), so class lookup returns null → `ctxt.readValue(p, null)` throws.
- Fix: Added `setupDefaultSpec() { defaultSpec = KmipSpec.V2_1; }` override to all 9 codec test classes.
- Final run: 5765 tests, 0 failures, BUILD SUCCESS.

### Completion Notes

- `SetAttributeOpRequestPayload`: UniqueIdentifier (optional) + NewAttribute (required) per KMIP v2.1 §6.16
- `AdjustAttributeOpRequestPayload`: UniqueIdentifier (optional) + CurrentAttribute (required) + AdjustmentType (required)
- `AdjustAttributeOpResponsePayload`: UniqueIdentifier (required) + NewAttribute (required)
- All 9 deserializers implemented with real switch dispatch
- All 9 codec tests now use `KmipSpec.V2_1` as defaultSpec and exercise real CryptographicAlgorithm attributes
- 3 benchmark subjects updated with real instances and `KmipSpec.V2_1` spec

## File List

- src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/SetAttributeOpRequestPayload.java
- src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayload.java
- src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayload.java
- src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadJsonDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadTtlvDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadXmlDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadJsonDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadTtlvDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadXmlDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadJsonDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadTtlvDeserializer.java
- src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadXmlDeserializer.java
- src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadJsonTest.java
- src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadTtlvTest.java
- src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadXmlTest.java
- src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadJsonTest.java
- src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadTtlvTest.java
- src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadXmlTest.java
- src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadJsonTest.java
- src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadTtlvTest.java
- src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadXmlTest.java
- src/test/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadTest.java
- src/test/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadTest.java
- src/test/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadTest.java
- src/test/java/org/purpleBean/kmip/benchmark/subjects/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadBenchmarkSubject.java
- src/test/java/org/purpleBean/kmip/benchmark/subjects/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayloadBenchmarkSubject.java
- src/test/java/org/purpleBean/kmip/benchmark/subjects/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayloadBenchmarkSubject.java

## Change Log

- Implement SetAttributeOpRequestPayload fields: UniqueIdentifier (optional), NewAttribute (required) per KMIP v2.1 §6.16 (2026-07-04)
- Implement AdjustAttributeOpRequestPayload fields: UniqueIdentifier (optional), CurrentAttribute (required), AdjustmentType (required) (2026-07-04)
- Implement AdjustAttributeOpResponsePayload fields: UniqueIdentifier (required), NewAttribute (required) (2026-07-04)
- Implement 9 stub deserializers with real tag-dispatch switch statements (2026-07-04)
- Update 9 codec serialization tests with real data and KmipSpec.V2_1 defaultSpec (2026-07-04)
- Update 3 model domain tests with real instances and component validation (2026-07-04)
- Update 3 benchmark subjects with real instances and V2_1 spec (2026-07-04)
- All 5765 tests pass, 0 failures (2026-07-04)

## Status

review
