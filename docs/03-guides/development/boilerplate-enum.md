# KMIP Enumeration Boilerplate

This guide provides a copy-ready blueprint for creating a new KMIP enumeration type called `FooDemoEnum`. It outlines consistent patterns across core, JSON, XML, and TTLV codecs, with tests and registration snippets.

Table of contents
1. Core enumeration implementation
2. JSON codec (serializer/deserializer)
3. XML codec (serializer/deserializer)
4. TTLV codec (serializer/deserializer)
5. Tests (core, JSON, XML, TTLV)
6. Registration snippets for modules
7. Gotchas and checklist

## 1) Core enumeration implementation

File: `src/main/java/org/purpleBean/kmip/common/enumeration/FooDemoEnum.java`

```java
package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class FooDemoEnum implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM); // add this entry in KmipTag
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public FooDemoEnum(@NonNull Value value) {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for %s is not supported for KMIP spec %s",
                            value.getDescription(), kmipTag.getDescription(), spec)
            );
        }
        this.value = value;
    }

    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
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

    private static boolean isValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        return !(value < extensionStart || value > 0);
    }

    public static Value fromValue(@NonNull KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for %d in KMIP spec %s", value, spec)
                ));
    }

    public static Value fromName(@NonNull KmipSpec spec, @NonNull String name) {
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

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        EXAMPLE_ONE(0x00000001, "ExampleOne", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)),
        EXAMPLE_TWO(0x00000002, "ExampleTwo", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = false;

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    public interface Value {
        int getValue();
        String getDescription();
        boolean isSupportedFor(KmipSpec spec);
        boolean isCustom();
    }

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

Notes
- Add `FOO_DEMO_ENUM` to `KmipTag.Standard` with a unique tag and description (e.g., "FooDemoEnum").
- Constructor validates spec compatibility via `KmipContext.getSpec()`.
- Registry allows extensions in the high-bit range.

## 2) JSON codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/enumeration/FooDemoEnumJsonSerializer.java`

```java
package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FooDemoEnumJsonSerializer extends KmipDataTypeJsonSerializer<FooDemoEnum> {
    @Override
    public void serialize(FooDemoEnum value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s '%s' is not supported for KMIP spec %s",
                            value.getKmipTag().getDescription(), value.getDescription(), spec));
        }

        if (value.getDescription() == null || value.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("Enumeration must have a valid description");
        }

        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeStringField("value", value.getDescription());
        gen.writeEndObject();
    }
}
```

Deserializer: `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/enumeration/FooDemoEnumJsonDeserializer.java`

```java
package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.util.NoSuchElementException;

public class FooDemoEnumJsonDeserializer extends KmipDataTypeJsonDeserializer<FooDemoEnum> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public FooDemoEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(FooDemoEnum.class, String.format("JSON node cannot be null for %s deserialization", kmipTag.getDescription()));
            return null;
        }

        KmipTag tag = p.getCodec().treeToValue(node, KmipTag.class);
        if (!node.isObject() || tag.getValue() != KmipTag.Standard.FOO_DEMO_ENUM) {
            ctxt.reportInputMismatch(FooDemoEnum.class,
                    String.format("Expected object with %s tag for %s", kmipTag.getValue().getValue(), kmipTag.getDescription()));
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType) {
            ctxt.reportInputMismatch(FooDemoEnum.class, String.format("Missing or non-text 'type' field for %s", kmipTag.getDescription()));
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, String.format("Missing or non-text 'value' field for %s", kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        FooDemoEnum.Value enumValue;
        try {
            enumValue = FooDemoEnum.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(FooDemoEnum.class,
                    String.format("Unknown %s value '%s' for KMIP spec %s", kmipTag.getDescription(), description, spec));
            return null;
        }

        FooDemoEnum value = new FooDemoEnum(enumValue);
        if (!value.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("%s '%s' is not supported for KMIP spec %s", kmipTag.getDescription(), description, spec)
            );
        }
        return value;
    }
}
```

## 3) XML codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/enumeration/FooDemoEnumXmlSerializer.java`

```java
package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FooDemoEnumXmlSerializer extends JsonSerializer<FooDemoEnum> {
    @Override
    public void serialize(FooDemoEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("type", value.getEncodingType().getDescription());
        xmlGen.setNextIsAttribute(true);
        xmlGen.writeStringField("value", value.getDescription());
        xmlGen.writeEndObject();
    }
}
```

Deserializer: `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/enumeration/FooDemoEnumXmlDeserializer.java`

