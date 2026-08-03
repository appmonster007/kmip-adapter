---
name: bmad-generate-kmip-code
description: "Scaffolds new KMIP enums, data types, and structures by running the project's generate.sh script. Use when the user asks to add/create a KMIP enum, datatype, or structure."
---

# Generate KMIP Boilerplate

**Your role:** Drive the full lifecycle — scaffold via `generate.sh`, scrape enum values from the KMIP spec HTML, fill in implementation details, and validate with `mvn test`. Do NOT read template files or write Java files manually.

---

## Core design principle: version-separated implementations

**Any KMIP type that differs between spec versions MUST be implemented as a separate Java class in a separate version package.** This applies to ALL type categories — enums, datatypes, and structures — not just message framing types. Never modify an existing version's class to accommodate a later version's changes.

### What counts as "changed" (triggers version separation)

| Change kind | Examples |
|-------------|---------|
| New child field added | `ClientCorrelationValue` added to `RequestHeader` in v2.1 |
| Child field removed | A field dropped between versions |
| Encoding type changed | Tag changes from INTEGER to TEXT_STRING |
| Java/KMIP data type changed | Field type changes between versions |
| Attribute status changed | Field becomes/stops being a KMIP attribute |
| Structure composition changed | Child field's own type changes, requiring a different class |
| Enum value set changed | Values added or removed for a given version |

### The rule

| Situation | Action |
|-----------|--------|
| Type exists only in one version range | Single class in that version's package |
| Type changes in any way in a later version | New class in the new version's package; existing class untouched |
| New class's `supportedVersions` overlaps with an older class | **Narrow the older class first**, then add the new class |

### Why

The codec registry is keyed on `(KmipSpec, tag)`. When two classes register for the same `(spec, tag)` pair, the last one loaded wins — which is non-deterministic. The only safe design is one class per `(version-range, tag)` pair, with no overlapping spec sets.

### Examples

```
# Structure with new fields in v2.1:
v1x2/structure/request/RequestHeader.java   supportedVersions = {V1_0, V1_1, V1_2, V1_3, V1_4}
v2x1/structure/request/RequestHeader.java   supportedVersions = {V2_0, V2_1, V3_0}

# Enum where values were added in v2.1:
core/enumeration/Operation.java             supportedVersions = {UnknownVersion, V1_2} (values up to v1.2)
v2x1/enumeration/Operation.java             supportedVersions = {UnknownVersion, V2_1, V3_0} (adds new values)

# Datatype where encoding type changed in v3.0:
v2x1/type/SomeValue.java                    supportedVersions = {V2_1}
v3x0/type/SomeValue.java                    supportedVersions = {V3_0} (different encoding)
```

If v3.0 further changes a v2.1 type: create the `v3x0/` class and narrow the v2x1 class's `supportedVersions` to exclude `V3_0`.

---

## Message framing types — a canonical version-separation example

The six message framing types illustrate the pattern and change with nearly every KMIP version. They also implement special interfaces and need additional `register()` calls — but the version-separation rule itself is the same as for any other type.

| Type | Sub-package flag |
|------|-----------------|
| `RequestMessage` | `-s request` |
| `RequestHeader` | `-s request` |
| `RequestBatchItem` | `-s request` |
| `ResponseMessage` | `-s response` |
| `ResponseHeader` | `-s response` |
| `ResponseBatchItem` | `-s response` |

These types implement special interfaces (`RequestMessageStructure`, `RequestHeaderStructure`, etc.) and must call `RequestMessageStructure.register(...)` / `RequestHeaderStructure.register(...)` in their static blocks — in addition to `KmipDataType.register(...)`. See the v1.2 classes as the authoritative template.

### Version-separated generation workflow (message framing types as example)

**Step A — Narrow the previous version's class** if its `supportedVersions` includes the new version's specs. Edit the field directly:

```java
// v1x2/structure/request/RequestBatchItem.java — before generating v2x1 version
// Change from: Set.of(UnknownVersion, V1_2, V1_3, V1_4, V2_0, V2_1, V3_0)
// Change to:   Set.of(UnknownVersion, V1_2, V1_3, V1_4)
```

Also update the static block's loop condition to match.

**Step B — Scaffold with the generator:**

