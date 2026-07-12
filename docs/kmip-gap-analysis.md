# KMIP Implementation Gap Analysis
Generated: 2026-07-12

## Summary

| Category | Count |
|---|---|
| Total spec objects (enums + structures + operations + attributes) | ~600+ |
| Enumerations (spec total) | 65 |
| Enumerations missing | 1 (`Item Type` — scraper deliberately omits it; class exists but uses wrong tag) |
| Operation payload pairs (spec total, across all versions) | 63 operations |
| Fully wired operation payload pairs | ~39 (all v1.2 ops + core v2.x/v3.0 ops with real fields) |
| Stub operation payloads (registered, field-level incomplete) | 24 |
| Structure models (all versions) | 254 files |
| Type/attribute models | 160 files |
| Stubbed deserializers (setValue has "No fields per KMIP spec") | 9 unique classes × 3 codecs = 27 codec files |
| Stubbed tests (createDefault uses empty builder on NonNull model) | 45 unique classes |
| Tests with wrong defaultSpec (V1_2 set for V2_1/V3_0-only types) | 18 test files |

---

## Missing Types (not implemented at all)

| Name | Version | Type | Notes |
|------|---------|------|-------|
| Item Type (tag mismatch) | v2.0 | Enumeration | `ItemType.java` EXISTS at `src/main/java/.../model/core/enumeration/ItemType.java` but uses `KmipTag.Standard.EXTENSION_ENUMERATION` instead of its own spec tag. The spec defines Item Type as a distinct enumeration (values: Structure, Integer, Long Integer, Big Integer, Enumeration, Boolean, Text String, Byte String, Date Time, Interval, Date Time Extended; plus v3.0 adds Name Reference, Identifier, Reference). The scraper intentionally omits it from IMPLEMENTED_ENUMS with a comment claiming it maps to `EncodingType`, but the project has created a separate `ItemType` KmipEnumeration class that is registered against the wrong tag. This is a classification inconsistency rather than a fully-missing type. |

## Stubbed Models (getValue() empty / wrong constructor)

| Class | Version | Type | Missing Fields | Notes |
|-------|---------|------|----------------|-------|
| `PingOpRequestPayload` | v2.1 | Request Payload | None (empty per spec) | `getValue()` returns `new KmipDataType[0]`. The v3.0 spec section 6.1.41 shows an empty request table — legitimately empty. |
| `PingOpResponsePayload` | v2.1 | Response Payload | None (empty per spec) | `getValue()` returns `new KmipDataType[0]`. Legitimately empty. |
| `LogOpResponsePayload` | v2.0 | Response Payload | None (empty per spec) | v2.1 spec §6.1.29 states "response payload returned is empty." Legitimately empty. |
| `LogoutOpResponsePayload` | v2.0 | Response Payload | None (empty per spec) | Legitimately empty per spec. |
| `InteropOpResponsePayload` | v2.0 | Response Payload | None (empty per spec) | Legitimately empty per spec. |
| `SetConstraintsOpResponsePayload` | v2.1 | Response Payload | None (empty per spec) | Legitimately empty per spec. |
| `SetDefaultsOpResponsePayload` | v2.1 | Response Payload | None (empty per spec) | Legitimately empty per spec. |
| `ObliterateOpResponsePayload` | v3.0 | Response Payload | None (empty per spec) | Legitimately empty per spec. |

> All 8 models above are **correctly empty** — the spec defines no fields for them. These should be reclassified as FULLY_IMPLEMENTED (model layer) once the scraper correctly identifies them.

## Stubbed Deserializers (setValue() has "No fields per KMIP spec")

All 9 stub deserializer classes correspond to **legitimately-empty payload models** (those listed above in Stubbed Models). Since the model has no fields, the `// No fields per KMIP spec` comment in each deserializer's `setValue()` is **correct** and not a real bug.

| Class | Codecs Affected | Notes |
|-------|----------------|-------|
| `PingOpRequestPayload` | JSON, XML, TTLV | Correctly empty per spec |
| `PingOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `LogOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `LogoutOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `InteropOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `SetConstraintsOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `SetDefaultsOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `ObliterateOpResponsePayload` | JSON, XML, TTLV | Correctly empty per spec |
| `SimpleRequestMessage` | JSON only | Has a `// TODO` comment in `SimpleRequestMessageJsonDeserializer` — needs investigation |
| `SimpleResponseMessage` | XML only | Has a `// TODO` comment in `SimpleResponseMessageXmlDeserializer` — needs investigation |

