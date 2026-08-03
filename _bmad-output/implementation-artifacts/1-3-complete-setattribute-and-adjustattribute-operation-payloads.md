---
status: "in progress"
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

## Revised Tasks / Subtasks

- [ ] **Task 1: Complete Payload Implementations**
    - [ ] **`SetAttributeOpRequestPayload`:**
        - Edit `src/main/java/org/purplebean/kmip/model/v2x1/structure/request/payload/SetAttributeOpRequestPayload.java`.
        - Add `@NonNull UniqueIdentifier uniqueIdentifier` and `@NonNull NewAttribute newAttribute`.
        - Update the `@Builder` constructor, `of()` factory, `getValue()`, and `isSupported()` methods.
    - [ ] **`AdjustAttributeOpRequestPayload`:**
        - Edit `src/main/java/org/purplebean/kmip/model/v2x1/structure/request/payload/AdjustAttributeOpRequestPayload.java`.
        - Add `@NonNull UniqueIdentifier uniqueIdentifier`, `@NonNull CurrentAttribute currentAttribute`, and `@NonNull AdjustmentType adjustmentType`.
        - Update the `@Builder` constructor, `of()` factory, `getValue()`, and `isSupported()` methods.
    - [ ] **`AdjustAttributeOpResponsePayload`:**
        - Edit `src/main/java/org/purplebean/kmip/model/v2x1/structure/response/payload/AdjustAttributeOpResponsePayload.java`.
        - Add `@NonNull UniqueIdentifier uniqueIdentifier` and `@NonNull NewAttribute newAttribute`.
        - Update the `@Builder` constructor, `of()` factory, `getValue()`, and `isSupported()` methods.

- [ ] **Task 2: Complete and Verify Tests**
    - [ ] **Implement Test Logic:**
        - Fill in the `createDefault()` and `createVariant()` methods in the existing test stubs for each of the three payloads (`SetAttributeOpRequestPayload`, `AdjustAttributeOpRequestPayload`, `AdjustAttributeOpResponsePayload`) across all three codecs (TTLV, JSON, XML).
    - [ ] **Run Tests:**
        - Execute the tests for each payload to verify round-trip equality.

- [ ] **Task 3: Final Verification**
    - [ ] Run the full test suite (`mvn test`) and verify no regressions.

## Dev Notes

### Existing Stub Files (already registered in codec)
These files exist as stubs with empty `getValue()` — add fields directly:
- `src/main/java/org/purplebean/kmip/model/v2x1/structure/request/payload/SetAttributeOpRequestPayload.java`
- `src/main/java/org/purplebean/kmip/model/v2x1/structure/request/payload/AdjustAttributeOpRequestPayload.java`
- `src/main/java/org/purplebean/kmip/model/v2x1/structure/response/payload/AdjustAttributeOpResponsePayload.java`

Note: `SetAttributeOpResponsePayload` is **already complete** (has `UniqueIdentifier` field). Do not modify it.

### Implementation Pattern — Filling Stubs
See `SetAttributeOpResponsePayload` as the reference for a completed payload with fields:
```
src/main/java/org/purplebean/kmip/model/v2x1/structure/response/payload/SetAttributeOpResponsePayload.java
```
Key changes from stub to full implementation:
1. Add `@NonNull` field declarations before the static block
2. Update `@Builder` constructor parameters
3. Update `of(List<KmipDataType> values)` to group by tag and extract each field
4. Update `getValue()` to return all non-null fields in spec order
5. Update `isSupported()` to chain child field checks

### Field Types
- `UniqueIdentifier`: `org.purplebean.kmip.model.core.type.UniqueIdentifier`
- `NewAttribute`: `org.purplebean.kmip.model.v2x1.structure.NewAttribute` (Story 1.1)
- `CurrentAttribute`: `org.purplebean.kmip.model.v2x1.structure.CurrentAttribute` (Story 1.1)
- `AdjustmentType`: `org.purplebean.kmip.model.core.enumeration.AdjustmentType` (already exists)

### KMIP Spec Field Order (v2.1)
- **SetAttributeOpRequest**: UniqueIdentifier (optional per spec, check §6.16), NewAttribute (required)
- **AdjustAttributeOpRequest**: UniqueIdentifier (optional), CurrentAttribute (required), AdjustmentType (required) — verify exact order in spec
- **AdjustAttributeOpResponse**: UniqueIdentifier (required), NewAttribute (required) — verify in spec

### Existing Tests
Some JSON test stubs already exist (see `SetAttributeOpRequestPayloadJsonTest.java`) — they have empty `createDefault()` returning `builder().build()`. These need real test data after payload is fully implemented.

### SPI Already Registered
All three payloads are already registered in META-INF/services (the stubs register themselves on class load). No SPI changes needed for this story.
