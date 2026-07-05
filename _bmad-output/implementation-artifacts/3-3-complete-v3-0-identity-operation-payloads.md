# Story 3.3: Complete v3.0 Identity Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `CreateUserOpRequestPayload`, `CreateGroupOpRequestPayload`, and `CreateCredentialOpRequestPayload` fully implemented,
So that I can model KMIP v3.0 identity management operations.

**Depends on:** Story 3.1 (`ObjectGroups`, `Username` v3.0), Story 3.2 (`CredentialValue`)

### 1.1. Acceptance Criteria

**Given** `CreateUserOpRequestPayload` with a `Username` field (and optional attributes)
**When** serialized with `KmipSpec.V3_0` context
**Then** the correct tag and fields appear per KMIP v3.0 spec

**Given** `CreateGroupOpRequestPayload` with an `ObjectGroups` field
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** `CreateCredentialOpRequestPayload` with `CredentialType` and `CredentialValue`
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v3.0 §6

**Given** all three payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass for each payload

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the operation payloads.

### CreateUser Operation

Run the following command to generate the `CreateUser` operation components:

```bash
./scripts/generators/generate.sh structure --name CreateUser --version 3.0
```

### CreateGroup Operation

Run the following command to generate the `CreateGroup` operation components:

```bash
./scripts/generators/generate.sh structure --name CreateGroup --version 3.0
```

### CreateCredential Operation

Run the following command to generate the `CreateCredential` operation components:

```bash
./scripts/generators/generate.sh structure --name CreateCredential --version 3.0
```

These commands will create or update the necessary request payload classes, ensuring all acceptance criteria are met.
