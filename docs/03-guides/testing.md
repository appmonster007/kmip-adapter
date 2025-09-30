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

## Test Style and Import Guidelines

- Prefer simple imports over fully qualified names (FQNs) throughout the test sources. For example:
  - Use `import java.util.Objects;` and `Objects.equals(...)` instead of `java.util.Objects.equals(...)`.
  - Use `import java.util.NoSuchElementException;` instead of `java.util.NoSuchElementException` in assertions.
  - Use AssertJ static imports, e.g., `import static org.assertj.core.api.Assertions.*;` or explicit `assertThat`, `assertThatThrownBy`, `assertThatExceptionOfType` rather than `org.assertj.core.api.Assertions.*` FQNs.
- Keep imports grouped and ordered consistently: standard imports, then static imports.
- Mirror runtime packages in the test directory and keep unit tests focused on domain behavior; place codec-specific round-trips under `codec/` packages.

Examples of preferred imports in tests:

```java
import java.util.Objects;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.*;
```

## Unit Testing

### Testing Enumerations

```java
/**
 * Tests for {@link FooEnum} enumeration.
 */
@ExtendWith(MockitoExtension.class)
class FooEnumTest {

    @Test
    void testDefaultCreation() {
        FooEnum fooEnum = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);
        assertThat(fooEnum.getValue()).isEqualTo(FooEnum.Standard.PLACEHOLDER_1);
        assertThat(fooEnum.getKmipTag()).isEqualTo(FooEnum.kmipTag);
        assertThat(fooEnum.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }

    @Test
    void testEquality() {
        FooEnum foo1 = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);
        FooEnum foo2 = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);
        assertThat(foo1).isEqualTo(foo2);
        assertThat(foo1.hashCode()).isEqualTo(foo2.hashCode());
    }

    @Test
    void testRegistryBehavior() {
        // Test that standard values are registered
        assertThat(FooEnum.fromValue(FooEnum.Standard.PLACEHOLDER_1.getValue()))
            .isEqualTo(FooEnum.Standard.PLACEHOLDER_1);
        
        assertThat(FooEnum.fromName("Placeholder1"))
            .isEqualTo(FooEnum.Standard.PLACEHOLDER_1);
    }

    @Test
    void testLookupValidation() {
        assertThatThrownBy(() -> FooEnum.fromValue(999))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("No FooEnum found for value: 999");
            
        assertThatThrownBy(() -> FooEnum.fromName("InvalidName"))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("No FooEnum found for name: InvalidName");
    }

### Reusable Enumeration Suite Hooks

`AbstractKmipEnumerationSuite` now provides two separate, opt-in hooks to validate registry behavior for enumeration types that support runtime registration (e.g., `State`):

- `supportsRegistryBehavior()` — return `true` to opt-in.
- `assertEnumerationRegistryBehaviorPositive()` — implement positive cases such as valid registration and lookups by value/name.
- `assertEnumerationRegistryBehaviorNegative()` — implement negative cases such as invalid extension ranges, empty descriptions, and empty version sets.

This split avoids overloading a single method and produces clearer test reports by separating positive and negative behaviors.

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

### Testing Data Types

```java
/**
 * Tests for {@link FooDataType} data type.
 */
@ExtendWith(MockitoExtension.class)
class FooDataTypeTest {

