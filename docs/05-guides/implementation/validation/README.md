# Validation Guide

This guide covers validation patterns and best practices for KMIP objects.

## Table of Contents
- [Why Validation Matters](#why-validation-matters)
- [Validation Patterns](#validation-patterns)
- [Common Validations](#common-validations)
- [Validation Utilities](#validation-utilities)
- [Testing Validations](#testing-validations)
- [Performance Considerations](#performance-considerations)

## Why Validation Matters

### In Main Code
- **Type Safety**: Prevent invalid object creation at runtime
- **KMIP Compliance**: Ensure objects conform to KMIP specification versions
- **Data Integrity**: Validate required fields and business constraints
- **Early Failure**: Catch issues at object creation rather than serialization
- **Security**: Prevent injection attacks and malformed data processing

### In Test Code
- **Test Quality**: Ensure tests fail fast with clear error messages
- **Test Coverage**: Verify all validation rules are tested
- **Documentation**: Serve as living documentation of validation rules
- **Regression Prevention**: Catch breaking changes in validation logic

## Validation Patterns

### Constructor Validation

```java
public class YourClass {
    private final String name;
    private final int value;

    public YourClass(String name, int value) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Value must be between 0 and 100");
        }
        this.value = value;
    }
}
```

### Builder Validation

```java
public class YourClass {
    public static class Builder {
        private String name;
        private int value;
        
        public Builder withName(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
            return this;
        }
        
        public Builder withValue(int value) {
            if (value < 0 || value > 100) {
                throw new IllegalArgumentException("Value must be between 0 and 100");
            }
            this.value = value;
            return this;
        }
        
        public YourClass build() {
            if (name == null) {
                throw new IllegalStateException("Name is required");
            }
            return new YourClass(this);
        }
    }
}
```

### Method Parameter Validation

```java
public void updateValue(String name, int value) {
    this.name = validateName(name);
    this.value = validateValue(value);
}

private String validateName(String name) {
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (name.length() > 100) {
        throw new IllegalArgumentException("Name cannot exceed 100 characters");
    }
    return name;
}
```

## Common Validations

### String Validations

```java
// Not null or empty
Objects.requireNonNull(value, "Value cannot be null");
if (value.trim().isEmpty()) {
    throw new IllegalArgumentException("Value cannot be empty");
}

// Length constraints
if (value.length() < minLength || value.length() > maxLength) {
    throw new IllegalArgumentException(
        String.format("Value length must be between %d and %d", minLength, maxLength)
    );
}

// Pattern matching
if (!value.matches("[A-Za-z0-9]+")) {
    throw new IllegalArgumentException("Value must be alphanumeric");
}
```

### Numeric Validations

```java
// Range check
if (value < minValue || value > maxValue) {
    throw new IllegalArgumentException(
        String.format("Value must be between %d and %d", minValue, maxValue)
    );
}

// Non-negative
if (value < 0) {
    throw new IllegalArgumentException("Value cannot be negative");
}
```

### Collection Validations

```java
// Not null or empty
if (collection == null || collection.isEmpty()) {
    throw new IllegalArgumentException("Collection cannot be null or empty");
}

// Size constraints
if (collection.size() > maxSize) {
    throw new IllegalArgumentException(
        String.format("Collection size cannot exceed %d", maxSize)
    );
}

// No null elements
if (collection.stream().anyMatch(Objects::isNull)) {
    throw new IllegalArgumentException("Collection cannot contain null elements");
}
```

## Validation Utilities

### Custom Validator

```java
public final class ValidationUtils {
    private ValidationUtils() {
        // Utility class
    }
    
    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }
    
    public static String requireNonBlank(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }
    
    public static int requireInRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }
}
```

### Using Java Bean Validation (JSR-380)

```java
import javax.validation.constraints.*;

public class User {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;
    
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 150, message = "Age cannot exceed 150")
    private int age;
    
    @Email(message = "Invalid email format")
    private String email;
    
    // Getters and setters
}
```

## Testing Validations

### Test Validation Rules

```java
class YourClassTest {
    @Test
    @DisplayName("Should reject null name")
    void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class, () -> 
            new YourClass(null, 42)
        );
    }
    
    @Test
    @DisplayName("Should reject empty name")
    void shouldRejectEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> 
            new YourClass("", 42)
        );
    }
    
    @Test
    @DisplayName("Should reject negative value")
    void shouldRejectNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> 
            new YourClass("test", -1)
        );
    }
}
```

### Parameterized Tests

```java
@ParameterizedTest
@ValueSource(ints = { -1, 101 })
@DisplayName("Should reject value out of range")
void shouldRejectValueOutOfRange(int invalidValue) {
    assertThrows(IllegalArgumentException.class, () -> 
        new YourClass("test", invalidValue)
    );
}
```

## Performance Considerations

1. **Fail Fast**:
   - Validate required fields first
   - Check for null before expensive validations

2. **Caching**:
   - Cache compiled patterns for regex validations
   - Reuse validator instances

3. **Lazy Validation**:
   - For complex objects, consider lazy validation
   - Cache validation results if validations are expensive

4. **Batch Validation**:
   - For collections, consider batch validation
   - Collect all errors before throwing an exception

## Error Messages

- Be specific about what went wrong
- Include the invalid value in the error message
- Provide guidance on how to fix the issue
- Consider localization for user-facing messages

## Common Pitfalls

1. **Over-Validation**:
   - Don't validate things that can't happen due to the type system
   - Focus on business rules and constraints

2. **Under-Validation**:
   - Don't trust external data
   - Validate at system boundaries

3. **Inconsistent Validation**:
   - Apply the same validation rules everywhere
   - Centralize validation logic when possible

4. **Poor Error Messages**:
   - Avoid generic error messages
   - Include context in error messages
