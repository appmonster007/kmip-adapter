# Testing

JUnit 5 + AssertJ. `mvn test` runs the whole suite. `-Pwith-integration` clears surefire's
`excludedGroups`, so tests tagged `integration` (if any) are included.

## Layout (`src/test/java/org/purplebean/kmip/`)

| Path | Contents |
|---|---|
| `model/<v>/<kind>/<Name>Test` | Domain tests for one type |
| `codec/{json,xml,ttlv}/model/<v>/<kind>/<Name>{Json,Xml,Ttlv}Test` | Per-format round-trip tests |
| `test/suite/` | Reusable base suites (below) |
| `benchmark/` | JMH benchmarks, `subjects/` (one subject per type), `util/BenchmarkSubjects`, report generator |
| `spi/` | `SpiRegistrationAuditTest`: every concrete type and codec class is listed in its `META-INF/services` file and every entry resolves |
| `verification/` | `KmipV12/V21/V30VerificationTest` run the official KMIP test-case vectors |
| `documentation/` | `DocumentationExamplesTest` (executes doc samples), `AllFormatsSerializationTest` (writes `build/reports/kmip/serialization*.md`) |

## Suite hierarchy

- `AbstractKmipDataTypeTestSuite<T>`, `AbstractKmipEnumerationTestSuite<T>`,
  `AbstractKmipStructureTestSuite<T>`, `KmipAttributeTestSuite`: domain behavior.
- `KmipSerializationTestSuite<T, Mapper, Output>`: interface with default round-trip and error
  tests. `AbstractJsonSerializationTestSuite`, `AbstractXmlSerializationTestSuite`,
  `AbstractTtlvSerializationTestSuite` implement it.

A per-format test supplies `type()`, `createDefault()`, optional `createVariant()`, the mapper and
(de)serialize methods; the generator scaffolds all of that. Your job is real values in
`createDefault()`.

## SPI audit

`SpiRegistrationAuditTest` fails when a concrete `KmipDataType` or codec class is missing from its
SPI file, or when a line names a class that does not exist or does not implement the service. It
reports all gaps in one message. After a hand edit or merge, run
`mvn test -Dtest=SpiRegistrationAuditTest`.

## Commands

```bash
mvn test                                   # all unit tests
mvn test -Dtest=ProtocolVersionTest        # one class
mvn test -Dtest='*Ttlv*'                   # one format
mvn test -Dtest=KmipV21VerificationTest    # official test-case vectors
mvn clean test jacoco:report               # report: target/site/jacoco/index.html
mvn -Pcoverage-strict verify               # fails below 60% line / 50% branch
```

## Benchmarks

```bash
mvn -Pperf verify          # full JMH run
mvn -Pperf-fast verify     # short run
```

Profiles take JMH arguments through `-Dbench.args="-wi 3 -i 5 -f 1"`. The in-process
`JmhBenchmarkRunner` (`make perf-runner`) is configured with `-Dbench.include`, `bench.threads`,
`bench.wi`, `bench.mi`, `bench.forks`, `bench.result` (default `target/jmh-results.json`) and
`bench.report` (default `target/jmh-report.md`).

## Conventions

- Tests are subject to checkstyle (Javadoc on classes/public methods, import order).
- State is global: clear `KmipContext` in `@AfterEach` and initialize types in `@BeforeAll` before
  setting a spec ([Versioning](versioning.md)).
- Do not assert counts of tests or percentages in docs; they go stale. Read the JaCoCo report.
