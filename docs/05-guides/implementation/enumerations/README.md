# Enumerations Guide

This guide covers the implementation of KMIP enumerations in the PurpleBean KMIP Adapter.

## Table of Contents
- [Overview](#overview)
- [Enumeration Implementation](#enumeration-implementation)
- [Version Support](#version-support)
- [Extension Values](#extension-values)
- [Serialization](#serialization)
- [Testing](#testing)
- [Best Practices](#best-practices)

## Overview

KMIP enumerations represent fixed sets of named values in the KMIP specification. They are used throughout the protocol to specify types, states, and other enumerated values.

## Enumeration Implementation

### Basic Structure

```java
public final class YourEnumeration implements KmipEnumeration {
    
    // Standard values
    public static final YourEnumeration VALUE_ONE = new YourEnumeration(1, "VALUE_ONE", EnumSet.allOf(KmipSpec.class));
    public static final YourEnumeration VALUE_TWO = new YourEnumeration(2, "VALUE_TWO", EnumSet.allOf(KmipSpec.class));
    
    // Extension values
    private static final Map<Integer, YourEnumeration> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, YourEnumeration> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    
    // Instance fields
    private final int code;
    private final String description;
    private final EnumSet<KmipSpec> supportedVersions;
    
    // Register standard values
    static {
        register(VALUE_ONE);
        register(VALUE_TWO);
    }
    
    // Private constructor
    private YourEnumeration(int code, String description, EnumSet<KmipSpec> supportedVersions) {
        this.code = code;
        this.description = description;
        this.supportedVersions = EnumSet.copyOf(supportedVersions);
    }
    
    // Registration method
    private static void register(YourEnumeration value) {
        VALUE_REGISTRY.put(value.code, value);
        DESCRIPTION_REGISTRY.put(value.description, value);
    }
    
    // Factory method
    public static YourEnumeration fromCode(int code) {
        YourEnumeration value = VALUE_REGISTRY.get(code);
        if (value == null) {
            throw new IllegalArgumentException("Unknown code: " + code);
        }
        if (!value.isSupported()) {
            throw new IllegalStateException("Enumeration value " + value + " is not supported in KMIP " + 
                KmipContext.getSpec());
        }
        return value;
    }
    
    // Getters
    public int getCode() { return code; }
    public String getDescription() { return description; }
    
    // Version support
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(KmipContext.getSpec());
    }
    
    // Standard methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YourEnumeration)) return false;
        YourEnumeration that = (YourEnumeration) o;
        return code == that.code;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(code);
    }
    
    @Override
    public String toString() {
        return description + "(" + code + ")";
    }
}
```

## Version Support

### Version-Specific Values

```java
// KMIP 1.0 only
public static final YourEnumeration OLD_VALUE = new YourEnumeration(
    3, 
    "OLD_VALUE", 
    EnumSet.of(KmipSpec.V1_0)
);

// KMIP 1.2 and later
public static final YourEnumeration NEW_VALUE = new YourEnumeration(
    4, 
    "NEW_VALUE", 
    EnumSet.range(KmipSpec.V1_2, KmipSpec.LATEST)
);
```

### Version Checking

```java
public void someMethod() {
    if (!VALUE_ONE.isSupported()) {
        throw new UnsupportedOperationException(
            "This operation requires KMIP 1.2 or later");
    }
    // Proceed with operation
}
```

## Extension Values

### Registering Extension Values

```java
/**
 * Registers an extension value for this enumeration.
 * 
 * @param code the extension code (must be in the range 0x80000000 to 0xFFFFFFFF)
 * @param description a description of the extension value
 * @param supportedVersions the KMIP versions that support this extension
 * @return the registered enumeration value
 * @throws IllegalArgumentException if the code is not a valid extension value
 * @throws IllegalStateException if the code is already registered
 */
public static YourEnumeration registerExtension(
        int code, 
        String description, 
        Set<KmipSpec> supportedVersions) {
    
    if (!isValidExtensionValue(code)) {
        throw new IllegalArgumentException(
            String.format("Extension value 0x%08X must be in range 0x80000000 to 0xFFFFFFFF", code));
    }
    
    if (description == null || description.trim().isEmpty()) {
        throw new IllegalArgumentException("Description cannot be null or empty");
    }
    
    if (supportedVersions == null || supportedVersions.isEmpty()) {
        throw new IllegalArgumentException("At least one supported version must be specified");
    }
    
    synchronized (VALUE_REGISTRY) {
        if (VALUE_REGISTRY.containsKey(code)) {
            throw new IllegalStateException("Code already registered: " + code);
        }
        
        if (DESCRIPTION_REGISTRY.containsKey(description)) {
            throw new IllegalStateException("Description already registered: " + description);
        }
        
        YourEnumeration value = new YourEnumeration(
            code, 
            description,
            EnumSet.copyOf(supportedVersions));
            
        register(value);
        return value;
    }
}

private static boolean isValidExtensionValue(int value) {
    return (value & 0x80000000) != 0; // Check if high bit is set
}
```

## Serialization

### JSON Serializer

```java
public class YourEnumerationJsonSerializer extends JsonSerializer<YourEnumeration> {
    
    @Override
    public void serialize(YourEnumeration value, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.getCode());
        }
    }
}
```

### XML Serializer

```java
public class YourEnumerationXmlSerializer extends XmlSerializer<YourEnumeration> {
    
    @Override
    public void serialize(YourEnumeration value, XmlGenerator gen, SerializerProvider provider) 
            throws IOException {
        
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.getCode());
        }
    }
}
```

## Testing

### Basic Tests

```java
class YourEnumerationTest extends BaseKmipTest {
    
    @Nested
    @DisplayName("Standard Values")
    class StandardValues {
        
        @Test
        @DisplayName("Should have correct code and description")
        void shouldHaveCorrectCodeAndDescription() {
            assertThat(YourEnumeration.VALUE_ONE)
                .hasCode(1)
                .hasDescription("VALUE_ONE");
                
            assertThat(YourEnumeration.VALUE_TWO)
                .hasCode(2)
                .hasDescription("VALUE_TWO");
        }
        
        @Test
        @DisplayName("Should be equal to itself")
        void shouldBeEqualToItself() {
            assertThat(YourEnumeration.VALUE_ONE)
                .isEqualTo(YourEnumeration.VALUE_ONE)
                .isNotEqualTo(YourEnumeration.VALUE_TWO);
        }
        
        @Test
        @DisplayName("Should have consistent hashCode")
        void shouldHaveConsistentHashCode() {
            YourEnumeration one = YourEnumeration.VALUE_ONE;
            assertThat(one.hashCode()).isEqualTo(one.hashCode());
        }
    }
    
    @Nested
    @DisplayName("fromCode")
    class FromCode {
        
        @Test
        @DisplayName("Should return correct value for valid code")
        void shouldReturnCorrectValueForValidCode() {
            assertThat(YourEnumeration.fromCode(1))
                .isSameAs(YourEnumeration.VALUE_ONE);
                
            assertThat(YourEnumeration.fromCode(2))
                .isSameAs(YourEnumeration.VALUE_TWO);
        }
        
        @Test
        @DisplayName("Should throw for unknown code")
        void shouldThrowForUnknownCode() {
            assertThatThrownBy(() -> YourEnumeration.fromCode(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown code: 999");
        }
    }
    
    @Nested
    @DisplayName("Version Support")
    class VersionSupport {
        
        @Test
        @DisplayName("Should be supported in all versions")
        void shouldBeSupportedInAllVersions() {
            for (KmipSpec spec : KmipSpec.values()) {
                KmipContext.withSpec(spec, () -> {
                    assertThat(YourEnumeration.VALUE_ONE.isSupported())
                        .as("VALUE_ONE should be supported in " + spec)
                        .isTrue();
                });
            }
        }
    }
    
    @Nested
    @DisplayName("Extension Values")
    class ExtensionValues {
        
        @Test
        @DisplayName("Should register extension value")
        void shouldRegisterExtensionValue() {
            // Given
            int extensionCode = 0x80000001;
            String description = "EXTENSION_VALUE";
            
            // When
            YourEnumeration extension = YourEnumeration.registerExtension(
                extensionCode, 
                description,
                EnumSet.allOf(KmipSpec.class));
                
            // Then
            assertThat(extension)
                .hasCode(extensionCode)
                .hasDescription(description);
                
            assertThat(YourEnumeration.fromCode(extensionCode))
                .isSameAs(extension);
        }
        
        @Test
        @DisplayName("Should reject invalid extension code")
        void shouldRejectInvalidExtensionCode() {
            assertThatThrownBy(() -> 
                YourEnumeration.registerExtension(1, "INVALID", EnumSet.allOf(KmipSpec.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Extension value 0x00000001 must be in range 0x80000000 to 0xFFFFFFFF");
        }
    }
    
    @Nested
    @DisplayName("Serialization")
    class SerializationTests {
        
        @Test
        @DisplayName("Should serialize to JSON")
        void shouldSerializeToJson() throws Exception {
            String json = jsonMapper.writeValueAsString(YourEnumeration.VALUE_ONE);
            assertThat(json).isEqualTo("1");
        }
        
        @Test
        @DisplayName("Should deserialize from JSON")
        void shouldDeserializeFromJson() throws Exception {
            YourEnumeration value = jsonMapper.readValue("1", YourEnumeration.class);
            assertThat(value).isSameAs(YourEnumeration.VALUE_ONE);
        }
    }
}
```

## Best Practices

1. **Immutability**:
   - Make enumeration classes final
   - Use final fields
   - Provide no mutator methods

2. **Thread Safety**:
   - Use concurrent collections for registries
   - Make registration thread-safe
   - Document thread safety guarantees

3. **Validation**:
   - Validate constructor parameters
   - Validate extension values
   - Provide clear error messages

4. **Documentation**:
   - Document standard values
   - Document version support
   - Include examples in Javadoc

5. **Testing**:
   - Test all standard values
   - Test version support
   - Test serialization/deserialization
   - Test edge cases and error conditions

6. **Performance**:
   - Cache frequently used values
   - Use efficient data structures for lookups
   - Minimize object creation in hot paths
