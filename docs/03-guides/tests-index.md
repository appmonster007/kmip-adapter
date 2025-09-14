# Tests Index (Per-Class and Integration)

A concise reference mapping production classes to their corresponding per-class tests and integration tests.

## Layout Overview

- JSON codec tests: `src/test/java/org/purpleBean/kmip/codec/json/`
- TTLV codec tests: `src/test/java/org/purpleBean/kmip/codec/ttlv/`
- XML codec tests: `src/test/java/org/purpleBean/kmip/codec/xml/`
- Request structures: `src/test/java/org/purpleBean/kmip/common/structure/request/`
- Top-level KMIP tests: `src/test/java/org/purpleBean/kmip/`
- Integration tests (cross-codec/behavioral): `src/test/java/org/purpleBean/kmip/codec/integration/`

## Class-to-Test Mapping (selected)

- `org.purpleBean.kmip.ProtocolVersion`
  - JSON: `codec/json/ProtocolVersionJsonTest.java`
  - TTLV: `codec/ttlv/ProtocolVersionTtlvTest.java`
  - XML: `codec/xml/ProtocolVersionXmlTest.java`

- `org.purpleBean.kmip.common.enumeration.State`
  - JSON: `codec/json/StateJsonTest.java`
  - TTLV: `codec/ttlv/StateTtlvTest.java`
  - XML: `codec/xml/StateXmlTest.java`

- `org.purpleBean.kmip.common.ActivationDateAttribute`
  - JSON: `codec/json/ActivationDateAttributeJsonTest.java`
  - TTLV: `codec/ttlv/ActivationDateAttributeTtlvTest.java`
  - XML: `codec/xml/ActivationDateAttributeXmlTest.java`

- `org.purpleBean.kmip.common.structure.SampleStructure`
  - JSON: `codec/json/SampleStructureJsonTest.java`
  - TTLV: `codec/ttlv/SampleStructureTtlvTest.java`
  - XML: `codec/xml/SampleStructureXmlTest.java`

- Request structures (`org.purpleBean.kmip.common.structure.request.*`)
  - `SimpleRequestHeader`: `common/structure/request/SimpleRequestHeaderTest.java`
  - `SimpleRequestBatchItem`: `common/structure/request/SimpleRequestBatchItemTest.java`
  - `SimpleRequestMessage`: `common/structure/request/SimpleRequestMessageTest.java`

- Context and core
  - `KmipContext`: `kmip/KmipContextTest.java`
  - Concurrency: `kmip/KmipContextConcurrencyTest.java`
  - Registry behavior: `kmip/RegistryBehaviorTest.java`

- Integration (cross-codec behavior)
  - `codec/integration/ParallelSerializationTest.java` (@Tag("integration"))

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
