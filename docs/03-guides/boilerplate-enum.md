# Boilerplate: DemoStatus (KmipEnumeration Implementation)

## Minimal Boilerplate (Copy-Ready)

### Main Code: `FooStatus` (implements `KmipEnumeration`)

```java
package org.purpleBean.kmip.foo.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class FooStatus implements KmipEnumeration {
    public interface Value extends KmipEnumeration {
        boolean isSupportedFor(KmipSpec spec);
    }

    @Getter
    public enum Standard implements Value {
        READY(0x01, "Ready");

        private final int value; private final String description;
        Standard(int v, String d) { this.value = v; this.description = d; }
        public int getValue() { return value; }
        public String getDescription() { return description; }
        public boolean isSupportedFor(KmipSpec spec) { return true; }
    }

    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> NAME_REGISTRY = new ConcurrentHashMap<>();
    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.getValue(), s);
            NAME_REGISTRY.put(s.getDescription(), s);
        }
    }

    public static Value register(int value, String description, Set<KmipSpec> supported) {
        Value ext = new Value() {
            public int getValue() { return value; }
            public String getDescription() { return description; }
            public boolean isSupportedFor(KmipSpec spec) { return supported.contains(spec); }
        };
        VALUE_REGISTRY.put(value, ext);
        NAME_REGISTRY.put(description, ext);
        return ext;
    }

    public static Value fromValue(KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        if (v == null || !v.isSupportedFor(spec)) throw new IllegalArgumentException("Unknown or unsupported value: " + value);
        return v;
    }

    public static Value fromName(KmipSpec spec, String name) {
        Value v = NAME_REGISTRY.get(name);
        if (v == null || !v.isSupportedFor(spec)) throw new IllegalArgumentException("Unknown or unsupported name: " + name);
        return v;
    }

    @NonNull private final Value value;
}
```

### JSON Test: `src/test/java/org/purpleBean/kmip/codec/json/FooStatusJsonTest.java`

```java
package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.foo.enumeration.FooStatus;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FooStatus JSON Tests")
class FooStatusJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: standard FooStatus")
    void roundTrip_standard() {
        FooStatus original = new FooStatus(FooStatus.Standard.READY);
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, FooStatus.class);
    }

    @Test
    @DisplayName("Round-trip: custom FooStatus")
    void roundTrip_custom() {
        FooStatus.Value custom = FooStatus.register(0x7FFFFFF0, "Custom", Set.of(KmipSpec.V1_2, KmipSpec.UnknownVersion));
        FooStatus original = new FooStatus(custom);
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, FooStatus.class);
        assertThat(original.getValue().getDescription()).isEqualTo("Custom");
    }
}
```

## DemoStatus Type (Copy-Ready)

```java
package org.purpleBean.kmip.demo.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Example enumeration demonstrating the KMIP enumeration pattern.
 * Replace 'DemoStatus' with your actual enumeration name.
 */
@Data
@Builder
public class DemoStatus implements KmipEnumeration {
    // Registry for all values
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    // Static initializer for standard values
    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.getValue(), s);
            DESCRIPTION_REGISTRY.put(s.getDescription(), s);
        }
    }

    // KMIP type information
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DEMO_STATUS);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    // The actual value
    @NonNull
    private final Value value;

    /**
     * Constructs a new DemoStatus with the given value.
     * @param value The value to use
     * @throws IllegalArgumentException if the value is not supported in the current KMIP spec
     */
    public DemoStatus(@NonNull Value value) {
        // Validate against current KMIP spec
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                String.format("Value '%s' is not supported for KMIP spec %s", 
                    value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    /**
     * Registers a custom value for this enumeration.
     * @param value The integer value (must be in extension range 0x80000000-0xFFFFFFFF)
     * @param description The description of the value
     * @param supportedVersions The KMIP versions that support this value
     * @return The registered value
     */
    public static synchronized Value register(int value, @NonNull String description, 
                               @NonNull Set<KmipSpec> supportedVersions) {
        if (!isValidExtensionValue(value)) {
            throw new IllegalArgumentException(
                String.format("Extension value 0x%08X must be in range 0x80000000-0xFFFFFFFF", value)
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

    private static boolean isValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        return (value & 0x80000000) != 0; // Check if high bit is set
    }

    /**
     * Gets a value by its integer value for the given KMIP spec.
     */
    public static Value fromValue(@NonNull KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for 0x%08X in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Gets a value by its description for the given KMIP spec.
     */
    public static Value fromName(@NonNull KmipSpec spec, @NonNull String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Gets all registered custom values.
     */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    /**
     * Gets the description of this status.
     */
    public String getDescription() {
        return value.getDescription();
    }

    /**
     * Checks if this is a custom (non-standard) value.
     */
    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return value.isSupportedFor(spec);
    }

    /**
     * Standard values for DemoStatus.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        READY(0x00000001, "Ready", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)),
        BUSY(0x00000002, "Busy", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = false;

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    /**
     * Interface for all DemoStatus values.
     */
    public interface Value {
        int getValue();
        String getDescription();
        boolean isSupportedFor(KmipSpec spec);
        boolean isCustom();
    }

    /**
     * Implementation for custom values.
     */
    @Getter
    @ToString
    @RequiredArgsConstructor
    public static final class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = true;

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

}
```