```bash
# v2.1 message framing — all six types
./scripts/generators/generate.sh structure --all -m v2x1 -s request \
    RequestMessage RequestHeader RequestBatchItem

./scripts/generators/generate.sh structure --all -m v2x1 -s response \
    ResponseMessage ResponseHeader ResponseBatchItem
```

**Step C — Fill in fields** using the previous version's class as a template. Copy all fields from the older version and add/remove/change fields per the spec. Update:
- The `@Builder` field list and constructor
- `of(List<KmipDataType>, List<Exception>)` factory — map lookups for each field
- `getValue()` — return fields in spec-mandated wire order
- `validate()` — same logic as previous version
- Static block — add `XxxStructure.register(spec, ClassName.class, ClassName::of)` alongside `KmipDataType.register(...)`
- Deserializer `setValue()` switch — one case per child tag, copying from old deserializer and adding new cases

**Step D — Fill in scalar prerequisites.** If a new version introduces new scalar fields (e.g., `ClientCorrelationValue` in v2.1), generate them first:

```bash
./scripts/generators/generate.sh datatype --all -m v2x1 --type String \
    ClientCorrelationValue ServerCorrelationValue
```

Scalar datatypes (TextString, ByteString, etc.) do NOT need custom deserializer/serializer bodies — the abstract base classes handle them. The generated empty-body codec classes are correct as-is.

**Step E — Compile and run the verification test:**

```bash
mvn compile -pl . -q
mvn test -pl . -Dtest="KmipV21VerificationTest#diagnoseFirst10" 2>&1 | grep -E "FAIL|Verified|Passed"
```

---

## Pre-flight: collect all required inputs before running anything

For **every** entity type, the caller must confirm:

| Input | Notes |
|-------|-------|
| Entity name | Any casing — script normalises to PascalCase |
| Entity type | `enum`, `datatype`, or `structure` |
| KMIP version introduced | e.g. `1.2`, `2.1`, `3.0` — consult spec HTML |
| KMIP version removed/last seen | Last version it appears in, or "still present" |
| Is KMIP attribute? | Boolean — drives `--attr` flag and attribute test suite |
| Encoding type | For `datatype` only (see `--type` table below) |
| Child fields (structure only) | Recursively: name, type, version range, attr flag |
| Is this a version-separated re-implementation? | Applies to ANY type (enum/datatype/structure) that changed in any way — fields, encoding, composition, attribute status. If yes: identify the previous version's class to narrow and use as template |

For child fields of a structure, resolve each child type first (bottom-up): check whether the child already exists in the source tree. If it does not, generate and fill it before the parent.

---

## Module selection

Map the KMIP "introduced in" version to the correct `-m` flag:

| Introduced in | Module flag | `supportedVersions` generated | `defaultSpec` generated |
|---------------|-------------|-------------------------------|-------------------------|
| 1.2           | `-m core`   | `{UnknownVersion, V1_2}`      | `KmipSpec.V1_2`         |
| 1.3           | `-m v1x3`   | `{UnknownVersion, V1_3}`      | `KmipSpec.V1_3`         |
| 1.4           | `-m v1x4`   | `{UnknownVersion, V1_4}`      | `KmipSpec.V1_4`         |
| 2.0           | `-m v2x0`   | `{UnknownVersion, V2_0}`      | `KmipSpec.V2_0`         |
| 2.1           | `-m v2x1`   | `{UnknownVersion, V2_1, V3_0}`| `KmipSpec.V2_1`         |
| 3.0           | `-m v3x0`   | `{UnknownVersion, V3_0}`      | `KmipSpec.V3_0`         |

**Cross-verify in spec HTML before choosing a module.** Read the relevant heading in:
- `docs/kmip-spec/v1.x/scraped/enumerations-v1.2.md` (and v1.3, v1.4)
- `docs/kmip-spec/v2.x/scraped/enumerations-v2.0.md` (and v2.1)
- `docs/kmip-spec/v3.x/scraped/enumerations-v3.0.md`

If the entity appears in v1.2, use `core`. If it first appears in v2.1, use `v2x1`. If it's present across multiple versions, choose the earliest as the module and note the ceiling (last-seen version) — you'll need it for per-value `supportedVersions` in the `Standard` enum.

