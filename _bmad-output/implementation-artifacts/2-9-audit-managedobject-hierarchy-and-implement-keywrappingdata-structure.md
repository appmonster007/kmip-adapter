# Story 2.9: Audit ManagedObject Hierarchy and Implement KeyWrappingData Structure

## 1. Story Requirements

**As a library consumer,**
I want a clear audit of how `ManagedObject` and its subtypes are currently implemented in the codebase, existing gaps identified against the KMIP spec, and `KeyWrappingData` (structure, v1.2+) implemented,
So that I can model managed cryptographic objects and their wrapping metadata correctly in Import/Export operations.

### 1.1. Acceptance Criteria

**Given** the existing codebase is audited for any existing `ManagedObject` abstract type and all concrete subtypes (SymmetricKey, PrivateKey, PublicKey, Certificate, SecretData, OpaqueObject, SplitKey)
**When** the audit is complete
**Then** a clear inventory exists: which subtypes exist, which are partial, which are missing, and what the current virtual dispatch mechanism is (if any)

**Given** the audit results and the KMIP spec §2.2 definition of each ManagedObject subtype
**When** gaps are identified between spec and implementation
**Then** only the subtypes strictly required by `ImportOpRequestPayload` and `ExportOpResponsePayload` are implemented or completed in this story; remaining gaps are documented as follow-on work

**Given** the implementation follows the existing virtual type pattern discovered in the audit (do not invent a new pattern)
**When** a concrete `ManagedObject` subtype is serialized to all three codecs
**Then** the type-discriminating tag renders correctly per KMIP spec §2.2 and round-trip deserialization restores the correct subtype

**Given** a `KeyWrappingData` instance with `WrappingMethod` (required) and optional fields (EncryptionKeyInformation, MACSignatureKeyInformation, MACSignature, IVCounterNonce, EncodingOption)
**When** serialized to TTLV, JSON, and XML
**Then** all fields appear in spec-defined order (KMIP spec §2.1.5)
**And** deserialization produces an equal instance

**Given** all implementations are complete and `META-INF/services` is audited
**Then** every new or modified class appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the `KeyWrappingData` structure and audit the `ManagedObject` hierarchy.

### KeyWrappingData Structure

Run the following command to generate the `KeyWrappingData` structure:

```bash
./scripts/generators/generate.sh structure --version 1.2 KeyWrappingData
```

### ManagedObject Hierarchy Audit

The `bmad-generate-kmip-code` skill will also be used to audit the `ManagedObject` hierarchy. This will involve using the script's auditing capabilities to identify existing subtypes, partial implementations, and missing components. The necessary `ManagedObject` subtypes required for `Import` and `Export` operations will be generated or completed as needed.

This approach will ensure that the `KeyWrappingData` structure is correctly implemented and that the `ManagedObject` hierarchy is consistent with the KMIP specification for the required operations.
