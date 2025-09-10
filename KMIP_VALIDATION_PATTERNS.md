# KMIP Validation Patterns

Comprehensive validation patterns and examples for KMIP objects in both main code and test code.

## Table of Contents

- [Why Validation Matters](#why-validation-matters)
- [Main Code Validation Patterns](#main-code-validation-patterns)
- [Test Code Validation Patterns](#test-code-validation-patterns)
- [Validation Utilities](#validation-utilities)
- [Common Validation Scenarios](#common-validation-scenarios)

## Why Validation Matters

### In Main Code
- **Type Safety**: Prevent invalid object creation at runtime
- **KMIP Compliance**: Ensure objects conform to KMIP specification versions
- **Data Integrity**: Validate required fields and business constraints
- **Early Failure**: Catch issues at object creation rather than serialization
- **Security**: Prevent injection attacks and malformed data processing

### In Test Code
- **Behavior Verification**: Ensure validations work as expected
- **Edge Case Coverage**: Test boundary conditions and error scenarios
- **Regression Prevention**: Catch validation logic changes
- **Documentation**: Show expected behavior through test examples
- **Quality Assurance**: Verify error messages and exception types

## Main Code Validation Patterns

### 1. Constructor Validation Pattern

```java
public YourKmipType(@NonNull SomeValue value) {
    // Step 1: Null safety (Lombok @NonNull handles this, but explicit for clarity)
    Objects.requireNonNull(value, "Value cannot be null");
    
    // Step 2: KMIP specification compatibility
    KmipSpec spec = KmipCodecContext.getSpec();
    if (!value.isSupportedFor(spec)) {
        throw new IllegalArgumentException(
            String.format("Value '%s' is not supported for KMIP specification %s", 
                value.getDescription(), spec)
        );
    }
    
    // Step 3: Business rule validation
    validateBusinessRules(value);
    
    // Step 4: Assign validated value
    this.value = value;
}

/**
 * Validate business-specific rules for the value
 * 
 * @param value The value to validate
 * @throws IllegalArgumentException if validation fails
 */
private void validateBusinessRules(SomeValue value) {
    // Range validation for numeric values
    if (value instanceof NumericValue) {
        NumericValue numValue = (NumericValue) value;
        if (numValue.getValue() < 0) {
            throw new IllegalArgumentException("Value must be non-negative");
        }
        if (numValue.getValue() > MAX_ALLOWED_VALUE) {
            throw new IllegalArgumentException(
                String.format("Value %d exceeds maximum allowed value %d", 
                    numValue.getValue(), MAX_ALLOWED_VALUE)
            );
        }
    }
    
    // Format validation for string values
    if (value instanceof StringValue) {
        StringValue strValue = (StringValue) value;
        if (strValue.getValue().trim().isEmpty()) {
            throw new IllegalArgumentException("String value cannot be empty or whitespace-only");
        }
        if (!strValue.getValue().matches(VALID_FORMAT_PATTERN)) {
            throw new IllegalArgumentException(
                String.format("String value '%s' does not match required format", strValue.getValue())
            );
        }
    }
    
    // Custom validation logic specific to your type
    validateCustomConstraints(value);
}
```

### 2. Builder Validation Pattern

```java
public static class YourStructureBuilder {
    
    public YourStructure build() {
        // Step 1: Validate required fields presence
        validateRequiredFields();
        
        // Step 2: Validate KMIP specification compatibility
        validateKmipSpecCompatibility();
        
        // Step 3: Validate field relationships and business rules
        validateBusinessRules();
        
        // Step 4: Create validated instance
        return new YourStructure(requiredField, optionalField, anotherOptionalField);
    }
    
    /**
     * Ensure all required fields are present
     */
    private void validateRequiredFields() {
        List<String> missingFields = new ArrayList<>();
        
        if (requiredField == null) {
            missingFields.add("requiredField");
        }
        // Add other required field checks
        
        if (!missingFields.isEmpty()) {
            throw new IllegalArgumentException(
                String.format("Missing required fields: %s", String.join(", ", missingFields))
            );
        }
    }
    
    /**
     * Validate all fields against current KMIP specification
     */
    private void validateKmipSpecCompatibility() {
        KmipSpec spec = KmipCodecContext.getSpec();
        List<String> unsupportedFields = new ArrayList<>();
        
        // Check each field that implements KmipDataType
        if (requiredField instanceof KmipDataType && !((KmipDataType) requiredField).isSupportedFor(spec)) {
            unsupportedFields.add("requiredField");
        }
        if (optionalField instanceof KmipDataType && !((KmipDataType) optionalField).isSupportedFor(spec)) {
            unsupportedFields.add("optionalField");
        }
        
        if (!unsupportedFields.isEmpty()) {
            throw new IllegalArgumentException(
                String.format("Fields %s are not supported for KMIP specification %s", 
                    unsupportedFields, spec)
            );
        }
    }
    
    /**
     * Validate business rules and field relationships
     */
    private void validateBusinessRules() {
        // Conditional field requirements
        if (optionalField != null && anotherOptionalField == null) {
            throw new IllegalArgumentException(
                "When optionalField is provided, anotherOptionalField is also required"
            );
        }
        
        // Cross-field validation
        if (requiredField instanceof NumericField && optionalField instanceof NumericField) {
            NumericField req = (NumericField) requiredField;
            NumericField opt = (NumericField) optionalField;
            if (opt.getValue() <= req.getValue()) {
                throw new IllegalArgumentException(
                    "OptionalField value must be greater than requiredField value"
                );
            }
        }
        
        // Business constraint validation
        validateBusinessConstraints();
    }
    
    private void validateBusinessConstraints() {
        // Add specific business logic validation
        // Examples:
        // - Date ranges
        // - Enum combinations
        // - Complex field interdependencies
    }
}
```

### 3. Serializer Validation Pattern

```java
@Override
public void serialize(YourKmipType value, JsonGenerator gen, SerializerProvider provider) 
        throws IOException {
    
    // Step 1: Null check
    Objects.requireNonNull(value, "Cannot serialize null value");
    
    // Step 2: KMIP specification support validation
    KmipSpec spec = KmipCodecContext.getSpec();
    if (!value.isSupportedFor(spec)) {
        throw new UnsupportedEncodingException(
            String.format("Type %s is not supported for KMIP specification %s", 
                value.getClass().getSimpleName(), spec)
        );
    }
    
    // Step 3: Serialization-specific validation
    validateSerializationPreconditions(value);
    
    // Step 4: Proceed with serialization
    performSerialization(value, gen, provider);
}

/**
 * Validate conditions specific to serialization
 */
private void validateSerializationPreconditions(YourKmipType value) throws IOException {
    // Check for circular references in complex structures
    if (value instanceof ComplexStructure) {
        detectCircularReferences((ComplexStructure) value);
    }
    
    // Validate nested object states
    if (value.hasNestedObjects()) {
        for (KmipDataType nested : value.getNestedObjects()) {
            if (nested == null) {
                throw new IOException("Nested object cannot be null during serialization");
            }
        }
    }
    
    // Check serialization constraints
    validateSerializationConstraints(value);
}
```

### 4. Enumeration Registration Validation Pattern

```java
public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
    // Step 1: Extension value range validation
    if (!isValidExtensionValue(value)) {
        throw new IllegalArgumentException(
            String.format("Extension value 0x%08X must be in range 0x80000000 to 0xFFFFFFFF", value)
        );
    }
    
    // Step 2: Description validation
    validateDescription(description);
    
    // Step 3: Supported versions validation
    validateSupportedVersions(supportedVersions);
    
    // Step 4: Uniqueness validation
    validateUniqueness(value, description);
    
    // Step 5: Register the extension
    Extension custom = new Extension(value, description, supportedVersions);
    return registerExtension(custom);
}

private static void validateDescription(String description) {
    if (description.trim().isEmpty()) {
        throw new IllegalArgumentException("Description cannot be empty or whitespace-only");
    }
    
    if (description.length() > MAX_DESCRIPTION_LENGTH) {
        throw new IllegalArgumentException(
            String.format("Description length %d exceeds maximum allowed length %d", 
                description.length(), MAX_DESCRIPTION_LENGTH)
        );
    }
    
    if (!description.matches(VALID_DESCRIPTION_PATTERN)) {
        throw new IllegalArgumentException(
            String.format("Description '%s' contains invalid characters", description)
        );
    }
}

private static void validateSupportedVersions(Set<KmipSpec> supportedVersions) {
    if (supportedVersions.isEmpty()) {
        throw new IllegalArgumentException("At least one supported KMIP specification version must be provided");
    }
    
    // Check for unknown versions
    Set<KmipSpec> unknownVersions = supportedVersions.stream()
        .filter(spec -> spec == KmipSpec.UnknownVersion)
        .collect(Collectors.toSet());
        
    if (!unknownVersions.isEmpty()) {
        throw new IllegalArgumentException("UnknownVersion is not a valid supported version");
    }
}

private static void validateUniqueness(int value, String description) {
    if (VALUE_REGISTRY.containsKey(value)) {
        throw new IllegalArgumentException(
            String.format("Extension value 0x%08X is already registered", value)
        );
    }
    
    if (DESCRIPTION_REGISTRY.containsKey(description)) {
        throw new IllegalArgumentException(
            String.format("Extension description '%s' is already registered", description)
        );
    }
}
```

## Test Code Validation Patterns

### 1. Constructor Validation Tests

```java
@Nested
@DisplayName("Constructor Validation")
class ConstructorValidation {

    @Test
    @DisplayName("Should reject null value")
    void shouldRejectNullValue() {
        // When & Then
        assertThatThrownBy(() -> new YourKmipType(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Value cannot be null");
    }

    @Test
    @DisplayName("Should reject unsupported KMIP specification")
    void shouldRejectUnsupportedKmipSpecification() {
        // Given
        KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
        SomeValue validValue = createValidValue();
        
        // When & Then
        assertThatThrownBy(() -> new YourKmipType(validValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("not supported for KMIP specification");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100, Integer.MIN_VALUE})
    @DisplayName("Should reject negative values")
    void shouldRejectNegativeValues(int negativeValue) {
        // Given
        NumericValue invalidValue = new NumericValue(negativeValue);
        
        // When & Then
        assertThatThrownBy(() -> new YourKmipType(invalidValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("must be non-negative");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    @DisplayName("Should reject empty or whitespace-only strings")
    void shouldRejectEmptyOrWhitespaceStrings(String invalidString) {
        // Given
        StringValue invalidValue = new StringValue(invalidString);
        
        // When & Then
        assertThatThrownBy(() -> new YourKmipType(invalidValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("cannot be empty");
    }

    @Test
    @DisplayName("Should validate format constraints")
    void shouldValidateFormatConstraints() {
        // Given
        StringValue invalidFormat = new StringValue("invalid@format!");
        
        // When & Then
        assertThatThrownBy(() -> new YourKmipType(invalidFormat))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("does not match required format");
    }
}
```

### 2. Builder Validation Tests

```java
@Nested
@DisplayName("Builder Validation")
class BuilderValidation {

    @Test
    @DisplayName("Should reject missing required fields")
    void shouldRejectMissingRequiredFields() {
        // When & Then
        assertThatThrownBy(() -> YourStructure.builder().build())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Missing required fields: requiredField");
    }

    @Test
    @DisplayName("Should reject fields unsupported by KMIP specification")
    void shouldRejectFieldsUnsupportedByKmipSpecification() {
        // Given
        KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
        RequiredFieldType unsupportedField = createUnsupportedField();
        
        // When & Then
        assertThatThrownBy(() -> YourStructure.builder()
            .requiredField(unsupportedField)
            .build())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("not supported for KMIP specification");
    }

    @Test
    @DisplayName("Should validate conditional field requirements")
    void shouldValidateConditionalFieldRequirements() {
        // Given
        RequiredFieldType requiredField = createValidRequiredField();
        OptionalFieldType optionalField = createValidOptionalField();
        // Missing anotherOptionalField when optionalField is present
        
        // When & Then
        assertThatThrownBy(() -> YourStructure.builder()
            .requiredField(requiredField)
            .optionalField(optionalField)
            .build())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("anotherOptionalField is also required");
    }

    @Test
    @DisplayName("Should validate cross-field constraints")
    void shouldValidateCrossFieldConstraints() {
        // Given
        NumericField smallerValue = new NumericField(10);
        NumericField largerValue = new NumericField(5); // Invalid: should be larger
        
        // When & Then
        assertThatThrownBy(() -> YourStructure.builder()
            .requiredField(smallerValue)
            .optionalField(largerValue)
            .build())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("must be greater than requiredField value");
    }
}
```

### 3. Serialization Validation Tests

```java
@Nested
@DisplayName("Serialization Validation")
class SerializationValidation {

    @Test
    @DisplayName("Should reject serialization of unsupported types")
    void shouldRejectSerializationOfUnsupportedTypes() {
        // Given
        YourKmipType validObject = createValidObject();
        KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
        
        // When & Then
        assertThatThrownBy(() -> jsonMapper.writeValueAsString(validObject))
            .isInstanceOf(UnsupportedEncodingException.class)
            .hasMessageContaining("not supported for KMIP specification");
    }

    @Test
    @DisplayName("Should reject null object serialization")
    void shouldRejectNullObjectSerialization() {
        // When & Then
        assertThatThrownBy(() -> jsonMapper.writeValueAsString(null))
            .isInstanceOf(JsonProcessingException.class);
    }

    @Test
    @DisplayName("Should validate nested object states during serialization")
    void shouldValidateNestedObjectStatesDuringSerialization() {
        // Given
        YourKmipType objectWithNullNested = createObjectWithNullNestedObject();
        
        // When & Then
        assertThatThrownBy(() -> jsonMapper.writeValueAsString(objectWithNullNested))
            .isInstanceOf(IOException.class)
            .hasMessageContaining("Nested object cannot be null");
    }
}
```

### 4. Registration Validation Tests

```java
@Nested
@DisplayName("Registration Validation")
class RegistrationValidation {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 0x7FFFFFFF})
    @DisplayName("Should reject invalid extension value ranges")
    void shouldRejectInvalidExtensionValueRanges(int invalidValue) {
        // When & Then
        assertThatThrownBy(() -> YourEnumeration.register(
            invalidValue, "ValidDescription", Set.of(KmipSpec.V1_2)
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("Extension value")
          .hasMessageContaining("must be in range");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\n"})
    @DisplayName("Should reject empty or whitespace descriptions")
    void shouldRejectEmptyOrWhitespaceDescriptions(String invalidDescription) {
        // When & Then
        assertThatThrownBy(() -> YourEnumeration.register(
            0x80000001, invalidDescription, Set.of(KmipSpec.V1_2)
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("Description cannot be empty");
    }

    @Test
    @DisplayName("Should reject null parameters")
    void shouldRejectNullParameters() {
        // Test null description
        assertThatThrownBy(() -> YourEnumeration.register(
            0x80000001, null, Set.of(KmipSpec.V1_2)
        )).isInstanceOf(NullPointerException.class);
        
        // Test null supported versions
        assertThatThrownBy(() -> YourEnumeration.register(
            0x80000001, "ValidDescription", null
        )).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Should reject empty supported versions set")
    void shouldRejectEmptySupportedVersionsSet() {
        // When & Then
        assertThatThrownBy(() -> YourEnumeration.register(
            0x80000001, "ValidDescription", Set.of()
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("At least one supported KMIP specification version");
    }

    @Test
    @DisplayName("Should reject duplicate value registration")
    void shouldRejectDuplicateValueRegistration() {
        // Given
        int duplicateValue = 0x80000001;
        YourEnumeration.register(duplicateValue, "FirstDescription", Set.of(KmipSpec.V1_2));
        
        // When & Then
        assertThatThrownBy(() -> YourEnumeration.register(
            duplicateValue, "SecondDescription", Set.of(KmipSpec.V1_2)
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("already registered");
    }

    @Test
    @DisplayName("Should reject duplicate description registration")
    void shouldRejectDuplicateDescriptionRegistration() {
        // Given
        String duplicateDescription = "DuplicateDescription";
        YourEnumeration.register(0x80000001, duplicateDescription, Set.of(KmipSpec.V1_2));
        
        // When & Then
        assertThatThrownBy(() -> YourEnumeration.register(
            0x80000002, duplicateDescription, Set.of(KmipSpec.V1_2)
        )).isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("already registered");
    }
}
```

## Validation Utilities

### Custom Validation Helper Class

```java
public final class KmipValidationUtils {
    
    private KmipValidationUtils() {
        // Utility class
    }
    
    /**
     * Validate that a value is supported for the current KMIP specification
     */
    public static void validateKmipSpecSupport(KmipDataType value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                String.format("Field '%s' with type %s is not supported for KMIP specification %s", 
                    fieldName, value.getClass().getSimpleName(), spec)
            );
        }
    }
    
    /**
     * Validate numeric range constraints
     */
    public static void validateRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                String.format("Field '%s' value %d must be between %d and %d", 
                    fieldName, value, min, max)
            );
        }
    }
    
    /**
     * Validate string format constraints
     */
    public static void validateStringFormat(String value, Pattern pattern, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty or whitespace-only");
        }
        
        if (!pattern.matcher(value).matches()) {
            throw new IllegalArgumentException(
                String.format("Field '%s' value '%s' does not match required format", 
                    fieldName, value)
            );
        }
    }
    
    /**
     * Validate extension value range for enumerations
     */
    public static void validateExtensionValue(int value) {
        if ((value & 0x80000000) == 0) {
            throw new IllegalArgumentException(
                String.format("Extension value 0x%08X must be in range 0x80000000 to 0xFFFFFFFF", value)
            );
        }
    }
}
```

### Test Validation Assertions

```java
public final class KmipValidationAssertions {
    
    /**
     * Assert that a validation exception is thrown with expected message
     */
    public static void assertValidationException(Executable executable, String expectedMessage) {
        assertThatThrownBy(executable)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(expectedMessage);
    }
    
    /**
     * Assert that KMIP spec validation fails
     */
    public static void assertKmipSpecValidationFails(Executable executable) {
        assertThatThrownBy(executable)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("not supported for KMIP specification");
    }
    
    /**
     * Assert that null validation fails
     */
    public static void assertNullValidationFails(Executable executable, String fieldName) {
        assertThatThrownBy(executable)
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(fieldName + " cannot be null");
    }
}
```

## Common Validation Scenarios

### 1. Date/Time Validation

```java
private void validateDateTime(LocalDateTime dateTime) {
    Objects.requireNonNull(dateTime, "DateTime cannot be null");
    
    LocalDateTime now = LocalDateTime.now();
    if (dateTime.isBefore(now.minusYears(100))) {
        throw new IllegalArgumentException("DateTime cannot be more than 100 years in the past");
    }
    
    if (dateTime.isAfter(now.plusYears(100))) {
        throw new IllegalArgumentException("DateTime cannot be more than 100 years in the future");
    }
}
```

### 2. Cryptographic Key Validation

```java
private void validateCryptographicKey(byte[] keyData) {
    Objects.requireNonNull(keyData, "Key data cannot be null");
    
    if (keyData.length == 0) {
        throw new IllegalArgumentException("Key data cannot be empty");
    }
    
    // Validate key length for different algorithms
    Set<Integer> validLengths = Set.of(16, 24, 32, 48, 64); // AES, 3DES, etc.
    if (!validLengths.contains(keyData.length)) {
        throw new IllegalArgumentException(
            String.format("Invalid key length %d bytes. Valid lengths: %s", 
                keyData.length, validLengths)
        );
    }
}
```

### 3. URL/URI Validation

```java
private void validateUri(String uri) {
    Objects.requireNonNull(uri, "URI cannot be null");
    
    if (uri.trim().isEmpty()) {
        throw new IllegalArgumentException("URI cannot be empty");
    }
    
    try {
        URI parsedUri = new URI(uri);
        if (parsedUri.getScheme() == null) {
            throw new IllegalArgumentException("URI must include a scheme");
        }
    } catch (URISyntaxException e) {
        throw new IllegalArgumentException("Invalid URI format: " + uri, e);
    }
}
```

These validation patterns ensure robust, secure, and KMIP-compliant object creation while providing comprehensive test coverage for all validation scenarios.
