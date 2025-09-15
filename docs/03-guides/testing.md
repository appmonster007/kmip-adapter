# Testing Guide

See also boilerplates for copy-ready tests and registrations:
- Structure: `../03-guides/development/boilerplate-structure.md`
- Attribute: `../03-guides/development/boilerplate-attribute.md`
- Enumeration: `../03-guides/development/boilerplate-enum.md`

## Table of Contents
- [Unit Testing](#unit-testing)
  - [Testing Enumerations](#testing-enumerations)
  - [Testing Attributes](#testing-attributes)
  - [Testing Structures](#testing-structures)
- [Integration Testing](#integration-testing)
  - [Serialization Round-Trip](#testing-serialization-round-trip)
  - [Version Compatibility](#version-compatibility-testing)
- [Performance Testing](#performance-testing)
- [Test Utilities](#test-utilities)
- [Test Coverage](#test-coverage)
- [Best Practices](#best-practices)

## Quick Start: Running Tests

Use Maven profiles to run fast unit tests by default, include integration tests when needed, and execute JMH performance benchmarks separately.

```bash
# Run unit tests (default)
mvn test

# Include integration tests (tests tagged with @Tag("integration"))
mvn -Pwith-integration test

# Run with coverage report (HTML at target/site/jacoco/index.html)
mvn clean test

# Strict coverage gate (optional; fails build below thresholds)
mvn -Pcoverage-strict verify

# Run performance benchmarks (JMH)
mvn -Pperf verify

# Quick benchmarks (lighter warmups/iterations)
mvn -Pperf-fast verify

# Optional: pass JMH args
mvn -Pperf -Dbench.args="-wi 3 -i 5 -f 1 -rf json -rff target/jmh.json" verify
```

Per-class unit tests are organized to mirror runtime packages for each codec layer:

- JSON: `src/test/java/org/purpleBean/kmip/codec/json/common/.../*JsonTest.java`
- TTLV: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/.../*TtlvTest.java`
- XML: `src/test/java/org/purpleBean/kmip/codec/xml/common/.../*XmlTest.java`

Examples:
- Enumerations (e.g., `State`): `codec/json/common/enumeration/StateJsonTest.java`, `codec/xml/common/enumeration/StateXmlTest.java`, `codec/ttlv/common/enumeration/StateTtlvTest.java`
- Attributes (e.g., `ActivationDateAttribute`): `codec/json/common/ActivationDateAttributeJsonTest.java`, etc.
- Structures (e.g., `SampleStructure`): `codec/json/common/structure/SampleStructureJsonTest.java`, etc.
- Request structures (e.g., `SimpleRequestHeader`, `SimpleRequestMessage`, `SimpleRequestBatchItem`):
  - JSON: `codec/json/common/structure/request/*`
  - XML: `codec/xml/common/structure/request/*`
  - TTLV: `codec/ttlv/common/structure/request/*`

Integration tests are tagged with `@Tag("integration")` and can be included with the `with-integration` profile.

## Unit Testing

### Testing Enumerations

```java
/**
 * Tests for {@link State} enumeration.
 */
@DisplayName("State Tests")
class StateTest {

    @Test
    @DisplayName("Should return correct state value for valid code")
    void shouldReturnCorrectValueForValidCode() {
        // Given
        int rawCode = 0x02; // ACTIVE state
        
        // When
        State.Value result = State.fromValue(KmipSpec.V1_4, rawCode);
        
        // Then
        assertThat(result)
            .isEqualTo(State.Standard.ACTIVE)
            .extracting(State.Value::getValue, State.Value::getDescription)
            .containsExactly(0x02, "Active");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid code")
    void shouldThrowForInvalidCode() {
        // Given
        int invalidCode = 999;
        
        // When/Then
        assertThatThrownBy(() -> State.fromValue(KmipSpec.V1_4, invalidCode))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Unknown state value: " + invalidCode);
    }
    
    @ParameterizedTest
    @MethodSource("validStateValues")
    @DisplayName("Should have consistent value and description")
    void shouldHaveConsistentValueAndDescription(State.Standard state, int expectedValue, String expectedDescription) {
        assertThat(state)
            .extracting(State.Value::getValue, State.Value::getDescription)
            .containsExactly(expectedValue, expectedDescription);
    }

    private static Stream<Arguments> validStateValues() {
        return Stream.of(
            Arguments.of(State.Standard.PRE_ACTIVE, 0x01, "PreActive"),
            Arguments.of(State.Standard.ACTIVE, 0x02, "Active"),
            Arguments.of(State.Standard.DEACTIVATED, 0x03, "Deactivated"),
            Arguments.of(State.Standard.COMPROMISED, 0x04, "Compromised"),
            Arguments.of(State.Standard.DESTROYED, 0x05, "Destroyed"),
            Arguments.of(State.Standard.DESTROYED_COMPROMISED, 0x06, "Destroyed Compromised")
        );
    }
}
```

### Testing Attributes

```java
/**
 * Tests for {@link ActivationDateAttribute} attribute.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ActivationDateAttribute Tests")
class ActivationDateAttributeTest extends BaseKmipTest {

    @Test
    @DisplayName("Should serialize to JSON with correct structure")
    void shouldSerializeToJson() throws Exception {
        // Given
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        ActivationDateAttribute attr = new ActivationDateAttribute(now);
        
        // Set the KMIP spec for the context
        KmipContext.setSpec(KmipSpec.V1_4);
        try {
            // When
            String json = jsonMapper.writeValueAsString(attr);
            
            // Then
            assertThat(json)
                .contains("\"tag\":\"ActivationDate\"")
                .contains("\"type\":\"DateTime\"")
                .contains(now.toString());
        } finally {
            KmipContext.clear();
        }
    }

    @Test
    @DisplayName("Should deserialize from JSON")
    void shouldDeserializeFromJson() throws Exception {
        // Given
        OffsetDateTime dateTime = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        String json = String.format(
            "{\"tag\":\"ActivationDate\",\"type\":\"DateTime\",\"value\":\"%s\"}",
            dateTime.toString()
        );

        // Set the KMIP spec for the context
        KmipContext.setSpec(KmipSpec.V1_4);
        try {
            // When
            ActivationDateAttribute deserialized = jsonMapper.readValue(json, ActivationDateAttribute.class);
            
            // Then
            assertThat(deserialized)
                .extracting(ActivationDateAttribute::getValue)
                .isEqualTo(dateTime);
        } finally {
            KmipContext.clear();
        }
    }
}
```

### Testing Structures

```java
/**
 * Tests for {@link ProtocolVersion} structure.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ProtocolVersion Tests")
class ProtocolVersionTest {

    @Test
    @DisplayName("Should create valid ProtocolVersion")
    void shouldCreateValidProtocolVersion() {
        // Given/When
        ProtocolVersion version = ProtocolVersion.of(1, 4);
            
        // Then
        assertThat(version)
            .extracting(
                ProtocolVersion::getMajor,
                ProtocolVersion::getMinor,
                v -> v.isSupportedFor(KmipSpec.V1_4))
            .containsExactly(1, 4, true);
    }
    
    @Test
    @DisplayName("Should support version compatibility check")
    void shouldSupportVersionCompatibility() {
        // Given
        ProtocolVersion version = ProtocolVersion.of(1, 2);
            
        // When/Then
        assertThat(version.isSupportedFor(KmipSpec.V1_2)).isTrue();
        assertThat(version.isSupportedFor(KmipSpec.V1_4)).isFalse();
    }
    
    @Test
    @DisplayName("Should implement equals and hashCode correctly")
    void shouldImplementEqualsAndHashCode() {
        // Given
        ProtocolVersion version1 = ProtocolVersion.of(1, 2);
        ProtocolVersion version2 = ProtocolVersion.of(1, 2);
            
        // When/Then
        assertThat(version1)
            .isEqualTo(version2)
            .hasSameHashCodeAs(version2);
    }
}
```

## Integration Testing

### Testing Serialization Round-Trip

```java
/**
 * Integration tests for serialization.
 */
@DisplayName("Serialization Integration Tests")
class SerializationIntegrationTest {

    private ObjectMapper buildJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new KmipJsonModule());
        return mapper;
    }

    private XmlMapper buildXmlMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new KmipXmlModule());
        return mapper;
    }

    private TtlvMapper buildTtlvMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Should perform JSON round-trip for ProtocolVersion")
    void shouldRoundTripJson() throws Exception {
        ObjectMapper objectMapper = buildJsonMapper();
        ProtocolVersion original = ProtocolVersion.of(1, 2);

        String json = objectMapper.writeValueAsString(original);
        ProtocolVersion deserialized = objectMapper.readValue(json, ProtocolVersion.class);

        assertThat(deserialized).isEqualTo(original);
    }

    @Test
    @DisplayName("Should perform XML round-trip for ProtocolVersion")
    void shouldRoundTripXml() throws Exception {
        XmlMapper xmlMapper = buildXmlMapper();
        ProtocolVersion original = ProtocolVersion.of(1, 2);

        String xml = xmlMapper.writeValueAsString(original);
        ProtocolVersion deserialized = xmlMapper.readValue(xml, ProtocolVersion.class);

        assertThat(deserialized).isEqualTo(original);
    }

    @Test
    @DisplayName("Should perform TTLV round-trip for ProtocolVersion")
    void shouldRoundTripTtlv() throws Exception {
        TtlvMapper ttlvMapper = buildTtlvMapper();
        KmipContext.setSpec(KmipSpec.V1_2);
        try {
            ProtocolVersion original = ProtocolVersion.of(1, 2);
            byte[] ttlv = ttlvMapper.writeValueAsBytes(original);
            ProtocolVersion deserialized = ttlvMapper.readValue(ttlv, ProtocolVersion.class);
            assertThat(deserialized).isEqualTo(original);
        } finally {
            KmipContext.clear();
        }
    }
}
```

### Version Compatibility Testing

```java
/**
 * Tests for version compatibility across different KMIP specifications.
 */
@DisplayName("Version Compatibility Tests")
class VersionCompatibilityTest {

    @ParameterizedTest
    @EnumSource(KmipSpec.class)
    @DisplayName("Should set KmipContext for all KMIP versions")
    void shouldSupportAllKmipVersions(KmipSpec spec) {
        KmipContext.setSpec(spec);
        try {
            assertThat(KmipContext.getSpec()).isEqualTo(spec);
        } finally {
            KmipContext.clear();
        }
    }

    @Test
    @DisplayName("Should handle unsupported features gracefully")
    void shouldHandleUnsupportedFeatures() {
        KmipSpec oldSpec = KmipSpec.V1_0;
        ProtocolVersion newVersion = ProtocolVersion.of(2, 0);
        assertThat(newVersion.isSupportedFor(oldSpec)).isFalse();
    }
}
```

## Performance Testing

### JMH Benchmark

```java
/**
 * JMH benchmarks for KeyBlock serialization performance.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@Threads(4)
public class ProtocolVersionBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
            private final ObjectMapper jsonMapper = new ObjectMapper()
            .registerModule(new KmipJsonModule());

        private final TtlvMapper ttlvMapper = new TtlvMapper();
        {
            ttlvMapper.registerModule(new KmipTtlvModule());
        }
                
        private ProtocolVersion protocolVersion;
        
        @Setup(Level.Trial)
        public void setup() {
            protocolVersion = ProtocolVersion.of(1, 2);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public byte[] benchmarkTtlvSerialization(BenchmarkState state) throws Exception {
        return state.ttlvMapper.writeValueAsBytes(state.protocolVersion);
    }
    
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public byte[] benchmarkJsonSerialization(BenchmarkState state) throws Exception {
        return state.jsonMapper.writeValueAsBytes(state.protocolVersion);
    }
    
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public ProtocolVersion benchmarkTtlvDeserialization(BenchmarkState state) throws Exception {
        byte[] data = state.ttlvMapper.writeValueAsBytes(state.protocolVersion);
        return state.ttlvMapper.readValue(data, ProtocolVersion.class);
    }
}
```

### Running Benchmarks

```bash
# Run all benchmarks via Maven profile
mvn -Pperf verify

# Quick benchmarks (reduced warmups/iterations)
mvn -Pperf-fast verify

# Pass JMH args (e.g., output JSON report)
mvn -Pperf -Dbench.args="-rf json -rff target/jmh.json -wi 3 -i 5 -f 1" verify
```

## Test Utilities

### Base Test Class

```java
/**
 * Base test class with common test utilities.
 */
public abstract class BaseKmipTest {

    protected ObjectMapper buildJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new KmipJsonModule());
        return mapper;
    }

    protected XmlMapper buildXmlMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.findAndRegisterModules();
        mapper.registerModule(new KmipXmlModule());
        return mapper;
    }

    protected TtlvMapper buildTtlvMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @BeforeEach
    void setUpContext() {
        KmipContext.setSpec(KmipSpec.V1_4);
    }

    @AfterEach
    void clearContext() {
        KmipContext.clear();
    }
}
```

### Test Data Factory

```java
/**
 * Factory for creating test data.
 */
public final class KmipTestDataFactory {

    private KmipTestDataFactory() {}

    public static ProtocolVersion createProtocolVersion(int major, int minor) {
        return ProtocolVersion.of(major, minor);
    }

    public static ActivationDateAttribute createActivationDate(OffsetDateTime dt) {
        return new ActivationDateAttribute(dt);
    }
}
```

## Test Coverage

### Configuring JaCoCo

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <excludes>
            <exclude>**/model/**/package-info.class</exclude>
            <exclude>**/config/**</exclude>
        </excludes>
    </configuration>
</plugin>
```

### Running with Coverage

```bash
# Run tests with coverage
mvn clean test jacoco:report

# View HTML report
open target/site/jacoco/index.html

# Check coverage thresholds
mvn jacoco:check
```

## Best Practices

### 1. Test Structure
- Follow the Arrange-Act-Assert (AAA) pattern
- Use descriptive test method names
- Group related tests with `@Nested` classes
- Use parameterized tests for similar test cases

### 2. Assertions
- Prefer AssertJ for fluent assertions
- Use custom assertion classes for complex objects
- Verify all relevant properties in assertions

### 3. Test Data
- Use test data builders for complex objects
- Keep test data close to tests
- Use random data for better test coverage

### 4. Performance Testing
- Use JMH for microbenchmarks
- Run benchmarks in isolated environments
- Monitor memory usage and GC behavior

### 5. Integration Testing
- Test all serialization formats
- Verify version compatibility
- Test error conditions and edge cases

### 6. Test Maintenance
- Keep tests independent and isolated
- Avoid test dependencies
- Use meaningful test data
- Document test assumptions and constraints

<!-- Mockito examples omitted intentionally to keep tests tied to existing project types. -->
