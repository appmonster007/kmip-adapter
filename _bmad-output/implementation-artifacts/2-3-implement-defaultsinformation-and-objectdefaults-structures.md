# Story 2.3: Implement DefaultsInformation and ObjectDefaults Structures

## 1. Story Requirements

**As a library consumer,**
I want `DefaultsInformation` (tag `0x420157`, v2.1+) and its child `ObjectDefaults` (tag `0x420158`, v2.1+) implemented,
So that I can model default server attribute settings used in SetDefaults operations.

### 1.1. Acceptance Criteria

**Given** an `ObjectDefaults` instance with required fields (ObjectType and default attributes)
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `DefaultsInformation` instance containing one or more `ObjectDefaults` children
**When** serialized to TTLV, JSON, and XML
**Then** the nesting is correct — `ObjectDefaults` children appear within `DefaultsInformation` in spec order

**Given** the deserialized result
**When** compared to the original
**Then** all `ObjectDefaults` children are equal (collection round-trip preserved)

**Given** both implementations are complete
**When** `META-INF/services` is audited
**Then** both classes appear in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the required structures.

### ObjectDefaults Structure

Run the following command to generate the `ObjectDefaults` structure:

```bash
./scripts/generators/generate.sh structure --tag 0x420158 --version 2.1 ObjectDefaults
```

### DefaultsInformation Structure

Run the following command to generate the `DefaultsInformation` structure:

```bash
./scripts/generators/generate.sh structure --tag 0x420157 --version 2.1 DefaultsInformation
```

These commands will generate the Java classes, test suites, and update the `META-INF/services` files, ensuring all acceptance criteria are met.
