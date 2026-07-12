---
name: bmad-generate-kmip-code
description: "Scaffolds new KMIP enums, data types, and structures by running the project's generate.sh script. Use when the user asks to add/create a KMIP enum, datatype, or structure."
---

# Generate KMIP Boilerplate

**Your role:** Drive the full lifecycle — scaffold via `generate.sh`, scrape enum values from the KMIP spec HTML, fill in implementation details, and validate with `mvn test`. Do NOT read template files or write Java files manually.

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

For child fields of a structure, resolve each child type first (bottom-up): check whether the child already exists in the source tree. If it does not, generate and fill it before the parent.

---

## Module selection

Map the KMIP "introduced in" version to the correct `-m` flag:

| Introduced in | Module flag | `supportedVersions` generated | `defaultSpec` generated |
|---------------|-------------|-------------------------------|-------------------------|
| 1.2           | `-m core`   | `{UnknownVersion, V1_2}`      | `KmipSpec.V1_2`         |
| 1.3           | `-m v1_3`   | `{UnknownVersion, V1_3}`      | `KmipSpec.V1_3`         |
| 1.4           | `-m v1_4`   | `{UnknownVersion, V1_4}`      | `KmipSpec.V1_4`         |
| 2.0           | `-m v2_0`   | `{UnknownVersion, V2_0}`      | `KmipSpec.V2_0`         |
| 2.1           | `-m v2_1`   | `{UnknownVersion, V2_1, V3_0}`| `KmipSpec.V2_1`         |
| 3.0           | `-m v3_0`   | `{UnknownVersion, V3_0}`      | `KmipSpec.V3_0`         |

**Cross-verify in spec HTML before choosing a module.** Read the relevant heading in:
- `docs/kmip-spec/v1.x/scraped/enumerations-v1.2.md` (and v1.3, v1.4)
- `docs/kmip-spec/v2.x/scraped/enumerations-v2.0.md` (and v2.1)
- `docs/kmip-spec/v3.x/scraped/enumerations-v3.0.md`

If the entity appears in v1.2, use `core`. If it first appears in v2.1, use `v2_1`. If it's present across multiple versions, choose the earliest as the module and note the ceiling (last-seen version) — you'll need it for per-value `supportedVersions` in the `Standard` enum.

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
   - Present from v1.2 through v3.0 → `KmipSpec.UnknownVersion, KmipSpec.V1_2`  *(ceiling is the current maximum; if v1_3, v1_4 etc. also have it add those)*
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

1. All child types must already exist (generate bottom-up if any are missing).
2. Add `@Builder` fields for each child with their types.
3. Update `createDefault()` in the test to build a valid instance.
4. Update `expectedMinComponentCount()` and `validateComponents()` in the test.

---

## Step 4 — Validate

```bash
mvn test
```

All tests must pass (including codec tests and benchmarks). Fix any failures before reporting done.

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
./scripts/generators/generate.sh enum -m v2_1 MyNewEnum
```

---

## Generated file layout

For `./scripts/generators/generate.sh enum --all -m v2_1 AdjustmentType`:

```
src/main/java/org/purpleBean/kmip/model/v2_1/enumeration/AdjustmentType.java
src/main/java/org/purpleBean/kmip/codec/json/serializer/model/v2_1/enumeration/AdjustmentTypeJsonSerializer.java
src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/v2_1/enumeration/AdjustmentTypeJsonDeserializer.java
src/main/java/org/purpleBean/kmip/codec/xml/serializer/model/v2_1/enumeration/AdjustmentTypeXmlSerializer.java
src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/v2_1/enumeration/AdjustmentTypeXmlDeserializer.java
src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/model/v2_1/enumeration/AdjustmentTypeTtlvSerializer.java
src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/v2_1/enumeration/AdjustmentTypeTtlvDeserializer.java
src/test/java/org/purpleBean/kmip/model/v2_1/enumeration/AdjustmentTypeTest.java
src/test/java/org/purpleBean/kmip/codec/json/model/v2_1/enumeration/AdjustmentTypeJsonTest.java
src/test/java/org/purpleBean/kmip/codec/xml/model/v2_1/enumeration/AdjustmentTypeXmlTest.java
src/test/java/org/purpleBean/kmip/codec/ttlv/model/v2_1/enumeration/AdjustmentTypeTtlvTest.java
src/test/java/org/purpleBean/kmip/benchmark/subjects/model/v2_1/enumeration/AdjustmentTypeBenchmarkSubject.java
META-INF/services entries updated automatically
```

---

## Examples

```bash
# v2.1 enum — full generation
./scripts/generators/generate.sh enum --all -m v2_1 AdjustmentType

# v1.2 attribute enum in core
./scripts/generators/generate.sh enum --all --attr -m core CryptographicAlgorithm

# v3.0-only structure
./scripts/generators/generate.sh structure --all -m v3_0 DeactivationReason

# v1.2 string-backed datatype (attribute)
./scripts/generators/generate.sh datatype --all --attr --type String UniqueIdentifier

# Sub-packaged structure
./scripts/generators/generate.sh structure --all -m core -s request CreateRequestPayload

# Scaffold only (class + domain test, no codecs)
./scripts/generators/generate.sh enum --class --domain-test -m v2_1 AdjustmentType
```
