# Story 3.7: Wire Capability Structures into Query Response Payload

## 1. Story Requirements

**As a library consumer,**
I want the existing `QueryOpResponsePayload` updated to include all capability and profile structures from Stories 3.4a, 3.4b, 3.5, and 3.6,
So that I can parse complete KMIP v2.1+ Query responses with full server capability information.

**Depends on:** Stories 3.4, 3.5, 3.6

### 1.1. Acceptance Criteria

**Given** a full `QueryOpResponsePayload` with all optional capability fields populated (`CapabilityInformation`, `ProfileInformation`, `ClusterInfo`, `ValidationInformation`, `RandomNumberGenerator`, `ProtectionStorageMasks`)
**When** serialized to TTLV, JSON, and XML
**Then** all fields appear in spec-defined order (KMIP v2.1 §6.26)

**Given** the serialized output
**When** deserialized
**Then** all nested capability structures are fully reconstructed and the result equals the original

**Given** a minimal `QueryOpResponsePayload` with only required fields
**When** serialized
**Then** optional capability fields are absent from the output (not serialized as null/empty)

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to update the `Query` operation payload.

Run the following command to update the `Query` operation components:

```bash
./scripts/generators/generate.sh structure --name Query --version 2.1
```

This command will update the `QueryOpResponsePayload` class to include the capability and profile structures from the dependent stories, ensuring that all acceptance criteria are met.
