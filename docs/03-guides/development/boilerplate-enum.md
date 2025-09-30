# KMIP Enumeration Boilerplate (Generated)

This guide shows the actual generated code for KMIP enumeration classes using `FooEnum` as an example.

## 1. Core Enumeration Class

**File:** `src/main/java/org/purpleBean/kmip/common/enumeration/FooEnum.java`

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
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for FooEnum is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    // Extension registration and lookup methods...
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        // Implementation details...
    }

    public static Value fromName(String name) {
        // Implementation details...
    }

    public static Value fromValue(int value) {
        // Implementation details...
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        PLACEHOLDER_1(0x00000001, "Placeholder1", KmipSpec.UnknownVersion ),
        PLACEHOLDER_2(0x00000002, "Placeholder2", KmipSpec.UnknownVersion );

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = false;

        Standard(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }
    }

    // Value interface and Extension class...
}
```

## 2. JSON Serializer

**File:** `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/enumeration/FooEnumJsonSerializer.java`

```java
public class FooEnumJsonSerializer extends KmipDataTypeJsonSerializer<FooEnum> {
    @Override
    public void serialize(FooEnum value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("FooEnum '%s' is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }

        jsonGenerator.writeStartObject();
        jsonGenerator.writeObject(value.getKmipTag());
        jsonGenerator.writeStringField("type", value.getEncodingType().getDescription());
        jsonGenerator.writeStringField("value", value.getDescription());
        jsonGenerator.writeEndObject();
    }
}
```

## 3. JSON Deserializer

**File:** `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/enumeration/FooEnumJsonDeserializer.java`

```java
public class FooEnumJsonDeserializer extends KmipDataTypeJsonDeserializer<FooEnum> {
    @Override
    public FooEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        // Validation and parsing logic...
        
        String description = valueNode.asText();
        FooEnum.Value fooenumValue = FooEnum.fromName(description);
        FooEnum fooenum = new FooEnum(fooenumValue);
        
        if (!fooenum.isSupported()) {
            throw new NoSuchElementException(
                    String.format("FooEnum '%s' is not supported for KMIP spec %s", description, spec)
            );
        }
        return fooenum;
    }
}
```

## 4. Test Class

**File:** `src/test/java/org/purpleBean/kmip/common/enumeration/FooEnumTest.java`

```java
@DisplayName("FooEnum Domain Tests")
class FooEnumTest extends AbstractKmipEnumerationSuite<FooEnum> {
    @Override
    protected FooEnum createDefault() {
        return new FooEnum(FooEnum.Standard.PLACEHOLDER_1);
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        FooEnum.Value custom = FooEnum.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.UnknownVersion));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
    }
}
```

## Key Features

1. **Thread-safe registries** for value management
2. **Extension support** with validation
3. **KMIP version compatibility** checking
4. **Comprehensive serialization** support (JSON/XML/TTLV)
5. **Immutable value objects** for thread safety
6. **Validation** at construction and serialization time

## Usage

```java
// Create with standard value
FooEnum foo = new FooEnum(FooEnum.Standard.PLACEHOLDER_1);

// Register custom extension
FooEnum.Value custom = FooEnum.register(0x80000001, "CustomValue", Set.of(KmipSpec.V1_4));
FooEnum customFoo = new FooEnum(custom);

// Lookup by name/value
FooEnum.Value byName = FooEnum.fromName("Placeholder1");
FooEnum.Value byValue = FooEnum.fromValue(0x00000001);
```
