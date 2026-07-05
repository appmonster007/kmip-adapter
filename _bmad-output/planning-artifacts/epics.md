---
stepsCompleted: [step-01, step-02, step-03, step-04]
inputDocuments:
  - README.md
  - docs/architecture.md
  - docs/implementation-coverage-report.md
  - docs/kmip-pending-implementation.md
  - docs/todo-list.md
  - git-log (reconstructed intent)
---

# kmip-adapter - Epic Breakdown

## Overview

This document provides the complete epic and story breakdown for **kmip-adapter**, a Java 21 library implementing KMIP (Key Management Interoperability Protocol) types v1.2–v3.0 with TTLV/JSON/XML serialization. Requirements were reconstructed from git history, coverage reports, spec analysis, and source code — the original PRD was lost during the Feb–Jun 2026 development gap.

**Current project state (as of last commit `987847ec`, Jun 22 2026):**
- 72/95 domain model types: COMPLETE (66%)
- 23/95 types: STUB — all blocked by missing dependent structures
- Codec coverage: 100% (TTLV/JSON/XML suites exist for every model)
- Test existence: 100% (skeletal tests exist; 23 stubs need full tests after unblocking)

---

## Requirements Inventory

### Functional Requirements

FR1: Implement all KMIP enumeration types (v1.2–v3.0) as type-safe Java classes with extensible value registry, including all version-gated additions and removals.
FR2: Implement all KMIP primitive/wrapper data types (TextString, Integer, DateTime, ByteString, etc.) with correct KmipTag assignment, encoding type, and supportedVersions.
FR3: Implement all KMIP composite structure types (KeyBlock, ProtocolVersion, Link, Constraints, Ticket, etc.) as composable, immutable Java classes.
FR4: Implement all KMIP operation request/response payload classes for all operations defined in KMIP v1.2 through v3.0 (Create, Locate, Get, Destroy, Register, Activate, Revoke, Query, Import, Export, Login, Logout, Deactivate, Obliterate, and all v2.x/v3.0 additions).
FR5: Implement TTLV (Tag-Type-Length-Value) binary serialization and deserialization for all KMIP types via the SPI (ServiceLoader) codec system.
FR6: Implement JSON serialization and deserialization via Jackson for all KMIP types, with correct field naming per KMIP JSON spec.
FR7: Implement XML serialization and deserialization via Jackson XML for all KMIP types, with correct element naming per KMIP XML spec.
FR8: Implement version-aware type gating — every type's `isSupportedFor(KmipSpec)` method must reflect the exact versions declared in the OASIS spec.
FR9: Implement thread-safe `KmipContext` for per-thread KMIP version scoping during serialization/deserialization.
FR10: Support custom type extension — library consumers can register custom enumerations, custom tags, and custom codec behavior without forking the library.
FR11: Implement SPI (META-INF/services) codec registrations for all types across all three codec suites (TTLV, JSON, XML) so the codec system discovers them automatically at runtime.
FR12: Implement `CapabilityInformation` and all Query-response supporting structures (ProfileInformation, ProfileVersion, ClusterInfo, RngParameters, RandomNumberGenerator, ValidationInformation, ProtectionStorageMasks, AttestationCapability, AsynchronousCapability, BatchContinueCapability, BatchUndoCapability, QuantumSafeCapability).
FR13: Implement missing blocking structures: `Ticket`, `Constraints`, `NewAttribute`, `CurrentAttribute`, `Pkcs11Interface`, `DefaultsInformation`, `ObjectDefaults`, `AsynchronousCorrelationValue`, `KeyWrappingData`, `ManagedObject` (abstract), `ObjectGroups`, `CredentialValue`.
FR14: Complete all 23 stub operation payloads once their blocking structures (FR13) are implemented.
FR15: Implement the missing `ItemType` enumeration (v2.0+) with all spec-defined values.
FR16: Fix enumeration description strings to match KMIP spec text for correct XML/JSON deserialization round-trips.

### NonFunctional Requirements

NFR1: All KMIP model types must be immutable (use Lombok `@Value` or final fields with builder) for thread safety.
NFR2: Test coverage gate: JaCoCo line coverage must be ≥90% across the library; each new type must have a full round-trip serialization test suite wired into `KmipSerializationTestSuite`.
NFR3: All types must support lossless round-trip: `serialize(X) → deserialize → Y` where `X.equals(Y)` for all three codec formats.
NFR4: Library must conform to OASIS KMIP specification versions 1.2, 1.3, 1.4, 2.0, 2.1, and 3.0 — spec is the authoritative source for all tag values, encoding types, field cardinalities, and version gates.
NFR5: Java 21 LTS required; Maven 3.6+ build system.
NFR6: Google Java Style Guide compliance enforced via Checkstyle.
NFR7: Performance: registry lookups O(1) via hash maps; immutable objects for zero-copy sharing; ObjectMapper instances reused.

### Additional Requirements (from architecture & code conventions)