Total deserializer files with stubs: 27 (8 × 3 codecs + 2 single-codec TODOs).

## Stubbed Tests

### Category A: Wrong `defaultSpec` (V1_2 set for V2_1/V3_0-only types)

18 test files under `src/test/java/.../model/core/type/` have `defaultSpec = KmipSpec.V1_2` but the corresponding model class declares `supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0)`. The model's `validate()` will throw `IllegalArgumentException("Unsupported object type for...")` when `createDefault()` runs in a V1_2 context.

| Test File | Correct `defaultSpec` |
|-----------|----------------------|
| `AlwaysSensitiveTest.java` | `KmipSpec.V2_1` |
| `CommentTest.java` | `KmipSpec.V2_1` |
| `DescriptionTest.java` | `KmipSpec.V2_1` |
| `ExtractableTest.java` | `KmipSpec.V2_1` |
| `NeverExtractableTest.java` | `KmipSpec.V2_1` |
| `Pkcs12FriendlyNameTest.java` | `KmipSpec.V2_1` |
| `ProtectionPeriodTest.java` | `KmipSpec.V2_1` |
| `ProtectionStorageMaskTest.java` | `KmipSpec.V2_1` |
| `QuantumSafeTest.java` | `KmipSpec.V2_1` |
| `RotateAutomaticTest.java` | `KmipSpec.V2_1` |
| `RotateDateTest.java` | `KmipSpec.V2_1` |
| `RotateGenerationTest.java` | `KmipSpec.V2_1` |
| `RotateIntervalTest.java` | `KmipSpec.V2_1` |
| `RotateLatestTest.java` | `KmipSpec.V2_1` |
| `RotateOffsetTest.java` | `KmipSpec.V2_1` |
| `SensitiveTest.java` | `KmipSpec.V2_1` |
| `ShortUniqueIdentifierTest.java` | `KmipSpec.V2_1` |
| `UniqueIdentifierTest.java` (type, not enum) | `KmipSpec.V2_1` |

**Fix needed**: Change `defaultSpec = KmipSpec.V1_2` to `defaultSpec = KmipSpec.V2_1` in each `setupDefaultSpec()` method.

### Category B: Broken `createDefault()` / `createVariant()` — Empty builder on NonNull model

45 codec test classes (across JSON/XML/TTLV = ~135 test files total) call `.builder().build()` in `createDefault()` or `createVariant()` when the model constructor has `@NonNull`-annotated fields. These will throw `NullPointerException` at test runtime.

| Class | NonNull Fields | Codec Tests Affected |
|-------|---------------|---------------------|
| `AsynchronousCorrelationValues` | 2 | JSON, XML, TTLV |
| `AsynchronousRequest` | 13 | JSON, XML, TTLV |
| `CertifyOpResponsePayload` | unknown | JSON, XML, TTLV |
| `CreateCredentialOpRequestPayload` | 4 | JSON, XML, TTLV |
| `CreateGroupOpRequestPayload` | 2 | JSON, XML, TTLV |
| `CreateKeyPairOpResponsePayload` | unknown | JSON, XML, TTLV |
| `CreateOpRequestPayload` | unknown | JSON, XML, TTLV |
| `CreateOpResponsePayload` | unknown | JSON, XML, TTLV |
| `CreateSplitKeyOpRequestPayload` | unknown | JSON, XML, TTLV |
| `CreateUserOpRequestPayload` | 2 | JSON, XML, TTLV |
| `DelegatedLoginOpRequestPayload` | 2 | JSON, XML, TTLV |
| `DeriveKeyOpRequestPayload` | unknown | JSON, XML, TTLV |
| `DeriveKeyOpResponsePayload` | unknown | JSON, XML, TTLV |
| `ExportOpResponsePayload` | unknown | JSON, XML, TTLV |
| `GetConstraintsOpResponsePayload` | 2 | JSON, XML, TTLV |
| `HashOpRequestPayload` | unknown | JSON, XML, TTLV |
| `ImportOpRequestPayload` | unknown | JSON, XML, TTLV |
| `LoginOpRequestPayload` | 2 | JSON, XML, TTLV |
| `ObjectGroups` | 2 | JSON, XML, TTLV |
| `ObjectTypes` | 2 | JSON, XML, TTLV |
| `Objects` | 2 | JSON, XML, TTLV |
| `Operations` | 3 | JSON, XML, TTLV |
| `Pkcs11OpRequestPayload` | 2 | JSON, XML, TTLV |
| `Pkcs11OpResponsePayload` | 2 | JSON, XML, TTLV |
| `ProcessOpRequestPayload` | 2 | JSON, XML, TTLV |
| `ProcessOpResponsePayload` | 2 | JSON, XML, TTLV |
| `ProfileInformation` | 4 | JSON, XML, TTLV |
| `ProfileVersion` | 5 | JSON, XML, TTLV |
| `ProtectionStorageMasks` | 2 | JSON, XML, TTLV |
| `RandomNumberGenerator` | unknown | JSON, XML, TTLV |
| `ReKeyKeyPairOpResponsePayload` | unknown | JSON, XML, TTLV |
| `ReKeyOpResponsePayload` | unknown | JSON, XML, TTLV |
| `RecertifyOpResponsePayload` | unknown | JSON, XML, TTLV |
| `RegisterOpRequestPayload` | unknown | JSON, XML, TTLV |
| `RegisterOpResponsePayload` | unknown | JSON, XML, TTLV |
| `RequestBatchItem` | unknown | JSON, XML, TTLV |
| `RequestMessage` | unknown | JSON, XML, TTLV |
| `Right` | 1 | JSON, XML, TTLV |
| `Rights` | 2 | JSON, XML, TTLV |
| `RngParameters` | 4 | JSON, XML, TTLV |
| `SetConstraintsOpRequestPayload` | 2 | JSON, XML, TTLV |
| `SetDefaultsOpRequestPayload` | 2 | JSON, XML, TTLV |
| `SimpleResponseBatchItem` | unknown | JSON, XML, TTLV |
| `SimpleResponseMessage` | unknown | JSON, XML, TTLV |
| `ValidationInformation` | 14 | JSON, XML, TTLV |

