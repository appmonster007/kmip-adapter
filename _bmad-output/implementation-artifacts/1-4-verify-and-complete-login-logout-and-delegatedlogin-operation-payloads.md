---
status: ready-for-dev
epic: 1
story: "1.4"
depends_on: "1.2"
---

# Story 1.4: Verify and Complete Login, Logout, and DelegatedLogin Operation Payloads

## Story

As a library consumer,
I want `LoginOpRequestPayload`, `LoginOpResponsePayload`, `LogoutOpRequestPayload`, `LogoutOpResponsePayload`, `DelegatedLoginOpRequestPayload`, and `DelegatedLoginOpResponsePayload` verified against the OASIS KMIP spec across all supported versions and fully implemented where gaps exist,
So that ticket-based authentication and delegation operations are spec-correct and complete for every KMIP version that defines them.

**Depends on:** Story 1.2 (`Ticket` must be complete first)

**Note:** `LogoutOpRequestPayload` and `LogoutOpResponsePayload` are marked COMPLETE in the existing codebase but have known field gaps. They are included for spec correctness verification and to add the `Ticket` field to `LogoutOpRequestPayload`.

## Acceptance Criteria

**Given** the OASIS KMIP specification for Login (§6.29), Logout, and DelegatedLogin across versions v1.2, v1.3, v1.4, v2.0, v2.1, and v3.0
**When** each payload's existing implementation (where present) is compared against the spec
**Then** any field additions, removals, or cardinality changes per version are identified and corrected before marking the story complete

**Given** `LogoutOpRequestPayload` spec review shows it requires a `Ticket` field (per v2.1 §6.1.31)
**When** the `Ticket` structure from Story 1.2 is available
**Then** `LogoutOpRequestPayload` is updated to include the required `Ticket` field and passes round-trip for all codecs

**Given** `LogoutOpResponsePayload` is reviewed against spec
**When** the spec confirms it is empty (no fields) across all supported versions
**Then** the existing implementation is confirmed correct and no changes are needed

**Given** `LoginOpRequestPayload` is implemented with required credential/authentication fields per KMIP v2.1 §6.29
**When** `getValue()` is called
**Then** it returns the correct `KmipDataType[]` in spec-defined field order

**Given** `LoginOpResponsePayload` containing a `Ticket`
**When** serialized to TTLV, JSON, and XML and deserialized
**Then** the `Ticket` fields (TicketType, TicketValue) are fully preserved in round-trip

**Given** `DelegatedLoginOpRequestPayload` with required fields (including an input `Ticket`)
**When** serialized and deserialized across all three codecs
**Then** round-trip is lossless

**Given** `DelegatedLoginOpResponsePayload` with its response fields
**When** serialized and deserialized
**Then** round-trip is lossless

**Given** all six payloads' test suites
**When** concrete test data is filled in and tests run
**Then** TTLV, JSON, and XML tests all pass for every payload across all applicable KMIP versions

## Tasks / Subtasks

- [ ] Task 1: Spec review — document exact fields for all 6 payloads across all KMIP versions
  - [ ] Review KMIP v2.1 spec: Login (§6.29), Logout, DelegatedLogin sections
  - [ ] Note which versions introduce each operation (Login/DelegatedLogin are v2.1+; Logout may differ)
  - [ ] Record exact required vs. optional field list for each of the 6 payloads
  - [ ] Note any version-specific field differences (e.g., v3.0 adds/removes fields)
  - [ ] Document findings in Dev Agent Record → Implementation Plan before coding
- [ ] Task 2: Update `LogoutOpRequestPayload` to add `Ticket` field
  - [ ] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LogoutOpRequestPayload.java`
  - [ ] Add `@NonNull Ticket ticket` field (required — ticket to be invalidated)
  - [ ] Update builder constructor, `of()` factory, `getValue()`, and `isSupported()`
  - [ ] Remove "stub" Javadoc; replace with accurate spec citation
- [ ] Task 3: Confirm `LogoutOpResponsePayload` is correct (empty)
  - [ ] Review `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LogoutOpResponsePayload.java`
  - [ ] Verify spec: Logout response has no fields
  - [ ] If spec confirms empty: leave as-is; record in Completion Notes
  - [ ] If spec shows any version adds fields: add them
- [ ] Task 4: Complete `LoginOpRequestPayload`
  - [ ] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LoginOpRequestPayload.java`
  - [ ] Based on spec findings from Task 1, add required fields (likely an `Authentication` or credential type)
  - [ ] Update builder, `of()`, `getValue()`, `isSupported()`
  - [ ] Remove "stub" language from Javadoc