- All new types must be registered in `META-INF/services` for their TTLV, JSON, and XML codec interfaces; use the `kmip-codec-registrar` agent to verify.
- Each type must implement `KmipDataType` (primitive) or `KmipStructure` (composite) interface from `api/`.
- Operation payloads live in `model/v{maj}_{min}/structure/request/payload/` or `response/payload/` by convention.
- **To improve efficiency, developers should use the `bmad-generate-kmip-code` skill to scaffold new type skeletons, then fill in the logic manually.**
- Stub payloads already have codec suites and skeletal tests wired — implementation only requires filling `getValue()` and any builder/constructor logic.

### UX Design Requirements

N/A — this is a backend Java library with no UI.

### FR Coverage Map

| FR | Epic | Summary |
|---|---|---|
| FR1 | Epic 4 | ItemType + full enum audit |
| FR2 | Epic 3 | Username extension, ObjectGroups type |
| FR3 | Epics 1, 2, 3 | Blocking structures across all operation clusters |
| FR4 | Epics 1, 2, 3 | Operation payloads by domain |
| FR5 | Epics 1, 2, 3 | TTLV codec (included with each type) |
| FR6 | Epics 1, 2, 3 | JSON codec (included with each type) |
| FR7 | Epics 1, 2, 3 | XML codec (included with each type) |
| FR8 | Epic 4 | Version-removal audit across all enums |
| FR9 | (already implemented — maintain) | Thread-safe KmipContext |
| FR10 | (already implemented — maintain) | Custom extension |
| FR11 | Epics 1, 2, 3, 4 | SPI registrations per type; full audit in Epic 4 |
| FR12 | Epic 3 | CapabilityInformation + Query sub-structures |
| FR13 | Epics 1, 2, 3 | Blocking structures distributed by domain |
| FR14 | Epics 1, 2, 3 | 23 stubs completed as structures are delivered |
| FR15 | Epic 4 | ItemType enumeration |
| FR16 | Epic 4 | Description string alignment |

---

## Epic List

### Epic 1: Auth, Attribute & Session Operations
Library consumers can model and serialize ticket-based auth flows (Login/Logout/DelegatedLogin) and attribute management operations (SetAttribute/AdjustAttribute) with all three codecs.
**FRs covered:** FR3 (partial), FR4 (partial), FR5, FR6, FR7 (partial), FR11 (partial)

### Epic 2: Constraint, Key Material & Async Operations
Library consumers can model constraint management, server default management, key import/export, async polling, PKCS#11 passthrough, and Process operations.
**FRs covered:** FR3 (partial), FR4 (partial), FR5, FR6, FR7 (partial), FR11 (partial)

### Epic 3: v3.0 Identity & Full Server Query
Library consumers can model v3.0 user/group/credential management and parse complete KMIP Query responses including full capability, profile, RNG, and validation information.
**FRs covered:** FR2 (partial), FR3 (remainder), FR4 (remainder), FR5, FR6, FR7 (remainder), FR11 (partial), FR12

### Epic 4: Spec Completeness & Quality Gate
Library is fully spec-compliant with no enumeration gaps, no description string mismatches, all types covered by round-trip tests, and the JaCoCo coverage gate green.
**FRs covered:** FR1, FR8, FR11 (audit), FR15, FR16, NFR1–NFR7

---

## Epic 1: Auth, Attribute & Session Operations

Library consumers can model and serialize ticket-based auth flows (Login/Logout/DelegatedLogin) and attribute management operations (SetAttribute/AdjustAttribute) with all three codecs (TTLV/JSON/XML).

### Story 1.1: Implement `NewAttribute` and `CurrentAttribute` Structures

As a library consumer,
I want `NewAttribute` (tag `0x420121`) and `CurrentAttribute` (tag `0x420120`) structures implemented with TTLV/JSON/XML codecs,
So that I can model attribute-carrying KMIP structures used in SetAttribute and AdjustAttribute operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `NewAttribute` and `CurrentAttribute`.

**Acceptance Criteria:**

**Given** a `NewAttribute` instance is constructed with a valid inner `Attribute` child
**When** serialized to TTLV, JSON, and XML under `KmipSpec.V2_1` context
**Then** the output contains the correct tag `0x420121` with the inner attribute correctly nested in spec-defined order

**Given** the TTLV/JSON/XML output from the above
**When** deserialized back to a `NewAttribute` instance
**Then** the result equals the original instance (round-trip lossless)

**Given** `CurrentAttribute` is constructed and serialized/deserialized under the same conditions
**When** round-trip is performed for all three codecs
**Then** the result is lossless and tag `0x420120` is correct

**Given** either structure is created within a `KmipSpec.V1_2` context
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (both are V2_1+ only)

**Given** both implementations are complete
**When** `META-INF/services` is audited for all three codec interfaces
**Then** both `NewAttribute` and `CurrentAttribute` appear in TTLV, JSON, and XML service files

---

