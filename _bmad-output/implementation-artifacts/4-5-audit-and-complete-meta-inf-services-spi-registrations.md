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

## 2. Implementation Plan

This story will be implemented by using the `bmad-generate-kmip-code` skill to audit and complete the `META-INF/services` SPI registrations.

The `generate.sh` script, when used to create new types, automatically updates the `META-INF/services` files. This story will leverage that capability to ensure all new types are correctly registered.

The process will be:
1.  Use the `bmad-generate-kmip-code` skill to get a list of all types that should be registered.
2.  Audit the `META-INF/services` files for the TTLV, JSON, and XML codecs against this list.
3.  For any missing registrations, use the `generate.sh` script's registration feature to add them. For example:

```bash
./scripts/generators/generate.sh --register-only <fully.qualified.ClassName>
```

This will ensure that all new types from Epics 1–4 are correctly registered for all three codecs, fulfilling all acceptance criteria.