**For version-separated re-implementations** (e.g., a v2.1 variant of a v1.2 structure), use the *new* version's module (`-m v2x1`) even though the type name existed before.

---

## Step 1 — Scaffold

```bash
./scripts/generators/generate.sh <entity_type> --all \
  -m <module> [-s <sub_package>] [--attr] [--type <JavaType>] \
  <Name> [<Name2> ...]
```

Always use `--all` unless the user explicitly asks for a subset. The script prints every file it creates.

### `--type` values for `datatype`

| `--type`       | Java type        | KMIP encoding  |
|----------------|------------------|----------------|
| `ByteBuffer`   | `ByteBuffer`     | BYTE_STRING (default) |
| `String`       | `String`         | TEXT_STRING    |
| `Integer`      | `Integer`        | INTEGER        |
| `Long`         | `Long`           | LONG_INTEGER   |
| `BigInteger`   | `BigInteger`     | BIG_INTEGER    |
| `Boolean`      | `Boolean`        | BOOLEAN        |
| `OffsetDateTime` | `OffsetDateTime` | DATE_TIME    |

---

## Step 2 — Fill in enum values (enum entity only)

### 2a. Read the scraped spec for this version

```
docs/kmip-spec/v<family>/scraped/enumerations-v<version>.md
```

Find the `## <Enum Name>` section. The table has `Name | Hex | Description` columns.

### 2b. Determine per-value `supportedVersions`

For each value:
1. Find its first appearance (check v1.2 → v3.0 scraped files in order).
2. Find its last appearance (last version where the row is present).
3. Map to the `KmipSpec` set:
   - Present from v1.2 through v3.0 → `KmipSpec.UnknownVersion, KmipSpec.V1_2`  *(ceiling is the current maximum; if v1x3, v1x4 etc. also have it add those)*
   - Removed in v2.0 → `KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4`
   - Introduced in v2.1, still in v3.0 → `KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0`
   - Only in v3.0 → `KmipSpec.UnknownVersion, KmipSpec.V3_0`

Always include `KmipSpec.UnknownVersion` in every value's set (enables extension/test contexts).

### 2c. Fill in `Standard` enum constants

```java
// Pattern: CONSTANT_NAME(hexValue, "Display Name", KmipSpec.UnknownVersion, KmipSpec.V_X_Y, ...)
INCREMENT(0x00000001, "Increment", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
DECREMENT(0x00000002, "Decrement", KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
NEGATE(0x00000003, "Negate",    KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);
```

- Constant name = SCREAMING_SNAKE_CASE of the spec value name
- Hex = from the `Hex` column, cast to `int` (they fit in 32-bit signed since values are ≤ `0x7FFFFFFF`)
- Skip `(Reserved)` rows

### 2d. Update `createDefault()` / `createDifferentFromDefault()` in the test

Replace the placeholder fallback with the first (and second) Standard value:

```java
@Override
protected ExampleEnum createDefault() {
    return ExampleEnum.Standard.FIRST_VALUE.inst();
}

@Override
protected ExampleEnum createDifferentFromDefault() {
    return ExampleEnum.Standard.SECOND_VALUE.inst();
}
```

---

## Step 3 — Fill in structure fields (structure entity only)

The generator produces a scaffold with TODO markers. For a **new type** (no predecessor):
1. All child types must already exist (generate bottom-up if any are missing).
2. Add `@Builder` fields for each child with their types.
3. Update `createDefault()` in the test to build a valid instance.
4. Update `expectedMinComponentCount()` and `validateComponents()` in the test.

For a **version-separated re-implementation** (e.g., v2.1 variant of a v1.2 structure):
1. Open the previous version's class alongside the new scaffold.
2. Copy all fields from the old class into the new scaffold.
3. Add new fields introduced in the new version (at the correct position per wire order).
4. Remove fields that were removed in the new version.
5. Copy the `of()` factory, `getValue()`, `validate()`, and `isSupported()` bodies from the old class, adapting to the new field set.
6. Copy the deserializer's `setValue()` switch from the old deserializer, adding new tag cases for new fields.
7. The serializer body stays empty — the abstract base handles it.
8. Update the test's `createDefault()` to build a valid instance using the new version's required fields.

### Static block for version-separated types with named interfaces