**Fix needed**: Each test's `createDefault()` and `createVariant()` must be populated with valid field values to satisfy `@NonNull` constraints.

### Category C: Generator Template Bug — Dead Import

The template `scripts/generators/templates/attribute/structure/AttributeStructureTest.java.template` (line 11) imports `org.purpleBean.kmip.test.suite.KmipStructureTestSuite` which does **not exist** in the codebase. Only `AbstractKmipStructureTestSuite` exists. Any class generated from this template will fail to compile. No generated files are currently affected (no test file has this import), but the template must be fixed before it is used.

**Fix needed**: Remove line 11 (`import org.purpleBean.kmip.test.suite.KmipStructureTestSuite;`) from the template.

### Category D: Generator Template Bug — Hardcoded V1_2 supportedVersions

The template `scripts/generators/templates/attribute/datatype/AttributeDataType.java.template` (line 28) hardcodes `KmipSpec.V1_2` in every generated attribute class:
```java
private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2); // TODO: Adjust supported versions
```
This caused the 18 wrong-defaultSpec test files identified in Category A (they were generated from v2.1/v3.0 attribute types but all defaulted to V1_2).

**Fix needed**: The generator script must pass the correct version set to the template rather than hardcoding V1_2.

---

## Stub Operations (Registered, Field-Level Design Pending)

24 operations from the scraper's `IMPLEMENTED_OPS_LATER` set have both request and response payloads registered in `PAYLOAD_REGISTRY`, but some are in varying states of completeness.

### Operations with fully-wired models AND deserializers (correctly "in progress" or complete):

