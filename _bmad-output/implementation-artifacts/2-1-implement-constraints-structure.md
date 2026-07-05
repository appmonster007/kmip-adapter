# Story 2.1: Implement Constraints Structure

## 1. Story Requirements

**As a library consumer,**
I want the `Constraints` structure (tag `0x420162`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model object-level constraints used in GetConstraints and SetConstraints operations.

### 1.1. Acceptance Criteria

**Given** a `Constraints` instance constructed with spec-defined child fields (consult KMIP v2.1 §2 for exact fields before implementing)
**When** serialized to TTLV, JSON, and XML
**Then** the tag `0x420162` appears with all child fields in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** the result equals the original `Constraints` (round-trip lossless for all three codecs)

**Given** `Constraints` is used with `KmipSpec.V1_2`
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (V2_1+ only)

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Constraints` appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill.

Run the following command to generate the `Constraints` structure:

```bash
./scripts/generators/generate.sh structure --tag 0x420162 --version 2.1 Constraints
```

This will generate the required Java class, test suite, and update the `META-INF/services` files, fulfilling the story's requirements.
