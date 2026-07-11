---
name: bmad-generate-kmip-code
description: "Scaffolds new KMIP enums, data types, and structures by running the project's generate.sh script. Use when the user asks to add/create a KMIP enum, datatype, or structure."
---

# Generate KMIP Boilerplate

**Your role:** Invoke the generator script to create KMIP boilerplate. Do NOT read template files or write Java files manually — the script handles everything.

## Workflow

1. **Gather** from the user's request:
   - Entity type: `enum`, `datatype`, or `structure`
   - Name(s) (script normalises any casing to PascalCase)
   - Module: `core` (default) or a version string like `v1_2`
   - Sub-package (optional)
   - `--attr` if this is a KMIP attribute variant
   - `--type <JavaType>` for `datatype` entities (see table below)

2. **Run** from the project root — always use `--all` unless the user asks for a subset:

```bash
./scripts/generators/generate.sh <entity_type> --all \
  [-m <module>] [-s <sub_package>] [--attr] [--type <JavaType>] \
  <Name> [<Name2> ...]
```

3. **Report** which files were created (the script prints them). Do not re-read generated files unless the user asks for a review.

---

## Quick reference

### Entity types

| Type | Typical command |
|------|----------------|
| `enum` | `./scripts/generators/generate.sh enum --all <Name>` |
| `datatype` | `./scripts/generators/generate.sh datatype --all --type <JavaType> <Name>` |
| `structure` | `./scripts/generators/generate.sh structure --all <Name>` |

### `--type` values for `datatype`

| `--type` | Java type | KMIP encoding |
|----------|-----------|---------------|
| `ByteBuffer` | `ByteBuffer` | BYTE_STRING (default) |
| `String` | `String` | TEXT_STRING |
| `Integer` | `Integer` | INTEGER |
| `Long` | `Long` | LONG_INTEGER |
| `BigInteger` | `BigInteger` | BIG_INTEGER |
| `Boolean` | `Boolean` | BOOLEAN |
| `OffsetDateTime` | `OffsetDateTime` | DATE_TIME |

### Generation flags (use `--all` or pick a subset)

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
| `--attr` | Use attribute-variant templates (class + test) |
| `-m <module>` | Module (default: `core`) |
| `-s <sub_package>` | Nested sub-package |

### Dry-run (preview without writing files)

Omit `--all` and all individual flags — the script will print what it *would* create:

```bash
./scripts/generators/generate.sh enum MyStatus
```

---

## Generated file layout

For `./scripts/generators/generate.sh enum --all MyStatus` (module `core`):

```
src/main/java/org/purpleBean/kmip/model/core/enumeration/MyStatus.java
src/main/java/org/purpleBean/kmip/codec/json/serializer/model/core/enumeration/MyStatusJsonSerializer.java
src/main/java/org/purpleBean/kmip/codec/json/deserializer/model/core/enumeration/MyStatusJsonDeserializer.java
src/main/java/org/purpleBean/kmip/codec/xml/serializer/model/core/enumeration/MyStatusXmlSerializer.java
src/main/java/org/purpleBean/kmip/codec/xml/deserializer/model/core/enumeration/MyStatusXmlDeserializer.java
src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/model/core/enumeration/MyStatusTtlvSerializer.java
src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/model/core/enumeration/MyStatusTtlvDeserializer.java
src/test/java/org/purpleBean/kmip/model/core/enumeration/MyStatusTest.java
src/test/java/org/purpleBean/kmip/codec/json/model/core/enumeration/MyStatusJsonTest.java
src/test/java/org/purpleBean/kmip/codec/xml/model/core/enumeration/MyStatusXmlTest.java
src/test/java/org/purpleBean/kmip/codec/ttlv/model/core/enumeration/MyStatusTtlvTest.java
src/test/java/org/purpleBean/kmip/benchmark/subjects/model/core/enumeration/MyStatusBenchmarkSubject.java
META-INF/services entries updated automatically
```

---

## Examples

```bash
# Full enum
./scripts/generators/generate.sh enum --all MyStatus

# Attribute enum in a version module
./scripts/generators/generate.sh enum --all --attr -m v1_2 CryptographicAlgorithm

# String-backed datatype
./scripts/generators/generate.sh datatype --all --type String UniqueIdentifier

# Structure in a sub-package
./scripts/generators/generate.sh structure --all -s request CreateRequestPayload

# Class + tests only (no codecs)
./scripts/generators/generate.sh enum --class --domain-test MyStatus
```

---

## Post-generation checklist (tell the user)

1. Fill in enum constants / struct fields in the generated class.
2. Update codec tests with meaningful fixture values.
3. Run `./gradlew test` to confirm everything compiles and tests pass.