| Operation | Hex | Version | Request Fields | Response Fields | Deserializer Status |
|---|---|---|---|---|---|
| Adjust Attribute | `0x00000030` | v2.0 | `uniqueIdentifier`, `currentAttribute`, `adjustmentType` | `uniqueIdentifier`, `attribute` | Wired (has case statements) |
| Create Credential | `0x0000003F` | v3.0 | `credentialType`, `commonAttributes`, `attributes`, `privateKeyAttributes` | `uniqueIdentifier`, `privateUniqueIdentifier` | Wired |
| Create Group | `0x0000003C` | v3.0 | `attributes` | `uniqueIdentifier` | Wired |
| Create User | `0x0000003E` | v3.0 | `attributes` | `uniqueIdentifier` | Wired |
| Deactivate | `0x00000040` | v3.0 | `uniqueIdentifier`, `deactivationReason` | `uniqueIdentifier` | Wired |
| Delegated Login | `0x0000002F` | v2.0 | `uniqueIdentifier`, `credential` | `credential` | Wired |
| Export | `0x0000002B` | v1.4 | `uniqueIdentifier`, `keyFormatType`, `keyWrapType` | `uniqueIdentifier`, `objectType`, `kmipObject` | Wired |
| Get Constraints | `0x00000038` | v2.1 | `uniqueIdentifier` (optional) | `uniqueIdentifier`, `constraints` | Wired |
| Import | `0x0000002A` | v1.4 | `uniqueIdentifier`, `objectType`, `replaceExisting`, `keyWrapType`, `kmipObject` | `uniqueIdentifier` | Wired |
| Interop | `0x00000034` | v2.0 | `interopFunction`, `interopIdentifier`, `attribute` | (empty per spec) | Request wired; Response legitimately empty |
| Log | `0x0000002C` | v2.0 | `logMessage` | (empty per spec) | Request wired; Response legitimately empty |
| Login | `0x0000002D` | v2.0 | `credential` | `uniqueIdentifier` | Wired |
| Logout | `0x0000002E` | v2.0 | `uniqueIdentifier` | (empty per spec) | Request wired; Response legitimately empty |
| Obliterate | `0x0000003D` | v3.0 | `uniqueIdentifier` | (empty per spec) | Request wired; Response legitimately empty |
| PKCS#11 | `0x00000033` | v2.0 | `uniqueIdentifier`, `pkcs11Function`, `pkcs11InputParameters` | `pkcs11ReturnCode`, `pkcs11OutputParameters` | Wired |
| Ping | `0x0000003B` | v2.1 | (empty per spec) | (empty per spec) | Legitimately empty both directions |
| Poll | `0x0000001A` | v1.2 | `asynchronousCorrelationValue`, `maximumResponseSize` | `responseMessage`, `correlationValue` | Wired |
| Process | `0x0000003A` | v2.1 | `uniqueIdentifier`, `attribute` | `data`, `correlationValue` | Wired |
| Query Asynchronous Requests | `0x00000039` | v2.1 | `asynchronousCorrelationValue`, `maximumItems` | `asynchronousCorrelationValues` | Wired |
| Re-Provision | `0x00000035` | v2.0 | `certificateRequest`, `certificate` | `uniqueIdentifier` | Wired |
| Set Attribute | `0x00000031` | v2.0 | `uniqueIdentifier`, `newAttribute` | `uniqueIdentifier` | Wired |
| Set Constraints | `0x00000037` | v2.1 | `uniqueIdentifier` (optional), `constraints` | (empty per spec) | Request wired; Response legitimately empty |
| Set Defaults | `0x00000036` | v2.1 | `defaultsInformation` | (empty per spec) | Request wired; Response legitimately empty |
| Set Endpoint Role | `0x00000032` | v2.0 | `endpointRole` | `endpointRole` | Wired |

> All 24 stub operations have **models with real fields** in their request payloads. The "stub" designation from the scraper refers to the fact that field-level testing (codec tests) uses `.builder().build()` stubs rather than properly constructed instances. The models and deserializers themselves are substantively implemented.

---

## Known Issues (Pre-identified)

### Issue 1: AttributeName Version Scope Bug

**File**: `src/main/java/org/purpleBean/kmip/model/core/type/AttributeName.java`, line 18

```java
private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);
```

`AttributeName` is only registered for V1.1 and V1.2. However, `getAttributeName()` in ALL attribute types (including v2.1+ types like `AlwaysSensitive`, `Sensitive`, `Comment`, `Description`, etc.) calls `AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()))`.

When `KmipContext.getSpec()` is `V2_1` or `V3_0`, constructing any attribute type will invoke `AttributeName.of()` which calls `new AttributeName(name)` → `validate()` → throws `IllegalArgumentException("Unsupported object type for V2_1: AttributeName")`.

**Impact**: ALL v2.1/v3.0 attribute types that implement `KmipAttribute.getAttributeName()` will throw at construction time. This is a P0 runtime bug.

**Fix needed**: Extend `AttributeName.supportedVersions` to include `KmipSpec.V2_1` and `KmipSpec.V3_0`, OR redesign v2.1/v3.0 attribute types to not call `AttributeName.of()` (since AttributeName is a v1.x concept replaced by tag-based attribute identification in v2.0+).

### Issue 2: `AttributeStructureTest.java.template` References Non-Existent Interface

**File**: `scripts/generators/templates/attribute/structure/AttributeStructureTest.java.template`, line 11

```java
import org.purpleBean.kmip.test.suite.KmipStructureTestSuite;
```

