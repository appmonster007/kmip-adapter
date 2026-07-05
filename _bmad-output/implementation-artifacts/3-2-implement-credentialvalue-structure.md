# Story 3.2: Implement CredentialValue Structure

## 1. Story Requirements

**As a library consumer,**
I want `CredentialValue` implemented for all KMIP credential subtypes (UsernameAndPassword, Device, Attestation, HashedPassword, OTP, Ticket, Certificate, Password),
So that I can model the credential payload used in CreateCredential operations.

### 1.1. Acceptance Criteria

**Given** a `CredentialValue` of each supported `CredentialType`
**When** serialized to TTLV, JSON, and XML with the appropriate `KmipSpec` context
**Then** the credential-type-specific fields render correctly per spec

**Given** the serialized output
**When** deserialized with the same `CredentialType` context
**Then** the correct value type is restored and round-trip is lossless

**Given** existing credential sub-types (HashedPasswordCredential, OtpCredential, PasswordCredential from commit `2e639ce8`) are mapped to `CredentialValue`
**When** the dispatch model is designed
**Then** no new structure duplication occurs — existing types are reused

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the `CredentialValue` structure and manage its subtypes.

Run the following command to generate the `CredentialValue` structure:

```bash
./scripts/generators/generate.sh structure CredentialValue
```

The `bmad-generate-kmip-code` skill will also be used to:
-   Define the dispatch model for handling different `CredentialType` values.
-   Reuse existing credential-related classes (`HashedPasswordCredential`, `OtpCredential`, `PasswordCredential`) to avoid duplication.
-   Generate any new required credential subtypes.

This approach will ensure that the `CredentialValue` structure is implemented correctly and that all acceptance criteria are met.
