# Story 2.4: Complete SetDefaults Operation Payload

## 1. Story Requirements

**As a library consumer,**
I want `SetDefaultsOpRequestPayload` fully implemented,
So that I can model the KMIP SetDefaults operation that configures server-side default attributes.

**Depends on:** Story 2.3 (`DefaultsInformation`, `ObjectDefaults`)

### 1.1. Acceptance Criteria

**Given** `SetDefaultsOpRequestPayload` constructed with a `DefaultsInformation` field
**When** `getValue()` is called
**Then** it returns `[defaultsInformation]` per KMIP v2.1 §6.25

**Given** the payload is serialized to all three codecs and deserialized
**When** compared to the original
**Then** the `DefaultsInformation` and all nested `ObjectDefaults` children are fully preserved

**Given** the skeletal test suite for this payload
**When** concrete test data is filled in and tests run
**Then** all three codec tests pass

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to complete the `SetDefaults` operation payload.

Run the following command to generate the `SetDefaults` operation components:

```bash
./scripts/generators/generate.sh structure --name SetDefaults --version 2.1
```

This command will create or update the `SetDefaultsOpRequestPayload` class, ensuring it correctly includes the `DefaultsInformation` structure and meets all acceptance criteria.
