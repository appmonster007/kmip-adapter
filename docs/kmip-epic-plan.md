# KMIP Implementation Epic Plan

Generated: 2026-07-12
Source: `docs/kmip-gap-analysis.md` + `docs/kmip-implementation-inventory.json`

---

## Summary Table

| Batch | Title | Blocked by | Est. files | Status |
|-------|-------|------------|------------|--------|
| 1 | Fix `AttributeName` version scope (P0 runtime bug) | none | 1 | TODO |
| 2 | Fix generator templates (prevent re-introducing bugs) | none | 2 | TODO |
| 3 | Fix wrong `defaultSpec` in 18 type test files | Batch 1 | 18 | TODO |
| 4 | Fix `SimpleRequestMessage` / `SimpleResponseMessage` deserializer inline TODOs | Batch 1 | 2 | TODO |
| 5 | Fix `ItemType` registered against wrong KMIP tag | none | 1 | TODO |
| 6 | Fix codec test NPEs — populate empty `.builder().build()` stubs (201 test files) | Batch 1, Batch 3 | 201 | TODO |
| 7 | Arch decision: `Name` sub-structure v3.0 guard | Batch 6 | 1–5 | TODO |
| 8 | Arch decision: `ItemType` vs `EncodingType` design intent | Batch 5 | 1–3 | TODO |

---

## Batch 1: Fix `AttributeName` version scope (P0 runtime bug)

**Goal:** Extend `AttributeName.supportedVersions` to include V2.1 and V3.0 so that all v2.1/v3.0 `KmipAttribute` implementations can be constructed without throwing `IllegalArgumentException`.
**Blocked by:** none
**Estimated files:** 1

### Files to change

- `src/main/java/org/purpleBean/kmip/model/core/type/AttributeName.java` — change line 18 from:
  ```java
  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);
  ```
  to:
  ```java
  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
  ```
  Also update the `static {}` registration block so the new specs are registered: add `KmipSpec.V2_1` and `KmipSpec.V3_0` to the loop's iteration set if it is driven from `supportedVersions` (it is — line 22 checks `spec == KmipSpec.UnknownVersion` and skips, so V2_1/V3_0 will automatically be registered once added to the set).

### Implementation notes

The `validate()` method delegates to `isSupported()` which checks `supportedVersions.contains(KmipContext.getSpec())`. The only change needed is the set declaration at line 18 and the corresponding `static {}` registration (which is already driven by `supportedVersions`, so it self-corrects). No structural change to the constructor or factory method `of()` is required.

The alternative fix (suppressing `AttributeName` construction in v2.0+ attribute types by returning `null` from `getAttributeName()`) is NOT preferred here: the interface contract is not annotated `@Nullable` and callers do not null-check. Extending the version set is the minimal, correct fix.

After this change the `static {}` block will register `AttributeName` for `KmipSpec.V2_1` and `KmipSpec.V3_0` via `KmipDataType.register()`. This is intentional — KMIP v2.0 removed `AttributeName` as a wire-format element but the Java model uses it as an internal lookup key (canonical name resolution), so its registration for newer versions is appropriate for this library's design.

### Acceptance criteria

- `mvn test -Dtest="AttributeNameTest"` passes (if a direct test exists; otherwise the downstream unblock is observed in Batch 3).
- Constructing `AlwaysSensitive.of("x")` (or any v2.1 attribute) inside a `KmipContext.withSpec(KmipSpec.V2_1, ...)` block no longer throws `IllegalArgumentException`.

---

## Batch 2: Fix generator templates

**Goal:** Prevent code generation from re-introducing the wrong-import and hardcoded-V1_2 bugs in any future `generate.sh` run.
**Blocked by:** none
**Estimated files:** 2

### Files to change

- `scripts/generators/templates/attribute/structure/AttributeStructureTest.java.template` — remove line 11:
  ```java
  import org.purpleBean.kmip.test.suite.KmipStructureTestSuite;
  ```
  `KmipStructureTestSuite` does not exist. Only `AbstractKmipStructureTestSuite` (the base class already referenced on line 16) exists. The template already correctly extends `AbstractKmipStructureTestSuite<{{ATTRIBUTE_NAME}}>` — the unused import is purely dead code that causes a compile error on any newly generated file.

