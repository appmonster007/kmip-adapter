# Code Style Guide

This document outlines the coding standards and conventions for the KMIP Adapter project.

## Java Conventions

### General
- Follow the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) with the following additions and exceptions.
- Use 4 spaces for indentation (not tabs).
- Keep lines under 120 characters.
- Use Unix-style line endings (LF).

### Naming
- **Classes**: `PascalCase`
- **Interfaces**: `PascalCase` (prefixed with `I` only if it's a common pattern in the codebase)
- **Methods**: `camelCase`
- **Variables**: `camelCase`
- **Constants**: `UPPER_SNAKE_CASE`
- **Enum values**: `UPPER_SNAKE_CASE`
- **Package names**: `lowercase`

### Annotations
- Place annotations on their own line above the annotated element.
- Prefer Lombok `@NonNull` on required fields/parameters in value objects.
- Use `@Override` whenever applicable.

### Documentation
- Use Javadoc for all public APIs.
- Include `@throws` for all exceptions that can be thrown.
- Document thread safety.
- Include examples in complex methods.

### Example
```java
@Value
public class ActivationDateAttribute implements KmipAttribute {
    @NonNull OffsetDateTime value;

    @Override public KmipTag getTag() { return KmipTag.ACTIVATION_DATE; }
    @Override public EncodingType getEncoding() { return EncodingType.DATE_TIME; }
}
```

## Testing

### Naming
- Test class names: `{ClassUnderTest}Test`
- Test method names: `should{Behavior}_when{Condition}` or `given{Precondition}_when{Action}_then{Result}`
- Use AssertJ for assertions
- Use JUnit 5

### Test Structure
- Use nested test classes to group related tests
- Use `@Nested` for logical grouping
- Use `@DisplayName` for descriptive test names
- Use `@ParameterizedTest` for parameterized tests

### Example
```java
@DisplayName("Key Validation")
class KeyValidationTest {
    
    @Nested
    @DisplayName("when key is valid")
    class WhenKeyIsValid {
        private Key<byte[]> key;
        
        @BeforeEach
        void setUp() {
            key = new Key<>("valid-key".getBytes(), KeyType.AES);
        }
        
        @Test
        @DisplayName("should not throw exception")
        void shouldNotThrowException() {
            assertThatNoException()
                .isThrownBy(key::validate);
        }
    }
    
    @Nested
    @DisplayName("when key is null")
    class WhenKeyIsNull {
        @Test
        @DisplayName("should throw NullPointerException")
        void shouldThrowNullPointerException() {
            assertThatThrownBy(() -> new Key<>(null, KeyType.AES))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Key material cannot be null");
        }
    }
}
```

## Code Organization

### Package Structure
Follow the structure outlined in `KMIP_IMPLEMENTATION_GUIDE.md`:
- `org.purpleBean.kmip/` — core KMIP interfaces and base classes
- `org.purpleBean.kmip.common/` — common KMIP types
  - `enumeration/` — enumerated types like `State`
  - `structure/` — complex data structures
  - `attribute/` — attribute implementations
- `org.purpleBean.kmip.codec/` — serialization/deserialization
  - `json/`, `xml/`, `ttlv/` — per-format codecs

### File Organization
- One top-level class per file
- Static members before instance members
- Public methods before private methods
- Group related methods together
- Keep methods small and focused

## Best Practices

### Error Handling
- Use specific exception types
- Include context in exception messages
- Use `Objects.requireNonNull` for null checks
- Use `Preconditions` for argument validation

### Performance
- Avoid unnecessary object creation
- Use `StringBuilder` for string concatenation in loops
- Prefer `EnumMap` and `EnumSet` for enum-based collections
- Use primitive types when possible

### Thread Safety
- Document thread safety
- Use `final` fields for immutability
- Use thread-safe collections when needed
- Prefer `synchronized` blocks over synchronized methods

### Logging
- Use SLF4J for logging
- Use appropriate log levels:
  - ERROR: System is in distress
  - WARN: Unexpected but recoverable
  - INFO: Important business process
  - DEBUG: Diagnostic information
  - TRACE: Detailed debugging

### Example
```java
private static final Logger log = LoggerFactory.getLogger(MyClass.class);

public void process(Data data) {
    try {
        log.debug("Processing data: {}", data.getId());
        // Process data
        log.info("Successfully processed data: {}", data.getId());
    } catch (ProcessingException e) {
        log.error("Failed to process data: {}", data.getId(), e);
        throw e;
    }
}
```
