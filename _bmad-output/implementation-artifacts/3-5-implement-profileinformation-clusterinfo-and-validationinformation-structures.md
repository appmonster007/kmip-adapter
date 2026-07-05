# Story 3.5: Implement ProfileInformation, ClusterInfo, and ValidationInformation Structures

## 1. Story Requirements

**As a library consumer,**
I want `ProfileInformation` (tag `0x420100`), `ProfileVersion` (tag `0x420101`), `ClusterInfo` (tag `0x420139`), and `ValidationInformation` (tag `0x420107`) implemented,
So that I can model and parse server profile, cluster, and validation data in Query responses.

### 1.1. Acceptance Criteria

**Given** a `ProfileInformation` instance with `ProfileName` and one or more `ProfileVersion` children
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless (collection of ProfileVersion children preserved)

**Given** a `ClusterInfo` instance with required fields
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `ValidationInformation` instance with all spec-defined fields
**When** serialized to all three codecs and deserialized
**Then** all fields are preserved

**Given** all implementations are complete
**When** `META-INF/services` is audited
**Then** all new classes appear in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the required structures.

Run the following commands to generate each structure:

```bash
./scripts/generators/generate.sh structure --tag 0x420100 ProfileInformation
./scripts/generators/generate.sh structure --tag 0x420101 ProfileVersion
./scripts/generators/generate.sh structure --tag 0x420139 ClusterInfo
./scripts/generators/generate.sh structure --tag 0x420107 ValidationInformation
```

These commands will generate the Java classes, test suites, and update the `META-INF/services` files for each of the four structures, ensuring all acceptance criteria are met.
