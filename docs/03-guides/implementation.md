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

KMIP enumerations are implemented using the generated pattern. Here's the `FooEnum` implementation pattern:

```java
package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP FooEnum enumeration.
 */
@Data
@Builder
public class FooEnum implements KmipEnumeration {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_ENUM);
    public static final EncodingType encodingType = EncodingType.ENUMERATION;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion);
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, FooEnum.class);
        }
    }

    @NonNull
    private final Value value;

    public FooEnum(@NonNull Value value) {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                String.format("Value '%s' for FooEnum is not supported for KMIP spec %s", 
                    value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    /**
     * Register an extension value.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        // Implementation with validation...
    }

    /**
     * Look up by name.
     */
    public static Value fromName(String name) {
        // Implementation with KMIP spec checking...
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        PLACEHOLDER_1(0x00000001, "Placeholder1", KmipSpec.UnknownVersion),
        PLACEHOLDER_2(0x00000002, "Placeholder2", KmipSpec.UnknownVersion);

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = false;

        @Override
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    // Value interface and Extension class...
}
```

### Data Types

KMIP data types are simple wrappers around primitive values. Here's the `FooDataType` pattern:

```java
@Data
@Builder
public class FooDataType implements KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DATA_TYPE);
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    @NonNull
    private final OffsetDateTime value;
    
    public static FooDataType of(@NonNull OffsetDateTime value) {
        return FooDataType.builder().value(value).build();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }
}
```

### Structures

KMIP structures contain multiple KMIP data types. Here's the `FooStructure` pattern:

```java
@Data
@Builder
public class FooStructure implements KmipStructure {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_STRUCTURE);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    
    @NonNull
    private final ActivationDate activationDate;
    private final State state;

    public static FooStructure of(@NonNull ActivationDate activationDate, State state) {
        return FooStructure.builder().activationDate(activationDate).state(state).build();
    }
    
    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(activationDate, state)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Custom builder with validation
    public static class FooStructureBuilder {
        public FooStructure build() {
            validate();
            return new FooStructure(activationDate, state);
        }

        private void validate() {
            // KMIP spec compatibility validation
            KmipSpec spec = KmipContext.getSpec();
            List<KmipDataType> fields = Stream.of(activationDate, state)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            for (KmipDataType field : fields) {
                if (field != null && !field.isSupported()) {
                    throw new IllegalArgumentException(
                        String.format("%s is not supported for KMIP spec %s", 
                            field.getKmipTag().getDescription(), spec)
                    );
                }
            }
        }
    }
}
```

## Key Implementation Patterns

1. **Use Lombok annotations** for boilerplate reduction (`@Data`, `@Builder`, `@NonNull`)
2. **Static factory methods** for common construction patterns (`of()` methods)
3. **KMIP spec validation** in constructors and builders
4. **Thread-safe registries** for enumerations with extension support
5. **Immutable design** for all data objects
6. **Builder pattern with validation** for complex structures

## Usage Examples

```java
// Create enumeration
FooEnum foo = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);

// Create data type
FooDataType data = FooDataType.of(OffsetDateTime.now());

// Create structure
ActivationDate activationDate = ActivationDate.of(OffsetDateTime.now());
State state = new State(State.Standard.ACTIVE);
FooStructure structure = FooStructure.of(activationDate, state);

// Register custom enumeration value
FooEnum.Value custom = FooEnum.register(0x80000001, "CustomValue", Set.of(KmipSpec.V1_4));
FooEnum customEnum = new FooEnum(custom);
```

For complete implementation details, see the boilerplate documentation:
- [Enumeration Boilerplate](development/boilerplate-enum.md)
- [Data Type Boilerplate](development/boilerplate-attribute.md)  
- [Structure Boilerplate](development/boilerplate-structure.md)

## Serialization and Deserialization

The KMIP adapter supports three serialization formats: JSON, XML, and TTLV. Each KMIP type requires corresponding serializers and deserializers.

### JSON Serialization

```java
@Component
public class FooEnumJsonSerializer extends JsonSerializer<FooEnum> {
    @Override
    public void serialize(FooEnum value, JsonGenerator gen, SerializerProvider serializers) 
            throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new JsonGenerationException(
                String.format("FooEnum is not supported for KMIP spec %s", spec), gen);
        }

        gen.writeStartObject();
        gen.writeStringField("tag", value.getKmipTag().getDescription());
        gen.writeStringField("type", value.getEncodingType().name());
        gen.writeStringField("value", value.getValue().getDescription());
        gen.writeEndObject();
    }
}
```

### Testing

All KMIP types should include comprehensive tests:

```java
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
}
```

## Best Practices

1. **Always validate KMIP spec compatibility** in constructors
2. **Use immutable objects** with builder patterns
3. **Implement proper equals/hashCode** for value objects
4. **Include comprehensive unit tests** for all scenarios
5. **Follow naming conventions** for consistency
6. **Document custom extensions** thoroughly
