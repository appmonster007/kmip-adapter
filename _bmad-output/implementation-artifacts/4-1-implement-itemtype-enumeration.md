# Story 4.1: Implement ItemType Enumeration

## 1. Story Requirements

**As a library consumer,**
I want the `ItemType` enumeration (v2.0+) implemented with all spec-defined values,
So that I can use the last missing KMIP enumeration without gaps.

### 1.1. Acceptance Criteria

**Given** `ItemType` class exists in `model/core/enumeration/` with all v2.0 values (Boolean, BigInteger, ByteString, DateTime, DateTimeExtended, Enumeration, Integer, Interval, LongInteger, TextString) and v3.0 additions (Identifier, NameReference, Reference)
**When** each value's `supportedVersions` is checked
**Then** v2.0 values include `V2_0`, `V2_1`, `V3_0` and v3.0-only values include only `V3_0`

**Given** `ItemType.isSupportedFor(KmipSpec.V1_2)` is called on any value
**Then** it returns `false` (V2_0+ only)

**Given** an `ItemType` value is serialized to JSON and XML
**When** deserialized
**Then** the correct enum value is restored

**Given** the implementation is complete and `META-INF/services` is audited
**Then** `ItemType` appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the `ItemType` enumeration.

Run the following command to generate the `ItemType` enumeration:

```bash
./scripts/generators/generate.sh enum --version 2.0 ItemType
```

This command will generate the Java class, test suite, and update the `META-INF/services` files. The `bmad-generate-kmip-code` skill will also be used to add the v3.0 values and correctly set the `supportedVersions` for all enum values, ensuring all acceptance criteria are met.
