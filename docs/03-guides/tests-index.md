# Tests Index (Per-Class and Integration)

A concise reference mapping production classes to their corresponding per-class tests and integration tests.

## Layout Overview

- JSON codec tests: `src/test/java/org/purpleBean/kmip/codec/json/common/...`
- TTLV codec tests: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/...`
- XML codec tests: `src/test/java/org/purpleBean/kmip/codec/xml/common/...`
- Core and request structure unit tests: `src/test/java/org/purpleBean/kmip/common/...`
- Top-level KMIP tests: `src/test/java/org/purpleBean/kmip/`
- Integration tests (cross-codec/behavioral): `src/test/java/org/purpleBean/kmip/codec/integration/`

## Class-to-Test Mapping (selected)

- `org.purpleBean.kmip.ProtocolVersion`
  - JSON: `codec/json/ProtocolVersionJsonTest.java`
  - TTLV: `codec/ttlv/ProtocolVersionTtlvTest.java`
  - XML: `codec/xml/ProtocolVersionXmlTest.java`

- `org.purpleBean.kmip.common.enumeration.State`
  - JSON: `codec/json/common/enumeration/StateJsonTest.java`
  - TTLV: `codec/ttlv/common/enumeration/StateTtlvTest.java`
  - XML: `codec/xml/common/enumeration/StateXmlTest.java`

- `org.purpleBean.kmip.common.ActivationDateAttribute`
  - JSON: `codec/json/common/ActivationDateAttributeJsonTest.java`
  - TTLV: `codec/ttlv/common/ActivationDateAttributeTtlvTest.java`
  - XML: `codec/xml/common/ActivationDateAttributeXmlTest.java`

- `org.purpleBean.kmip.common.structure.SampleStructure`
  - JSON: `codec/json/common/structure/SampleStructureJsonTest.java`
  - TTLV: `codec/ttlv/common/structure/SampleStructureTtlvTest.java`
  - XML: `codec/xml/common/structure/SampleStructureXmlTest.java`

- Request structures (`org.purpleBean.kmip.common.structure.request.*`)
  - Unit tests:
    - `SimpleRequestHeader`: `common/structure/request/SimpleRequestHeaderTest.java`
    - `SimpleRequestBatchItem`: `common/structure/request/SimpleRequestBatchItemTest.java`
    - `SimpleRequestMessage`: `common/structure/request/SimpleRequestMessageTest.java`
  - Codec tests:
    - JSON:
      - `codec/json/common/structure/request/SimpleRequestHeaderJsonTest.java`
      - `codec/json/common/structure/request/SimpleRequestBatchItemJsonTest.java`
      - `codec/json/common/structure/request/SimpleRequestMessageJsonTest.java`
    - XML:
      - `codec/xml/common/structure/request/SimpleRequestHeaderXmlTest.java`
      - `codec/xml/common/structure/request/SimpleRequestMessageXmlTest.java`
    - TTLV:
      - `codec/ttlv/common/structure/request/SimpleRequestHeaderTtlvTest.java`
      - `codec/ttlv/common/structure/request/SimpleRequestMessageTtlvTest.java`

- Context and core
  - `KmipContext`: `kmip/KmipContextTest.java`
  - Concurrency: `kmip/KmipContextConcurrencyTest.java`
  - Tag registry and lookups: `kmip/KmipTagTest.java`

- Integration (cross-codec behavior)
  - `codec/integration/ParallelSerializationTest.java` (@Tag("integration"))

## Reusable Test Suites

To reduce duplication and standardize behavior checks, several abstract suites are used across tests:

- `AbstractKmipDataTypeSuite` — tag and encoding invariants; supported/unsupported spec checks.
- `AbstractKmipStructureSuite` — structure values list shape and component validation hook.
- `AbstractKmipEnumerationSuite` — enumeration description and equality semantics, plus optional registry behavior.
  - Opt-in hooks for enums that support runtime registration:
    - `supportsRegistryBehavior()`
    - `assertEnumerationRegistryBehaviorPositive()`
    - `assertEnumerationRegistryBehaviorNegative()`

For tag registry and lookup semantics, refer to the canonical `kmip/KmipTagTest.java`.

## How to Run

```bash
# Unit tests (default)
mvn test

# Include integration tests (@Tag("integration"))
mvn -Pwith-integration test

# Coverage (HTML: target/site/jacoco/index.html)
mvn clean test

# Perf (JMH)
mvn -Pperf verify
mvn -Pperf-fast verify
```
