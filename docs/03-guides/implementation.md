# KMIP Implementation Guide

This guide provides comprehensive instructions for implementing KMIP objects, serialization, and error handling in the PurpleBean KMIP Adapter.

## Table of Contents
- [Creating KMIP Objects](#creating-kmip-objects)
  - [Enumerations](#enumerations)
  - [Attributes](#attributes)
  - [Structures](#structures)
- [Serialization and Deserialization](#serialization-and-deserialization)
  - [JSON](#json-serialization)
  - [XML](#xml-serialization)
  - [TTLV](#ttlv-serialization)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [Best Practices](#best-practices)

## Creating KMIP Objects

### Enumerations

KMIP value sets are represented as value-based types with a fixed set of standard values and optional custom extensions. Here's the `State` implementation pattern used in this project:

```java
package org.purplebean.kmip.common.enumeration;

import lombok.Getter;
import org.purplebean.kmip.KmipEnumeration;
import java.util.Arrays;

/**
 * Represents the state of a cryptographic object in KMIP.
 */
/**
 * Represents the state of a cryptographic object in KMIP.
 * This is a value-based class that supports both standard and custom states.
 */
public final class State implements KmipDataType {
    
    /**
     * Standard KMIP state values.
     */
    public enum Standard implements Value {
        PRE_ACTIVE(0x01, "PreActive"),
        ACTIVE(0x02, "Active"),
        DEACTIVATED(0x03, "Deactivated"),
        COMPROMISED(0x04, "Compromised"),
        DESTROYED(0x05, "Destroyed"),
        DESTROYED_COMPROMISED(0x06, "Destroyed Compromised");

        private final int value;
        private final String description;

        Standard(int value, String description) {
            this.value = value;
            this.description = description;
        }

        @Override public int getValue() { return value; }
        @Override public String getDescription() { return description; }
    }

    /**
     * Interface for state values, allowing for custom states.
     */
    public interface Value {
        int getValue();
        String getDescription();
    }

    private static final Map<Integer, Value> VALUES = new ConcurrentHashMap<>();
    private static final Map<String, Value> VALUES_BY_NAME = new ConcurrentHashMap<>();

    static {
        // Register standard values
        for (Standard standard : Standard.values()) {
            register(standard);
        }
    }

    /**
     * Registers a custom state value.
     * @param value The value to register
     * @return The registered value
     * @throws IllegalArgumentException if the value is already registered
     */
    public static synchronized Value register(Value value) {
        Objects.requireNonNull(value, "Value cannot be null");
        if (VALUES.containsKey(value.getValue())) {
            throw new IllegalArgumentException("Value already registered: " + value.getValue());
        }
        VALUES.put(value.getValue(), value);
        VALUES_BY_NAME.put(value.getDescription().toUpperCase(Locale.ROOT), value);
        return value;
    }

    /**
     * Looks up a state value by its integer value.
     * @param spec The KMIP specification version
     * @param value The value to look up
     * @return The corresponding state value
     * @throws IllegalArgumentException if the value is not found
     */
    public static Value fromValue(KmipSpec spec, int value) {
        Value result = VALUES.get(value);
        if (result == null) {
            throw new IllegalArgumentException("Unknown state value: " + value);
        }
        return result;
    }

    /**
     * Looks up a state value by its name (case-insensitive).
     * @param spec The KMIP specification version
     * @param name The name to look up
     * @return The corresponding state value
     * @throws IllegalArgumentException if the name is not found
     */
    public static Value fromName(KmipSpec spec, String name) {
        Objects.requireNonNull(name, "Name cannot be null");
        Value result = VALUES_BY_NAME.get(name.toUpperCase(Locale.ROOT));
        if (result == null) {
            throw new IllegalArgumentException("Unknown state name: " + name);
        }
        return result;
    }

    private final Value value;

    /**
     * Creates a new State with the given value.
     * @param value The state value
     * @throws NullPointerException if value is null
     */
    public State(Value value) {
        this.value = Objects.requireNonNull(value, "Value cannot be null");
    }

    /**
     * Gets the state value.
     * @return The state value
     */
    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return value == state.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
```

### Attributes

KMIP attributes implement the `KmipAttribute` interface. Example: `ActivationDateAttribute`:

```java
package org.purplebean.kmip.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.purplebean.kmip.EncodingType;
import org.purplebean.kmip.KmipAttribute;
import org.purplebean.kmip.KmipTag;

import java.time.OffsetDateTime;

/**
 * Represents the Activation Date attribute in KMIP.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ActivationDateAttribute implements KmipAttribute {
    @NonNull
    private final OffsetDateTime value;

    @Override
    public KmipTag getTag() {
        return KmipTag.ACTIVATION_DATE;
    }

    @Override
    public EncodingType getEncoding() {
        return EncodingType.DATE_TIME;
    }
}
```

### Structures

Complex KMIP objects are implemented as structures. Example: `RequestMessage`:

```java
package org.purplebean.kmip.common.structure.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.jackson.Jacksonized;
import org.purplebean.kmip.EncodingType;
import org.purplebean.kmip.KmipSpec;
import org.purplebean.kmip.KmipStructure;
import org.purplebean.kmip.KmipTag;

import java.util.List;
import java.util.Set;

/**
 * Represents a KMIP request message structure.
 */
@Getter
@Builder
@Jacksonized
public class RequestMessage implements KmipStructure {
    @NonNull
    private final RequestHeader header;
    
    @NonNull
    private final List<RequestBatchItem> batchItems;

    @Override
    public KmipTag getKmipTag() {
        return KmipTag.REQUEST_MESSAGE;
    }

    @Override
    public EncodingType getEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    public boolean isSupportedFor(KmipSpec spec) {
        return spec != null && spec.getMajor() == 1 && spec.getMinor() >= 2;
    }
}
```

## Serialization and Deserialization

### JSON Serialization

```java
// Setup
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new KmipJsonModule())
    .enable(SerializationFeature.INDENT_OUTPUT);

// Serialize
String json = mapper.writeValueAsString(kmipObject);

// Deserialize
KmipDataType deserialized = mapper.readValue(json, KmipDataType.class);
```

### XML Serialization

```java
// Setup
XmlMapper xmlMapper = new XmlMapper()
    .registerModule(new KmipXmlModule())
    .enable(SerializationFeature.INDENT_OUTPUT);

// Serialize
String xml = xmlMapper.writeValueAsString(kmipObject);

// Deserialize
KmipDataType deserialized = xmlMapper.readValue(xml, KmipDataType.class);
```

### TTLV Serialization

```java
// Set KMIP spec for the current thread
KmipContext.setSpec(KmipSpec.V1_4);
try {
    // Use TtlvMapper for TTLV (Tag-Type-Length-Value) operations
    TtlvMapper ttlvMapper = new TtlvMapper();
    ttlvMapper.registerModule(new KmipTtlvModule());

    // Encode to TTLV bytes
    byte[] ttlvData = ttlvMapper.writeValueAsBytes(kmipObject);

    // Decode from TTLV bytes
    KmipDataType decoded = ttlvMapper.readValue(ttlvData, KmipDataType.class);
} finally {
    KmipContext.clear();
}
```

## Error Handling

Follow these exception patterns (as used across serializers/deserializers):

- `IllegalArgumentException`: Invalid input, unknown values, tag/type mismatches
- `UnsupportedEncodingException`: Value or type not supported for current KMIP spec
- `NoSuchElementException`: Lookup failures (e.g., fromName/fromValue not found)
- `IOException`: I/O-level issues in codec layers

Example error handling:

```java
KmipContext.setSpec(KmipSpec.V1_4);
try {
    byte[] ttlv = ttlvMapper.writeValueAsBytes(kmipObject);
    return ttlv;
} catch (UnsupportedEncodingException e) {
    logger.warn("Not supported for KMIP {}: {}", KmipContext.getSpec(), e.getMessage());
    throw e;
} catch (IllegalArgumentException e) {
    logger.error("Invalid KMIP value: {}", e.getMessage());
    throw e;
} catch (IOException e) {
    logger.error("I/O error during TTLV processing: {}", e.getMessage(), e);
    throw e;
} finally {
    KmipContext.clear();
}
```

## Testing

KMIP includes a comprehensive test suite with:

1. **Unit Tests**: Test individual components in isolation
2. **Integration Tests**: Test component interactions
3. **Serialization Tests**: Verify JSON/XML/TTLV serialization
4. **Performance Tests**: Benchmark critical operations

Example test class:

```java
class ActivationDateAttributeTest extends BaseKmipTest {
    
    @Test
    @DisplayName("Should create valid ProtocolVersion")
    void shouldCreateValidProtocolVersion() {
        // Given/When
        ProtocolVersion version = new ProtocolVersion(1, 4);
            
        // Then
        assertThat(version)
            .extracting(
                ProtocolVersion::getMajor,
                ProtocolVersion::getMinor,
                v -> v.isSupportedFor(KmipSpec.V1_4))
            .containsExactly(1, 4, true);
    }
    
    @Test
    void shouldRejectNullValue() {
        assertThatThrownBy(() -> new ActivationDateAttribute(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("value is marked non-null but is null");
    }
}
```

## Best Practices

1. **Immutability**: All KMIP objects should be immutable
2. **Null Safety**: Use `@NonNull` annotations and validate inputs
3. **Thread Safety**: Ensure thread safety in shared objects
4. **Versioning**: Support multiple KMIP spec versions
5. **Documentation**: Include Javadoc for all public APIs
6. **Testing**: Maintain high test coverage
7. **Error Handling**: Provide meaningful error messages
8. **Performance**: Optimize for high-throughput scenarios
9. **Validation**: Validate inputs early and fail fast
10. **Logging**: Include appropriate logging for debugging

### Unit Tests

- Test individual KMIP types in isolation
- Verify serialization/deserialization round-trips
- Test edge cases and error conditions

### Integration Tests

- Test end-to-end serialization/deserialization
- Verify compatibility with different KMIP versions
- Test with real-world KMIP messages

### Performance Tests

- Measure serialization/deserialization performance
- Identify bottlenecks and optimize critical paths
- Ensure acceptable performance under load

## Best Practices

1. **Immutability**: Make KMIP objects immutable where possible
2. **Validation**: Validate all inputs in constructors and setters
3. **Thread Safety**: Document thread safety guarantees
4. **Error Messages**: Provide clear, actionable error messages
5. **Documentation**: Document all public APIs with Javadoc
6. **Testing**: Write tests for all new functionality
7. **Performance**: Be mindful of object creation and memory usage
8. **Error Handling**: Handle errors gracefully and provide context

## Next Steps

- [Type System](../02-architecture/type-system.md)
- [Serialization](../02-architecture/serialization.md)
- [API Reference](../04-api/)
    }

    // ... other methods ...
}
```

### Structures

```java
public class KeyBlock implements KmipStructure {
    private final KeyFormatType format;
    private final byte[] keyMaterial;
    private final List<CryptographicAlgorithm> algorithms;

    public KeyBlock(KeyFormatType format, byte[] keyMaterial, List<CryptographicAlgorithm> algorithms) {
        this.format = Objects.requireNonNull(format, "Key format cannot be null");
        this.keyMaterial = keyMaterial != null ? keyMaterial.clone() : new byte[0];
        this.algorithms = algorithms != null ? List.copyOf(algorithms) : List.of();
    }

    @Override
    public KmipTag getKmipTag() {
        return KmipTag.KEY_BLOCK;
    }

    // ... other methods ...
}
```

## Serialization

### JSON Serialization

```java
ObjectMapper mapper = new ObjectMapper();
KmipContext context = new KmipContext(KmipSpec.V1_2);
KmipModule module = new KmipModule(context);
mapper.registerModule(module);

// Serialize
String json = mapper.writeValueAsString(keyBlock);

// Deserialize
KeyBlock deserialized = mapper.readValue(json, KeyBlock.class);
```

### TTLV Serialization

```java
KmipContext context = new KmipContext(KmipSpec.V1_2);
TtlvEncoder encoder = new TtlvEncoder(context);
TtlvDecoder decoder = new TtlvDecoder(context);

// Serialize
byte[] ttlv = encoder.encode(keyBlock);

// Deserialize
KeyBlock deserialized = decoder.decode(ttlv, KeyBlock.class);
```
