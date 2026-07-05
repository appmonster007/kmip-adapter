# Story 3.6: Implement RngParameters, RandomNumberGenerator, and ProtectionStorageMasks Structures

## 1. Story Requirements

**As a library consumer,**
I want `RngParameters` (tag `0x4200D0`), `RandomNumberGenerator` (tag `0x4200D3`), and `ProtectionStorageMasks` (tag `0x420146`) implemented,
So that I can model and parse RNG capability and storage protection data in Query responses and object attributes.

### 1.1. Acceptance Criteria

**Given** an `RngParameters` instance with `RngAlgorithm`, `RngMode`, and optional fields
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `RandomNumberGenerator` wrapping one or more `RngParameters` children
**When** serialized to all three codecs
**Then** nesting is correct and round-trip is lossless

**Given** a `ProtectionStorageMasks` instance
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** all implementations are complete
**When** `META-INF/services` is audited
**Then** all new classes appear in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the required structures.

Run the following commands to generate each structure:

```bash
./scripts/generators/generate.sh structure --tag 0x4200D0 RngParameters
./scripts/generators/generate.sh structure --tag 0x4200D3 RandomNumberGenerator
./scripts/generators/generate.sh structure --tag 0x420146 ProtectionStorageMasks
```

These commands will generate the Java classes, test suites, and update the `META-INF/services` files for each of the three structures, ensuring all acceptance criteria are met.
