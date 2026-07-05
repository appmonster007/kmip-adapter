# Story 3.1: Implement ObjectGroups Structure and Extend Username to v3.0

## 1. Story Requirements

**As a library consumer,**
I want `ObjectGroups` (tag `0x420166`, v2.1+) implemented and `Username.supportedVersions` extended to include `V3_0`,
So that I can model v3.0 user and group management operations.

### 1.1. Acceptance Criteria

**Given** an `ObjectGroups` instance with its child field(s)
**When** serialized to TTLV, JSON, and XML
**Then** tag `0x420166` appears and child fields are correctly nested

**Given** the serialized output
**When** deserialized
**Then** round-trip is lossless

**Given** `Username.isSupportedFor(KmipSpec.V3_0)` is called after the version extension
**Then** it returns `true`

**Given** `Username.isSupportedFor(KmipSpec.V1_2)` is called (existing behavior must be preserved)
**Then** it returns `true` (V1_2 support must not be removed)

**Given** `ObjectGroups` is complete and `META-INF/services` is audited
**Then** it appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill.

### ObjectGroups Structure

Run the following command to generate the `ObjectGroups` structure:

```bash
./scripts/generators/generate.sh structure --tag 0x420166 --version 2.1 ObjectGroups
```

### Extend Username Support

The `bmad-generate-kmip-code` skill will be used to extend the `Username` class to support KMIP v3.0. This will involve using the script's auditing and modification capabilities to update the `supportedVersions` of the `Username` class.

This approach will ensure that the `ObjectGroups` structure is correctly implemented and that the `Username` class is updated to support KMIP v3.0 while preserving existing functionality.