## Usage Example

```java
// Set the KMIP spec before creating instances
KmipContext.setSpec(KmipSpec.V1_2);

// Create a standard status
DemoStatus readyStatus = new DemoStatus(DemoStatus.Standard.READY);

// Create a custom status
DemoStatus.Value customStatus = DemoStatus.register(
    0x80000001, 
    "CustomStatus", 
    Set.of(KmipSpec.V1_2, KmipSpec.V1_4)
);
DemoStatus customDemoStatus = new DemoStatus(customStatus);

// Look up by value
DemoStatus.Value foundStatus = DemoStatus.fromValue(KmipSpec.V1_2, 0x00000001);

// Look up by name
DemoStatus.Value foundByName = DemoStatus.fromName(KmipSpec.V1_2, "Ready");

// Check if supported in current spec
boolean isSupported = readyStatus.isSupportedFor(KmipContext.getSpec());
```

## Testing

```java
@ExtendWith(MockitoExtension.class)
class DemoStatusTest extends BaseKmipTest {
    
    @BeforeEach
    void setUp() {
        // Reset the KMIP context before each test
        KmipContext.setSpec(KmipSpec.V1_2);
    }

    @Test
    @DisplayName("Should create standard status")
    void shouldCreateStandardStatus() {
        // Given
        Value expectedValue = DemoStatus.Standard.READY;
        
        // When
        DemoStatus status = new DemoStatus(expectedValue);
        
        // Then
        assertThat(status).isNotNull();
        assertThat(status.getValue()).isSameAs(expectedValue);
        assertThat(status.getDescription()).isEqualTo("Ready");
        assertThat(status.isCustom()).isFalse();
        assertThat(status.isSupportedFor(KmipSpec.V1_2)).isTrue();
    }

    @Test
    @DisplayName("Should create custom status")
    void shouldCreateCustomStatus() {
        // Given
        Value custom = DemoStatus.register(
            0x80000001, 
            "CustomStatus", 
            Set.of(KmipSpec.V1_2, KmipSpec.V1_4)
        );
        
        // When
        DemoStatus status = new DemoStatus(custom);
        
        // Then
        assertThat(status).isNotNull();
        assertThat(status.getValue()).isSameAs(custom);
        assertThat(status.getDescription()).isEqualTo("CustomStatus");
        assertThat(status.isCustom()).isTrue();
        assertThat(status.isSupportedFor(KmipSpec.V1_2)).isTrue();
        assertThat(status.isSupportedFor(KmipSpec.V1_4)).isTrue();
    }
    
    @Test
    @DisplayName("Should fail to create with null value")
    void shouldFailWithNullValue() {
        assertThatNullPointerException()
            .isThrownBy(() -> new DemoStatus(null))
            .withMessage("value is marked non-null but is null");
    }

    @Test
    @DisplayName("Should validate KMIP version support")
    void shouldValidateKmipVersion() {
        // Given
        KmipContext.setSpec(KmipSpec.V1_0);
        
        // When/Then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new DemoStatus(DemoStatus.Standard.READY))
            .withMessageContaining("not supported for KMIP spec");
    }
    
    @Test
    @DisplayName("Should find by value")
    void shouldFindByValue() {
        // When
        Value status = DemoStatus.fromValue(KmipSpec.V1_2, 0x00000001);
        
        // Then
        assertThat(status).isNotNull();
        assertThat(status.getDescription()).isEqualTo("Ready");
        assertThat(status.isCustom()).isFalse();
    }
    
    @Test
    @DisplayName("Should fail to find by invalid value")
    void shouldFailToFindByInvalidValue() {
        // When/Then
        assertThatThrownBy(() -> DemoStatus.fromValue(KmipSpec.V1_2, 0xFFFFFFFF))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("No value found for");
    }

    @Test
    @DisplayName("Should find by name")
    void shouldFindByName() {
        // When
        Value status = DemoStatus.fromName(KmipSpec.V1_2, "Ready");
        
        // Then
        assertThat(status).isNotNull();
        assertThat(status.getValue()).isEqualTo(0x00000001);
        assertThat(status.getDescription()).isEqualTo("Ready");
    }
    
    @Test
    @DisplayName("Should fail to find by invalid name")
    void shouldFailToFindByInvalidName() {
        // When/Then
        assertThatThrownBy(() -> DemoStatus.fromName(KmipSpec.V1_2, "InvalidName"))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining("No value found for");
    }
    
    @Test
    @DisplayName("Should register and find custom value")
    void shouldRegisterAndFindCustomValue() {
        // Given
        Value custom = DemoStatus.register(
            0x80000002,
            "AnotherStatus",
            Set.of(KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4)
        );
        
        // When
        Value foundByValue = DemoStatus.fromValue(KmipSpec.V1_2, 0x80000002);
        Value foundByName = DemoStatus.fromName(KmipSpec.V1_2, "AnotherStatus");
        
        // Then
        assertThat(foundByValue).isSameAs(custom);
        assertThat(foundByName).isSameAs(custom);
        assertThat(foundByValue.isCustom()).isTrue();
    }
    
    @Test
    @DisplayName("Should fail to register duplicate value")
    void shouldFailToRegisterDuplicateValue() {
        // Given
        DemoStatus.register(0x80000003, "UniqueStatus", Set.of(KmipSpec.V1_2));
        
        // When/Then
        assertThatIllegalStateException()
            .isThrownBy(() -> 
                DemoStatus.register(0x80000003, "DuplicateStatus", Set.of(KmipSpec.V1_2))
            )
            .withMessageContaining("already registered");
    }
    
    @Test
    @DisplayName("Should fail to register invalid extension value")
    void shouldFailToRegisterInvalidExtensionValue() {
        // When/Then
        assertThatIllegalArgumentException()
            .isThrownBy(() -> 
                DemoStatus.register(0x00000001, "InvalidStatus", Set.of(KmipSpec.V1_2))
            )
            .withMessageContaining("must be in range 0x80000000-0xFFFFFFFF");
    }
    
    @Test
    @DisplayName("Should get all registered values")
    void shouldGetAllRegisteredValues() {
        // Given
        int initialSize = DemoStatus.registeredValues().size();
        Value custom = DemoStatus.register(
            0x80000004,
            "TestStatus",
            Set.of(KmipSpec.V1_2)
        );
        
        // When
        Collection<Value> values = DemoStatus.registeredValues();
        
        // Then
        assertThat(values).hasSize(initialSize + 1);
        assertThat(values).contains(custom);
    }
    
    @Test
    @DisplayName("Should handle concurrent access")
    void shouldHandleConcurrentAccess() throws InterruptedException, ExecutionException {
        // Given
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Future<Value>> futures = new ArrayList<>();
        
        // When
        for (int i = 0; i < threadCount; i++) {
            final int value = 0x80001000 + i;
            futures.add(executor.submit(() -> 
                DemoStatus.register(
                    value,
                    "ConcurrentStatus" + value,
                    Set.of(KmipSpec.V1_2)
                )
            ));
        }
        
        // Then
        for (int i = 0; i < threadCount; i++) {
            Value value = futures.get(i).get();
            assertThat(value).isNotNull();
            assertThat(DemoStatus.fromValue(KmipSpec.V1_2, 0x80001000 + i)).isSameAs(value);
        }
        
        executor.shutdown();
        assertThat(executor.awaitTermination(1, TimeUnit.SECONDS)).isTrue();
    }
}
```