When the type implements a named structure interface (e.g., `RequestHeaderStructure`), the static block must call BOTH `KmipDataType.register` AND the interface-specific register. Plain structures, enums, and datatypes only need `KmipDataType.register`:

```java
static {
    for (KmipSpec spec : supportedVersions) {
        if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
        KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestHeader.class);
        RequestHeaderStructure.register(spec, RequestHeader.class, RequestHeader::of);  // interface-specific
    }
}
```

The interface-specific register call varies by type:
- `RequestMessage` → `RequestMessageStructure.register(...)`
- `RequestHeader` → `RequestHeaderStructure.register(...)`
- `RequestBatchItem` → `RequestBatchItemStructure.register(...)`
- `ResponseMessage` → `ResponseMessageStructure.register(...)`
- `ResponseHeader` → `ResponseHeaderStructure.register(...)`
- `ResponseBatchItem` → `ResponseBatchItemStructure.register(...)`

---

## Step 4 — Validate

```bash
mvn test
```

All tests must pass (including codec tests and benchmarks). Fix any failures before reporting done.

For message framing types, also run the version-specific verification test:

```bash
mvn test -pl . -Dtest="KmipV21VerificationTest#diagnoseFirst10" 2>&1 | grep -E "FAIL|Verified|Passed"
```

---

## Generation flags reference

| Flag | Artifact |
|------|----------|
| `--class` | Domain class |
| `--domain-test` | Domain unit test |
| `--json-ser` / `--json-des` | JSON serializer / deserializer |
| `--xml-ser` / `--xml-des` | XML serializer / deserializer |
| `--ttlv-ser` / `--ttlv-des` | TTLV serializer / deserializer |
| `--json-test` / `--xml-test` / `--ttlv-test` | Codec integration tests |
| `--benchmark` | JMH benchmark subject |
| `--all` | Every artifact above |
| `--attr` | Use attribute-variant templates (class + domain test) |
| `-m <module>` | Version module (see table above) |
| `-s <sub_package>` | Nested sub-package |

### Dry-run (preview paths without writing)

Omit `--all` and all individual flags:

```bash
./scripts/generators/generate.sh enum -m v2x1 MyNewEnum
```

---

## Generated file layout

For `./scripts/generators/generate.sh enum --all -m v2x1 AdjustmentType`:

```
src/main/java/org/purplebean/kmip/model/v2x1/enumeration/AdjustmentType.java
src/main/java/org/purplebean/kmip/codec/json/serializer/model/v2x1/enumeration/AdjustmentTypeJsonSerializer.java
src/main/java/org/purplebean/kmip/codec/json/deserializer/model/v2x1/enumeration/AdjustmentTypeJsonDeserializer.java
src/main/java/org/purplebean/kmip/codec/xml/serializer/model/v2x1/enumeration/AdjustmentTypeXmlSerializer.java
src/main/java/org/purplebean/kmip/codec/xml/deserializer/model/v2x1/enumeration/AdjustmentTypeXmlDeserializer.java
src/main/java/org/purplebean/kmip/codec/ttlv/serializer/model/v2x1/enumeration/AdjustmentTypeTtlvSerializer.java
src/main/java/org/purplebean/kmip/codec/ttlv/deserializer/model/v2x1/enumeration/AdjustmentTypeTtlvDeserializer.java
src/test/java/org/purplebean/kmip/model/v2x1/enumeration/AdjustmentTypeTest.java
src/test/java/org/purplebean/kmip/codec/json/model/v2x1/enumeration/AdjustmentTypeJsonTest.java
src/test/java/org/purplebean/kmip/codec/xml/model/v2x1/enumeration/AdjustmentTypeXmlTest.java
src/test/java/org/purplebean/kmip/codec/ttlv/model/v2x1/enumeration/AdjustmentTypeTtlvTest.java
src/test/java/org/purplebean/kmip/benchmark/subjects/model/v2x1/enumeration/AdjustmentTypeBenchmarkSubject.java
META-INF/services entries updated automatically
```

For `./scripts/generators/generate.sh structure --all -m v2x1 -s request RequestHeader`:

