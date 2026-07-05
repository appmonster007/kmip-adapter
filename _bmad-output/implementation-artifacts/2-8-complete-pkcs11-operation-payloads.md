# Story 2.8: Complete Pkcs11 Operation Payloads

## 1. Story Requirements

**As a library consumer,**
I want `Pkcs11OpRequestPayload` and `Pkcs11OpResponsePayload` fully implemented,
So that I can model KMIP PKCS#11 passthrough operations.

**Depends on:** Story 2.7 (`Pkcs11Interface`)

### 1.1. Acceptance Criteria

**Given** `Pkcs11OpRequestPayload` constructed with a `Pkcs11Interface` field
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.32

**Given** `Pkcs11OpResponsePayload` with its response fields including `Pkcs11Interface`
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** both payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the `Pkcs11` operation payloads.

Run the following command to generate the `Pkcs11` operation components:

```bash
./scripts/generators/generate.sh structure --name Pkcs11 --version 2.1
```

This command will create or update the `Pkcs11OpRequestPayload` and `Pkcs11OpResponsePayload` classes, ensuring they correctly include the `Pkcs11Interface` structure and meet all acceptance criteria.
