# KMIP Testing Guide

Comprehensive testing framework and best practices for the KMIP Adapter, ensuring reliability, performance, and correctness of KMIP protocol implementations.

## 🧪 Test Coverage

| Test Category | Description | Example Test Classes |
|--------------|-------------|----------------------|
| **Unit Tests** | Test individual components in isolation | `KmipTagTest`, `EncodingTypeTest` |
| **Serialization** | JSON/XML/TTLV serialization/deserialization | `JsonSerializationTest`, `TtlvSerializationTest` |
| **Integration** | Component interactions and end-to-end flows | `SerializationIntegrationTest` |
| **Concurrency** | Thread safety and parallel execution | `ParallelSerializationTest` |
| **Performance** | Benchmarks and performance testing | `BasePerformanceTest` |
| **Validation** | Input validation and constraints | `ProtocolVersionTest` |

## 📋 Table of Contents

- [Test Structure](#test-structure)
- [Test Organization](#test-organization)
- [Testing Patterns](#testing-patterns)
- [Test Utilities](#test-utilities)
- [Performance Testing](#performance-testing)
- [Best Practices](#best-practices)

## Test Structure

### Base Test Classes

#### BaseKmipTest
Base class providing common test utilities and setup:

```java
public abstract class BaseKmipTest {
    protected ObjectMapper jsonMapper;
    protected XmlMapper xmlMapper;
    
    @BeforeEach
    void setUp() {
        // Initialize JSON mapper with KMIP module
        jsonMapper = new ObjectMapper();
        jsonMapper.findAndRegisterModules();
        jsonMapper.registerModule(new KmipJsonModule());
        
        // Initialize XML mapper
        xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();
        xmlMapper.registerModule(new KmipXmlModule());
        
        // Set up KMIP context
        KmipContext.setSpec(KmipSpec.V1_2);
    }
    
    @AfterEach
    void tearDown() {
        // Clean up context
        KmipContext.clear();
    }
}
```

## Test Organization

### Package Structure

```
src/test/java/org/purpleBean/kmip/
├── codec/
│   ├── json/JsonSerializationTest.java
│   ├── ttlv/TtlvSerializationTest.java
│   └── xml/XmlSerializationTest.java
├── common/
│   ├── ActivationDateAttributeTest.java
│   └── enumeration/StateTest.java
├── integration/
│   ├── SerializationIntegrationTest.java
│   └── KmipIntegrationTest.java
└── test/
    ├── BaseKmipTest.java
    └── BasePerformanceTest.java
```

## Testing Patterns

### 1. Serialization Testing

```java
class JsonSerializationTest extends BaseKmipTest {
    @Test
    void shouldSerializeAndDeserializeState() throws Exception {
        // Given
        State state = State.ACTIVE;
        
        // When
        String json = jsonMapper.writeValueAsString(state);
        State deserialized = jsonMapper.readValue(json, State.class);
        
        // Then
        assertThat(deserialized).isEqualTo(state);
    }
}
```

### 2. Validation Testing

```java
class ProtocolVersionTest {
    @Test
    void shouldValidateMajorVersion() {
        // Given
        ProtocolVersion version = ProtocolVersion.of(1, 2);
        
        // When/Then
        assertThat(version.isSupportedFor(KmipSpec.V1_2)).isTrue();
        assertThat(version.isSupportedFor(KmipSpec.V1_4)).isFalse();
    }
}
```

### 3. Concurrency Testing

```java
class ParallelSerializationTest {
    @Test
    void shouldHandleConcurrentSerialization() throws Exception {
        // Given
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Boolean>> futures = new ArrayList<>();
        
        // When
        for (int i = 0; i < threadCount; i++) {
            futures.add(executor.submit(() -> {
                try {
                    // Each thread sets its own thread-local spec
                    KmipContext.setSpec(KmipSpec.V1_2);
                    
                    // Test serialization
                    ProtocolVersion version = ProtocolVersion.of(1, 4);
                    String json = jsonMapper.writeValueAsString(version);
                    ProtocolVersion deserialized = jsonMapper.readValue(json, ProtocolVersion.class);
                    
                    return version.equals(deserialized);
                } finally {
                    KmipContext.clear();
                }
            }));
        }
        
        // Then
        for (Future<Boolean> future : futures) {
            assertThat(future.get()).isTrue();
        }
    }
}
```

## Test Utilities

### AssertJ Custom Assertions

```java
public class KmipAssertions extends AbstractAssert<KmipAssertions, KmipDataType> {
    public static KmipAssertions assertThat(KmipDataType actual) {
        return new KmipAssertions(actual);
    }
    
    public KmipAssertions hasTag(KmipTag expected) {
        isNotNull();
        
        if (!Objects.equals(actual.getKmipTag(), expected)) {
            failWithMessage("Expected tag to be <%s> but was <%s>", 
                expected, actual.getKmipTag());
        }
        
        return this;
    }
    
    public KmipAssertions isSupportedFor(KmipSpec spec) {
        isNotNull();
        
        if (!actual.isSupportedFor(spec)) {
            failWithMessage("Expected type to be supported for %s", spec);
        }
        
        return this;
    }
}
```

### Test Data Factory

```java
public final class KmipTestDataFactory {
    private KmipTestDataFactory() {}
    
    public static ProtocolVersion createProtocolVersion(int major, int minor) {
        return ProtocolVersion.builder()
            .major(new ProtocolVersionMajor(major))
            .minor(new ProtocolVersionMinor(minor))
            .build();
    }
    
    public static ActivationDateAttribute createActivationDate(Instant when) {
        return ActivationDateAttribute.builder()
            .activationDate(OffsetDateTime.ofInstant(when, ZoneOffset.UTC))
            .build();
    }
}
```

## Performance Testing

### Base Performance Test

```java
public abstract class BasePerformanceTest {
    protected static final int WARMUP_ITERATIONS = 10_000;
    protected static final int MEASUREMENT_ITERATIONS = 100_000;
    
    protected void runBenchmark(String name, Runnable benchmark) {
        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            benchmark.run();
        }
        
        // Measurement
        long start = System.nanoTime();
        for (int i = 0; i < MEASUREMENT_ITERATIONS; i++) {
            benchmark.run();
        }
        long duration = System.nanoTime() - start;
        
        System.out.printf("%s: %d ops/ms%n", 
            name, 
            (MEASUREMENT_ITERATIONS * 1_000_000) / duration);
    }
}
```

### Example Performance Test

```java
class ProtocolVersionPerformanceTest extends BasePerformanceTest {
    private final ObjectMapper jsonMapper = new ObjectMapper().registerModule(new KmipJsonModule());
    
    @Test
    void benchmarkSerialization() {
        ProtocolVersion version = ProtocolVersion.of(1, 4);
        
        runBenchmark("ProtocolVersion JSON Serialization", () -> {
            try {
                jsonMapper.writeValueAsString(version);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
```

## Best Practices

### 1. Test Organization
- Group tests by functionality (unit, integration, performance)
- Follow the same package structure as main code
- Use descriptive test method names (shouldXxx, whenXxx_thenXxx)

### 2. Test Data Management
- Use test data factories for complex objects
- Keep test data close to tests
- Use builders for object creation in tests

### 3. Assertions
- Prefer AssertJ for fluent assertions
- Add custom assertions for domain-specific checks
- Include descriptive failure messages

### 4. Test Isolation
- Each test should be independent
- Reset shared state in @BeforeEach/@AfterEach
- Use @Nested for related test cases

### 5. Performance Testing
- Include warmup iterations
- Measure operations per second
- Run in a controlled environment
- Document performance expectations

### 6. Test Naming Conventions
- Method Naming: `should<DoSomething>When<Condition>`
- Class Naming: `<ClassName>Test` for unit tests
- Integration Tests: `<Feature>IntegrationTest`
- Performance Tests: `<Feature>PerformanceTest`

### 7. Test Coverage
- Aim for high branch coverage
- Test edge cases and error conditions
- Include negative test cases
- Test with different KMIP versions
- [Code Coverage](#code-coverage)

## Test Structure

### Base Test Classes

1. **BaseKmipTest**: Base class for all KMIP tests
   - Common test utilities
   - Test data factories
   - Assertion helpers

2. **BaseSerializationTest**: For serialization tests
   - Common serialization assertions
   - Round-trip testing
   - Format-specific test cases

### Test Naming Conventions

- **Test Classes**: `{TypeUnderTest}Test`
- **Test Methods**: `should{ExpectedBehavior}When{StateUnderTest}`
- **Nested Classes**: Group related test cases
  - `ConstructionAndBasicProperties`
  - `Validation`
  - `Serialization`
  - `ThreadSafety`

## Test Organization

### Test Packages

```
src/test/java/org/purpleBean/kmip/
├── common/                   # Common type tests
│   ├── enumeration/         # Enumeration tests
│   ├── structure/           # Structure tests
│   └── attribute/           # Attribute tests
├── codec/                   # Codec tests
│   ├── json/                # JSON codec tests
│   └── ttlv/                # TTLV codec tests
└── integration/             # Integration tests
```

### Test Data Factories

- `KmipTestDataFactory`: Centralized test data creation
- `TestFixtures`: Reusable test data constants
- `TestDataGenerator`: Dynamic test data generation

All KMIP tests should extend `BaseKmipTest` which provides common test utilities and setup:

```java
@DisplayName("YourType Tests")
class YourTypeTest extends BaseKmipTest {
    // Test implementation
}
```

### Test Organization Pattern

Organize tests using nested classes for better structure and readability:

```java
@Nested
@DisplayName("Construction and Basic Properties")
class ConstructionAndBasicProperties {
    // Test basic object construction and properties
}

@Nested
@DisplayName("Validation")
class Validation {
    // Test input validation and edge cases
}

@Nested
@DisplayName("Serialization")
class Serialization {
    // Test serialization/deserialization
}

@Nested
@DisplayName("Performance")
class Performance extends BasePerformanceTest {
    @Benchmark
    public void benchmarkSerialization(Blackhole bh) {
        // Example performance test for serialization
        YourType obj = createTestObject();
        String json = objectMapper.writeValueAsString(obj);
        preventOptimization(bh, json);
    }
}

@Nested
@DisplayName("Thread Safety")
class ThreadSafety {
    // Test thread safety for concurrent access
}
```

## Performance Testing

Performance tests should extend `BasePerformanceTest` and use JMH annotations:

```java
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class YourTypePerformanceTest extends BasePerformanceTest {
    
    private YourType testObject;
    
    @Setup(Level.Trial)
    public void setup() {
        testObject = createTestObject();
    }
    
    @Benchmark
    public void benchmarkSerialization(Blackhole bh) throws Exception {
        String json = objectMapper.writeValueAsString(testObject);
        preventOptimization(bh, json);
    }
    
    @Benchmark
    public void benchmarkDeserialization(Blackhole bh) throws Exception {
        String json = objectMapper.writeValueAsString(testObject);
        YourType obj = objectMapper.readValue(json, YourType.class);
        preventOptimization(bh, obj);
    }
}
```

## Test Reporting

Test execution statistics are automatically collected and can be viewed in the generated reports:

1. **HTML Reports**: Generated by Surefire in `target/site/surefire-report.html`
2. **Performance Reports**: Generated in `target/jmh-reports` after running JMH benchmarks
3. **Custom Reports**: Detailed test execution statistics are generated in `target/reports/test-report-{timestamp}.md`

To generate reports:

```bash
# Run tests and generate reports
mvn clean test

# Run performance tests
mvn clean verify -Pbenchmark

# Generate site with test reports
mvn site
```

## Analyzing Test Results

The test execution report includes:
- Summary of test executions and success rates
- List of the slowest tests
- Detailed execution statistics for all tests
- Performance metrics for benchmarks

## Best Practices

1. **Test Naming**: Use descriptive test names that explain the scenario being tested
2. **Test Isolation**: Each test should be independent and not rely on state from other tests
3. **Performance Testing**:
   - Run performance tests in isolation
   - Use `@State` to share expensive setup between benchmark methods
   - Use `Blackhole` to prevent dead code elimination
4. **Test Coverage**:
   - Aim for high test coverage (80%+)
   - Focus on testing behavior, not implementation details
   - Include edge cases and error conditions

## Continuous Integration

Add these steps to your CI pipeline to ensure test quality:

```yaml
steps:
  - name: Run tests with coverage
    run: mvn clean verify
    
  - name: Run performance tests
    run: mvn clean verify -Pbenchmark
    
  - name: Generate reports
    run: mvn site
    
  - name: Upload test results
    uses: actions/upload-artifact@v2
    with:
      name: test-reports
      path: |
        target/site/**/*
        target/jmh-reports/**/*
        target/reports/**/*
```

## Testing Patterns

### Unit Testing

```java
@Nested
@DisplayName("Construction and Basic Properties")
class ConstructionAndBasicProperties {
    @Test
    @DisplayName("Should create with valid parameters")
    void shouldCreateWithValidParameters() {
        // Given
        String expectedValue = "test";
        
        // When
        YourType instance = new YourType(expectedValue);
        
        // Then
        assertThat(instance.getValue()).isEqualTo(expectedValue);
    }
}
```

### Serialization Testing

```java
@Nested
@DisplayName("Serialization")
class SerializationTests {
    @Test
    @DisplayName("Should serialize to JSON")
    void shouldSerializeToJson() throws Exception {
        // Given
        YourType instance = TestFixtures.createYourType();
        
        // When
        String json = objectMapper.writeValueAsString(instance);
        
        // Then
        assertThatJson(json)
            .isObject()
            .containsEntry("value", instance.getValue());
    }
    
    @Test
    @DisplayName("Should deserialize from JSON")
    void shouldDeserializeFromJson() throws Exception {
        // Given
        String json = "{\"value\":\"test\"}";
        
        // When
        YourType result = objectMapper.readValue(json, YourType.class);
        
        // Then
        assertThat(result.getValue()).isEqualTo("test");
    }
}
```

### Thread Safety Testing

```java
@Test
@DisplayName("Should be thread-safe for concurrent access")
void shouldBeThreadSafe() throws Exception {
    // Given
    final YourType sharedInstance = new YourType();
    final int threadCount = 10;
    final ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    final CountDownLatch latch = new CountDownLatch(threadCount);
    
    // When
    for (int i = 0; i < threadCount; i++) {
        executor.submit(() -> {
            try {
                sharedInstance.performOperation();
            } finally {
                latch.countDown();
            }
        });
    }
    
    // Then
    assertThat(latch.await(5, TimeUnit.SECONDS)).isTrue();
    assertThat(sharedInstance.getOperationCount()).isEqualTo(threadCount);
}
```

## Test Utilities

### Custom Assertions

```java
public class YourTypeAssert extends AbstractAssert<YourTypeAssert, YourType> {
    public static YourTypeAssert assertThat(YourType actual) {
        return new YourTypeAssert(actual, YourTypeAssert.class);
    }
    
    public YourTypeAssert hasValue(String expected) {
        isNotNull();
        if (!Objects.equals(actual.getValue(), expected)) {
            failWithMessage("Expected value to be <%s> but was <%s>", 
                expected, actual.getValue());
        }
        return this;
    }
}
```

### Test Data Builders

```java
@Builder(builderClassName = "Builder", builderMethodName = "aYourType")
public class YourTypeTestData {
    @Builder.Default
    private String value = "default";
    
    public YourType build() {
        return new YourType(value);
    }
}

// Usage:
YourType instance = aYourType().withValue("test").build();
```

## Best Practices

1. **Test Organization**
   - Group related tests in nested classes
   - Use descriptive test names
   - Follow the Arrange-Act-Assert pattern

2. **Test Data**
   - Use test data factories
   - Keep test data consistent
   - Generate random data for edge cases

3. **Assertions**
   - Use AssertJ for fluent assertions
   - Add custom assertions for domain-specific validation
   - Include descriptive failure messages

4. **Test Isolation**
   - Each test should be independent
   - Reset shared state between tests
   - Use `@BeforeEach` for common setup

5. **Performance Testing**
   - Use JMH for microbenchmarks
   - Test with realistic data volumes
   - Monitor memory usage

## Code Coverage

- **Aim for 100% code coverage**
- Use JaCoCo for coverage reports
- Focus on testing edge cases and error conditions
- Include negative test cases
- Test all public methods and constructors

### Coverage Reports

Generate coverage reports with Maven:

```bash
mvn clean test jacoco:report
```

View the report at: `target/site/jacoco/index.html`

## Troubleshooting

- **Flaky Tests**: Use `@RepeatedTest` to identify intermittently failing tests
- **Performance Regressions**: Compare benchmark results with previous runs
- **Memory Leaks**: Use `-XX:+HeapDumpOnOutOfMemoryError` to debug OOM issues

## Additional Resources

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [JMH Samples](https://hg.openjdk.java.net/code-tools/jmh/file/tree/jmh-samples/src/main/java/org/openjdk/jmh/samples/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)

## Next Steps

1. Review the generated test reports
2. Identify and optimize slow tests
3. Add performance benchmarks for critical paths
4. Set up CI/CD integration for test reporting

```
@DisplayName("Edge Cases")
class EdgeCases {
    // Test boundary conditions and edge cases
}
class Serialization { }

@Nested
@DisplayName("KMIP Spec Compatibility")
class KmipSpecCompatibility { }
```

## ✅ Unit Test Templates

### Test Organization

Tests are organized by component and test type, following this structure:

```
src/test/java/
  org/purpleBean/kmip/
    common/
      enumeration/
        StateTest.java          # Tests for State enumeration
    structure/
      SampleStructureTest.java  # Tests for sample structures
    codec/
      json/
      xml/
      ttlv/
    integration/
      KmipIntegrationTest.java  # Integration tests
```

### Running Tests

Run all tests:
```bash
mvn clean test
```

Run a specific test class:
```bash
mvn test -Dtest=StateTest
```

Run a specific test method:
```bash
mvn test -Dtest=StateTest#shouldHandleStandardStates
```

## Unit Test Templates

### Enumeration Test Template

```java
package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("YourEnumeration Tests")
class YourEnumerationTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @ParameterizedTest
        @EnumSource(YourEnumeration.Standard.class)
        @DisplayName("Should create with all standard values")
        void shouldCreateWithStandardValues(YourEnumeration.Standard standard) {
            // Given & When
            YourEnumeration enumeration = new YourEnumeration(standard);

            // Then
            assertThat(enumeration).isNotNull();
            assertThat(enumeration.getValue()).isEqualTo(standard);
            assertThat(enumeration.getDescription()).isEqualTo(standard.getDescription());
            assertThat(enumeration.getIntValue()).isEqualTo(standard.getValue());
        }

        @Test
        @DisplayName("Should have correct KMIP properties")
        void shouldHaveCorrectKmipProperties() {
            // Given
            YourEnumeration enumeration = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);

            // Then
            assertThat(enumeration.getKmipTag()).isNotNull();
            assertThat(enumeration.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.YOUR_ENUMERATION_TAG);
            assertThat(enumeration.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }

        @Test
        @DisplayName("Should implement equals and hashCode correctly")
        void shouldImplementEqualsAndHashCodeCorrectly() {
            // Given
            YourEnumeration enum1 = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);
            YourEnumeration enum2 = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);
            YourEnumeration enum3 = new YourEnumeration(YourEnumeration.Standard.VALUE_TWO);

            // Then
            assertThat(enum1).isEqualTo(enum2);
            assertThat(enum1).isNotEqualTo(enum3);
            assertThat(enum1.hashCode()).isEqualTo(enum2.hashCode());
            assertThat(enum1.hashCode()).isNotEqualTo(enum3.hashCode());
        }
    }

    @Nested
    @DisplayName("Registry Operations")
    class RegistryOperations {

        @Test
        @DisplayName("Should register custom extension value")
        void shouldRegisterCustomExtensionValue() {
            // Given
            int customValue = 0x80000001;
            String description = "CustomValue";
            Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

            // When
            YourEnumeration.Value registered = YourEnumeration.register(
                    customValue, description, supportedVersions
            );

            // Then
            assertThat(registered).isNotNull();
            assertThat(registered.getValue()).isEqualTo(customValue);
            assertThat(registered.getDescription()).isEqualTo(description);
            assertThat(registered.isCustom()).isTrue();
            assertThat(registered.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }

        @Test
        @DisplayName("Should lookup registered custom value")
        void shouldLookupRegisteredCustomValue() {
            // Given
            int customValue = 0x80000002;
            String description = "AnotherCustomValue";
            YourEnumeration.register(customValue, description, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            // When
            YourEnumeration.Value found = YourEnumeration.fromValue(KmipSpec.V1_2, customValue);

            // Then
            assertThat(found).isNotNull();
            assertThat(found.getValue()).isEqualTo(customValue);
            assertThat(found.getDescription()).isEqualTo(description);
        }

        @Test
        @DisplayName("Should lookup by name")
        void shouldLookupByName() {
            // Given
            String description = "ValueOne";

            // When
            YourEnumeration.Value found = YourEnumeration.fromName(KmipSpec.V1_2, description);

            // Then
            assertThat(found).isNotNull();
            assertThat(found).isEqualTo(YourEnumeration.Standard.VALUE_ONE);
        }
    }

    @Nested
    @DisplayName("Validation")
    class Validation {

        @Test
        @DisplayName("Should reject null value in constructor")
        void shouldRejectNullValueInConstructor() {
            // When & Then
            assertThatThrownBy(() -> new YourEnumeration(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should reject invalid extension value in register")
        void shouldRejectInvalidExtensionValueInRegister() {
            // Given
            int invalidValue = 0x7FFFFFFF; // Not in extension range

            // When & Then
            assertThatThrownBy(() -> YourEnumeration.register(
                    invalidValue, "Invalid", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)
            )).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Extension value");
        }

        @Test
        @DisplayName("Should reject empty description in register")
        void shouldRejectEmptyDescriptionInRegister() {
            // When & Then
            assertThatThrownBy(() -> YourEnumeration.register(
                    0x80000003, "", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)
            )).isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Description cannot be empty");
        }

        @Test
        @DisplayName("Should reject null parameters in register")
        void shouldRejectNullParametersInRegister() {
            // When & Then
            assertThatThrownBy(() -> YourEnumeration.register(0x80000004, null, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)))
                    .isInstanceOf(NullPointerException.class);

            assertThatThrownBy(() -> YourEnumeration.register(0x80000005, "Test", null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should reject unsupported value for KMIP spec")
        void shouldRejectUnsupportedValueForKmipSpec() {
            // Given
            KmipContext.setSpec(KmipSpec.UnknownVersion);

            // When & Then
            assertThatThrownBy(() -> new YourEnumeration(YourEnumeration.Standard.VALUE_ONE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Unsupported value");
        }
    }

    @Nested
    @DisplayName("KMIP Spec Compatibility")
    class KmipSpecCompatibility {

        @Test
        @DisplayName("Should support V1_2 specification")
        void shouldSupportV1_2Specification() {
            // Given
            YourEnumeration enumeration = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);

            // When & Then
            assertThat(enumeration.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }

        @Test
        @DisplayName("Should handle null spec gracefully")
        void shouldHandleNullSpecGracefully() {
            // Given
            YourEnumeration enumeration = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);

            // When & Then
            assertThat(enumeration.isSupportedFor(null)).isTrue();
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize to JSON")
        void shouldSerializeToJson() throws Exception {
            // Given
            YourEnumeration enumeration = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);

            // When
            String json = jsonMapper.writeValueAsString(enumeration);

            // Then
            assertThat(json).isNotBlank();
            assertThat(json).contains("kmipTag");
            assertThat(json).contains("type");
            assertThat(json).contains("value");
        }

        @Test
        @DisplayName("Should deserialize from JSON")
        void shouldDeserializeFromJson() throws Exception {
            // Given
            YourEnumeration original = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);
            String json = jsonMapper.writeValueAsString(original);

            // When
            YourEnumeration deserialized = jsonMapper.readValue(json, YourEnumeration.class);

            // Then
            assertThat(deserialized).isEqualTo(original);
        }

        @Test
        @DisplayName("Should serialize to XML")
        void shouldSerializeToXml() throws Exception {
            // Given
            YourEnumeration enumeration = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);

            // When
            String xml = xmlMapper.writeValueAsString(enumeration);

            // Then
            assertThat(xml).isNotBlank();
            assertThat(xml).contains(enumeration.getKmipTag().getDescription());
        }

        @Test
        @DisplayName("Should perform JSON round-trip serialization")
        void shouldPerformJsonRoundTripSerialization() throws Exception {
            // Given
            YourEnumeration original = new YourEnumeration(YourEnumeration.Standard.VALUE_TWO);

            // When
            YourEnumeration result = SerializationTestUtils.jsonRoundTrip(original, YourEnumeration.class);

            // Then
            assertThat(result).isEqualTo(original);
        }
    }
}
```

### Registry Pattern Testing

When testing classes that maintain registries (like `KmipTag`), ensure proper cleanup:

```java
@Nested
@DisplayName("Registry Operations")
class RegistryOperations {
    
    @AfterEach
    void cleanup() {
        // Clean up test registrations
        cleanupTestTags();
    }
    
    @Test
    @DisplayName("Should register and find custom value")
    void shouldRegisterAndFindCustomValue() {
        try {
            // Register test value
            CustomType value = CustomType.register("TEST", "Test Value");
            
            // Verify registration
            assertThat(CustomType.fromName("TEST")).isEqualTo(value);
        } finally {
            // Clean up
            CustomType.cleanupTestValues();
        }
    }
}

// In test base class or utility
protected static void cleanupTestTags() {
    // Implementation to clean up test registrations
}
```

### Thread Safety Testing

Test thread safety for concurrent access:

```java
@Nested
@DisplayName("Thread Safety")
class ThreadSafety {
    
    @Test
    @DisplayName("Should be thread-safe for concurrent access")
    void shouldBeThreadSafe() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(1);
        
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            futures.add(executor.submit(() -> {
                latch.await();
                // Perform thread-safe operations
                return null;
            }));
        }
        
        // Start all threads at once
        latch.countDown();
        
        // Wait for completion
        for (Future<?> future : futures) {
            future.get();
        }
        
        // Verify thread safety expectations
    }
}
```

### Attribute Test Template

```java
@DisplayName("YourAttribute Tests")
class YourAttributeTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create with valid data")
        void shouldCreateWithValidData() {
            // Given
            YourDataType validData = createValidData();
            
            // When
            YourAttribute attribute = YourAttribute.builder()
                .data(validData)
                .build();
            
            // Then
            assertThat(attribute).isNotNull();
            assertThat(attribute.getData()).isEqualTo(validData);
            assertThat(attribute.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.YOUR_ATTRIBUTE_TAG);
        }

        @Test
        @DisplayName("Should have correct attribute properties")
        void shouldHaveCorrectAttributeProperties() {
            // Given
            YourAttribute attribute = YourAttribute.builder()
                .data(createValidData())
                .build();
            
            // Then
            assertThat(attribute.isAlwaysPresent()).isFalse();
            assertThat(attribute.isServerInitializable()).isTrue();
            assertThat(attribute.isClientInitializable()).isTrue();
            assertThat(attribute.isClientDeletable()).isFalse();
            assertThat(attribute.isMultiInstanceAllowed()).isFalse();
        }
    }

    @Nested
    @DisplayName("State-Based Modifiability")
    class StateBasedModifiability {

        @Test
        @DisplayName("Should allow client modification in PRE_ACTIVE state")
        void shouldAllowClientModificationInPreActiveState() {
            // Given
            YourAttribute attribute = createValidAttribute();
            State preActiveState = new State(State.Standard.PRE_ACTIVE);
            
            // When & Then
            assertThat(attribute.isClientModifiable(preActiveState)).isTrue();
        }

        @Test
        @DisplayName("Should not allow client modification in ACTIVE state")
        void shouldNotAllowClientModificationInActiveState() {
            // Given
            YourAttribute attribute = createValidAttribute();
            State activeState = new State(State.Standard.ACTIVE);
            
            // When & Then
            assertThat(attribute.isClientModifiable(activeState)).isFalse();
        }

        @Test
        @DisplayName("Should allow server modification in multiple states")
        void shouldAllowServerModificationInMultipleStates() {
            // Given
            YourAttribute attribute = createValidAttribute();
            
            // When & Then
            assertThat(attribute.isServerModifiable(new State(State.Standard.PRE_ACTIVE))).isTrue();
            assertThat(attribute.isServerModifiable(new State(State.Standard.ACTIVE))).isTrue();
        }
    }

    @Nested
    @DisplayName("Validation")
    class Validation {

        @Test
        @DisplayName("Should reject null data in builder")
        void shouldRejectNullDataInBuilder() {
            // When & Then
            assertThatThrownBy(() -> YourAttribute.builder().build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("data cannot be null");
        }

        @Test
        @DisplayName("Should reject null state in modifiability checks")
        void shouldRejectNullStateInModifiabilityChecks() {
            // Given
            YourAttribute attribute = createValidAttribute();
            
            // When & Then
            assertThatThrownBy(() -> attribute.isClientModifiable(null))
                .isInstanceOf(NullPointerException.class);
                
            assertThatThrownBy(() -> attribute.isServerModifiable(null))
                .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should validate data constraints")
        void shouldValidateDataConstraints() {
            // When & Then - Add specific validation tests for your data type
            // Example for string data:
            // assertThatThrownBy(() -> YourAttribute.builder().data("").build())
            //     .isInstanceOf(IllegalArgumentException.class);
        }
    }

    private YourDataType createValidData() {
        // Return valid test data for your attribute
        return new YourDataType(/* valid parameters */);
    }

    private YourAttribute createValidAttribute() {
        return YourAttribute.builder()
            .data(createValidData())
            .build();
    }
}
```

### Structure Test Template

```java
@DisplayName("YourStructure Tests")
class YourStructureTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create with required fields only")
        void shouldCreateWithRequiredFieldsOnly() {
            // Given
            RequiredFieldType requiredField = createValidRequiredField();
            
            // When
            YourStructure structure = YourStructure.builder()
                .requiredField(requiredField)
                .build();
            
            // Then
            assertThat(structure).isNotNull();
            assertThat(structure.getRequiredField()).isEqualTo(requiredField);
            assertThat(structure.getOptionalField()).isNull();
        }

        @Test
        @DisplayName("Should create with all fields")
        void shouldCreateWithAllFields() {
            // Given
            RequiredFieldType requiredField = createValidRequiredField();
            OptionalFieldType optionalField = createValidOptionalField();
            
            // When
            YourStructure structure = YourStructure.builder()
                .requiredField(requiredField)
                .optionalField(optionalField)
                .build();
            
            // Then
            assertThat(structure).isNotNull();
            assertThat(structure.getRequiredField()).isEqualTo(requiredField);
            assertThat(structure.getOptionalField()).isEqualTo(optionalField);
        }
    }

    @Nested
    @DisplayName("Validation")
    class Validation {

        @Test
        @DisplayName("Should reject missing required field")
        void shouldRejectMissingRequiredField() {
            // When & Then
            assertThatThrownBy(() -> YourStructure.builder().build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Required field cannot be null");
        }

        @Test
        @DisplayName("Should validate KMIP spec compatibility of fields")
        void shouldValidateKmipSpecCompatibilityOfFields() {
            // Given
            KmipContext.setSpec(KmipSpec.UnknownVersion);
            RequiredFieldType requiredField = createValidRequiredField();
            
            // When & Then - Assuming the field doesn't support UnknownVersion
            assertThatThrownBy(() -> YourStructure.builder()
                .requiredField(requiredField)
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not supported for KMIP spec");
        }

        @Test
        @DisplayName("Should validate business rules")
        void shouldValidateBusinessRules() {
            // Add tests for specific business rule validations
            // Example: conditional field requirements
        }
    }

    @Nested
    @DisplayName("Utility Methods")
    class UtilityMethods {

        @Test
        @DisplayName("Should return all fields correctly")
        void shouldReturnAllFieldsCorrectly() {
            // Given
            RequiredFieldType requiredField = createValidRequiredField();
            OptionalFieldType optionalField = createValidOptionalField();
            
            YourStructure structure = YourStructure.builder()
                .requiredField(requiredField)
                .optionalField(optionalField)
                .build();
            
            // When
            List<Object> allFields = structure.getAllFields();
            
            // Then
            assertThat(allFields).hasSize(2);
            assertThat(allFields).contains(requiredField, optionalField);
        }

        @Test
        @DisplayName("Should detect optional fields presence")
        void shouldDetectOptionalFieldsPresence() {
            // Given
            YourStructure withOptional = YourStructure.builder()
                .requiredField(createValidRequiredField())
                .optionalField(createValidOptionalField())
                .build();
                
            YourStructure withoutOptional = YourStructure.builder()
                .requiredField(createValidRequiredField())
                .build();
            
            // When & Then
            assertThat(withOptional.hasOptionalFields()).isTrue();
            assertThat(withoutOptional.hasOptionalFields()).isFalse();
        }
    }

    private RequiredFieldType createValidRequiredField() {
        // Return valid test data for required field
        return new RequiredFieldType(/* valid parameters */);
    }

    private OptionalFieldType createValidOptionalField() {
        // Return valid test data for optional field
        return new OptionalFieldType(/* valid parameters */);
    }
}
```

## Serialization Test Templates

### Adding to Existing Serialization Tests

Add your type to the existing serialization test files:

#### JsonSerializationTest.java

```java
@Test
@DisplayName("Should serialize and deserialize YourEnumeration")
void shouldSerializeAndDeserializeYourEnumeration() throws Exception {
    // Given
    YourEnumeration original = new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);
    
    // When & Then
    SerializationTestUtils.assertJsonRoundTrip(original, YourEnumeration.class);
}

@Test
@DisplayName("Should serialize and deserialize YourAttribute")
void shouldSerializeAndDeserializeYourAttribute() throws Exception {
    // Given
    YourAttribute original = KmipTestDataFactory.createYourAttribute();
    
    // When & Then
    SerializationTestUtils.assertJsonRoundTrip(original, YourAttribute.class);
}

@Test
@DisplayName("Should serialize and deserialize YourStructure")
void shouldSerializeAndDeserializeYourStructure() throws Exception {
    // Given
    YourStructure original = KmipTestDataFactory.createYourStructure();
    
    // When & Then
    SerializationTestUtils.assertJsonRoundTrip(original, YourStructure.class);
}
```

#### Update KmipTestDataFactory.java

```java
public static YourEnumeration createYourEnumeration() {
    return new YourEnumeration(YourEnumeration.Standard.VALUE_ONE);
}

public static YourAttribute createYourAttribute() {
    return YourAttribute.builder()
        .data(createValidYourDataType())
        .build();
}

public static YourStructure createYourStructure() {
    return YourStructure.builder()
        .requiredField(createValidRequiredField())
        .optionalField(createValidOptionalField())
        .build();
}
```

## Validation Test Templates

### Comprehensive Validation Test Methods

```java
@Test
@DisplayName("Should validate input parameters comprehensively")
void shouldValidateInputParametersComprehensively() {
    // Null validation
    assertThatThrownBy(() -> new YourType(null))
        .isInstanceOf(NullPointerException.class);
    
    // Range validation (for numeric types)
    assertThatThrownBy(() -> new YourType(-1))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("must be non-negative");
    
    // Format validation (for string types)
    assertThatThrownBy(() -> new YourType(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("cannot be empty");
    
    // KMIP spec validation
    KmipContext.setSpec(KmipSpec.UnknownVersion);
    assertThatThrownBy(() -> new YourType(validValue))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("not supported for KMIP spec");
}

@Test
@DisplayName("Should validate serialization preconditions")
void shouldValidateSerializationPreconditions() throws Exception {
    // Given
    YourType validObject = createValidObject();
    KmipContext.setSpec(KmipSpec.UnknownVersion);
    
    // When & Then
    assertThatThrownBy(() -> jsonMapper.writeValueAsString(validObject))
        .isInstanceOf(UnsupportedEncodingException.class)
        .hasMessageContaining("not supported for KMIP spec");
}
```

## Integration Test Templates

### Multi-Codec Integration Test

```java
@Test
@DisplayName("Should work across all codec formats")
void shouldWorkAcrossAllCodecFormats() throws Exception {
    // Given
    YourType original = KmipTestDataFactory.createYourType();
    
    // JSON round-trip
    YourType jsonResult = SerializationTestUtils.jsonRoundTrip(original, YourType.class);
    assertThat(jsonResult).isEqualTo(original);
    
    // XML round-trip
    YourType xmlResult = SerializationTestUtils.xmlRoundTrip(original, YourType.class);
    assertThat(xmlResult).isEqualTo(original);
    
    // TTLV round-trip
    YourType ttlvResult = SerializationTestUtils.ttlvRoundTrip(original, YourType.class);
    assertThat(ttlvResult).isEqualTo(original);
}

@Test
@DisplayName("Should maintain consistency across codec contexts")
void shouldMaintainConsistencyAcrossContexts() throws Exception {
    // Test with different KMIP specs in parallel threads
    CompletableFuture<YourType> v12Future = CompletableFuture.supplyAsync(() -> {
        KmipContext.setSpec(KmipSpec.V1_2);
        return KmipTestDataFactory.createYourType();
    });
    
    YourType result = v12Future.get();
    assertThat(result).isNotNull();
    assertThat(result.isSupportedFor(KmipSpec.V1_2)).isTrue();
}
```

These templates provide comprehensive test coverage including construction, validation, serialization, and integration testing with proper validation examples throughout.