- [ ] Task 5: Complete `LoginOpResponsePayload`
  - [ ] Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LoginOpResponsePayload.java`
  - [ ] Add `Ticket` field per spec (Login response returns a Ticket)
  - [ ] Update builder, `of()`, `getValue()`, `isSupported()`
- [ ] Task 6: Complete `DelegatedLoginOpRequestPayload`
  - [ ] Find/edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/DelegatedLoginOpRequestPayload.java`
  - [ ] Add required fields per spec (likely: input Ticket, delegation target credential, etc.)
  - [ ] Update builder, `of()`, `getValue()`, `isSupported()`
- [ ] Task 7: Complete `DelegatedLoginOpResponsePayload`
  - [ ] Find/edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/DelegatedLoginOpResponsePayload.java`
  - [ ] Add fields per spec (likely: output Ticket)
  - [ ] Update builder, `of()`, `getValue()`, `isSupported()`
- [ ] Task 8: Update test suites — `LogoutOpRequestPayload`
  - [ ] Edit existing Json test; create Ttlv and Xml tests if missing
  - [ ] `createDefault()` uses a real `Ticket` instance; `createVariant()` uses a different TicketType
- [ ] Task 9: Update test suites — `LogoutOpResponsePayload`
  - [ ] Confirm existing tests pass (empty payload, no changes expected)
- [ ] Task 10: Update test suites — `LoginOpRequestPayload`
  - [ ] Edit existing Json test; create Ttlv and Xml tests if missing
  - [ ] `createDefault()` and `createVariant()` use real credential/auth field data
- [ ] Task 11: Update test suites — `LoginOpResponsePayload`
  - [ ] Edit existing Json test; create Ttlv and Xml tests if missing
  - [ ] `createDefault()` uses a real `Ticket` instance
- [ ] Task 12: Update test suites — `DelegatedLoginOpRequestPayload`
  - [ ] Edit or create Ttlv, Json, Xml test classes
  - [ ] Fill with real test data per discovered fields
- [ ] Task 13: Update test suites — `DelegatedLoginOpResponsePayload`
  - [ ] Edit or create Ttlv, Json, Xml test classes
  - [ ] Fill with real test data
- [ ] Task 14: Run full test suite and verify no regressions
  - [ ] `mvn test` passes clean

## Dev Notes

### Existing Stub Files
All 6 payload files already exist as stubs with empty `getValue()` returning `new KmipDataType[0]`:
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LoginOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LoginOpResponsePayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LogoutOpRequestPayload.java` — has Javadoc noting Ticket is required but blocked
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LogoutOpResponsePayload.java` — likely correct as empty
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/DelegatedLoginOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/DelegatedLoginOpResponsePayload.java`

### Known Field (from Javadoc in LogoutOpRequestPayload)
The existing Javadoc states: "Ticket — Required — the ticket to be invalidated" (KMIP v2.1 spec §6.1.31).
After Story 1.2 completes, `Ticket` is available at `org.purpleBean.kmip.model.v2_1.structure.Ticket`.

### Implementation Pattern
See `SetAttributeOpResponsePayload` as the reference for a completed payload with fields:
```
src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/SetAttributeOpResponsePayload.java
```
Same steps: add fields, update builder/of()/getValue()/isSupported(), update tests.

### SPI Registration
All 6 payloads are already registered in META-INF/services. No SPI changes needed.

### Existing Tests
Several JSON test stubs exist — search for `Login`, `Logout`, `DelegatedLogin` in:
`src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/`
These need `createDefault()` and `createVariant()` populated after payload fields are implemented.

### Spec Access
KMIP v2.1 operation section references:
- Login: §6.29
- Logout: §6.30 (or nearby — check the spec section numbering for your version)
- DelegatedLogin: §6.31 (or nearby)
If the exact section is unclear, search the spec PDF for "Login Request Payload" / "Logout Request Payload" tables.

### Version Range for These Operations
- Login, Logout, DelegatedLogin are V2_1+ operations
- Check if V3.0 spec introduces any field changes before finalizing `supportedVersions`

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