    @Test
    void testDefaultCreation() {
        OffsetDateTime now = OffsetDateTime.now();
        FooDataType fooDataType = FooDataType.of(now);
        
        assertThat(fooDataType.getValue()).isEqualTo(now);
        assertThat(fooDataType.getKmipTag()).isEqualTo(FooDataType.kmipTag);
        assertThat(fooDataType.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
    }

    @Test
    void testExpectedEncodingType() {
        OffsetDateTime now = OffsetDateTime.now();
        FooDataType fooDataType = FooDataType.of(now);
        
        assertThat(fooDataType.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
    }

    @Test
    void testKmipSpecSupport() {
        OffsetDateTime now = OffsetDateTime.now();
        FooDataType fooDataType = FooDataType.of(now);
        
        // Test with supported spec
        KmipContext.setSpec(KmipSpec.V1_2);
        try {
            assertThat(fooDataType.isSupported()).isTrue();
        } finally {
            KmipContext.clear();
        }
    }

    @Test
    void testEquality() {
        OffsetDateTime now = OffsetDateTime.now();
        FooDataType foo1 = FooDataType.of(now);
        FooDataType foo2 = FooDataType.of(now);
        
        assertThat(foo1).isEqualTo(foo2);
        assertThat(foo1.hashCode()).isEqualTo(foo2.hashCode());
    }
}
```

### Testing Structures

```java
/**
 * Tests for {@link FooStructure} structure.
 */
@ExtendWith(MockitoExtension.class)
class FooStructureTest {

    @Test
    void testDefaultCreation() {
        ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
        State state = new State(State.Standard.ACTIVE);
        
        FooStructure fooStructure = FooStructure.of(activationDate, state);
        
        assertThat(fooStructure.getActivationDate()).isEqualTo(activationDate);
        assertThat(fooStructure.getState()).isEqualTo(state);
        assertThat(fooStructure.getKmipTag()).isEqualTo(FooStructure.kmipTag);
        assertThat(fooStructure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
    }

    @Test
    void testExpectedEncodingType() {
        ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
        FooStructure fooStructure = FooStructure.of(activationDate, null);
        
        assertThat(fooStructure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
    }

    @Test
    void testMinimumComponentCount() {
        ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
        FooStructure fooStructure = FooStructure.of(activationDate, null);
        
        assertThat(fooStructure.getValues()).hasSize(1);
        assertThat(fooStructure.getValues()).contains(activationDate);
    }

    @Test
    void testBuilderValidation() {
        // Test that builder validates KMIP spec compatibility
        KmipContext.setSpec(KmipSpec.V1_2);
        try {
            ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
            State state = new State(State.Standard.ACTIVE);
            
            FooStructure structure = FooStructure.builder()
                .activationDate(activationDate)
                .state(state)
                .build();
                
            assertThat(structure).isNotNull();
            assertThat(structure.getValues()).hasSize(2);
        } finally {
            KmipContext.clear();
        }
    }

    @Test
    void testEquality() {
        ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
        State state = new State(State.Standard.ACTIVE);
        
        FooStructure foo1 = FooStructure.of(activationDate, state);
        FooStructure foo2 = FooStructure.of(activationDate, state);
        
        assertThat(foo1).isEqualTo(foo2);
        assertThat(foo1.hashCode()).isEqualTo(foo2.hashCode());
    }
}
```

## Integration Testing

### Testing Serialization Round-Trip

```java
/**
 * Integration tests for FooEnum, FooDataType, and FooStructure serialization.
 */
@DisplayName("Foo Types Serialization Integration Tests")
class FooTypesSerializationIntegrationTest extends BaseKmipTest {

    @Test
    @DisplayName("Should perform JSON round-trip for FooEnum")
    void shouldRoundTripFooEnumJson() throws Exception {
        ObjectMapper objectMapper = buildJsonMapper();
        FooEnum original = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);

        String json = objectMapper.writeValueAsString(original);
        FooEnum deserialized = objectMapper.readValue(json, FooEnum.class);

        assertThat(deserialized).isEqualTo(original);
    }

    @Test
    @DisplayName("Should perform JSON round-trip for FooDataType")
    void shouldRoundTripFooDataTypeJson() throws Exception {
        ObjectMapper objectMapper = buildJsonMapper();
        FooDataType original = FooDataType.of(OffsetDateTime.now());

        String json = objectMapper.writeValueAsString(original);
        FooDataType deserialized = objectMapper.readValue(json, FooDataType.class);

        assertThat(deserialized).isEqualTo(original);
    }

    @Test
    @DisplayName("Should perform JSON round-trip for FooStructure")
    void shouldRoundTripFooStructureJson() throws Exception {
        ObjectMapper objectMapper = buildJsonMapper();
        ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
        State state = new State(State.Standard.ACTIVE);
        FooStructure original = FooStructure.of(activationDate, state);

        String json = objectMapper.writeValueAsString(original);
        FooStructure deserialized = objectMapper.readValue(json, FooStructure.class);

        assertThat(deserialized).isEqualTo(original);
    }

    @Test
    @DisplayName("Should perform TTLV round-trip for all Foo types")
    void shouldRoundTripFooTypesTtlv() throws Exception {
        TtlvMapper ttlvMapper = buildTtlvMapper();
        KmipContext.setSpec(KmipSpec.V1_2);
        try {
            // Test FooEnum
            FooEnum originalEnum = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);
            byte[] enumTtlv = ttlvMapper.writeValueAsBytes(originalEnum);
            FooEnum deserializedEnum = ttlvMapper.readValue(enumTtlv, FooEnum.class);
            assertThat(deserializedEnum).isEqualTo(originalEnum);

            // Test FooDataType
            FooDataType originalData = FooDataType.of(OffsetDateTime.now());
            byte[] dataTtlv = ttlvMapper.writeValueAsBytes(originalData);
            FooDataType deserializedData = ttlvMapper.readValue(dataTtlv, FooDataType.class);
            assertThat(deserializedData).isEqualTo(originalData);

            // Test FooStructure
            ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
            State state = new State(State.Standard.ACTIVE);
            FooStructure originalStructure = FooStructure.of(activationDate, state);
            byte[] structureTtlv = ttlvMapper.writeValueAsBytes(originalStructure);
            FooStructure deserializedStructure = ttlvMapper.readValue(structureTtlv, FooStructure.class);
            assertThat(deserializedStructure).isEqualTo(originalStructure);
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