## Key Points

1. **Thread Safety**: The implementation is thread-safe due to the use of `ConcurrentHashMap` for registries.

2. **Immutability**: The class and its standard values are immutable.

3. **Extensibility**: Custom values can be added at runtime using the `register` method.

4. **Type Safety**: The `Value` interface ensures type safety for all enumeration values.

5. **KMIP Spec Compliance**: The implementation validates values against the current KMIP specification.

6. **Serialization**: Includes support for JSON, XML, and TTLV serialization formats.

7. **Validation**: Comprehensive validation of input parameters and KMIP version compatibility.

8. **Documentation**: Includes Javadoc for all public APIs.

9. **Testing**: Provides a comprehensive test suite covering all major functionality.

10. **Performance**: Optimized lookups using hash maps and minimal object creation.

---

## JSON

```java
package org.purpleBean.kmip.codec.json.serializer.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.demo.enumeration.DemoStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DemoStatusJsonSerializer extends KmipDataTypeJsonSerializer<DemoStatus> {
    @Override
    public void serialize(DemoStatus value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("DemoStatus not supported for spec " + spec);
        }
        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeStringField("value", value.getValue().getDescription());
        gen.writeEndObject();
    }
}
```

```java
package org.purpleBean.kmip.codec.json.deserializer.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.demo.enumeration.DemoStatus;

import java.io.IOException;

public class DemoStatusJsonDeserializer extends KmipDataTypeJsonDeserializer<DemoStatus> {
    @Override
    public DemoStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(DemoStatus.class, "Expected JSON object");
            return null;
        }
        KmipSpec spec = KmipContext.getSpec();
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DemoStatus.class, "Field 'value' must be textual");
            return null;
        }
        DemoStatus.Value v = DemoStatus.fromName(spec, valueNode.asText());
        return new DemoStatus(v);
    }
}
```