`KmipStructureTestSuite` does not exist. Only `AbstractKmipStructureTestSuite` exists at `src/test/java/.../test/suite/AbstractKmipStructureTestSuite.java`. No currently-generated file uses this import, but any new class generated from this template will fail to compile.

**Fix needed**: Remove the non-existent import from the template.

### Issue 3: `AttributeDataType.java.template` Hardcodes `KmipSpec.V1_2`

**File**: `scripts/generators/templates/attribute/datatype/AttributeDataType.java.template`, line 28

The template hardcodes `V1_2` as the only supported spec version regardless of the actual introduced version of the attribute type. This produced 18 model classes with incorrect version scope.

**Fix needed**: The generator must pass the actual minimum-version parameter to the template.

### Issue 4: 18 Test Files with Wrong `defaultSpec`

Documented above in "Stubbed Tests — Category A." All 18 are in `src/test/java/.../model/core/type/`.

**Fix needed**: Change `defaultSpec = KmipSpec.V1_2` → `defaultSpec = KmipSpec.V2_1` in each `setupDefaultSpec()`.

### Issue 5: ~60 Codec Test Files with Empty `.builder().build()` Stubs

Documented above in "Stubbed Tests — Category B." 45 unique model classes have broken codec tests across JSON/XML/TTLV (up to 135 broken test files). Some are v1.2-era structures (e.g., `CreateOpRequestPayload`, `RegisterOpRequestPayload`) that also have this problem.

**Fix needed**: Populate each `createDefault()` and `createVariant()` with valid field-instantiated objects.

---

## Structural Breaking Changes (from spec §7.2)

| Object | Introduced | Change |
|--------|-----------|--------|
| `CustomAttribute` (tag `42002D`) | v2.0 | Tag **Reserved** in v2.0+. `CustomAttribute.java` must guard against emission in v2.0+ contexts. |
| `AttributeIndex` (tag `420009`) | v2.0 | Tag **Reserved** in v2.0+. Was an integer qualifier for attributes in v1.x. |
| `CommonTemplateAttribute` (`42001F`) | v2.0 | Tag **Reserved** in v2.0+. Replaced by `CommonAttributes` (`420126`). |
| `Link` + `LinkType` + `LinkedObjectIdentifier` | v3.0 | Generic link tags **Reserved** in v3.0. Replaced by 14 typed link structures `CertificateLink`…`WrappingKeyLink` (tags `0x420190`–`0x42019D`). All 14 typed link classes are implemented in `src/main/java/.../model/v3_0/structure/link/`. |
| `Name` sub-structure (`NameType`, `NameValue`) | v3.0 | `Name Type` (`420054`) and `Name Value` (`420055`) **Reserved** in v3.0. `Name` (`420053`) carries string value directly. |
| `BatchCount` (`42000D`), `BatchOrderOption` (`420010`), `UniqueBatchItemID` (`420093`) | v3.0 | All **Reserved** in v3.0. Must not appear in v3.0 request/batch contexts. |
| `ObjectGroup` (`420056`) | v3.0 | **Reserved** in v3.0. Replaced by `ObjectGroups` aggregate (`420166`). |
| `Template` ObjectType value `0x6` | v3.0 | Value **Reserved** (removed) in v3.0 ObjectType enum. |
| `CertificateSubject`, `CertificateIssuer` (old tags `42001A`, `420015`) | v2.0 | **Reserved** in v2.0. Replaced by new sub-tag structures at `0x420108`–`0x42011F`. |
| `Credential` structure — new credential value types | v2.0–v3.0 | v2.0 adds OneTimePassword/HashedPassword/Ticket variants; v3.0 adds Password/Certificate variants. New `PasswordCredential`, `OtpCredential`, `HashedPasswordCredential` structures implemented in `src/main/java/.../model/v3_0/structure/`. |

---

## Fully Implemented

The following categories are fully implemented (model + codecs exist; not exhaustive):