```java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.util.NoSuchElementException;

public class FooDemoEnumXmlDeserializer extends JsonDeserializer<FooDemoEnum> {
    @Override
    public FooDemoEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Expected XML element object for FooDemoEnum");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || !EncodingType.ENUMERATION.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Missing or invalid '@type' attribute for FooDemoEnum");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Missing or non-text '@value' attribute for FooDemoEnum");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        FooDemoEnum.Value v;
        try {
            v = FooDemoEnum.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Unknown value '" + description + "' for spec " + spec);
            return null;
        }

        FooDemoEnum result = new FooDemoEnum(v);
        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException("FooDemoEnum '" + description + "' not supported for spec " + spec);
        }
        return result;
    }
}
```

## 4) TTLV codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/enumeration/FooDemoEnumTtlvSerializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class FooDemoEnumTtlvSerializer implements TtlvSerializer<FooDemoEnum> {
    @Override
    public ByteBuffer serialize(FooDemoEnum value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(FooDemoEnum value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getValue().getValue()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
```

Deserializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/enumeration/FooDemoEnumTtlvDeserializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class FooDemoEnumTtlvDeserializer implements TtlvDeserializer<FooDemoEnum> {
    EncodingType type = EncodingType.ENUMERATION;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);

    @Override
    public FooDemoEnum deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        FooDemoEnum result = new FooDemoEnum(FooDemoEnum.fromValue(spec, value));

        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
```

## 5) Tests

These test skeletons provide comprehensive coverage patterns for KMIP enumerations.

Core: `src/test/java/org/purpleBean/kmip/common/enumeration/FooDemoEnumTest.java`

```java
package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("FooDemoEnum Tests")
class FooDemoEnumTest extends BaseKmipTest {

    @Nested
    @DisplayName("Standard Values")
    class StandardValues {
        @Test
        @DisplayName("Should create with standard value")
        void shouldCreateWithStandardValue() {
            FooDemoEnum.Standard std = FooDemoEnum.Standard.EXAMPLE_ONE;
            FooDemoEnum e = new FooDemoEnum(std);
            assertThat(e.getDescription()).isEqualTo(std.getDescription());
            assertThat(e.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }

        @ParameterizedTest
        @EnumSource(FooDemoEnum.Standard.class)
        @DisplayName("Should construct all standard values")
        void shouldConstructAll(FooDemoEnum.Standard std) {
            FooDemoEnum e = new FooDemoEnum(std);
            assertThat(e.getDescription()).isEqualTo(std.getDescription());
        }

        @Test
        @DisplayName("UnsupportedVersion context: construction should fail")
        void unsupportedVersion_constructFails() {
            withKmipSpec(KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE))
                        .isInstanceOf(IllegalArgumentException.class));
        }
    }

    @Nested
    @DisplayName("Custom Values")
    class CustomValues {
        @Test
        @DisplayName("Should register and construct custom value")
        void shouldRegisterAndConstructCustom() {
            int custom = -2000001;
            var v = FooDemoEnum.register(custom, "CustomFoo", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            FooDemoEnum e = new FooDemoEnum(v);
            assertThat(e.getValue().isCustom()).isTrue();
            assertThat(e.getDescription()).isEqualTo("CustomFoo");
        }
    }

    @Nested
    @DisplayName("Lookup")
    class Lookup {
        @Test
        @DisplayName("Should find by value and name")
        void shouldFindByValueAndName() {
            var std = FooDemoEnum.Standard.EXAMPLE_TWO;
            assertThat(FooDemoEnum.fromValue(KmipSpec.V1_2, std.getValue())).isEqualTo(std);
            assertThat(FooDemoEnum.fromName(KmipSpec.V1_2, std.getDescription())).isEqualTo(std);
        }

        @Test
        @DisplayName("Should throw for unknowns")
        void shouldThrowForUnknowns() {
            assertThatThrownBy(() -> FooDemoEnum.fromValue(KmipSpec.V1_2, -999)).isInstanceOf(NoSuchElementException.class);
            assertThatThrownBy(() -> FooDemoEnum.fromName(KmipSpec.V1_2, "Nope")).isInstanceOf(NoSuchElementException.class);
        }
    }
}
```

JSON: `src/test/java/org/purpleBean/kmip/codec/json/common/enumeration/FooDemoEnumJsonTest.java`

```java
package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("FooDemoEnum JSON Tests")
class FooDemoEnumJsonTest extends BaseKmipTest {
    @Test
    @DisplayName("Round-trip: standard and custom")
    void roundTrip() {
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE), FooDemoEnum.class);
    }

    @Test
    @DisplayName("Structure: expected JSON fields")
    void structure_expectFields() {
        FooDemoEnum e = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_TWO);
        SerializationTestUtils.testJsonSerialization(jsonMapper, e, json -> {
            SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
            assertThat(json).contains("ExampleTwo");
        });
    }

    @Test
    @DisplayName("UnsupportedVersion context: JSON construction should fail")
    void unsupportedVersion_fails() {
        withKmipSpec(KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE)).isInstanceOf(IllegalArgumentException.class));
    }
}
```

XML: `src/test/java/org/purpleBean/kmip/codec/xml/common/enumeration/FooDemoEnumXmlTest.java`

```java
package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("FooDemoEnum XML Tests")
class FooDemoEnumXmlTest extends BaseKmipTest {
    @Test
    @DisplayName("Round-trip: standard")
    void roundTrip() {
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE), FooDemoEnum.class);
    }

    @Test
    @DisplayName("UnsupportedVersion context: XML serialization should fail")
    void unsupportedVersion_xmlSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> xmlMapper.writeValueAsString(new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE)))
                        .isInstanceOf(Exception.class));
    }
}
```

TTLV: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/enumeration/FooDemoEnumTtlvTest.java`

```java
package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("FooDemoEnum TTLV Tests")
class FooDemoEnumTtlvTest extends BaseKmipTest {
    private final TtlvMapper ttlvMapper = buildMapper();
    private TtlvMapper buildMapper() { TtlvMapper m = new TtlvMapper(); m.registerModule(new KmipTtlvModule()); return m; }

    @Test
    @DisplayName("Round-trip: TTLV")
    void roundTrip() {
        assertRoundTrip(new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_TWO));
    }

    @Test
    @DisplayName("UnsupportedVersion context: TTLV serialization should fail")
    void unsupportedVersion_ttlvSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> ttlvMapper.writeValueAsByteBuffer(new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE)))
                        .isInstanceOf(Exception.class));
    }

    private void assertRoundTrip(FooDemoEnum original) {
        ByteBuffer buffer;
        try { buffer = ttlvMapper.writeValueAsByteBuffer(original); }
        catch (IOException e) { throw new RuntimeException("Failed to serialize to TTLV", e); }

        FooDemoEnum deserialized;
        try { deserialized = ttlvMapper.readValue(buffer, FooDemoEnum.class); }
        catch (IOException e) { throw new RuntimeException("Failed to deserialize from TTLV", e); }

        // value equality via description
        assertThat(deserialized.getDescription()).isEqualTo(original.getDescription());
    }
}
```

## 6) Registration snippets

Add these lines to the respective modules to register FooDemoEnum codecs.

JSON module: `src/main/java/org/purpleBean/kmip/codec/json/KmipJsonModule.java`

```java
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration.FooDemoEnumJsonSerializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration.FooDemoEnumJsonDeserializer;

