---
status: "in progress"
baseline_commit: 32c214b2020e5d457230f5e059eddbc1c85a2d93
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

## Revised Tasks / Subtasks

- [ ] **Task 1: Spec Review and Verification**
    - [ ] Review KMIP v2.1 spec for Login, Logout, and DelegatedLogin payloads.
    - [ ] Verify `LogoutOpResponsePayload` is correctly implemented as an empty payload.
    - [ ] Verify `LoginOpRequestPayload` is valid as an empty stub since its fields are optional and models are unavailable.
    - [ ] Verify `DelegatedLoginOpRequestPayload` is blocked on the `Rights` structure and update its documentation.

- [ ] **Task 2: Complete Payload Implementations**
    - [ ] **`LogoutOpRequestPayload`:**
        - Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LogoutOpRequestPayload.java`.
        - Add the required `@NonNull Ticket ticket` field.
        - Update the `@Builder` constructor, `of()` factory, `getValue()`, and `isSupported()` methods.
    - [ ] **`LoginOpResponsePayload`:**
        - Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LoginOpResponsePayload.java`.
        - Add the required `@NonNull Ticket ticket` field.
        - Update the `@Builder` constructor, `of()` factory, `getValue()`, and `isSupported()` methods.
    - [ ] **`DelegatedLoginOpResponsePayload`:**
        - Edit `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/DelegatedLoginOpResponsePayload.java`.
        - Add the required `@NonNull Ticket ticket` field.
        - Update the `@Builder` constructor, `of()` factory, `getValue()`, and `isSupported()` methods.

- [ ] **Task 3: Complete and Verify Tests**
    - [ ] **Implement Test Logic:**
        - Fill in the `createDefault()` and `createVariant()` methods in the existing test stubs for `LogoutOpRequestPayload`, `LoginOpResponsePayload`, and `DelegatedLoginOpResponsePayload` across all three codecs (TTLV, JSON, XML).
    - [ ] **Run Tests:**
        - Execute the tests for each updated payload to verify round-trip equality.
        - Ensure tests for unmodified payloads still pass.

- [ ] **Task 4: Final Verification**
    - [ ] Run the full test suite (`mvn test`) and verify no regressions.

## Dev Notes

### Existing Stub Files
All 6 payload files already exist as stubs:
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LoginOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LoginOpResponsePayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/LogoutOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/LogoutOpResponsePayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/request/payload/DelegatedLoginOpRequestPayload.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/response/payload/DelegatedLoginOpResponsePayload.java`

### Implementation Pattern
Follow the pattern for completing stubs:
1. Add `@NonNull` field declarations.
2. Update `@Builder` constructor.
3. Update `of(List<KmipDataType> values)` factory.
4. Update `getValue()` to return fields in spec order.
5. Update `isSupported()` to chain child field checks.

### SPI Registration
All 6 payloads are already registered in META-INF/services. No SPI changes needed.
