# Story 3.4a: Implement Capability Sub-Structures

## 1. Story Requirements

**As a library consumer,**
I want `AttestationCapability`, `AsynchronousCapability`, `BatchContinueCapability`, `BatchUndoCapability`, and `QuantumSafeCapability` implemented with TTLV/JSON/XML codecs,
So that the building blocks for `CapabilityInformation` are available for composing full server capability responses.

### 1.1. Acceptance Criteria

**Given** each of the five capability sub-structures is implemented with its spec-defined fields (consult KMIP v2.1 spec for each structure's field list before implementing)
**When** each is serialized to TTLV, JSON, and XML and deserialized
**Then** round-trip is lossless for each sub-structure independently

**Given** `AttestationCapability.isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (all five are V2_1+ only)

**Given** all five implementations are complete and `META-INF/services` is audited
**Then** all five classes appear in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the required capability sub-structures.

Run the following commands to generate each structure:

```bash
./scripts/generators/generate.sh structure --version 2.1 AttestationCapability
./scripts/generators/generate.sh structure --version 2.1 AsynchronousCapability
./scripts/generators/generate.sh structure --version 2.1 BatchContinueCapability
./scripts/generators/generate.sh structure --version 2.1 BatchUndoCapability
./scripts/generators/generate.sh structure --version 2.1 QuantumSafeCapability
```

These commands will generate the Java classes, test suites, and update the `META-INF/services` files for each of the five capability sub-structures, ensuring all acceptance criteria are met.
