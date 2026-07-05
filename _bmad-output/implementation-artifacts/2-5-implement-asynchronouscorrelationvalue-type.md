# Story 2.5: Implement AsynchronousCorrelationValue Type

## 1. Story Requirements

**As a library consumer,**
I want the `AsynchronousCorrelationValue` ByteString wrapper type (v2.1+) implemented,
So that I can model async request correlation identifiers used in QueryAsyncRequests and Poll operations.

### 1.1. Acceptance Criteria

**Given** an `AsynchronousCorrelationValue` instance with a byte array value
**When** serialized to TTLV, JSON, and XML
**Then** the correct tag appears with ByteString encoding

**Given** the serialized output
**When** deserialized
**Then** the result equals the original (round-trip lossless)

**Given** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (V2_1+ only)

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** the class appears in all three codec service files

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to generate the `AsynchronousCorrelationValue` data type.

Run the following command to generate the `AsynchronousCorrelationValue` data type:

```bash
./scripts/generators/generate.sh datatype --version 2.1 AsynchronousCorrelationValue
```

This command will generate the Java class, test suite, and update the `META-INF/services` files, ensuring all acceptance criteria are met.