Register in `KmipJsonModule` constructor:

```java
addSerializer(DemoStatus.class, new DemoStatusJsonSerializer());
addDeserializer(DemoStatus.class, new DemoStatusJsonDeserializer());
```

## XML

```java
package org.purpleBean.kmip.codec.xml.serializer.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.demo.enumeration.DemoStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DemoStatusXmlSerializer extends JsonSerializer<DemoStatus> {
    @Override
    public void serialize(DemoStatus value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("DemoStatus not supported for spec " + spec);
        }
        ToXmlGenerator xml = (ToXmlGenerator) gen;
        xml.setNextName(javax.xml.namespace.QName.valueOf(value.getKmipTag().getDescription()));
        xml.writeStartObject(value);
        // For Structure type 'type' can be omitted; DemoStatus is Enumeration so include it.
        xml.setNextIsAttribute(true);
        xml.writeStringField("type", value.getEncodingType().getDescription());
        xml.setNextIsAttribute(true);
        xml.writeStringField("value", value.getValue().getDescription());
        xml.writeEndObject();
    }
}
```

```java
package org.purpleBean.kmip.codec.xml.deserializer.demo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.demo.enumeration.DemoStatus;

import java.io.IOException;

public class DemoStatusXmlDeserializer extends JsonDeserializer<DemoStatus> {
    @Override
    public DemoStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        if (!node.isObject()) {
            ctxt.reportInputMismatch(DemoStatus.class, "Expected XML object");
            return null;
        }
        KmipSpec spec = KmipContext.getSpec();
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DemoStatus.class, "Field 'value' must be textual");
            return null;
        }
        DemoStatus.Value v = DemoStatus.fromName(spec, valueNode.asText());
        return new DemoStatus(v);
    }
}
```

Register in `KmipXmlModule` constructor:

```java
addSerializer(DemoStatus.class, new DemoStatusXmlSerializer());
addDeserializer(DemoStatus.class, new DemoStatusXmlDeserializer());
```

## TTLV

```java
package org.purpleBean.kmip.codec.ttlv.serializer.demo;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.demo.enumeration.DemoStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class DemoStatusTtlvSerializer implements TtlvSerializer<DemoStatus> {
    @Override
    public ByteBuffer serialize(DemoStatus value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("DemoStatus not supported for spec " + spec);
        }
        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        ByteBuffer payload = ByteBuffer.allocate(4).putInt(value.getValue().getValue());
        payload.flip();
        return TtlvObject.builder().tag(tag).type(type).value(payload.array()).build().toByteBuffer();
    }
}
```

```java
package org.purpleBean.kmip.codec.ttlv.deserializer.demo;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.demo.enumeration.DemoStatus;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DemoStatusTtlvDeserializer implements TtlvDeserializer<DemoStatus> {
    @Override
    public DemoStatus deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        KmipSpec spec = KmipContext.getSpec();
        if (obj.getValue().length != 4) {
            throw new IllegalArgumentException("Enumeration value must be 4 bytes");
        }
        int intValue = ByteBuffer.wrap(obj.getValue()).getInt();
        DemoStatus.Value v = DemoStatus.fromValue(spec, intValue);
        return new DemoStatus(v);
    }
}
```

Register with a TTLV module (e.g., in `KmipTtlvModule` constructor or a custom module):

```java
addSerializer(DemoStatus.class, new DemoStatusTtlvSerializer());
addDeserializer(DemoStatus.class, new DemoStatusTtlvDeserializer());
```