**Enumerations (64/65)**: AdjustmentType, AlternativeNameType, AsynchronousIndicator, AttestationType, BatchErrorContinuationOption, BlockCipherMode, CancellationResult, CertificateRequestType, CertificateType, ClientRegistrationMethod, CredentialType, CryptographicAlgorithm, DataEnumeration, DeactivationReasonCode, DerivationMethod, DestroyAction, DigitalSignatureAlgorithm, DrbgAlgorithm, EncodingOption, EndpointRole, Ephemeral, Fips186Variation, HashingAlgorithm, InteropFunction, ItemType (tag wrong), KeyCompressionType, KeyFormatType, KeyRoleType, KeyValueLocationType, KeyWrapType, LinkType, MaskGenerator, NameType, NistKeyType, ObjectClass, ObjectGroupMember, ObjectType, OpaqueDataType, Operation, OtpAlgorithm, PaddingMethod, Pkcs11Function, ProcessingStage, ProfileName, ProtectionLevel, PutFunction, QueryFunction, RecommendedCurve, ResultReason, ResultStatus, RevocationReasonCode, RngAlgorithm, RngMode, RotateNameType, SecretDataType, ShreddingAlgorithm, SplitKeyMethod, SplitKeyPolynomial, State, TicketType, UniqueIdentifier (enum), UnwrapMode, UsageLimitsUnit, ValidationAuthorityType, ValidationType, ValidityIndicator, WrappingMethod

**Core Structures**: AlternativeName, ApplicationSpecificInformation, AttestationCredential, Attribute, Attributes, Authentication, Certificate, CertificateIdentifier, CertificateIssuer, CertificateSubject, CommonAttributes, CommonTemplateAttribute, Constraint, Constraints, Credential, CredentialValueGenericStructure, CryptographicDomainParameters, CryptographicParameters, CustomAttribute, DefaultsInformation, DerivationParameters, DeviceCredential, Digest, EncryptionKeyInformation, ExtensionInformation, KeyBlock, KeyMaterialStructure, KeyValueLocation, KeyValueStructure, KeyWrappingData, KeyWrappingSpecification, Link, MACSignatureKeyInformation, MessageExtension, Name, Nonce, ObjectDefaults, OpaqueObject, PgpKey, PrivateKey, PrivateKeyAttributes, PrivateKeyTemplateAttribute, ProtocolVersion, PublicKey, PublicKeyAttributes, PublicKeyTemplateAttribute, RandomNumberGenerator, RevocationReason, SecretData, ServerInformation, SplitKey, SymmetricKey, Template, TemplateAttribute, TransparentDhPrivateKey, TransparentDhPublicKey, TransparentDsaPrivateKey, TransparentDsaPublicKey, TransparentEcdhPrivateKey, TransparentEcdhPublicKey, TransparentEcdsaPrivateKey, TransparentEcdsaPublicKey, TransparentEcmqvPrivateKey, TransparentEcmqvPublicKey, TransparentRsaPrivateKey, TransparentRsaPublicKey, TransparentSymmetricKey, UsageLimits, UsernameAndPassword, VendorExtension, X509CertificateIdentifier, X509CertificateIssuer, X509CertificateSubject

**V2.1 Structures**: AsynchronousCorrelationValues, AsynchronousRequest, AttributeReference, CapabilityInformation, CurrentAttribute, NewAttribute, ObjectGroups, ObjectTypes, Objects, Operations, ProfileInformation, ProfileVersion, ProtectionStorageMasks, Right, Rights, RngParameters, Ticket, TransparentEcPrivateKey, TransparentEcPublicKey, ValidationInformation

**V3.0 Structures**: CertificateLink, ChildLink, CredentialInformation, DeactivationReason, DerivationObjectLink, DerivedObjectLink, HashedPasswordCredential, NextLink, OtpCredential, ParentLink, PasswordCredential, Pkcs12CertificateLink, Pkcs12PasswordLink, PreviousLink, PrivateKeyLink, PublicKeyLink, ReplacedObjectLink, ReplacementObjectLink, WrappingKeyLink

**V1.2 Operations**: Activate, AddAttribute, Archive, Cancel, Certify, Check, Create, CreateKeyPair, CreateSplitKey, Decrypt, DeleteAttribute, DeriveKey, Destroy, DiscoverVersions, Encrypt, GetAttributeList, GetAttributes, Get, GetUsageAllocation, Hash, JoinSplitKey, Locate, MAC, MACVerify, ModifyAttribute, Notify, ObtainLease, Poll, Put, Query, ReKeyKeyPair, ReKey, Recertify, Recover, Register, Revoke, RNGRetrieve, RNGSeed, Sign, SignatureVerify, Validate

**Later Operations** (request+response both registered and field-populated): AdjustAttribute, CreateCredential, CreateGroup, CreateUser, Deactivate, DelegatedLogin, Export, GetConstraints, Import, Interop, Log, Login, Logout, Obliterate, PKCS11, Ping, Process, QueryAsynchronousRequests, ReProvision, SetAttribute, SetConstraints, SetDefaults, SetEndpointRole