### Story 1.2: Implement `Ticket` Structure

As a library consumer,
I want the `Ticket` structure (tag `0x420190`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model ticket-based session objects used in Login and DelegatedLogin operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `Ticket`.

**Acceptance Criteria:**

**Given** a `Ticket` instance with a valid `TicketType` (required enumeration) and `TicketValue` (required ByteString)
**When** serialized to TTLV, JSON, and XML
**Then** the tag `0x420190` appears with both child fields in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** the result equals the original `Ticket` (round-trip lossless for all three codecs)

**Given** a `Ticket` is constructed with a null `TicketType`
**When** construction or validation is attempted
**Then** an appropriate exception is thrown (required field enforcement)

**Given** `Ticket` is used within a `KmipSpec.V1_2` context
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false`

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Ticket` appears in all three codec service registration files

---

### Story 1.3: Complete `SetAttribute` and `AdjustAttribute` Operation Payloads

As a library consumer,
I want `SetAttributeOpRequestPayload`, `AdjustAttributeOpRequestPayload`, and `AdjustAttributeOpResponsePayload` fully implemented,
So that I can model KMIP attribute management operations end-to-end.

**Depends on:** Story 1.1 (`NewAttribute`, `CurrentAttribute`)

**Acceptance Criteria:**

**Given** `SetAttributeOpRequestPayload` constructed with a `UniqueIdentifier` and `NewAttribute`
**When** `getValue()` is called
**Then** it returns `[uniqueIdentifier, newAttribute]` in spec-defined field order (KMIP v2.1 §6.16)

**Given** `AdjustAttributeOpRequestPayload` constructed with `UniqueIdentifier`, `CurrentAttribute`, and `AdjustmentType`
**When** `getValue()` is called
**Then** it returns all three fields in spec-defined order

**Given** `AdjustAttributeOpResponsePayload` constructed with `UniqueIdentifier` and `NewAttribute`
**When** `getValue()` is called
**Then** it returns the correct field array per spec

**Given** all three payloads are serialized to TTLV, JSON, and XML and then deserialized
**When** round-trip is performed
**Then** all fields are preserved exactly and the result equals the original

**Given** existing skeletal test suites for all three payloads
**When** `validateComponents()` TODO annotations are resolved and test data is filled in
**Then** all three codec serialization tests pass with zero failures

---

### Story 1.4: Verify and Complete Login, Logout, and DelegatedLogin Operation Payloads

As a library consumer,
I want `LoginOpRequestPayload`, `LoginOpResponsePayload`, `LogoutOpRequestPayload`, `LogoutOpResponsePayload`, `DelegatedLoginOpRequestPayload`, and `DelegatedLoginOpResponsePayload` verified against the OASIS KMIP spec across all supported versions and fully implemented where gaps exist,
So that ticket-based authentication and delegation operations are spec-correct and complete for every KMIP version that defines them.

**Depends on:** Story 1.2 (`Ticket`)

**Note:** `LogoutOpRequestPayload` and `LogoutOpResponsePayload` are marked COMPLETE in the existing codebase. They are included in this story for spec correctness verification across all KMIP versions (v1.2–v3.0), not assumed to be correct as-is.

**Acceptance Criteria:**

**Given** the OASIS KMIP specification for Login (§6.29), Logout, and DelegatedLogin across versions v1.2, v1.3, v1.4, v2.0, v2.1, and v3.0
**When** each payload's existing implementation (where present) is compared against the spec
**Then** any field additions, removals, or cardinality changes per version are identified and corrected before marking the story complete

**Given** `LogoutOpRequestPayload` and `LogoutOpResponsePayload` are reviewed against spec
**When** the spec defines them as empty (no fields) across all versions that include Logout
**Then** the existing implementation is confirmed correct; if the spec adds fields in any version, those fields are added

**Given** `LoginOpRequestPayload` with required credential/authentication fields per KMIP v2.1 §6.29
**When** `getValue()` is called
**Then** it returns the correct `KmipDataType[]` in spec-defined field order

**Given** `LoginOpResponsePayload` containing a `Ticket`
**When** serialized to TTLV, JSON, and XML and deserialized
**Then** the `Ticket` fields (TicketType, TicketValue) are fully preserved in round-trip

**Given** `DelegatedLoginOpRequestPayload` with required fields (including an input `Ticket`)
**When** serialized and deserialized across all three codecs
**Then** round-trip is lossless

**Given** `DelegatedLoginOpResponsePayload` with its response fields
**When** serialized and deserialized
**Then** round-trip is lossless

**Given** all six payloads' test suites
**When** concrete test data is filled in and tests run
**Then** TTLV, JSON, and XML tests all pass for every payload across all applicable KMIP versions

---

## Epic 2: Constraint, Key Material & Async Operations

Library consumers can model constraint management (GetConstraints/SetConstraints), server default management (SetDefaults), key import/export (Import/Export), async polling (QueryAsyncRequests/Poll), PKCS#11 passthrough, and Process operations.

### Story 2.1: Implement `Constraints` Structure

As a library consumer,
I want the `Constraints` structure (tag `0x420162`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model object-level constraints used in GetConstraints and SetConstraints operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `Constraints`.

**Acceptance Criteria:**

**Given** a `Constraints` instance constructed with spec-defined child fields (consult KMIP v2.1 §2 for exact fields before implementing)
**When** serialized to TTLV, JSON, and XML
**Then** the tag `0x420162` appears with all child fields in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** the result equals the original `Constraints` (round-trip lossless for all three codecs)

**Given** `Constraints` is used with `KmipSpec.V1_2`
**When** `isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (V2_1+ only)

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Constraints` appears in all three codec service files

---

### Story 2.2: Complete `GetConstraints` and `SetConstraints` Operation Payloads

As a library consumer,
I want `GetConstraintsOpResponsePayload` and `SetConstraintsOpRequestPayload` fully implemented,
So that I can model KMIP object constraint management operations.

**Depends on:** Story 2.1 (`Constraints`)

**Acceptance Criteria:**

**Given** `GetConstraintsOpResponsePayload` with a `UniqueIdentifier` and `Constraints` field
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.27

**Given** `SetConstraintsOpRequestPayload` with `UniqueIdentifier` and `Constraints`
**When** serialized to all three codecs and deserialized
**Then** all fields are preserved in round-trip

**Given** both payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass

---

### Story 2.3: Implement `DefaultsInformation` and `ObjectDefaults` Structures

As a library consumer,
I want `DefaultsInformation` (tag `0x420157`, v2.1+) and its child `ObjectDefaults` (tag `0x420158`, v2.1+) implemented,
So that I can model default server attribute settings used in SetDefaults operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `DefaultsInformation` and `ObjectDefaults`.

**Acceptance Criteria:**

**Given** an `ObjectDefaults` instance with required fields (ObjectType and default attributes)
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `DefaultsInformation` instance containing one or more `ObjectDefaults` children
**When** serialized to TTLV, JSON, and XML
**Then** the nesting is correct — `ObjectDefaults` children appear within `DefaultsInformation` in spec order

**Given** the deserialized result
**When** compared to the original
**Then** all `ObjectDefaults` children are equal (collection round-trip preserved)

**Given** both implementations are complete
**When** `META-INF/services` is audited
**Then** both classes appear in all three codec service files

---

### Story 2.4: Complete `SetDefaults` Operation Payload

As a library consumer,
I want `SetDefaultsOpRequestPayload` fully implemented,
So that I can model the KMIP SetDefaults operation that configures server-side default attributes.

**Depends on:** Story 2.3 (`DefaultsInformation`, `ObjectDefaults`)

**Acceptance Criteria:**

**Given** `SetDefaultsOpRequestPayload` constructed with a `DefaultsInformation` field
**When** `getValue()` is called
**Then** it returns `[defaultsInformation]` per KMIP v2.1 §6.25

**Given** the payload is serialized to all three codecs and deserialized
**When** compared to the original
**Then** the `DefaultsInformation` and all nested `ObjectDefaults` children are fully preserved

**Given** the skeletal test suite for this payload
**When** concrete test data is filled in and tests run
**Then** all three codec tests pass

---

### Story 2.5: Implement `AsynchronousCorrelationValue` Type

As a library consumer,
I want the `AsynchronousCorrelationValue` ByteString wrapper type (v2.1+) implemented,
So that I can model async request correlation identifiers used in QueryAsyncRequests and Poll operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `datatype` entity type and `--type ByteBuffer` to generate the initial boilerplate for `AsynchronousCorrelationValue`.

**Acceptance Criteria:**

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

---

### Story 2.6: Complete `QueryAsyncRequests` and `Poll` Operation Payloads

As a library consumer,
I want `QueryAsynchronousRequestsOpRequestPayload`, `QueryAsynchronousRequestsOpResponsePayload`, and `PollOpResponsePayload` fully implemented,
So that I can model KMIP asynchronous operation tracking flows.

**Depends on:** Story 2.5 (`AsynchronousCorrelationValue`)

**Acceptance Criteria:**

**Given** `QueryAsynchronousRequestsOpRequestPayload` with one or more `AsynchronousCorrelationValue` fields
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.31

**Given** `QueryAsynchronousRequestsOpResponsePayload` with response fields
**When** serialized to all three codecs and deserialized
**Then** all correlation values are preserved in order (round-trip lossless)

**Given** `PollOpResponsePayload` with its response fields
**When** serialized and deserialized
**Then** round-trip is lossless

**Given** all three payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass for each payload

---

### Story 2.7: Implement `Pkcs11Interface` Structure

As a library consumer,
I want the `Pkcs11Interface` structure (tag `0xC11EFACE`, v2.1+) implemented with TTLV/JSON/XML codecs,
So that I can model PKCS#11 passthrough operation data used in Pkcs11 request/response payloads.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `Pkcs11Interface`.

**Acceptance Criteria:**

**Given** a `Pkcs11Interface` instance with all required fields (confirm exact fields from KMIP v2.1 §6.32 before implementing)
**When** serialized to TTLV, JSON, and XML
**Then** the correct tag and all child fields appear in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** round-trip is lossless for all three codecs

**Given** the implementation is complete
**When** `META-INF/services` is audited
**Then** `Pkcs11Interface` appears in all three codec service files

---

### Story 2.8: Complete `Pkcs11` Operation Payloads

As a library consumer,
I want `Pkcs11OpRequestPayload` and `Pkcs11OpResponsePayload` fully implemented,
So that I can model KMIP PKCS#11 passthrough operations.

**Depends on:** Story 2.7 (`Pkcs11Interface`)

**Acceptance Criteria:**

**Given** `Pkcs11OpRequestPayload` constructed with a `Pkcs11Interface` field
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v2.1 §6.32

**Given** `Pkcs11OpResponsePayload` with its response fields including `Pkcs11Interface`
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** both payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass

---

### Story 2.9: Audit `ManagedObject` Hierarchy and Implement `KeyWrappingData` Structure

As a library consumer,
I want a clear audit of how `ManagedObject` and its subtypes are currently implemented in the codebase, existing gaps identified against the KMIP spec, and `KeyWrappingData` (structure, v1.2+) implemented,
So that I can model managed cryptographic objects and their wrapping metadata correctly in Import/Export operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `KeyWrappingData`.

**Acceptance Criteria:**

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

---

### Story 2.10: Complete `Import` and `Export` Operation Payloads

As a library consumer,
I want `ImportOpRequestPayload`, `ExportOpRequestPayload`, and `ExportOpResponsePayload` fully implemented,
So that I can model KMIP managed object import and export flows.

**Depends on:** Story 2.9 (`ManagedObject`, `KeyWrappingData`)

**Acceptance Criteria:**

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

---

### Story 2.11: Clarify and Implement `Process` Operation Payloads

As a library consumer,
I want `ProcessOpRequestPayload` and `ProcessOpResponsePayload` implemented per KMIP v2.1 §6.33,
So that I can model KMIP cryptographic processing operations.

**Acceptance Criteria:**

**Given** the KMIP v2.1 §6.33 spec is consulted using the `kmip-architect` agent
**When** the field list for Process request/response is confirmed and documented
**Then** an implementation design is reviewed before any code is written

**Given** the design is confirmed
**When** both payloads are implemented
**Then** `getValue()` returns the correct field arrays and all three codec round-trips pass

**Given** the skeletal test suites
**When** test data is filled in
**Then** all three codec tests pass

---

## Epic 3: v3.0 Identity & Full Server Query

Library consumers can model v3.0 user/group/credential management (CreateUser/CreateGroup/CreateCredential) and parse complete KMIP Query responses including full capability, profile, RNG, and validation information.

### Story 3.1: Implement `ObjectGroups` Structure and Extend `Username` to v3.0

As a library consumer,
I want `ObjectGroups` (tag `0x420166`, v2.1+) implemented and `Username.supportedVersions` extended to include `V3_0`,
So that I can model v3.0 user and group management operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `ObjectGroups`.

**Acceptance Criteria:**

**Given** an `ObjectGroups` instance with its child field(s)
**When** serialized to TTLV, JSON, and XML
**Then** tag `0x420166` appears and child fields are correctly nested

**Given** the serialized output
**When** deserialized
**Then** round-trip is lossless

**Given** `Username.isSupportedFor(KmipSpec.V3_0)` is called after the version extension
**Then** it returns `true`

**Given** `Username.isSupportedFor(KmipSpec.V1_2)` is called (existing behavior must be preserved)
**Then** it returns `true` (V1_2 support must not be removed)

**Given** `ObjectGroups` is complete and `META-INF/services` is audited
**Then** it appears in all three codec service files

---

### Story 3.2: Implement `CredentialValue` Structure

As a library consumer,
I want `CredentialValue` implemented for all KMIP credential subtypes (UsernameAndPassword, Device, Attestation, HashedPassword, OTP, Ticket, Certificate, Password),
So that I can model the credential payload used in CreateCredential operations.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `CredentialValue`.

**Acceptance Criteria:**

**Given** a `CredentialValue` of each supported `CredentialType`
**When** serialized to TTLV, JSON, and XML with the appropriate `KmipSpec` context
**Then** the credential-type-specific fields render correctly per spec

**Given** the serialized output
**When** deserialized with the same `CredentialType` context
**Then** the correct value type is restored and round-trip is lossless

**Given** existing credential sub-types (HashedPasswordCredential, OtpCredential, PasswordCredential from commit `2e639ce8`) are mapped to `CredentialValue`
**When** the dispatch model is designed
**Then** no new structure duplication occurs — existing types are reused

---

### Story 3.3: Complete v3.0 Identity Operation Payloads

As a library consumer,
I want `CreateUserOpRequestPayload`, `CreateGroupOpRequestPayload`, and `CreateCredentialOpRequestPayload` fully implemented,
So that I can model KMIP v3.0 identity management operations.

**Depends on:** Story 3.1 (`ObjectGroups`, `Username` v3.0), Story 3.2 (`CredentialValue`)

**Acceptance Criteria:**

**Given** `CreateUserOpRequestPayload` with a `Username` field (and optional attributes)
**When** serialized with `KmipSpec.V3_0` context
**Then** the correct tag and fields appear per KMIP v3.0 spec

**Given** `CreateGroupOpRequestPayload` with an `ObjectGroups` field
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** `CreateCredentialOpRequestPayload` with `CredentialType` and `CredentialValue`
**When** `getValue()` is called
**Then** it returns the correct field array per KMIP v3.0 §6

**Given** all three payloads' skeletal test suites
**When** test data is filled in and tests run
**Then** all three codec tests pass for each payload

---

### Story 3.4a: Implement Capability Sub-Structures

As a library consumer,
I want `AttestationCapability`, `AsynchronousCapability`, `BatchContinueCapability`, `BatchUndoCapability`, and `QuantumSafeCapability` implemented with TTLV/JSON/XML codecs,
So that the building blocks for `CapabilityInformation` are available for composing full server capability responses.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for each of these capability structures.

**Acceptance Criteria:**

**Given** each of the five capability sub-structures is implemented with its spec-defined fields (consult KMIP v2.1 spec for each structure's field list before implementing)
**When** each is serialized to TTLV, JSON, and XML and deserialized
**Then** round-trip is lossless for each sub-structure independently

**Given** `AttestationCapability.isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (all five are V2_1+ only)

**Given** all five implementations are complete and `META-INF/services` is audited
**Then** all five classes appear in all three codec service files

---

### Story 3.4b: Implement `CapabilityInformation` Aggregate Structure

As a library consumer,
I want `CapabilityInformation` (tag `0x420180`, v2.1+) implemented as an aggregate containing the capability sub-structures,
So that I can model and parse complete KMIP server capability declarations in Query responses.

**Depends on:** Story 3.4a (capability sub-structures)

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for `CapabilityInformation`.

**Acceptance Criteria:**

**Given** a `CapabilityInformation` instance with a mix of optional capability children (operations list, object types list, attestation, async, batch-continue, batch-undo, quantum-safe sub-structures)
**When** serialized to TTLV, JSON, and XML
**Then** tag `0x420180` appears and all child structures are correctly nested in spec-defined order

**Given** the serialized output
**When** deserialized
**Then** all child capability structures are fully reconstructed and the result equals the original

**Given** a minimal `CapabilityInformation` with only required fields
**When** serialized
**Then** absent optional capability sub-structures are not serialized (no null/empty nodes in output)

**Given** `CapabilityInformation.isSupportedFor(KmipSpec.V1_2)` is called
**Then** it returns `false` (V2_1+ only)

**Given** implementation is complete and `META-INF/services` is audited
**Then** `CapabilityInformation` appears in all three codec service files

---

### Story 3.5: Implement `ProfileInformation`, `ClusterInfo`, and `ValidationInformation` Structures

As a library consumer,
I want `ProfileInformation` (tag `0x420100`), `ProfileVersion` (tag `0x420101`), `ClusterInfo` (tag `0x420139`), and `ValidationInformation` (tag `0x420107`) implemented,
So that I can model and parse server profile, cluster, and validation data in Query responses.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for each of these structures.

**Acceptance Criteria:**

**Given** a `ProfileInformation` instance with `ProfileName` and one or more `ProfileVersion` children
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless (collection of ProfileVersion children preserved)

**Given** a `ClusterInfo` instance with required fields
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `ValidationInformation` instance with all spec-defined fields
**When** serialized to all three codecs and deserialized
**Then** all fields are preserved

**Given** all implementations are complete
**When** `META-INF/services` is audited
**Then** all new classes appear in all three codec service files

---

### Story 3.6: Implement `RngParameters`, `RandomNumberGenerator`, and `ProtectionStorageMasks` Structures

As a library consumer,
I want `RngParameters` (tag `0x4200D0`), `RandomNumberGenerator` (tag `0x4200D3`), and `ProtectionStorageMasks` (tag `0x420146`) implemented,
So that I can model and parse RNG capability and storage protection data in Query responses and object attributes.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `structure` entity type to generate the initial boilerplate for each of these structures.

**Acceptance Criteria:**

**Given** an `RngParameters` instance with `RngAlgorithm`, `RngMode`, and optional fields
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** a `RandomNumberGenerator` wrapping one or more `RngParameters` children
**When** serialized to all three codecs
**Then** nesting is correct and round-trip is lossless

**Given** a `ProtectionStorageMasks` instance
**When** serialized to all three codecs and deserialized
**Then** round-trip is lossless

**Given** all implementations are complete
**When** `META-INF/services` is audited
**Then** all new classes appear in all three codec service files

---

### Story 3.7: Wire Capability Structures into Query Response Payload

As a library consumer,
I want the existing `QueryOpResponsePayload` updated to include all capability and profile structures from Stories 3.4a, 3.4b, 3.5, and 3.6,
So that I can parse complete KMIP v2.1+ Query responses with full server capability information.

**Depends on:** Stories 3.4, 3.5, 3.6

**Acceptance Criteria:**

**Given** a full `QueryOpResponsePayload` with all optional capability fields populated (`CapabilityInformation`, `ProfileInformation`, `ClusterInfo`, `ValidationInformation`, `RandomNumberGenerator`, `ProtectionStorageMasks`)
**When** serialized to TTLV, JSON, and XML
**Then** all fields appear in spec-defined order (KMIP v2.1 §6.26)

**Given** the serialized output
**When** deserialized
**Then** all nested capability structures are fully reconstructed and the result equals the original

**Given** a minimal `QueryOpResponsePayload` with only required fields
**When** serialized
**Then** optional capability fields are absent from the output (not serialized as null/empty)

---

## Epic 4: Spec Completeness & Quality Gate

Library is fully spec-compliant with no enumeration gaps, no description string mismatches causing deserialization failures, all types covered by round-trip tests, and the JaCoCo coverage gate green.

### Story 4.1: Implement `ItemType` Enumeration

As a library consumer,
I want the `ItemType` enumeration (v2.0+) implemented with all spec-defined values,
So that I can use the last missing KMIP enumeration without gaps.

**Developer Note:** Use the `bmad-generate-kmip-code` skill with the `enum` entity type to generate the initial boilerplate for `ItemType`.

**Acceptance Criteria:**

**Given** `ItemType` class exists in `model/core/enumeration/` with all v2.0 values (Boolean, BigInteger, ByteString, DateTime, DateTimeExtended, Enumeration, Integer, Interval, LongInteger, TextString) and v3.0 additions (Identifier, NameReference, Reference)
**When** each value's `supportedVersions` is checked
**Then** v2.0 values include `V2_0`, `V2_1`, `V3_0` and v3.0-only values include only `V3_0`

**Given** `ItemType.isSupportedFor(KmipSpec.V1_2)` is called on any value
**Then** it returns `false` (V2_0+ only)

**Given** an `ItemType` value is serialized to JSON and XML
**When** deserialized
**Then** the correct enum value is restored

**Given** the implementation is complete and `META-INF/services` is audited
**Then** `ItemType` appears in all three codec service files

---

### Story 4.2: Audit and Fix All Enumeration Description Strings for XML/JSON Deserialization

As a library consumer,
I want every KMIP enumeration description string across all enumeration classes audited against the OASIS spec text and corrected where mismatches exist,
So that deserialization from any spec-compliant XML/JSON document succeeds without case, spacing, or punctuation mismatches — for every enumeration in the library.

**Acceptance Criteria:**

**Given** the full set of enumeration classes in `model/core/enumeration/` (all ~50 enumeration types across v1.2–v3.0)
**When** each value's description string is systematically compared against the OASIS KMIP specification text using `docs/kmip-spec/chunks/enumerations/` as the authoritative source
**Then** a complete change list is produced showing every mismatch (enumeration class, value name, current string, spec string) before any edits are applied

**Given** the change list is reviewed and approved
**When** corrections are applied to all mismatching description strings
**Then** every description string exactly matches the corresponding spec text (same casing, spacing, punctuation, and abbreviation)

**Given** a spec-compliant JSON document containing enum values as their spec-defined string labels
**When** deserialized using the Jackson JSON codec for every enumeration type
**Then** the correct `Value` instance is returned with no `UnknownValue` fallbacks

**Given** the same documents in XML format
**When** deserialized
**Then** the correct `Value` instance is returned for every enumeration type

**Given** all corrections are applied
**When** the full test suite runs
**Then** no test regressions are introduced and any previously-failing deserialization round-trips now pass

---

### Story 4.3: Audit `supportedVersions` for All Version-Removed Enum Values

As a library consumer,
I want all enumeration values removed or reserved in later KMIP versions to correctly return `false` from `isSupportedFor()` for those versions,
So that the library refuses to use deprecated values in wrong version contexts.

**Acceptance Criteria:**

**Given** the complete list of version-removed values from `docs/kmip-pending-implementation.md` §4 (e.g., `Undo` removed from `BatchErrorContinuationOption` in v2.0, `Canceled` removed from `CancellationResult` in v2.0, `Template` removed from `ObjectType` in v2.0, etc.)
**When** `isSupportedFor()` is called for the removing version on each affected value
**Then** it returns `false`

**Given** a value re-added in a later version (e.g., `PGP` in `CertificateType`: removed v2.0, re-added v2.1)
**When** `isSupportedFor()` is called for v1.4, v2.0, and v2.1
**Then** it returns `true`, `false`, `true` respectively

**Given** the audit is complete
**When** all existing tests run
**Then** no regressions are introduced

---

### Story 4.4a: Complete Test Suites for Epic 1 Payloads

As a developer,
I want full `KmipSerializationTestSuite` test suites for all payloads implemented in Epic 1 (Login, Logout, DelegatedLogin, SetAttribute, AdjustAttribute — 7 payloads),
So that Epic 1 payload implementations are verified correct and regressions are detected.

**Acceptance Criteria:**

**Given** each of the 7 Epic 1 payloads now has a full implementation
**When** its test suite is upgraded with concrete test data (minimum: one minimal instance, one fully-populated instance per payload)
**Then** TTLV, JSON, and XML serialization tests all pass for every payload

**Given** any Epic 1 test still has `// TODO validateComponents` annotations
**When** this story is complete
**Then** all such annotations are resolved — either with actual validation logic or with a justified removal

**Given** Epic 1 test suites run
**When** JaCoCo coverage is measured for Epic 1 classes
**Then** line coverage is ≥90% for all Epic 1 payload classes

---

### Story 4.4b: Complete Test Suites for Epic 2 Payloads

As a developer,
I want full `KmipSerializationTestSuite` test suites for all payloads implemented in Epic 2 (GetConstraints, SetConstraints, SetDefaults, QueryAsyncRequests request+response, Poll, Pkcs11 request+response, Import, Export request+response, Process request+response — 11 payloads),
So that Epic 2 payload implementations are verified correct and regressions are detected.

**Acceptance Criteria:**

**Given** each of the 11 Epic 2 payloads now has a full implementation
**When** its test suite is upgraded with concrete test data (minimum: one minimal instance, one fully-populated instance per payload)
**Then** TTLV, JSON, and XML serialization tests all pass for every payload

**Given** any Epic 2 test still has `// TODO validateComponents` annotations
**When** this story is complete
**Then** all such annotations are resolved

**Given** Epic 2 test suites run
**When** JaCoCo coverage is measured for Epic 2 classes
**Then** line coverage is ≥90% for all Epic 2 payload classes

---

### Story 4.4c: Complete Test Suites for Epic 3 Payloads and Capability Structures

As a developer,
I want full `KmipSerializationTestSuite` test suites for all payloads and structures implemented in Epic 3 (CreateUser, CreateGroup, CreateCredential — 3 payloads; CapabilityInformation + sub-structures, ProfileInformation, ClusterInfo, ValidationInformation, RngParameters, RandomNumberGenerator, ProtectionStorageMasks — capability structures),
So that Epic 3 implementations are verified correct and the full Query response round-trip is validated end-to-end.

**Acceptance Criteria:**

**Given** each of the 3 Epic 3 payloads and all Epic 3 capability structures now have full implementations
**When** test suites are upgraded with concrete test data
**Then** TTLV, JSON, and XML serialization tests all pass for every class

**Given** the full `QueryOpResponsePayload` (Story 3.7) with all capability fields populated
**When** serialized and deserialized across all three codecs
**Then** end-to-end round-trip is lossless — this is the integration-level acceptance test for Epic 3

**Given** all Epic 3 test suites run
**When** JaCoCo coverage is measured
**Then** line coverage is ≥90% across all Epic 3 classes

---

### Story 4.5: Audit and Complete META-INF/services SPI Registrations

As a developer,
I want all new types added in Epics 1–4 verified in `META-INF/services` for all three codec interfaces,
So that the runtime codec system discovers every type automatically without silent failures at deserialization time.

**Acceptance Criteria:**

**Given** the complete list of new types added across Epics 1–4
**When** `META-INF/services` files for the TTLV, JSON, and XML codec interfaces are audited (use the `kmip-codec-registrar` agent)
**Then** every new type appears in all three service files

**Given** a type is correctly registered
**When** the codec attempts to deserialize a byte stream for that type from cold start (no warm-up)
**Then** the type is discovered and deserialized without `ClassNotFoundException` or silent unknown-type fallbacks

**Given** the audit finds any missing registration
**When** it is added
**Then** the corresponding codec round-trip test passes

---

### Story 4.6: Unify Remaining Attribute Test Suites to Interface Pattern

As a developer,
I want any remaining attribute test classes still extending abstract base classes migrated to the `KmipSerializationTestSuite` interface pattern,
So that the attribute test suite is consistent with the rest of the library's test approach.

**Acceptance Criteria:**

**Given** all attribute test classes are audited for abstract base class usage
**When** any are found that do not implement `KmipSerializationTestSuite` (or its typed specialization)
**Then** they are migrated to the interface pattern following the established convention from commit `f778fbf3`

**Given** the migration is complete
**When** all tests run
**Then** no test regressions are introduced and JaCoCo coverage does not decrease