// inside constructor
addSerializer(FooDemoEnum.class, new FooDemoEnumJsonSerializer());
addDeserializer(FooDemoEnum.class, new FooDemoEnumJsonDeserializer());
```

XML module: `src/main/java/org/purpleBean/kmip/codec/xml/KmipXmlModule.java`

```java
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration.FooDemoEnumXmlSerializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration.FooDemoEnumXmlDeserializer;

// inside constructor
addSerializer(FooDemoEnum.class, new FooDemoEnumXmlSerializer());
addDeserializer(FooDemoEnum.class, new FooDemoEnumXmlDeserializer());
```

TTLV module: `src/main/java/org/purpleBean/kmip/codec/ttlv/KmipTtlvModule.java`

```java
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration.FooDemoEnumTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration.FooDemoEnumTtlvDeserializer;

// inside constructor
addSerializer(FooDemoEnum.class, new FooDemoEnumTtlvSerializer());
addDeserializer(FooDemoEnum.class, new FooDemoEnumTtlvDeserializer());
```

## 7) Gotchas and checklist

- Define `FOO_DEMO_ENUM` in `KmipTag.Standard` with unique tag and description.
- JSON and XML shape: object with `tag`, `type`, `value` where `value` is the description/name.
- JSON deserializer must validate tag, type, and value fields.
- XML deserializer must validate `type` attribute equals `Enumeration` and parse `value`.
- TTLV payload is the integer `Value.getValue()`; validate type byte equals `ENUMERATION`.
- All constructors and codecs must validate spec via `KmipContext.getSpec()` and throw when unsupported.
- Provide tests for standard, custom registration, lookups, round-trips, and UnsupportedVersion contexts.
        