- `scripts/generators/templates/attribute/datatype/AttributeDataType.java.template` — change line 28 from:
  ```java
  private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2); // TODO: Adjust supported versions
  ```
  to a parameterized placeholder that the generator script substitutes, e.g.:
  ```java
  private static final Set<KmipSpec> supportedVersions = Set.of({{SUPPORTED_VERSIONS}}); // generated from spec version
  ```
  The generator script `scripts/generators/generate.sh` (or the corresponding Python/shell script that drives the template) must be updated to pass the correct version set string for `{{SUPPORTED_VERSIONS}}` based on the attribute's introduced-version from the spec. For v2.1-introduced attributes this should expand to `KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0`; for v3.0-only attributes to `KmipSpec.UnknownVersion, KmipSpec.V3_0`; for v1.2 attributes (existing) to `KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0` (unless the spec deprecates them).

### Implementation notes

The template fix for BUG-002 is a one-line deletion — verify with `grep -n "KmipStructureTestSuite" scripts/generators/templates/attribute/structure/AttributeStructureTest.java.template` before and after.

The template fix for BUG-003 requires coordination with the generator script. Read `scripts/generators/generate.sh` to identify which variable/loop invokes `AttributeDataType.java.template` and what context variables are already passed. Add `SUPPORTED_VERSIONS` as a new substitution variable. The 18 already-generated model files (the v2.1/v3.0 type files) are NOT regenerated from this template fix alone — they are fixed individually in Batch 3. The template fix only guards against future codegen runs.

### Acceptance criteria

- `grep "KmipStructureTestSuite" scripts/generators/templates/attribute/structure/AttributeStructureTest.java.template` returns no output.
- Running `scripts/generators/generate.sh` for a v2.1 attribute produces a class with `KmipSpec.V2_1` (not `V1_2`) in `supportedVersions`.
- `mvn compile` passes on any newly generated file.

---

## Batch 3: Fix wrong `defaultSpec` in 18 type test files

**Goal:** Change `defaultSpec = KmipSpec.V1_2` to `defaultSpec = KmipSpec.V2_1` in 18 test files whose models only support V2.1/V3.0, so that `setupDefaultSpec()` uses a version the model actually supports.
**Blocked by:** Batch 1 (without the AttributeName fix, any test that constructs a v2.1 attribute inside `createDefault()` will still throw, masking the defaultSpec fix)
**Estimated files:** 18

### Files to change

All 18 files are under `src/test/java/org/purpleBean/kmip/model/core/type/`. In each file, change the `setupDefaultSpec()` method body from `defaultSpec = KmipSpec.V1_2;` to `defaultSpec = KmipSpec.V2_1;`.

- `src/test/java/org/purpleBean/kmip/model/core/type/AlwaysSensitiveTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/CommentTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/DescriptionTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/ExtractableTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/NeverExtractableTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/Pkcs12FriendlyNameTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/ProtectionPeriodTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/ProtectionStorageMaskTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/QuantumSafeTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/RotateAutomaticTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/RotateDateTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/RotateGenerationTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/RotateIntervalTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/RotateLatestTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/RotateOffsetTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/SensitiveTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/ShortUniqueIdentifierTest.java`
- `src/test/java/org/purpleBean/kmip/model/core/type/UniqueIdentifierTest.java`

Also update the corresponding model classes' `supportedVersions` declarations if they still contain only `KmipSpec.V1_2` (the root cause from BUG-003 in Batch 2 fixed the template, but the already-generated 18 model source files themselves may also carry the wrong version set). Cross-check each model class at `src/main/java/org/purpleBean/kmip/model/core/type/<Name>.java` — if `supportedVersions` still contains `V1_2` only, update it to `Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0)`.

### Implementation notes

The exact method signature to change is:
```java
@Override
protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;  // <-- change this
}
```
to:
```java
@Override
protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
}
```

No other method in these test files requires change. The model's `validate()` will pass because after Batch 1, `AttributeName` accepts V2.1.

To bulk-verify: `grep -rn "defaultSpec = KmipSpec.V1_2" src/test/java/org/purpleBean/kmip/model/core/type/` should return 0 lines after this batch.

### Acceptance criteria

- `mvn test -Dtest="AlwaysSensitiveTest,CommentTest,DescriptionTest,ExtractableTest,NeverExtractableTest,Pkcs12FriendlyNameTest,ProtectionPeriodTest,ProtectionStorageMaskTest,QuantumSafeTest,RotateAutomaticTest,RotateDateTest,RotateGenerationTest,RotateIntervalTest,RotateLatestTest,RotateOffsetTest,SensitiveTest,ShortUniqueIdentifierTest,UniqueIdentifierTest"` all pass.
- `grep -rn "defaultSpec = KmipSpec.V1_2" src/test/java/org/purpleBean/kmip/model/core/type/` returns no output.

