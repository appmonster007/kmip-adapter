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

## 2. Developer Context

### 2.1. Technical Requirements

- Implement the `AsynchronousCorrelationValue` type.
- This is a ByteString wrapper.
- It is v2.1+.

### 2.2. Architecture Compliance

- Create a new class in the appropriate `model/core/type` directory.
- The class must implement the correct `KmipDataType` interface.
- Ensure immutability.

### 2.3. Library and Framework Requirements

- No new libraries are required.

### 2.4. File Structure Requirements

- `src/main/java/org/purpleBean/kmip/model/core/type/AsynchronousCorrelationValue.java`
- Corresponding test file.
- Update `META-INF/services` for all codecs.

### 2.5. Testing Requirements

- Create a full round-trip serialization test suite.
- Test must cover TTLV, JSON, and XML.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
