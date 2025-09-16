# Testing Guide

This guide covers testing strategies and best practices for the KMIP Adapter.

## Table of Contents
- [Test Structure](#test-structure)
- [Unit Testing](#unit-testing)
- [Integration Testing](#integration-testing)
- [Test Utilities](#test-utilities)
- [Test Data Factories](#test-data-factories)
- [Assertions](#assertions)
- [Parameterized Tests](#parameterized-tests)
- [Test Coverage](#test-coverage)
- [Performance Testing](#performance-testing)

## Test Structure

### Package Structure

```
src/test/java/org/purpleBean/kmip/
├── common/
│   ├── YourTypeTest.java
│   └── AnotherTypeTest.java
├── codec/
│   ├── json/JsonSerializationTest.java
│   ├── xml/XmlSerializationTest.java
│   └── ttlv/TtlvSerializationTest.java
└── integration/
    ├── SerializationIntegrationTest.java
    └── ConcurrencyTest.java
```

### Test Class Structure

```java
class YourTypeTest extends BaseKmipTest {
    
    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {
        @Test
        @DisplayName("Should create with valid parameters")
        void shouldCreateWithValidParameters() {
            // Test code
        }
    }
    
    @Nested
    @DisplayName("Validation")
    class Validation {
        @Test
        @DisplayName("Should reject null value")
        void shouldRejectNullValue() {
            // Test code
        }
    }
    
    @Nested
    @DisplayName("Serialization")
    class Serialization {
        @Test
        @DisplayName("Should serialize to JSON")
        void shouldSerializeToJson() {
            // Test code
        }
    }
}
```

## Unit Testing

### Testing Enumerations

```java
class YourEnumerationTest extends BaseKmipTest {
    
    @Test
    @DisplayName("Should return correct value by code")
    void shouldReturnCorrectValueByCode() {
        YourEnumeration value = YourEnumeration.fromCode(1);
        assertEquals(YourEnumeration.SOME_VALUE, value);
    }
    
    @Test
    @DisplayName("Should throw for unknown code")
    void shouldThrowForUnknownCode() {
        assertThrows(IllegalArgumentException.class, () -> 
            YourEnumeration.fromCode(9999)
        );
    }
}
```

#### Enumeration Registry Hooks (Opt-in)

If your enumeration supports runtime registration (e.g., `State`), extend the reusable suite and split the registry checks into positive and negative hooks:

- `supportsRegistryBehavior()` — return `true`.
- `assertEnumerationRegistryBehaviorPositive()` — implement valid registration and lookups by value/name.
- `assertEnumerationRegistryBehaviorNegative()` — implement invalid range, blank description, and empty version set checks.

This improves clarity of test reports and keeps responsibilities separated.

### Testing Attributes

```java
class YourAttributeTest extends BaseKmipTest {
    
    @Test
    @DisplayName("Should create with valid parameters")
    void shouldCreateWithValidParameters() {
        YourAttribute attribute = new YourAttribute("name", "value");
        assertThat(attribute)
            .hasName("name")
            .hasValue("value");
    }
    
    @Test
    @DisplayName("Should reject null name")
    void shouldRejectNullName() {
        assertThrows(NullPointerException.class, () -> 
            new YourAttribute(null, "value")
        );
    }
}
```

### Testing Structures

```java
class YourStructureTest extends BaseKmipTest {
    
    @Test
    @DisplayName("Should build with all fields")
    void shouldBuildWithAllFields() {
        YourStructure structure = YourStructure.builder()
            .withName("test")
            .withValue(42)
            .build();
            
        assertThat(structure)
            .hasName("test")
            .hasValue(42);
    }
    
    @Test
    @DisplayName("Should require name")
    void shouldRequireName() {
        assertThrows(IllegalStateException.class, () ->
            YourStructure.builder()
                .withValue(42)
                .build()
        );
    }
}
```

## Integration Testing

### Serialization Testing

```java
class SerializationIntegrationTest extends BaseKmipTest {
    
    @Test
    @DisplayName("Should round-trip through JSON")
    void shouldRoundTripThroughJson() throws Exception {
        YourType original = createTestObject();
        String json = jsonMapper.writeValueAsString(original);
        YourType deserialized = jsonMapper.readValue(json, YourType.class);
        
        assertThat(deserialized).isEqualTo(original);
    }
    
    @Test
    @DisplayName("Should handle version differences")
    void shouldHandleVersionDifferences() {
        // Test different KMIP spec versions
        for (KmipSpec spec : KmipSpec.values()) {
            KmipContext.withSpec(spec, () -> {
                YourType obj = createTestObject();
                String json = jsonMapper.writeValueAsString(obj);
                YourType deserialized = jsonMapper.readValue(json, YourType.class);
                assertThat(deserialized).isEqualTo(obj);
            });
        }
    }
}
```

## Test Utilities

### Base Test Class

```java
public abstract class BaseKmipTest {
    protected static final ObjectMapper jsonMapper;
    protected static final XmlMapper xmlMapper;
    
    static {
        jsonMapper = new ObjectMapper()
            .registerModule(new KmipJsonModule())
            .configure(SerializationFeature.INDENT_OUTPUT, true);
            
        xmlMapper = new XmlMapper()
            .registerModule(new KmipXmlModule())
            .configure(SerializationFeature.INDENT_OUTPUT, true);
    }
    
    protected YourType createTestObject() {
        return YourType.builder()
            .withName("test")
            .withValue(42)
            .build();
    }
}
```

### Custom Assertions

Prefer AssertJ with static imports for readability in all tests, and avoid fully qualified names:

```java
import static org.assertj.core.api.Assertions.*;
```

Examples: `assertThat`, `assertThatThrownBy`, `assertThatExceptionOfType`.

```java
public class YourTypeAssert extends AbstractAssert<YourTypeAssert, YourType> {
    
    public static YourTypeAssert assertThat(YourType actual) {
        return new YourTypeAssert(actual);
    }
    
    private YourTypeAssert(YourType actual) {
        super(actual, YourTypeAssert.class);
    }
    
    public YourTypeAssert hasName(String expected) {
        isNotNull();
        
        if (!Objects.equals(actual.getName(), expected)) {
            failWithMessage("Expected name to be <%s> but was <%s>", 
                expected, actual.getName());
        }
        
        return this;
    }
    
    public YourTypeAssert hasValue(int expected) {
        isNotNull();
        
        if (actual.getValue() != expected) {
            failWithMessage("Expected value to be <%d> but was <%d>", 
                expected, actual.getValue());
        }
        
        return this;
    }
}
```

## Test Data Factories

```java
public final class TestDataFactory {
    
    private TestDataFactory() {
        // Utility class
    }
    
    public static YourType createYourType() {
        return createYourType("test", 42);
    }
    
    public static YourType createYourType(String name, int value) {
        return YourType.builder()
            .withName(name)
            .withValue(value)
            .build();
    }
    
    public static List<YourType> createYourTypes(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> createYourType("test-" + i, i))
            .collect(Collectors.toList());
    }
}
```

## Parameterized Tests

### Value Source

```java
@ParameterizedTest
@ValueSource(ints = { 1, 2, 3, 5, 8, 13 })
@DisplayName("Should accept valid values")
void shouldAcceptValidValues(int value) {
    YourType obj = YourType.builder()
        .withName("test")
        .withValue(value)
        .build();
        
    assertThat(obj).hasValue(value);
}
```

### Method Source

```java
@ParameterizedTest
@MethodSource("invalidValues")
@DisplayName("Should reject invalid values")
void shouldRejectInvalidValues(int value) {
    assertThrows(IllegalArgumentException.class, () ->
        YourType.builder()
            .withName("test")
            .withValue(value)
            .build()
    );
}

private static Stream<Arguments> invalidValues() {
    return Stream.of(
        Arguments.of(-1),
        Arguments.of(0),
        Arguments.of(101)
    );
}
```

### Csv Source

```java
@ParameterizedTest
@CsvSource({
    "test1, 42",
    "test2, 99",
    "test3, 1"
})
@DisplayName("Should create with different parameters")
void shouldCreateWithDifferentParameters(String name, int value) {
    YourType obj = YourType.builder()
        .withName(name)
        .withValue(value)
        .build();
        
    assertThat(obj)
        .hasName(name)
        .hasValue(value);
}
```

## Test Coverage

### Coverage Goals
- 100% line coverage for core classes
- 100% branch coverage for complex logic
- Test all public methods
- Test edge cases and error conditions

### Measuring Coverage

```bash
# Run tests with JaCoCo
mvn clean test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

## Performance Testing

### JMH Benchmarks

```java
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class YourBenchmark {
    
    private YourType testObject;
    
    @Setup
    public void setup() {
        testObject = TestDataFactory.createYourType();
    }
    
    @Benchmark
    public void benchmarkMethod() {
        // Code to benchmark
    }
}
```

### Running Benchmarks

```bash
# Run all benchmarks
mvn clean test-compile exec:java -Dexec.mainClass="org.openjdk.jmh.Main"

# Run specific benchmark
mvn clean test-compile exec:java -Dexec.mainClass="org.openjdk.jmh.Main" -Dexec.args="YourBenchmark -f 1 -wi 3 -i 5"
```

## Best Practices

1. **Test Naming**:
   - Use descriptive test method names
   - Follow a consistent naming pattern (e.g., `should[Action]When[Condition]`)
   - Use `@DisplayName` for more readable test output

2. **Test Organization**:
   - Group related tests with `@Nested` classes
   - Use `@BeforeEach` and `@AfterEach` for test setup/teardown
   - Keep tests independent and isolated

3. **Assertions**:
   - Use appropriate assertion methods
   - Prefer assertion libraries for better error messages
   - Test one thing per test method

4. **Test Data**:
   - Use test data factories for common objects
   - Make test data realistic but minimal
   - Consider using a library like Faker for realistic test data

5. **Maintainability**:
   - Keep tests DRY (Don't Repeat Yourself)
   - Extract common assertions into helper methods
   - Document complex test cases

## Common Pitfalls

1. **Fragile Tests**:
   - Avoid testing implementation details
   - Use meaningful assertions
   - Don't overspecify test expectations

2. **Slow Tests**:
   - Avoid I/O in unit tests
   - Use mocks for external dependencies
   - Run tests in parallel when possible

3. **Flaky Tests**:
   - Avoid test dependencies
   - Don't rely on test execution order
   - Use fixed data for time-dependent tests

4. **Over-Mocking**:
   - Don't mock value objects
   - Prefer real objects over mocks when possible
   - Only mock what you own
