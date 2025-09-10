# KMIP Testing Guide

Comprehensive test templates and best practices for KMIP objects, with 551 passing tests across the codebase.

## 🚀 Test Coverage

- **Total Tests**: 551 passing tests
- **Code Coverage**: 100% of classes analyzed (JaCoCo report)
- **Test Categories**:
  - Unit Tests
  - Integration Tests
  - Serialization Tests (JSON/XML/TTLV)
  - Validation Tests
  - Edge Case Tests
  - Parameterized Tests
  - Thread Safety Tests

## Table of Contents

- [Test Structure Overview](#test-structure-overview)
- [Unit Test Templates](#unit-test-templates)
- [Serialization Test Templates](#serialization-test-templates)
- [Validation Test Templates](#validation-test-templates)
- [Integration Test Templates](#integration-test-templates)
- [Test Coverage Report](#test-coverage-report)

## Test Structure Overview

### Base Test Class Usage

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
@DisplayName("Thread Safety")
class ThreadSafety {
    // Test thread safety for concurrent access
}

@Nested
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
import org.purpleBean.kmip.codec.KmipCodecContext;
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
            Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);
            
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
            YourEnumeration.register(customValue, description, Set.of(KmipSpec.V1_2));
            
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
                invalidValue, "Invalid", Set.of(KmipSpec.V1_2)
            )).isInstanceOf(IllegalArgumentException.class)
              .hasMessageContaining("Extension value");
        }

        @Test
        @DisplayName("Should reject empty description in register")
        void shouldRejectEmptyDescriptionInRegister() {
            // When & Then
            assertThatThrownBy(() -> YourEnumeration.register(
                0x80000003, "", Set.of(KmipSpec.V1_2)
            )).isInstanceOf(IllegalArgumentException.class)
              .hasMessageContaining("Description cannot be empty");
        }

        @Test
        @DisplayName("Should reject null parameters in register")
        void shouldRejectNullParametersInRegister() {
            // When & Then
            assertThatThrownBy(() -> YourEnumeration.register(0x80000004, null, Set.of(KmipSpec.V1_2)))
                .isInstanceOf(NullPointerException.class);
                
            assertThatThrownBy(() -> YourEnumeration.register(0x80000005, "Test", null))
                .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("Should reject unsupported value for KMIP spec")
        void shouldRejectUnsupportedValueForKmipSpec() {
            // Given
            KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
            
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
            KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
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
    KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
    assertThatThrownBy(() -> new YourType(validValue))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("not supported for KMIP spec");
}

@Test
@DisplayName("Should validate serialization preconditions")
void shouldValidateSerializationPreconditions() throws Exception {
    // Given
    YourType validObject = createValidObject();
    KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
    
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
void shouldMaintainConsistencyAcrossCodecContexts() throws Exception {
    // Test with different KMIP specs in parallel threads
    CompletableFuture<YourType> v12Future = CompletableFuture.supplyAsync(() -> {
        KmipCodecContext.setSpec(KmipSpec.V1_2);
        return KmipTestDataFactory.createYourType();
    });
    
    YourType result = v12Future.get();
    assertThat(result).isNotNull();
    assertThat(result.isSupportedFor(KmipSpec.V1_2)).isTrue();
}
```

These templates provide comprehensive test coverage including construction, validation, serialization, and integration testing with proper validation examples throughout.
