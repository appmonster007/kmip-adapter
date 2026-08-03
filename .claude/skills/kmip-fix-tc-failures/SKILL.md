---
name: kmip-fix-tc-failures
description: >
  Diagnoses and fixes failures in KmipV21VerificationTest#testKmip21TestCases.
  Runs the test, triages failures into categories, looks up the KMIP spec HTML/scraped
  docs as the source of truth, applies fixes (using generators for new types, direct
  edits for patching existing ones), and re-verifies. Skips and logs failures it
  cannot auto-classify. Trigger when the user says "fix TC failures", "improve
  verification score", "continue TC fixes", or "run the TC fixer skill".
---

# KMIP TC Failure Fixer

**Your role:** Maximize the number of passing messages in `KmipV21VerificationTest#testKmip21TestCases` (currently 1410 total messages across all TC XML files in `docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1/`). For every failure, consult the KMIP spec HTML/scraped docs **first** before reading code. Apply fixes using the generator scripts for new types and direct `Edit` calls for patching existing structures. Skip and log anything ambiguous; never guess.

---

## Fundamental constraints (always enforce)

1. **Spec is source of truth.** Before touching code, read the relevant section in `docs/kmip-spec/v2.x/scraped/` (or the raw HTML under `docs/kmip-spec/v2.x/`) to confirm the correct field names, types, optionality, and wire order.
2. **Version separation.** Any type that differs between KMIP spec versions lives in a separate Java class in its version package. Never add v2.x fields to a `v1x2/` class — create a new class in `v2x1/` and narrow the old class's `supportedVersions` to exclude v2.1+. See `bmad-generate-kmip-code` skill for the full rule set.
3. **Round-trip fidelity.** The test does `XML → domain object → XML` and compares JSON trees. Every field that appears in the TC file must round-trip exactly: same element name, same encoding type, same ordering within the parent, same presence/absence.
4. **Generator first.** For any completely new type (class doesn't exist), use `./scripts/generators/generate.sh` with `--all`. Never hand-write codec files. After generation, fix the known template bug: `protected createDefault()` → `public createDefault()` in generated test files.
5. **Skip, don't guess.** If the root cause is unclear after spec + code lookup, log the failure to the skip log and move on. Never apply a speculative fix.

---

## Phase 1 — Run & collect failures

```bash
mvn test -pl . -Dtest="KmipV21VerificationTest#testKmip21TestCases" 2>&1 \
  | grep -E "^\[TC-|^Passed:" \
  | grep -v "Verified"
```

Capture the output. Extract:
- **Pass count** from the `Passed: N / 1410 messages` line.
- **Failure list**: each line is `[FILE.xml] Tag[index]: <reason>`.

Parse each failure into a structured record:
```
file:     TC-FOO-1-21.xml
tag:      RequestMessage | ResponseMessage
index:    0, 1, 2, ...
reason:   "null" | "Round-trip mismatch ... expected: <E> but was: <A>"
```

---

## Phase 2 — Triage into fix categories

For each failure, apply this decision tree in order:

### 2a. Reason is `null`
The batch item parser threw an exception that was silently swallowed. Root cause is always one of:
- A tag in the XML message has no registered class (`No class registered for tag 0xXXXXXX`)
- A deserializer switch throws `IllegalArgumentException: Unsupported tag: X`
- A factory `of()` throws because a required `@NonNull` field is absent

**Diagnosis steps:**
1. Extract the failing message from the TC file:
   ```bash
   # Find all RequestMessage/ResponseMessage elements and select the one at [index]
   python3 -c "
   import xml.etree.ElementTree as ET, sys
   tree = ET.parse('$TC_FILE')
   root = tree.getroot()
   msgs = root.findall('.//$TAG')  # TAG = RequestMessage or ResponseMessage
   print(ET.tostring(msgs[$INDEX], encoding='unicode'))
   " 2>/dev/null || grep -A200 "$TAG" "$TC_FILE" | head -100
   ```
2. List every XML element name in that fragment:
   ```bash
   grep -oP '(?<=<)[A-Za-z][A-Za-z0-9_]*(?=[ />])' <fragment> | sort -u
   ```
3. For each element name, look up its KmipTag description in `KmipTag.java`:
   ```bash
   grep -n '"ElementName"' src/main/java/org/purplebean/kmip/api/KmipTag.java
   ```
4. Check if a Java class is registered for that tag in the relevant spec+encoding combination:
   ```bash
   # Find which class uses this tag constant
   TAG_CONST="THE_TAG_CONSTANT"
   grep -rn "KmipTag\.Standard\.${TAG_CONST}" src/main/java/org/purplebean/kmip/model/
   ```
5. Check if the operation's request/response payload deserializer has a case for each tag:
   ```bash
   OPERATION="EncryptOp"
   find src/main/java -name "${OPERATION}RequestPayloadXmlDeserializer.java" -o \
        -name "${OPERATION}ResponsePayloadXmlDeserializer.java"
   grep "case KmipTag" <deserializer-file>
   ```

**Classify as one of:**
- `MISSING_TYPE` — no Java class registered for tag+encoding. Fix: generate.
- `MISSING_CASE` — class exists but deserializer switch lacks the case. Fix: add case to XML + JSON + TTLV deserializers.
- `STRICT_VALIDATION` — `of()` throws because a `@NonNull` field not present in TC message. Fix: make field optional if spec says so.
- `WRONG_FIELD_TYPE` — field exists in deserializer but mapped to wrong Java type. Fix: correct the type.

### 2b. Reason is `Round-trip mismatch`

Compare the expected JSON tree `<E>` and actual JSON tree `<A>`. Find the **first** key path where they differ.

**Sub-categories:**

| Diff pattern | Category | Fix |
|---|---|---|
| Key present in E, absent in A | `MISSING_FIELD_IN_OUTPUT` | Field not included in `getValue()` or dropped silently |
| Key absent in E, present in A | `EXTRA_FIELD_IN_OUTPUT` | Field included in `getValue()` that shouldn't be |
| Same key, different string value | `VALUE_TRANSFORM` | Serializer mutates the value (timezone normalization, mask ordering, enum name case) |
| BatchItem array in E, single object in A | `BATCH_ITEM_COUNT_MISMATCH` | A prior batch item threw, collapsing multiple items to one |
| Same fields but different element order | `FIELD_ORDER` | `getValue()` returns fields in wrong order vs TC wire order |
| Element name differs (e.g. `CertificateIssuerCn` vs `CertificateIssuerCN`) | `TAG_DESCRIPTION_WRONG` | Fix `KmipTag.Standard` description string to match TC element name exactly |

---

## Phase 3 — Spec lookup (mandatory before every fix)

**For EVERY fix, before touching code:**

1. Look up the structure/operation in the scraped spec:
   ```bash
   # Find the relevant section
   grep -n "StructureName\|OperationName" docs/kmip-spec/v2.x/scraped/*.md | head -20
   ```
2. Read the field table for the structure (name, type, occurrence, notes).
3. Check the TC XML to see which field variant is used (e.g. `ObjectType` vs `ObjectTypes`).
4. Reconcile: if spec says "one of `ObjectType` OR `ObjectTypes`", both must be supported as optional alternates. If spec says one mandatory form, the TC file must use that form.
5. Note the **wire order** of fields in the spec — `getValue()` must return them in that order.

**Spec file locations:**
```
docs/kmip-spec/v2.x/scraped/
  structures-v2.1.md       ← structure field tables
  enumerations-v2.1.md     ← enum values
  operations-v2.1.md       ← operation request/response payload fields
  attributes-v2.1.md       ← attribute definitions and flags
docs/kmip-spec/v2.x/
  kmip-spec-v2.1.html      ← authoritative HTML (use when scraped is ambiguous)
```

To search the raw HTML without loading it fully:
```bash
grep -o '.\{0,200\}StructureName.\{0,200\}' docs/kmip-spec/v2.x/kmip-spec-v2.1.html | head -20
```

---

## Phase 4 — Apply fixes by category

### `MISSING_TYPE` — Generate a new type

```bash
# 1. Determine module (see bmad-generate-kmip-code for version-to-module mapping)
# 2. Determine entity type (enum / datatype / structure)
# 3. Run generator
./scripts/generators/generate.sh <entity_type> --all -m <module> [--attr] [--type <JavaType>] <Name>

# 4. Fix protected → public in generated test file
find src/test -name "${Name}*Test.java" \
  -exec sed -i '' 's/protected \(.*createDefault()\)/public \1/' {} +

# 5. Fill in implementation (see bmad-generate-kmip-code for field-filling steps)
# 6. Add to KmipAttribute.register if --attr
# 7. Verify META-INF services entry was added
grep -r "${Name}" src/main/resources/META-INF/services/
```

### `MISSING_CASE` — Add switch case to deserializers

For each of the three deserializer files (XML, JSON, TTLV) for the affected structure:
1. Add `import` for the missing type's Java class.
2. Add the `case KmipTag.Standard.THE_TAG -> builder.fieldName(ctxt.readValue(p, TheType.class));` line.
3. For TTLV: use `mapper.readValue(p, TheType.class)`.
4. Also update `of()` in the domain model if it uses a separate `Map<KmipTag, List<KmipDataType>>` path.

**Pattern for adding a field to an existing structure (XML + JSON + TTLV):**
```java
// In XmlDeserializer.setValue():
case KmipTag.Standard.NEW_TAG -> builder.newField(ctxt.readValue(p, NewType.class));

// In JsonDeserializer.setValue():
case KmipTag.Standard.NEW_TAG -> builder.newField(ctxt.readValue(p, NewType.class));

// In TtlvDeserializer.setValue():
case KmipTag.Standard.NEW_TAG -> builder.newField(mapper.readValue(p, NewType.class));

// In domain model of():
if (map.containsKey(NewType.kmipTag)) {
    builder.newField((NewType) map.get(NewType.kmipTag).getFirst());
}

// In domain model getValue() — append in spec wire order:
return Stream.of(..., newField, ...)
    .filter(Objects::nonNull)...
```

### `STRICT_VALIDATION` — Relax validation

If the TC file omits a field that the model marks `@NonNull` or that `validate()` requires:
1. Confirm the spec marks it OPTIONAL.
2. Remove `@NonNull`, change constructor param to nullable, add null check in `getValue()`.
3. If `of()` uses `.getFirst()` on a potentially absent key — add a `containsKey` guard.

### `VALUE_TRANSFORM` — Fix serializer/deserializer logic

- **DateTime UTC normalization**: `OffsetDateTimeXmlDeserializer` must use `OffsetDateTime.parse(p.getText())` not `OffsetDateTime.ofInstant(Instant.parse(...), ZoneOffset.UTC)`.
- **Mask string ordering**: Do not change the canonical sort order without checking ALL TC files first. If ordering is inconsistent across TC files (some ascending, some descending) → `skip and log`.
- **Enum name case**: Fix the `description` string in `KmipTag.Standard` to exactly match the TC element name.

### `TAG_DESCRIPTION_WRONG` — Fix KmipTag description

```bash
grep -n "THE_OLD_DESCRIPTION" src/main/java/org/purplebean/kmip/api/KmipTag.java
# Edit the description string to match TC element name exactly
# Verify with: grep "THE_NEW_DESCRIPTION" docs/kmip-spec/v2.x/kmip-testcases/v2.1/cn01/test-cases/kmip-v2.1/*.xml | head -3
```

### `FIELD_ORDER` — Fix getValue() order

Read the spec wire order for the structure, then reorder the `Stream.of(...)` arguments in `getValue()` to match. The TC file's JSON preserves the XML wire order.

### `BATCH_ITEM_COUNT_MISMATCH` — Cascading failure

This happens when one batch item in a multi-item response throws, collapsing the array to a single object. Fix the root cause batch item first (it will be the one with `null` in the same ResponseMessage).

---

## Phase 5 — Version separation protocol

When a v2.1 TC reveals that an existing v1.x class is missing a field:

1. **Do NOT add v2.1 fields to the v1.x class.**
2. Narrow the v1.x class: change `supportedVersions` to exclude V2_0, V2_1, V3_0, etc.
3. Generate a new v2.1 class:
   ```bash
   ./scripts/generators/generate.sh structure --all -m v2x1 -s <sub_package> StructureName
   ```
4. In the new class: copy all fields from v1.x, add v2.1-specific fields.
5. Copy deserializer switch cases from v1.x deserializer, add new cases.
6. Register with appropriate interfaces (ResponsePayloadStructure, RequestPayloadStructure, etc.).

---

## Phase 6 — Batch vs. single fix decision

Apply this rule:
- **Batch** if multiple failures share the **same root cause** (same missing switch case, same missing field in the same structure, same KmipTag description fix). Apply all at once, then verify together.
- **Single** if failures are in different structures or require spec research per failure. Fix one, verify compile + targeted test, then continue.

After ANY set of fixes:
```bash
mvn compile -pl . -q 2>&1 | grep -E "ERROR|error:" | head -20
```
If compile fails, fix errors before running tests.

---

## Phase 7 — Verify and iterate

After fixes:
```bash
mvn test -pl . -Dtest="KmipV21VerificationTest#testKmip21TestCases" 2>&1 \
  | grep -E "Passed:|verification failure"
```

Compare new pass count to previous. If count decreased, a fix introduced a regression — revert and re-diagnose.

For targeted diagnosis of a specific file:
```bash
# Point testSpecificFile at the failing file and use diagnoseFile
# (edit KmipV21VerificationTest.testSpecificFile temporarily)
mvn test -pl . -Dtest="KmipV21VerificationTest#testSpecificFile" 2>&1 | grep -v "^\[INFO\]\|^\[WARNING\]"
```

---

## Phase 8 — Skip log

For any failure that cannot be auto-classified or where the spec is ambiguous, append to the skip log (print to output — do NOT create a file):

```
SKIP LOG ENTRY
  File:     TC-FOO-1-21.xml
  Message:  RequestMessage[2]
  Category: UNCLASSIFIED | SPEC_AMBIGUOUS | CROSS_FILE_INCONSISTENCY
  Reason:   <one-sentence description of why this cannot be auto-fixed>
  Evidence: <the specific expected vs actual diff or XML fragment>
  Needs:    <what human input is required>
```

After the run, print the full skip log and ask the user for direction on each entry.

---

## Common fix patterns reference

### Adding DefaultsInformation to a Query response payload

The v1.2 `QueryOpResponsePayload` must NOT be modified. Instead, create a v2.1 version:
```bash
./scripts/generators/generate.sh structure --all -m v2x1 -s response/payload QueryOpResponsePayload
```
Copy all v1.2 fields, add `DefaultsInformation defaultsInformation` (optional). Register for V2_0, V2_1, V3_0. Narrow the v1.2 class to V1_2, V1_3, V1_4.

### LocatedItems in Locate response

`LocateOpResponsePayload` in v1.2 lacks `LocatedItems`. Create `v2x1/structure/response/payload/LocateOpResponsePayload.java` with `LocatedItems locatedItems` (optional Integer, tag `LOCATED_ITEMS`) and all existing fields. Narrow v1.2 to exclude V2_x.

### ObjectType vs ObjectTypes in ObjectDefaults

Per spec, `ObjectDefaults` can contain either `ObjectType` (singular, one occurrence) or `ObjectTypes` (plural wrapper containing 1+ `ObjectType` entries). Support BOTH as optional alternates. In `ObjectDefaults`:
- `private final ObjectTypes objectTypes;` — when TC uses `<ObjectTypes>` wrapper
- `private final ObjectType objectType;` — when TC uses `<ObjectType>` directly
`getValue()` emits whichever is non-null.

### LoginOpRequestPayload

The Login request payload contains `LeaseTime` (INTERVAL) and `RequestCount` (INTEGER), both optional. The `Credential` is in the `Authentication` element of the request header, NOT in the payload.

---

## Execution checklist per fix

- [ ] Read spec scraped doc for structure/field
- [ ] Confirm field name matches TC XML element name exactly
- [ ] Confirm encoding type matches
- [ ] Confirm optionality (required vs optional)
- [ ] Confirm wire order
- [ ] Apply fix (generator or Edit)
- [ ] Compile clean
- [ ] Targeted test passes
- [ ] No regression in previously passing messages
