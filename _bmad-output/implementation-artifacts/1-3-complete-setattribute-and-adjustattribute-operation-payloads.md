---
status: ready-for-dev
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

- [ ] Task 1: Complete `SetAttributeOpRequestPayload`
  - [ ] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/SetAttributeOpRequestPayload.java`
  - [ ] Add required field: `@NonNull UniqueIdentifier uniqueIdentifier`
  - [ ] Add required field: `@NonNull NewAttribute newAttribute` (from Story 1.1)
  - [ ] Update `@Builder` constructor to accept both fields and call `validate()`
  - [ ] Update `of(List<KmipDataType> values)` to stream-group and build with both fields
  - [ ] Update `getValue()` to return `[uniqueIdentifier, newAttribute]` per spec order
  - [ ] Update `isSupported()` to check child fields as well
  - [ ] Update Javadoc to reflect actual fields (remove "stub" language)
- [ ] Task 2: Complete `AdjustAttributeOpRequestPayload`
  - [ ] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/AdjustAttributeOpRequestPayload.java`
  - [ ] Add required field: `@NonNull UniqueIdentifier uniqueIdentifier`
  - [ ] Add required field: `@NonNull CurrentAttribute currentAttribute` (from Story 1.1)
  - [ ] Add required field: `@NonNull AdjustmentType adjustmentType`
  - [ ] Update builder, `of()` factory, `getValue()`, and `isSupported()` accordingly
  - [ ] Update Javadoc
- [ ] Task 3: Complete `AdjustAttributeOpResponsePayload`
  - [ ] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/AdjustAttributeOpResponsePayload.java`
  - [ ] Add required field: `@NonNull UniqueIdentifier uniqueIdentifier`
  - [ ] Add required field: `@NonNull NewAttribute newAttribute` (check spec — response returns the new value)
  - [ ] Update builder, `of()` factory, `getValue()`, and `isSupported()`
  - [ ] Update Javadoc
- [ ] Task 4: Update test suites for `SetAttributeOpRequestPayload`
  - [ ] Edit existing test stubs:
    - `src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadJsonTest.java`
    - `src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadTtlvTest.java` (if exists; create if not)
    - `src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/request/payload/SetAttributeOpRequestPayloadXmlTest.java` (if exists; create if not)
  - [ ] Fill `createDefault()` with a real `UniqueIdentifier` and `NewAttribute` instance
  - [ ] Fill `createVariant()` with a different `NewAttribute` value
- [ ] Task 5: Update test suites for `AdjustAttributeOpRequestPayload`
  - [ ] Locate or create Ttlv, Json, Xml test classes
  - [ ] Fill `createDefault()` with real `UniqueIdentifier`, `CurrentAttribute`, `AdjustmentType` instances
  - [ ] Fill `createVariant()` with different `AdjustmentType` value
- [ ] Task 6: Update test suites for `AdjustAttributeOpResponsePayload`
  - [ ] Locate or create Ttlv, Json, Xml test classes
  - [ ] Fill `createDefault()` and `createVariant()` with real test data
- [ ] Task 7: Run full test suite and verify no regressions
  - [ ] `mvn test` passes clean

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

_To be filled by dev agent_

### Debug Log

_To be filled by dev agent_

### Completion Notes

_To be filled by dev agent_

## File List

_To be filled by dev agent_

## Change Log

_To be filled by dev agent_

## Status

ready-for-dev
