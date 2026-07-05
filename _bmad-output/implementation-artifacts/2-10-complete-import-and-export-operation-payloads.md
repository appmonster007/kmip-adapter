# Story 2.10: Complete Import and Export Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `ImportOpRequestPayload`, `ExportOpRequestPayload`, and `ExportOpResponsePayload` fully implemented,
So that I can model KMIP managed object import and export flows.

**Depends on:** Story 2.9 (`ManagedObject`, `KeyWrappingData`)

### 1.1. Acceptance criteria

**Given** `ImportOpRequestPayload` with `UniqueIdentifier`, `ObjectType`, optional `ReplaceExisting`, optional `Attributes`, and a `ManagedObject`
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v1.4 §6.20

**Given** `ExportOpResponsePayload` with a `ManagedObject` and optional `KeyWrappingData`
**When** serialized to all three codecs and deserialized
**Then** the correct `ManagedObject` subtype is restored and round-trip is lossless

**Given** `ExportOpRequestPayload` fields confirmed from KMIP v1.4 §6.21
**When** `getValue()` is implemented
**Then** it returns the correct field array

**Given** all three payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass for each payload

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the operation payloads.

### Import Operation

Run the following command to generate the `Import` operation components:

```bash
./scripts/generators/generate.sh structure --name Import --version 1.4
```

### Export Operation

Run the following command to generate the `Export` operation components:

```bash
./scripts/generators/generate.sh structure --name Export --version 1.4
```

These commands will create or update the necessary request and response payload classes, ensuring all acceptance criteria are met.
