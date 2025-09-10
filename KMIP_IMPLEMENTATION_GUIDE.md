# KMIP Implementation Guide

This document provides comprehensive guidelines for implementing KMIP objects in the PurpleBean KMIP Adapter, following the patterns established in the codebase.

## Table of Contents
1. [Core Interfaces](#core-interfaces)
2. [Enumeration Implementation](#enumeration-implementation)
3. [Attribute Implementation](#attribute-implementation)
4. [Structure Implementation](#structure-implementation)
5. [Serialization & Deserialization](#serialization--deserialization)
6. [Testing Guidelines](#testing-guidelines)
7. [Best Practices](#best-practices)

## Core Interfaces

### `KmipDataType`
Base interface for all KMIP data types.

```java
public interface KmipDataType {
    KmipTag getKmipTag();
    EncodingType getEncodingType();
    boolean isSupportedFor(@NonNull KmipSpec spec);
}
```

### `KmipEnumeration`
For fixed sets of values.

```java
public interface KmipEnumeration extends KmipDataType {
    String getDescription();
}
```

### `KmipAttribute`
For KMIP attributes.

```java
public interface KmipAttribute extends KmipDataType {
    boolean isClientModifiable(@NonNull State state);
    boolean isServerModifiable(@NonNull State state);
    boolean isAlwaysPresent();
    boolean isServerInitializable();
    boolean isClientInitializable();
    boolean isClientDeletable();
    boolean isMultiInstanceAllowed();
}
```

### `KmipStructure`
For complex KMIP structures.

```java
public interface KmipStructure extends KmipDataType {
    List<KmipDataType> getValues();
}
```

## Enumeration Implementation

Here's how to implement a KMIP enumeration following the patterns in the codebase:

```java
package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.KmipCodecContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class YourEnumeration implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.YOUR_ENUM_TAG);
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    
    @NonNull
    private final Value value;

    public YourEnumeration(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                String.format("Value '%s' for %s is not supported for KMIP spec %s", 
                    kmipTag.getDescription(), value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    // Standard values
    public enum Standard implements Value {
        VALUE_1(0x01, "Value 1", Set.of(KmipSpec.V1_2)),
        VALUE_2(0x02, "Value 2", Set.of(KmipSpec.V1_2));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        Standard(int value, String description, Set<KmipSpec> supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Collections.unmodifiableSet(supportedVersions);
        }

        @Override public int getValue() { return value; }
        @Override public String getDescription() { return description; }
        @Override public boolean isCustom() { return false; }
        @Override public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    // Extension value for custom values
    @Data
    @AllArgsConstructor
    public static class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        @Override public boolean isCustom() { return true; }
        @Override public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    // Factory methods
    public static Value register(int value, String description, Set<KmipSpec> supportedVersions) {
        if (!isValidExtensionValue(value)) {
            throw new IllegalArgumentException(
                String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
            );
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.put(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        return custom;
    }

    public static Value fromValue(KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
            .filter(x -> x.isSupportedFor(spec))
            .orElseThrow(() -> new NoSuchElementException(
                String.format("No value found for %d in KMIP spec %s", value, spec)
            ));
    }

    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
            .filter(x -> x.isSupportedFor(spec))
            .orElseThrow(() -> new NoSuchElementException(
                String.format("No value found for '%s' in KMIP spec %s", name, spec)
            ));
    }

    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    @Override
    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return value.isSupportedFor(spec);
    }

    private static boolean isValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        return !(value < extensionStart || value > 0);
    }
}
```

## Attribute Implementation

Here's how to implement a KMIP attribute following the patterns in the codebase:

```java
package org.purpleBean.kmip.common;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
public class YourAttribute implements KmipAttribute {
    // Required fields from KmipDataType
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.YOUR_ATTRIBUTE_TAG);
    private final EncodingType encodingType = EncodingType.TEXT_STRING; // Adjust based on attribute type
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);

    // Attribute properties with default values
    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    // Attribute value - adjust type as needed (e.g., String, Integer, OffsetDateTime)
    @NonNull
    private final String value;

    @Override
    public boolean isClientModifiable(@NonNull State state) {
        // Example: Only modifiable in PRE_ACTIVE state
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        // Example: Only modifiable in PRE_ACTIVE state
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }
}
```

### Key Points for Attribute Implementation:

1. **Required Fields**:
   - `kmipTag`: The standard KMIP tag for this attribute
   - `encodingType`: The appropriate encoding type (e.g., TEXT_STRING, DATE_TIME, INTEGER)
   - `supportedVersions`: Set of supported KMIP specifications

2. **Attribute Properties**:
   - `alwaysPresent`: Whether this attribute must always be present
   - `serverInitializable`: Whether the server can initialize this attribute
   - `clientInitializable`: Whether the client can initialize this attribute
   - `clientDeletable`: Whether the client can delete this attribute
   - `multiInstanceAllowed`: Whether multiple instances of this attribute are allowed

3. **Implementation Notes**:
   - All fields should be `final` to ensure immutability
   - Use `@NonNull` for fields that are required

## Structure Implementation

Here's how to implement a KMIP structure following the patterns in the codebase:

```java
package org.purpleBean.kmip.common.structure;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.KmipCodecContext;

import java.util.*;

@Data
@Builder
public class YourStructure implements KmipStructure {
    // Required fields from KmipDataType
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.YOUR_STRUCTURE_TAG);
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);

    // Structure fields - these should be KMIP data types
    @NonNull
    private final SomeKmipType field1;
    
    private final AnotherKmipType field2;

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(field1);
        if (field2 != null) {
            values.add(field2);
        }
        return values;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    public static class YourStructureBuilder {
        public YourStructure build() {
            List<KmipDataType> fields = new ArrayList<>();
            fields.add(field1);
            if (field2 != null) {
                fields.add(field2);
            }

            // KMIP spec compatibility validation
            KmipSpec spec = KmipCodecContext.getSpec();
            for (KmipDataType field : fields) {
                if (field != null && !field.isSupportedFor(spec)) {
                    throw new IllegalArgumentException(
                        String.format("Value '%s' is not supported for KMIP spec %s", 
                            field.getKmipTag().getDescription(), spec)
                    );
                }
            }

            return new YourStructure(field1, field2);
        }
    }
}
```

## Serialization & Deserialization

### JSON Serialization

The KMIP adapter uses a consistent pattern for JSON serialization. Here's how to implement a JSON serializer for a KMIP type:

```java
package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class YourTypeJsonSerializer extends KmipDataTypeJsonSerializer<YourType> {

    @Override
    public void serialize(YourType value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) 
            throws IOException {
        // Validation: Null check
        if (value == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                String.format("%s is not supported for KMIP spec %s", 
                    value.getKmipTag().getDescription(), spec)
            );
        }

        // Write the JSON structure
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        
        // Write the value field(s) - adjust based on your type
        if (value.getYourValue() != null) {
            jsonGenerator.writeStringField("value", value.getYourValue().toString());
        }
        
        jsonGenerator.writeEndObject();
    }
}
```

### Key Points for Serialization:

1. **Base Class**:
   - Extend `KmipDataTypeJsonSerializer<T>` for your type
   - The base class handles common serialization logic

2. **Required Validations**:
   - Null check at the beginning
   - KMIP spec compatibility check

3. **JSON Structure**:
   - Start with an object
   - Include the KMIP tag
   - Include the encoding type
   - Include the value(s)
   - End the object

4. **Error Handling**:
   - Throw `UnsupportedEncodingException` for unsupported operations
   - Include descriptive error messages

5. **Performance**:
   - Reuse `JsonGenerator` methods
   - Avoid unnecessary object creation
   - Use primitive types when possible

## Testing Guidelines

Here's how to write tests for KMIP types following the patterns in the codebase:

```java
package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("YourType Tests")
class YourTypeTest extends BaseKmipTest {

    @Nested
    @DisplayName("Standard Operations")
    class StandardOperations {

        @Test
        @DisplayName("Should create instance with standard value")
        void shouldCreateWithStandardValue() {
            // Given
            YourType.Standard standardValue = YourType.Standard.VALUE_1;
            
            // When
            YourType instance = new YourType(standardValue);
            
            // Then
            assertThat(instance.getValue()).isInstanceOf(YourType.Standard.class);
            assertThat(instance.getDescription()).isEqualTo(standardValue.getDescription());
            assertThat(instance.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.YOUR_TYPE_TAG);
            assertThat(instance.getEncodingType()).isEqualTo(EncodingType.YOUR_ENCODING_TYPE);
        }

        @ParameterizedTest
        @EnumSource(YourType.Standard.class)
        @DisplayName("Should handle all standard values correctly")
        void shouldHandleAllStandardValues(YourType.Standard standardValue) {
            // When
            YourType instance = new YourType(standardValue);
            
            // Then
            assertThat(instance.getValue()).isInstanceOf(YourType.Standard.class);
            assertThat(instance.getDescription()).isEqualTo(standardValue.getDescription());
        }

        @Test
        @DisplayName("Should support version compatibility for standard values")
        void shouldSupportVersionCompatibility() {
            // Given
            YourType instance = new YourType(YourType.Standard.VALUE_1);
            
            // When & Then
            assertThat(instance.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("Custom Value Operations")
    class CustomValueOperations {

        @Test
        @DisplayName("Should register and create with custom value")
        void shouldRegisterAndCreateWithCustomValue() {
            // Given
            int customValue = 0x80000001;
            String customDescription = "CustomValue";
            Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);
            
            // When
            YourType.Value customValueObj = YourType.register(
                customValue, customDescription, supportedVersions);
            YourType instance = new YourType(customValueObj);
            
            // Then
            assertThat(customValueObj.getValue()).isEqualTo(customValue);
            assertThat(customValueObj.getDescription()).isEqualTo(customDescription);
            assertThat(customValueObj.isCustom()).isTrue();
            assertThat(instance.getValue()).isSameAs(customValueObj);
        }
    }

    @Nested
    @DisplayName("Serialization Tests")
    class SerializationTests extends BaseKmipTest {

        @Test
        @DisplayName("Should serialize and deserialize correctly")
        void shouldRoundTripThroughJson() {
            // Given
            YourType original = new YourType(YourType.Standard.VALUE_1);
            
            // When
            YourType deserialized = SerializationTestUtils.roundTripJson(original, YourType.class);
            
            // Then
            assertThat(deserialized).isEqualTo(original);
            assertThat(deserialized.getDescription()).isEqualTo(original.getDescription());
        }
    }
}
```

### Key Points for Testing:

1. **Test Structure**:
   - Use nested test classes to group related tests
   - Extend `BaseKmipTest` for common test utilities
   - Use descriptive test names with `@DisplayName`

2. **Test Categories**:
   - Standard value operations
   - Custom value operations
   - Serialization/deserialization
   - Edge cases and error conditions
   - Version compatibility

3. **Assertions**:
   - Use AssertJ for fluent assertions
   - Verify both the happy path and error conditions
   - Test equality and hash code implementations

4. **Test Data**:
   - Use `KmipTestDataFactory` for creating test data
   - Reuse test data across tests when possible
   - Use parameterized tests for testing multiple values

5. **Test Coverage**:
   - Aim for 100% code coverage for core types
   - Test all public methods and constructors
   - Include tests for edge cases and error conditions

## Best Practices

1. **Immutability**: All KMIP objects should be immutable. Use the Builder pattern for construction.
2. **Null Safety**: Use `@NonNull` annotations and validate inputs in the builder.
3. **Thread Safety**: Ensure thread-safe implementations, especially for shared state.
4. **Documentation**: Include Javadoc for all public APIs.
5. **Testing**: Cover all public methods with unit tests, including edge cases.
6. **Error Handling**: Provide clear error messages for invalid states or inputs.
7. **Versioning**: Always consider KMIP spec version compatibility.
8. **Performance**: Be mindful of object creation in hot paths.
9. **Code Organization**: Follow the existing package structure and naming conventions.
10. **Validation**: Validate all inputs and object states.
