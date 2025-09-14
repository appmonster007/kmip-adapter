# Quick Start for New Types

A concise checklist to add a new KMIP type and per-class tests end-to-end, with links to minimal boilerplates and CI snippets.

## Links

- Boilerplates:
  - Structure: `docs/03-guides/boilerplate-structure.md`
  - Attribute: `docs/03-guides/boilerplate-attribute.md`
  - Enumeration: `docs/03-guides/boilerplate-enum.md`
- Tests Index (per-class mapping): `docs/03-guides/tests-index.md`
- Testing Guide: `docs/03-guides/testing.md`
- Performance: `docs/04-performance/performance-testing-guide.md`

## 5-Step Checklist

1. Define the type (minimal @Data/@Builder)
   - Structure example: see `Foo` in `boilerplate-structure.md`.
   - Attribute example: see `FooAttribute` in `boilerplate-attribute.md`.
   - Enumeration example: see `FooStatus` in `boilerplate-enum.md`.
   - Register a tag if needed (e.g., `KmipTag.register(...)`).

2. Add a small factory (optional but helpful for tests)
   - Provide a `FooFactory.createFoo()` or `createFooAttribute()` returning a valid instance.

3. Add per-class tests (copy-ready)
   - JSON: place in `src/test/java/org/purpleBean/kmip/codec/json/`.
   - TTLV: place in `src/test/java/org/purpleBean/kmip/codec/ttlv/`.
   - XML: place in `src/test/java/org/purpleBean/kmip/codec/xml/`.
   - Extend `BaseKmipTest` and reuse `SerializationTestUtils`.

4. Run tests and coverage locally
   - Unit tests: `mvn test`
   - Include integration tests: `mvn -Pwith-integration test`
   - Coverage (HTML): `mvn clean test` → `target/site/jacoco/index.html`
   - Strict coverage: `mvn -Pcoverage-strict verify`

5. (Optional) Benchmarks
   - Standard perf: `mvn -Pperf verify`
   - Fast perf: `mvn -Pperf-fast verify`
   - Custom: `mvn -Pperf -Dbench.args="-wi 3 -i 5 -f 1 -rf json -rff target/jmh.json" verify`

## CI Snippets

### GitHub Actions (Unit + Coverage)
```yaml
name: CI
on: [push, pull_request]
jobs:
  unit:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '21'
      - name: Build and test
        run: mvn -B -q clean test
      - name: Upload coverage html (artifact)
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: jacoco-html
          path: target/site/jacoco
```

### GitHub Actions (Include Integration)
```yaml
  integration:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '21'
      - name: Test with integration
        run: mvn -B -q -Pwith-integration test
```

### GitHub Actions (Strict Coverage Gate)
```yaml
  coverage-gate:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '21'
      - name: Verify with strict coverage
        run: mvn -B -q -Pcoverage-strict verify
```

### GitHub Actions (Performance – optional)
```yaml
  perf:
    if: github.event_name == 'workflow_dispatch'
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '21'
      - name: Run perf (fast)
        run: mvn -B -q -Pperf-fast verify
      - name: Run perf (custom args)
        run: mvn -B -q -Pperf -Dbench.args="-wi 3 -i 5 -f 1 -rf json -rff target/jmh.json" verify
```
