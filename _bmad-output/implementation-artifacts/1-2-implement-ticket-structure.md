---
status: in-progress
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

## Tasks / Subtasks

- [x] Task 1: Identify `TicketValue` type and confirm `TicketType` enumeration
  - [x] Confirm `TicketType` enumeration exists at `src/main/java/org/purpleBean/kmip/model/core/enumeration/TicketType.java`
  - [x] Determine the Java type for `TicketValue` — created as new ByteString type `model/core/type/TicketValue.java`
  - [x] Note field order per KMIP v2.1 spec §2.1.7: TicketType first, TicketValue second
- [x] Task 2: Implement `Ticket` structure
  - [x] Create `src/main/java/org/purpleBean/kmip/model/v2_1/structure/Ticket.java`
  - [x] Use `KmipTag.Standard.TICKET` (0x420149)
  - [x] `supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0)`
  - [x] Static registry block: `KmipDataType.register(...)` for each supported non-unknown version
  - [x] `@NonNull TicketType ticketType` — required field (Lombok @NonNull enforces null check)
  - [x] `@NonNull TicketValue ticketValue` — required, enforced via @NonNull
  - [x] `getValue()` returns `[ticketType, ticketValue]` in spec-defined order
  - [x] `of(List<KmipDataType> values)` factory: stream-group by tag and build instance
  - [x] `validate()` — throws if `!isSupported()`
  - [x] `isSupported()` — checks supportedVersions and child field support
- [x] Task 3: Register in `META-INF/services`
  - [x] Add `TicketValue` and `Ticket` serializer/deserializer entries to all 6 SPI codec files (TTLV, JSON, XML)
  - [x] Add `TicketValue` and `Ticket` to `KmipDataType` SPI file
- [x] Task 4: Write tests for `Ticket`
  - [x] Create `src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/TicketTtlvTest.java`
  - [x] Create `src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/TicketJsonTest.java`
  - [x] Create `src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/TicketXmlTest.java`
  - [x] `createDefault()` — TicketType.LOGIN + TicketValue bytes
  - [x] `createVariant()` — different TicketValue bytes
  - [x] Verify round-trip equality for all three codecs
  - [x] Verify null TicketType throws NullPointerException at construction
- [x] Task 5: Run full test suite and verify no regressions
  - [x] All 10 new tests pass; full suite 5765 tests — 24 failures + 112 errors unchanged (pre-existing)

## Dev Notes

### Package Location
- New structure: `src/main/java/org/purpleBean/kmip/model/v2_1/structure/Ticket.java`
- New tests: `src/test/java/org/purpleBean/kmip/codec/{ttlv,json,xml}/model/v2_1/structure/TicketXxxTest.java`

### Implementation Pattern
Follow the `Digest` structure (`src/main/java/org/purpleBean/kmip/model/core/structure/Digest.java`) as the canonical reference:
- `@Data @Builder(toBuilder = true)` Lombok annotations
- Private `@Builder`-annotated constructor calls `validate()`
- `@NonNull` on required fields (Lombok enforces NPE at construction)
- `of(List<KmipDataType> values)` uses `Collectors.groupingBy(KmipDataType::getKmipTag)` then pulls each field by tag
- `getValue()` uses `Stream.of(ticketType, ticketValue).filter(Objects::nonNull).toArray(KmipDataType[]::new)`

### Key Tags (already defined in `KmipTag.java`)
- `KmipTag.Standard.TICKET` → 0x420149, supported from V2_1
- `KmipTag.Standard.TICKET_TYPE` → 0x42014A
- `KmipTag.Standard.TICKET_VALUE` → 0x42014B

### Known Dependencies
- `TicketType` enumeration: `src/main/java/org/purpleBean/kmip/model/core/enumeration/TicketType.java` (already exists, codec-registered)
- `TicketValue`: likely a `ByteString` or similar core type in `model/core/type/` — check `KmipTag.Standard.TICKET_VALUE`'s encoding type; if no dedicated class exists, use the raw `ByteString` / `OctetString` type

### SPI Registration
Add entries to all 6 SPI files in `src/main/resources/META-INF/services/`:
- `org.purpleBean.kmip.codec.ttlv.serializer.api.KmipDataTypeTtlvSerializer`
- `org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer`
- `org.purpleBean.kmip.codec.json.serializer.api.KmipDataTypeJsonSerializer`
- `org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer`
- `org.purpleBean.kmip.codec.xml.serializer.api.KmipDataTypeXmlSerializer`
- `org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer`

### Downstream Dependency
Story 1.4 (Login/Logout/DelegatedLogin payloads) depends on `Ticket`. This story must be complete before starting 1.4.

## Dev Agent Record

### Implementation Plan

Created `TicketValue` as a new `ByteString` type (`model/core/type/`) mirroring the `DigestValue`/`NonceValue` pattern. Created `Ticket` structure (`model/v2_1/structure/`) with `@NonNull` fields enforcing both required fields. Implemented 6 codec classes for TicketValue (simple ByteBuffer passthrough) and 6 for Ticket (switch-case tag dispatch). Updated 7 SPI files. Tests use `KmipSpec.V2_1` context override.

### Debug Log

No issues — straightforward implementation following established patterns.

### Completion Notes

All 10 tests pass (TTLV:4 including null-check assertion, JSON:3, XML:3). No regressions: 5765 total tests, pre-existing failure count unchanged.

## File List

**New model classes:**
- `src/main/java/org/purpleBean/kmip/model/core/type/TicketValue.java`
- `src/main/java/org/purpleBean/kmip/model/v2_1/structure/Ticket.java`

**New codec serializers:**
- `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/model/core/type/TicketValueTtlvSerializer.java`
- `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/model/v2_1/structure/TicketTtlvSerializer.java`
- `src/main/java/org/purpleBean/kmip/codec/json/serializer/model/core/type/TicketValueJsonSerializer.java`
- `src/main/java/org/purpleBean/kmip/codec/json/serializer/model/v2_1/structure/TicketJsonSerializer.java`
- `src/main/java/org/purpleBean/kmip/codec/xml/serializer/model/core/type/TicketValueXmlSerializer.java`
- `src/main/java/org/purpleBean/kmip/codec/xml/serializer/model/v2_1/structure/TicketXmlSerializer.java`

**New codec deserializers:**
- `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/core/type/TicketValueTtlvDeserializer.java`
- `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/v2_1/structure/TicketTtlvDeserializer.java`
- `src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/core/type/TicketValueJsonDeserializer.java`
- `src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/v2_1/structure/TicketJsonDeserializer.java`
- `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/core/type/TicketValueXmlDeserializer.java`
- `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/v2_1/structure/TicketXmlDeserializer.java`

**New tests:**
- `src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/structure/TicketTtlvTest.java`
- `src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/structure/TicketJsonTest.java`
- `src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/structure/TicketXmlTest.java`

**Modified SPI files (7 files, +2 entries each):**
- `src/main/resources/META-INF/services/org.purpleBean.kmip.api.KmipDataType`
- `src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.api.KmipDataTypeTtlvSerializer`
- `src/main/resources/META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.api.KmipDataTypeTtlvDeserializer`
- `src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.serializer.api.KmipDataTypeJsonSerializer`
- `src/main/resources/META-INF/services/org.purpleBean.kmip.codec.json.deserializer.api.KmipDataTypeJsonDeserializer`
- `src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.serializer.api.KmipDataTypeXmlSerializer`
- `src/main/resources/META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.api.KmipDataTypeXmlDeserializer`

## Change Log

- 2026-07-04: Story 1.2 implemented — TicketValue type and Ticket structure with full codec support and tests — status → review

## Status

review
