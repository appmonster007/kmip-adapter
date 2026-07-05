# Story 4.5: Audit and Complete META-INF/services SPI Registrations

## 1. Story Requirements

**As a developer,**
I want all new types added in Epics 1–4 verified in `META-INF/services` for all three codec interfaces,
So that the runtime codec system discovers every type automatically without silent failures at deserialization time.

### 1.1. Acceptance Criteria

**Given** the complete list of new types added across Epics 1–4
**When** `META-INF/services` files for the TTLV, JSON, and XML codec interfaces are audited (use the `kmip-codec-registrar` agent)
**Then** every new type appears in all three service files

**Given** a type is correctly registered
**When** the codec attempts to deserialize a byte stream for that type from cold start (no warm-up)
**Then** the type is discovered and deserialized without `ClassNotFoundException` or silent unknown-type fallbacks

**Given** the audit finds any missing registration
**When** it is added
**Then** the corresponding codec round-trip test passes

## 2. Developer Context

### 2.1. Technical Requirements

- Audit the `META-INF/services` files for all three codecs.
- Ensure that all new types from Epics 1-4 are registered.

### 2.2. Architecture Compliance

- This is a verification and correction task.

### 2.3. Library and Framework Requirements

- The `kmip-codec-registrar` agent should be used for the audit.

### 2.4. File Structure Requirements

- **Files to modify:**
    - `src/main/resources/META-INF/services/org.purplebean.kmip.codec.ttlv.KmipTtlvCodec`
    - `src/main/resources/META-INF/services/org.purplebean.kmip.codec.json.KmipJsonCodec`
    - `src/main/resources/META-INF/services/org.purplebean.kmip.codec.xml.KmipXmlCodec`

### 2.5. Testing Requirements

- This change should fix existing failing tests or prevent future failures.

## 3. Story Completion Status

- **Status:** ready-for-dev
- **Completion Note:** Ultimate context engine analysis completed - comprehensive developer guide created