---

## Batch 4: Resolve `SimpleRequestMessage` / `SimpleResponseMessage` inline TODOs

**Goal:** Remove or resolve the `// TODO: can be removed?` inline comments in the JSON and XML deserializers so these files are not flagged as stubs in future audits.
**Blocked by:** Batch 1 (without AttributeName fix, integration tests touching these paths may throw for v2.1 contexts)
**Estimated files:** 2

### Files to change

- `src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/core/structure/request/SimpleRequestMessageJsonDeserializer.java` — line 32: `// TODO: can be removed?` comment on the `while (p.nextToken() != ... END_ARRAY)` loop inside the `BATCH_ITEM` array branch. Investigate whether this loop guard is still needed given Jackson's streaming API behavior when the token is already positioned at an array start. If the loop is correct, remove the comment and replace with a brief explanatory note. If the loop is redundant, remove it and verify with the existing JSON codec tests.

- `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/core/structure/response/SimpleResponseMessageXmlDeserializer.java` — line 31: `// TODO: can be removed?` comment on the `if (p.isExpectedStartArrayToken())` guard inside the `BATCH_ITEM` branch. Same resolution approach as above.

### Implementation notes

On inspection, both deserializers are substantively complete — they handle `REQUEST_HEADER` / `RESPONSE_HEADER` and `BATCH_ITEM` (with both array and single-item variants). The TODOs are inline code-review comments, not unimplemented stubs.

Resolution options (developer chooses one per file):
1. If the array-branch loop/guard is needed (e.g., JSON encodes batch items as a JSON array): keep the code, remove the `// TODO` comment, add `// batch items may be encoded as a JSON array per KMIP JSON profile`.
2. If the guard is genuinely redundant given the abstract base class's token handling: delete the branch and verify with `mvn test -Dtest="SimpleRequestMessage*Test,SimpleResponseMessage*Test"`.

The analyst classified this as OPEN-002 requiring investigation. The developer must read `AbstractKmipDataTypeJsonDeserializer` and `AbstractKmipDataTypeXmlDeserializer` to determine if the base class already advances past array tokens before calling `setValue()`.

### Acceptance criteria

- Neither file contains `// TODO` comments after this batch.
- `mvn test -Dtest="SimpleRequestMessageJsonTest,SimpleResponseMessageXmlTest"` passes (adjust test class names to match actual test file names in `src/test/java/.../codec/`).

---

## Batch 5: Fix `ItemType` registered against wrong KMIP tag

**Goal:** Assign the correct KMIP tag to `ItemType` so it does not collide with `EXTENSION_ENUMERATION` (`0x420129`) and correctly identifies Item Type fields in TTLV/JSON/XML streams.
**Blocked by:** none
**Estimated files:** 1

### Files to change