```
src/main/java/org/purplebean/kmip/model/v2x1/structure/request/RequestHeader.java
src/main/java/org/purplebean/kmip/codec/xml/deserializer/model/v2x1/structure/request/RequestHeaderXmlDeserializer.java
src/main/java/org/purplebean/kmip/codec/xml/serializer/model/v2x1/structure/request/RequestHeaderXmlSerializer.java
src/main/java/org/purplebean/kmip/codec/json/deserializer/model/v2x1/structure/request/RequestHeaderJsonDeserializer.java
src/main/java/org/purplebean/kmip/codec/json/serializer/model/v2x1/structure/request/RequestHeaderJsonSerializer.java
src/main/java/org/purplebean/kmip/codec/ttlv/deserializer/model/v2x1/structure/request/RequestHeaderTtlvDeserializer.java
src/main/java/org/purplebean/kmip/codec/ttlv/serializer/model/v2x1/structure/request/RequestHeaderTtlvSerializer.java
src/test/java/org/purplebean/kmip/model/v2x1/structure/request/RequestHeaderTest.java
src/test/java/org/purplebean/kmip/codec/xml/model/v2x1/structure/request/RequestHeaderXmlTest.java
src/test/java/org/purplebean/kmip/codec/json/model/v2x1/structure/request/RequestHeaderJsonTest.java
src/test/java/org/purplebean/kmip/codec/ttlv/model/v2x1/structure/request/RequestHeaderTtlvTest.java
src/test/java/org/purplebean/kmip/benchmark/subjects/model/v2x1/structure/request/RequestHeaderBenchmarkSubject.java
META-INF/services entries updated automatically
```

---

## Examples

```bash
# v2.1 enum — full generation
./scripts/generators/generate.sh enum --all -m v2x1 AdjustmentType

# v1.2 attribute enum in core
./scripts/generators/generate.sh enum --all --attr -m core CryptographicAlgorithm

# v3.0-only structure
./scripts/generators/generate.sh structure --all -m v3x0 DeactivationReason

# v1.2 string-backed datatype (attribute)
./scripts/generators/generate.sh datatype --all --attr --type String UniqueIdentifier

# Sub-packaged structure (generic)
./scripts/generators/generate.sh structure --all -m core -s request CreateRequestPayload

# Scaffold only (class + domain test, no codecs)
./scripts/generators/generate.sh enum --class --domain-test -m v2x1 AdjustmentType

# v2.1 scalar prerequisite types for message framing
./scripts/generators/generate.sh datatype --all -m v2x1 --type String \
    ClientCorrelationValue ServerCorrelationValue

# v2.1 message framing types (version-separated re-implementations)
./scripts/generators/generate.sh structure --all -m v2x1 -s request \
    RequestMessage RequestHeader RequestBatchItem
./scripts/generators/generate.sh structure --all -m v2x1 -s response \
    ResponseMessage ResponseHeader ResponseBatchItem

# v3.0 message framing types (only if v3.0 changes fields vs v2.1)
./scripts/generators/generate.sh structure --all -m v3x0 -s request \
    RequestMessage RequestHeader RequestBatchItem
./scripts/generators/generate.sh structure --all -m v3x0 -s response \
    ResponseMessage ResponseHeader ResponseBatchItem
```

---

## Version-separated implementation checklist

Applies whenever ANY KMIP type (enum, datatype, structure) differs between spec versions:

**Before generating:**
- [ ] Confirm the type actually changed (see "What counts as changed" table above)
- [ ] Identify which existing class(es) register for the new version's specs
- [ ] Narrow those class(es)' `supportedVersions` (and static block) to exclude the new version's specs

**Generate and fill:**
- [ ] Generate any new scalar/enum prerequisite types first (bottom-up)
- [ ] Scaffold the new-version class(es) using the appropriate module flag (`-m v2x1`, `-m v3x0`, etc.)
- [ ] Fill each model class: copy from old version, apply spec delta (add/remove/change fields, encoding, composition), update `of()`, `getValue()`, `validate()`
- [ ] Fill each deserializer's `setValue()` switch: copy from old version, add/remove cases per spec delta
- [ ] For structure types implementing a named interface (e.g., `RequestHeaderStructure`): add the interface-specific `register()` call to the static block

**Validate:**
- [ ] `mvn compile` — zero errors
- [ ] `mvn test` — all tests pass (including any version-specific verification tests)
- [ ] For message framing types specifically: `mvn test -Dtest="KmipV21VerificationTest#diagnoseFirst10"` resolves framing errors
