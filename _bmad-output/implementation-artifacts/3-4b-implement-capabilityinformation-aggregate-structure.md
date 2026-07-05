# Story 3.4b: Implement CapabilityInformation Aggregate Structure

## 1. Story Requirements

**As a library consumer,**
I want `CapabilityInformation` (tag `0x420180`, v2.1+) implemented as an aggregate containing the capability sub-structures,
So that I can model and parse complete KMIP server capability declarations in Query responses.

**Depends on:** Story 3.4a (capability sub-structures)

### 1.1. Acceptance Criteria

**Given** a `CapabilityInformation` instance with a mix of optional capability children (operations list, object types list, attestation, async, batch-continue, batch-undo, quantum-safe sub-structures)
**When** serialized to TTLV, JSON, and XML
**Then** tag `0x420180` appears and all child structures are correctly nested in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** all child capability structures are fully reconstructed and the result equals the original

**Given** a minimal `CapabilityInformation` with only required fields
**When** serialized
**Then** absent optional capability sub-structures are not serialized (no null/empty nodes in output)

**Given** `CapabilityInformation.isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (V2_1+ only)

**Given** implementation is complete and `META-INF/services` is audited
**Then** `CapabilityInformation` appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the `CapabilityInformation` aggregate structure.

Run the following command to generate the `CapabilityInformation` structure:

```bash
./scripts/generators/generate.sh structure --tag 0x420180 --version 2.1 CapabilityInformation
```

This command will generate the Java class, test suite, and update the `META-INF/services` files. The generated structure will serve as an aggregate for the capability sub-structures implemented in Story 3.4a, ensuring all acceptance criteria are met.