- `src/main/java/org/purpleBean/kmip/model/core/enumeration/ItemType.java` — line 14: change:
  ```java
  public static final KmipTag kmipTag = KmipTag.Standard.EXTENSION_ENUMERATION.inst();
  ```
  to the correct KMIP spec tag for Item Type. The KMIP specification defines "Item Type" as a distinct enumeration. The correct tag must be resolved from the KMIP spec document or `docs/kmip-spec/kmip-all-versions-data.json`. The current scraper comment in `scrape_kmip_all_versions.py` (line 394) deliberately omits Item Type because the scraper author believed it maps to `EncodingType` — but `ItemType.java` already exists as a separate `KmipEnumeration` class. The developer must:
  1. Look up the KMIP-specified tag hex for "Item Type" in the official KMIP spec table of tags (section 9.1 of the KMIP spec PDF, or `docs/kmip-spec/kmip-all-versions-data.json`).
  2. Add a new constant to `KmipTag.Standard` enum for the Item Type tag (if not already present).
  3. Update `ItemType.kmipTag` to reference that constant.
  4. Verify the registration in `static {}` now targets the correct tag value.

  If the design decision is instead that `ItemType` is redundant with `EncodingType` (the scraper's original intent), then `ItemType.java` should be deleted and any references updated to use `EncodingType`. Document the decision as a comment in `scrape_kmip_all_versions.py`.

### Implementation notes

Currently `ItemType` is registered against `KmipTag.Standard.EXTENSION_ENUMERATION` which has tag value `0x420129`. This means any TTLV decoder encountering a legitimate Extension Enumeration field (tag `0x420129`) would erroneously route it to `ItemType`. Conversely, actual Item Type fields (whatever their tag is) will not be decoded into `ItemType` at all.

The fix requires a tag constant to exist in `KmipTag.Standard`. If the KMIP spec assigns a distinct hex tag to Item Type enumeration, add it there. If the spec genuinely uses `0x420129` for both Extension Enumeration and Item Type (unlikely), document that and leave the current tag but rename the constant.

OPEN-003 ("ItemType vs EncodingType design intent unclear") is handled in Batch 8 as a follow-on architecture decision after this tag fix is made. This batch only addresses the mechanical tag assignment.

### Acceptance criteria

- `ItemType.kmipTag` no longer points to `KmipTag.Standard.EXTENSION_ENUMERATION`.
- `KmipDataType.register()` for `ItemType` uses the spec-correct tag value.
- `mvn test -Dtest="ItemTypeTest"` passes (if an `ItemTypeTest.java` exists; verify with `find src/test -name "ItemTypeTest.java"`).
- `mvn compile` passes.

---

## Batch 6: Fix codec test NPEs — populate empty `.builder().build()` stubs

**Goal:** Eliminate `NullPointerException` failures in 201 codec test files by supplying valid field values in `createDefault()` and `createVariant()` for all 45 affected model classes.
**Blocked by:** Batch 1 (AttributeName fix), Batch 3 (correct defaultSpec so v2.1 models can be constructed under the right spec context)
**Estimated files:** 201

### Files to change

The following 201 test files all contain `.builder().build()` where the model has `@NonNull`-annotated constructor fields. Each file needs `createDefault()` and `createVariant()` populated with valid minimal instances. The files are grouped below by model class for easier execution.

**Group A — v2.1 structures (under `src/test/java/org/purpleBean/kmip/codec/*/model/v2_1/structure/`)**

| Model class | @NonNull fields (from gap analysis) | JSON test | XML test | TTLV test |
|-------------|--------------------------------------|-----------|----------|-----------|
| `AsynchronousCorrelationValues` | `List<AsynchronousCorrelationValue> asynchronousCorrelationValues` (min 1 element) | `AsynchronousCorrelationValuesJsonTest.java` | `AsynchronousCorrelationValuesXmlTest.java` | `AsynchronousCorrelationValuesTtlvTest.java` |
| `AsynchronousRequest` | 13 fields — check model class at `src/main/java/.../model/v2_1/structure/AsynchronousRequest.java` | `AsynchronousRequestJsonTest.java` | `AsynchronousRequestXmlTest.java` | `AsynchronousRequestTtlvTest.java` |
| `ObjectGroups` | `List<ObjectGroup> objectGroups`, `ObjectType objectType` (2 fields) | `ObjectGroupsJsonTest.java` | `ObjectGroupsXmlTest.java` | *(check if TtlvTest exists)* |
| `ObjectTypes` | 2 fields | `ObjectTypesJsonTest.java` | `ObjectTypesXmlTest.java` | *(check)* |
| `Objects` | 2 fields | `ObjectsJsonTest.java` | `ObjectsXmlTest.java` | *(check)* |
| `Operations` | 3 fields | `OperationsJsonTest.java` | `OperationsXmlTest.java` | *(check)* |
| `ProfileInformation` | 4 fields | `ProfileInformationJsonTest.java` | `ProfileInformationXmlTest.java` | *(check)* |
| `ProfileVersion` | 5 fields | `ProfileVersionJsonTest.java` | `ProfileVersionXmlTest.java` | *(check)* |
| `ProtectionStorageMasks` | 2 fields | *(check)* | `ProtectionStorageMasksXmlTest.java` | *(check)* |
| `Right` | 1 field | `RightJsonTest.java` | `RightXmlTest.java` | *(check)* |
| `Rights` | 2 fields | `RightsJsonTest.java` | `RightsXmlTest.java` | *(check)* |
| `RngParameters` | 4 fields | *(check)* | `RngParametersXmlTest.java` | *(check)* |
| `ValidationInformation` | 14 fields | *(check)* | `ValidationInformationXmlTest.java` | *(check)* |

**Group B — v2.1 operation payloads (under `src/test/java/org/purpleBean/kmip/codec/*/model/v2_1/structure/request/payload/` and `.../response/payload/`)**

| Model class | JSON test | XML test | TTLV test |
|-------------|-----------|----------|-----------|
| `GetConstraintsOpResponsePayload` | *(check)* | `GetConstraintsOpResponsePayloadXmlTest.java` | `GetConstraintsOpResponsePayloadTtlvTest.java` |
| `Pkcs11OpRequestPayload` | *(check)* | `Pkcs11OpRequestPayloadXmlTest.java` | `Pkcs11OpRequestPayloadTtlvTest.java` |
| `Pkcs11OpResponsePayload` | *(check)* | `Pkcs11OpResponsePayloadXmlTest.java` | `Pkcs11OpResponsePayloadTtlvTest.java` |
| `ProcessOpRequestPayload` | *(check)* | `ProcessOpRequestPayloadXmlTest.java` | `ProcessOpRequestPayloadTtlvTest.java` |
| `ProcessOpResponsePayload` | *(check)* | `ProcessOpResponsePayloadXmlTest.java` | `ProcessOpResponsePayloadTtlvTest.java` |
| `SetConstraintsOpRequestPayload` | *(check)* | `SetConstraintsOpRequestPayloadXmlTest.java` | `SetConstraintsOpRequestPayloadTtlvTest.java` |
| `SetDefaultsOpRequestPayload` | *(check)* | `SetDefaultsOpRequestPayloadXmlTest.java` | `SetDefaultsOpRequestPayloadTtlvTest.java` |
| `QueryAsynchronousRequestsOpRequestPayload` | *(check)* | `QueryAsynchronousRequestsOpRequestPayloadXmlTest.java` | `QueryAsynchronousRequestsOpRequestPayloadTtlvTest.java` |
| `QueryAsynchronousRequestsOpResponsePayload` | *(check)* | `QueryAsynchronousRequestsOpResponsePayloadXmlTest.java` | `QueryAsynchronousRequestsOpResponsePayloadTtlvTest.java` |
| `LoginOpRequestPayload` | *(check)* | `LoginOpRequestPayloadXmlTest.java` | `LoginOpRequestPayloadTtlvTest.java` |
| `DelegatedLoginOpRequestPayload` | *(check)* | `DelegatedLoginOpRequestPayloadXmlTest.java` | `DelegatedLoginOpRequestPayloadTtlvTest.java` |
| `GetConstraintsOpRequestPayload` | *(check)* | `GetConstraintsOpRequestPayloadXmlTest.java` | `GetConstraintsOpRequestPayloadTtlvTest.java` |

**Group C — v3.0 operation payloads (under `.../v3_0/structure/request/payload/`)**

| Model class | JSON test | XML test | TTLV test |
|-------------|-----------|----------|-----------|
| `CreateCredentialOpRequestPayload` | `CreateCredentialOpRequestPayloadJsonTest.java` | `CreateCredentialOpRequestPayloadXmlTest.java` | `CreateCredentialOpRequestPayloadTtlvTest.java` |
| `CreateGroupOpRequestPayload` | `CreateGroupOpRequestPayloadJsonTest.java` | `CreateGroupOpRequestPayloadXmlTest.java` | `CreateGroupOpRequestPayloadTtlvTest.java` |
| `CreateUserOpRequestPayload` | `CreateUserOpRequestPayloadJsonTest.java` | `CreateUserOpRequestPayloadXmlTest.java` | `CreateUserOpRequestPayloadTtlvTest.java` |

**Group D — v1.2 operation payloads (under `.../v1_2/structure/request/payload/` and `.../response/payload/`)**

| Model class | JSON test | XML test | TTLV test |
|-------------|-----------|----------|-----------|
| `CertifyOpResponsePayload` | `CertifyOpResponsePayloadJsonTest.java` | `CertifyOpResponsePayloadXmlTest.java` | `CertifyOpResponsePayloadTtlvTest.java` |
| `CreateKeyPairOpResponsePayload` | `CreateKeyPairOpResponsePayloadJsonTest.java` | `CreateKeyPairOpResponsePayloadXmlTest.java` | `CreateKeyPairOpResponsePayloadTtlvTest.java` |
| `CreateOpRequestPayload` | `CreateOpRequestPayloadJsonTest.java` | *(check)* | `CreateOpRequestPayloadTtlvTest.java` |
| `CreateOpResponsePayload` | `CreateOpResponsePayloadJsonTest.java` | `CreateOpResponsePayloadXmlTest.java` | `CreateOpResponsePayloadTtlvTest.java` |
| `CreateSplitKeyOpRequestPayload` | `CreateSplitKeyOpRequestPayloadJsonTest.java` | *(check)* | `CreateSplitKeyOpRequestPayloadTtlvTest.java` |
| `DeriveKeyOpRequestPayload` | `DeriveKeyOpRequestPayloadJsonTest.java` | *(check)* | `DeriveKeyOpRequestPayloadTtlvTest.java` |
| `DeriveKeyOpResponsePayload` | `DeriveKeyOpResponsePayloadJsonTest.java` | `DeriveKeyOpResponsePayloadXmlTest.java` | `DeriveKeyOpResponsePayloadTtlvTest.java` |
| `ExportOpResponsePayload` | `ExportOpResponsePayloadJsonTest.java` | `ExportOpResponsePayloadXmlTest.java` | `ExportOpResponsePayloadTtlvTest.java` |
| `HashOpRequestPayload` | `HashOpRequestPayloadJsonTest.java` | *(check)* | `HashOpRequestPayloadTtlvTest.java` |
| `ImportOpRequestPayload` | `ImportOpRequestPayloadJsonTest.java` | *(check)* | `ImportOpRequestPayloadTtlvTest.java` |
| `ReKeyKeyPairOpResponsePayload` | `ReKeyKeyPairOpResponsePayloadJsonTest.java` | `ReKeyKeyPairOpResponsePayloadXmlTest.java` | `ReKeyKeyPairOpResponsePayloadTtlvTest.java` |
| `ReKeyOpResponsePayload` | `ReKeyOpResponsePayloadJsonTest.java` | `ReKeyOpResponsePayloadXmlTest.java` | `ReKeyOpResponsePayloadTtlvTest.java` |
| `RecertifyOpResponsePayload` | `RecertifyOpResponsePayloadJsonTest.java` | `RecertifyOpResponsePayloadXmlTest.java` | `RecertifyOpResponsePayloadTtlvTest.java` |
| `RegisterOpRequestPayload` | `RegisterOpRequestPayloadJsonTest.java` | *(check)* | `RegisterOpRequestPayloadTtlvTest.java` |
| `RegisterOpResponsePayload` | `RegisterOpResponsePayloadJsonTest.java` | `RegisterOpResponsePayloadXmlTest.java` | `RegisterOpResponsePayloadTtlvTest.java` |

**Group E — Core structures (under `.../model/core/structure/` and `.../codec/*/model/core/structure/`)**

| Model class | JSON test | XML test | TTLV test |
|-------------|-----------|----------|-----------|
| `RandomNumberGenerator` | `RandomNumberGeneratorJsonTest.java` | `RandomNumberGeneratorXmlTest.java` | `RandomNumberGeneratorTtlvTest.java` |
| `RequestBatchItem` | *(check)* | *(check)* | *(check)* |
| `RequestMessage` | *(check)* | *(check)* | *(check)* |
| `SimpleResponseBatchItem` | `SimpleResponseBatchItemJsonTest.java` | `SimpleResponseBatchItemXmlTest.java` | `SimpleResponseBatchItemTtlvTest.java` |
| `SimpleResponseMessage` | `SimpleResponseMessageJsonTest.java` | `SimpleResponseMessageXmlTest.java` | `SimpleResponseMessageTtlvTest.java` |

### Implementation notes

For each affected test, the developer must:

1. Open the model class (e.g., `src/main/java/.../model/v2_1/structure/AsynchronousCorrelationValues.java`) and identify all `@NonNull`-annotated fields.
2. In the test file's `createDefault()` method, replace `ModelClass.builder().build()` with a builder call that supplies valid minimal values for every `@NonNull` field. Use the simplest valid value (e.g., `List.of(someValidInstance)` for a list, first enum value for enumerations, `"test"` for strings).
3. Do the same for `createVariant()`, using different values to test round-trip fidelity.
4. For model classes whose `@NonNull` fields are themselves complex structures (e.g., `AsynchronousRequest` has 13 fields including nested structures), write a private helper method `private static AsynchronousRequest minimalAsynchronousRequest()` in the test to keep `createDefault()` readable.

For `SimpleResponseMessage` and `SimpleResponseBatchItem`, the `RequestBatchItem` and `RequestMessage` fields likely require nested `RequestHeader`, `BatchItem` etc. — trace the field types from the model and supply the minimum valid graph. These are the highest-complexity test fixes in this batch.

Codec tests that test legitimately-empty payloads (e.g., `PingOpRequestPayloadXmlTest`, `LogOpResponsePayloadTtlvTest`) may appear in the 201 file count but their builders genuinely produce valid empty objects — verify before changing. The 45 model classes listed in BUG-005 are the definitive set to fix; the remaining files in the 201 count that correspond to correctly-empty models (from the "Stubbed Deserializers" section of the gap analysis) should be left as-is or annotated with a comment explaining they are legitimately empty.

### Acceptance criteria

- `mvn test -Dtest="AsynchronousCorrelationValuesJsonTest,AsynchronousCorrelationValuesXmlTest,AsynchronousCorrelationValuesTtlvTest"` passes as a spot-check.
- `mvn test` — total NPE failures attributable to BUG-005 drops to 0.
- `find src/test/java/org/purpleBean/kmip/codec -name "*.java" | xargs grep -l "builder().build()" | wc -l` drops from 201 to at most the number of legitimately-empty models (8, corresponding to the correctly-empty payloads).

---

## Batch 7: Architecture decision — `Name` sub-structure v3.0 reserved tags guard

**Goal:** Decide and implement whether `Name.getValue()` must guard against emitting `NameType` (`0x420054`) and `NameValue` (`0x420055`) child tags in v3.0 contexts, where the spec marks them as Reserved.
**Blocked by:** Batch 6 (codec tests must be green before a spec-correctness guard change is meaningful to validate)
**Estimated files:** 1–5

### Files to change

Architecture decision required before file list can be finalized. The decision is between two options:

**Option A — Runtime guard in `Name` model**: Add version-conditional logic to `Name.getValue()` (or `Name`'s codec serializers) that suppresses `NameType`/`NameValue` child elements when `KmipContext.getSpec()` is `V3_0` and instead emits the string value directly.

**Option B — Separate `NameV3` class**: Create a new `src/main/java/org/purpleBean/kmip/model/v3_0/structure/NameV3.java` that implements the v3.0 wire format, registered for V3.0 at the `NAME` tag, while the existing `Name.java` remains registered for V1.2–V2.1.

Files affected depend on the option chosen:
- **Option A**: `src/main/java/org/purpleBean/kmip/model/core/structure/Name.java` + its 3 codec serializers/deserializers (JSON, XML, TTLV) + corresponding test updates.
- **Option B**: New `src/main/java/org/purpleBean/kmip/model/v3_0/structure/NameV3.java` + 3 new codec files + 3 new test files; existing `Name.java` registers only for V1.2/V2.1.

### Implementation notes

The gap analysis notes (OPEN-001): "v3.0 spec reserves `NameType`/`NameValue` child tags — unclear if current `Name.getValue()` guards this." This is a spec-correctness issue for any v3.0 usage.

The architect must read:
- `src/main/java/org/purpleBean/kmip/model/core/structure/Name.java` — check if `getValue()` already checks `KmipContext.getSpec()`.
- KMIP v3.0 spec §2 (Name attribute): confirm the v3.0 wire format for Name.
- Precedent set by the Link/typed-link implementation (v3.0 uses 14 typed link classes; the project created `src/main/java/.../model/v3_0/structure/link/` directory). Option B follows this precedent.

### Acceptance criteria

- A decision is documented in an ADR or as a comment in the relevant file.
- If Option A: `Name` serializers do not emit `NameType`/`NameValue` when `KmipContext.getSpec() == KmipSpec.V3_0`.
- If Option B: `NameV3` exists, is registered for V3.0 at tag `0x420053`, and the existing `Name` class removes V3.0 from its `supportedVersions`.
- `mvn test` passes.

---

## Batch 8: Architecture decision — `ItemType` vs `EncodingType` design intent

**Goal:** Resolve OPEN-003: decide whether `ItemType` is a duplicate of `EncodingType` or a genuinely separate first-class KMIP enumeration, and act on that decision.
**Blocked by:** Batch 5 (tag fix must come first to know if a correctly-tagged `ItemType` makes sense)
**Estimated files:** 1–3

### Files to change

Architecture decision required. Two options:

**Option A — Keep `ItemType` as a distinct `KmipEnumeration`**: The correct KMIP tag (resolved in Batch 5) distinguishes it from `EncodingType`. Remove the scraper's exclusion comment, add `ItemType` to `IMPLEMENTED_ENUMS` in `scrape_kmip_all_versions.py`. Update the comment in `comprehensive_enum_sync.py` (line 35) that currently lists "Item Type" as a TTLV discriminator exempt from enumeration implementation.

**Option B — Delete `ItemType` and use `EncodingType`**: If the project's design intent is that `EncodingType` (an untagged Java enum at `org.purpleBean.kmip.api.EncodingType`) serves the same purpose, delete `src/main/java/org/purpleBean/kmip/model/core/enumeration/ItemType.java`, remove its SPI registrations, and update the scraper comment to formally document this design choice. Any callers of `ItemType` must be updated to use `EncodingType`.

Files affected:
- **Both options**: `scripts/comprehensive_enum_sync.py` (update the exclusion list comment).
- **Option A only**: `docs/kmip-spec/scrape_kmip_all_versions.py` (remove the omission comment and add to `IMPLEMENTED_ENUMS`).
- **Option B only**: `src/main/java/org/purpleBean/kmip/model/core/enumeration/ItemType.java` (delete) + any test file at `src/test/java/.../model/core/enumeration/ItemTypeTest.java` (delete or repurpose).

### Implementation notes

The current state after Batch 5 will be: `ItemType` has the correct KMIP spec tag. The architect then decides whether having both `ItemType` (a wire-tagged `KmipEnumeration`) and `EncodingType` (an untagged Java API enum) is coherent with the library's design. If other enumeration types follow the pattern of having both a Java-API enum and a `KmipEnumeration` class (check e.g. `State` vs any state-related API enum), Option A is consistent. If not, Option B is cleaner.

### Acceptance criteria

- One of the two options is implemented.
- `mvn test` passes.
- The scraper's exclusion comment in `scrape_kmip_all_versions.py` (line 394) is updated to accurately reflect the final design.
- `docs/kmip-implementation-inventory.json` entry for Item Type shows either `FULLY_IMPLEMENTED` (Option A) or is removed (Option B) — note: this inventory file is regenerated by the analyst agent, not hand-edited.

---

## Rationale

Batch 1 is the P0 unlock: every v2.1/v3.0 attribute construction throws at runtime, which means Batch 3's test fixes would pass compilation but fail at the first `createDefault()` call even after the `defaultSpec` correction. Batch 2 is independent of Batch 1 but must be done before the next codegen run — placing it in the same early window prevents the 18 wrong-defaultSpec files from being regenerated incorrectly. Batch 4 is a housekeeping fix to the SimpleMessage deserializers that can run any time after Batch 1 provides the correct attribute-construction context. Batch 5 is independent and low-risk (one file, one constant change) but gated before Batch 8 because the architecture decision in Batch 8 depends on knowing what the correctly-tagged `ItemType` looks like. Batch 6 is the largest body of work (201 files) and is deliberately last among the mechanical fixes: it requires Batch 1 (so v2.1 models construct) and Batch 3 (so tests run in the right spec context) to be in place first, otherwise many `createDefault()` calls would still fail for unrelated reasons, obscuring which fixes are actually needed. Batches 7 and 8 are architecture decisions that require a green build to validate — they are the final cleanup pass.

## Out of scope this round

- **Structural breaking-change guards** (CustomAttribute, AttributeIndex, CommonTemplateAttribute, Link v3.0 reservation, BatchCount/BatchOrderOption/UniqueBatchItemID v3.0, ObjectGroup v3.0, Template ObjectType value, CertificateSubject/CertificateIssuer old tags): the gap analysis documents these as existing in the codebase but does not identify them as active runtime failures in the current test suite. They represent spec-correctness work for v3.0 hardening and should be a separate epic once the test suite is green (Batches 1–6 complete). Prioritizing them before the NPE fixes would mean working against a broken test baseline.
- **24 stub operation tests** (those in `stub_operations` whose `request_test` or `response_test` is `STUBBED_TEST`): these are the same BUG-005 subset captured in Batch 6. No separate treatment needed.
- **Scraper re-run**: the analyst's inventory was generated 2026-07-12, the same date as this plan. No re-run of `kmip-spec-analyst` is needed before executing these batches.
