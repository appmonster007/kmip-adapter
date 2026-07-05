# Story 2.7: Implement Pkcs11Interface Structure

## 1. Story Requirements

**As a library consumer,**
I want the `Pkcs11Interface` structure (tag `0xC11EFACE`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model PKCS#11 passthrough operation data used in Pkcs11 request/response payloads.

### 1.1. Acceptance Criteria

**Given** a `Pkcs11Interface` instance with all required fields (confirm exact fields from KMIP v2.1 §6.32 before implementing)
**When** serialized to TTLV, JSON, and XML
**Then** the correct tag and all child fields appear in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** round-trip is lossless for all three codecs

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Pkcs11Interface` appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the `Pkcs11Interface` structure.

Run the following command to generate the `Pkcs11Interface` structure:

```bash
./scripts/generators/generate.sh structure --tag 0xC11EFACE --version 2.1 Pkcs11Interface
```

This command will generate the Java class, test suite, and update the `META-INF/services` files, ensuring all acceptance criteria are met.
