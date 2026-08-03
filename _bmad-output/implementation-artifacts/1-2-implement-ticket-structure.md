---
status: "in progress"
epic: 1
story: "1.2"
baseline_commit: 987847ec04756a638dca2f747d462cf4c9253e75
---

# Story 1.2: Implement `Ticket` Structure

## Story

As a library consumer,
I want the `Ticket` structure (tag `0x420149`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model ticket-based session objects used in Login and DelegatedLogin operations.

## Acceptance Criteria

**Given** a `Ticket` instance with a valid `TicketType` (required enumeration) and `TicketValue` (required ByteString)
**When** serialized to TTLV, JSON, and XML
**Then** the tag `0x420149` appears with both child fields in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** the result equals the original `Ticket` (round-trip lossless for all three codecs)

**Given** a `Ticket` is constructed with a null `TicketType`
**When** construction or validation is attempted
**Then** an appropriate exception is thrown (required field enforcement)

**Given** `Ticket` is used within a `KmipSpec.V1_2` context
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false`

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Ticket` appears in all three codec service registration files

## Revised Tasks / Subtasks

- [ ] **Task 1: Generate Code using `bmad-generate-kmip-code` skill**
    - [ ] Invoke `bmad-generate-kmip-code` for `Ticket`.
    - This will generate the model, serializers, deserializers, and test stubs.

- [ ] **Task 2: Review and Refine Generated Code**
    - [ ] **Model:** Verify `Ticket.java` in `src/main/java/org/purplebean/kmip/model/v2x1/structure/` has the correct fields (`TicketType` and `TicketValue`), supported versions, and required field enforcement.
    - [ ] **SPI Registration:** Confirm that the `bmad-generate-kmip-code` skill has correctly registered `Ticket` in the `META-INF/services` files.

- [ ] **Task 3: Complete and Verify Tests**
    - [ ] **Implement Test Logic:** Fill in the `createDefault()` and `createVariant()` methods in the generated test files.
        - `TicketTtlvTest.java`
        - `TicketJsonTest.java`
        - `TicketXmlTest.java`
    - [ ] **Run Tests:** Execute the tests to verify round-trip equality for all three codecs and that a null `TicketType` throws an exception.

- [ ] **Task 4: Final Verification**
    - [ ] Run the full test suite (`mvn test`) and verify no regressions.

## Dev Notes

### Package Location
- New structure: `src/main/java/org/purplebean/kmip/model/v2x1/structure/`
- New tests: `src/test/java/org/purplebean/kmip/codec/{ttlv,json,xml}/model/v2x1/structure/TicketXxxTest.java`

### Key Tags (already defined in `KmipTag.java`)
- `KmipTag.Standard.TICKET` → 0x420149, supported from V2_1
- `KmipTag.Standard.TICKET_TYPE` → 0x42014A
- `KmipTag.Standard.TICKET_VALUE` → 0x42014B

### Known Dependencies
- `TicketType` enumeration: `src/main/java/org/purplebean/kmip/model/core/enumeration/TicketType.java` (already exists, codec-registered)
- `TicketValue`: likely a `ByteString` or similar core type in `model/core/type/` — check `KmipTag.Standard.TICKET_VALUE`'s encoding type; if no dedicated class exists, use the raw `ByteString` / `OctetString` type

### Downstream Dependency
Story 1.4 (Login/Logout/DelegatedLogin payloads) depends on `Ticket`. This story must be complete before starting 1.4.
