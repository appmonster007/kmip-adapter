# KMIP Enumeration Implementation Guide

This guide provides a complete reference for implementing KMIP enumeration classes, using `FooDemoEnum` as an example.

## 1. Core Enumeration Class

Create the main enum class that implements `KmipEnumeration` interface:

```java
package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP FooDemoEnum enumeration.
 */
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

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public FooDemoEnum(@NonNull Value value) {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for FooDemoEnum is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    private static void checkValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        if (value < extensionStart || value > 0) {
            throw new IllegalArgumentException(
                    String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
            );
        }
    }

    /**
     * Register an extension value.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        checkValidExtensionValue(value);
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

    /**
     * Look up by name.
     */
    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No FooDemoEnum value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Look up by value.
     */
    public static Value fromValue(KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No FooDemoEnum value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Get registered values.
     */
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
        DEMO_ONE(0x00000001, "DemoOne", KmipSpec.V1_0, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4),
        DEMO_TWO(0x00000002, "DemoTwo", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4);

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
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    // ----- Value hierarchy -----
    public interface Value {
        int getValue();

        String getDescription();

        boolean isSupportedFor(KmipSpec spec);

        boolean isCustom();
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = true;

        public Extension(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }
}
```

## 2. JSON Serializer

Create a JSON serializer for the enum:

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

/**
 * JSON serializer for FooDemoEnum.
 */
public class FooDemoEnumJsonSerializer extends KmipDataTypeJsonSerializer<FooDemoEnum> {

    @Override
    public void serialize(FooDemoEnum value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) 
            throws IOException {
        if (value == null) {
            return;
        }

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("FooDemoEnum '%s' is not supported for KMIP spec %s",
                            value.getDescription(), spec)
            );
        }

        if (value.getDescription() == null || value.getDescription().trim().isEmpty()) {
            throw new IllegalStateException("FooDemoEnum must have a valid description");
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

Create a JSON deserializer for the enum:

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

/**
 * JSON deserializer for FooDemoEnum.
 */
public class FooDemoEnumJsonDeserializer extends KmipDataTypeJsonDeserializer<FooDemoEnum> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @Override
    public FooDemoEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(FooDemoEnum.class, 
                    "JSON node cannot be null for FooDemoEnum deserialization");
            return null;
        }

        // Validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(FooDemoEnum.class, "Invalid KMIP tag for FooDemoEnum");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(FooDemoEnum.class, 
                    String.format("Failed to parse KMIP tag for FooDemoEnum: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(FooDemoEnum.class,
                    String.format("Expected object with %s tag for FooDemoEnum, got tag: %s", 
                    kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }

        // Validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || 
                EncodingType.fromName(typeNode.asText()).isEmpty() ||
                EncodingType.fromName(typeNode.asText()).get() != encodingType) {
            ctxt.reportInputMismatch(FooDemoEnum.class, 
                    "Missing or invalid 'type' field for FooDemoEnum");
            return null;
        }

        // Validate value field
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, 
                    String.format("Missing or non-text 'value' field for %s", 
                    kmipTag.getDescription()));
            return null;
        }

        String description = valueNode.asText();
        if (description == null || description.trim().isEmpty()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, 
                    String.format("%s value cannot be empty", kmipTag.getDescription()));
            return null;
        }

        // KMIP spec compatibility and value lookup
        KmipSpec spec = KmipContext.getSpec();
        try {
            FooDemoEnum.Value enumValue = FooDemoEnum.fromName(spec, description);
            FooDemoEnum result = new FooDemoEnum(enumValue);

            if (!result.isSupportedFor(spec)) {
                throw new NoSuchElementException(
                        String.format("FooDemoEnum '%s' is not supported for KMIP spec %s", 
                        description, spec)
                );
            }

            return result;
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(FooDemoEnum.class,
                    String.format("Unknown FooDemoEnum value '%s' for KMIP spec %s", 
                    description, spec));
            return null;
        }
    }
}
```

## 4. XML Serializer

```java
package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * XML serializer for FooDemoEnum.
 */
public class FooDemoEnumXmlSerializer extends KmipDataTypeXmlSerializer<FooDemoEnum> {

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

## 5. XML Deserializer

```java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for FooDemoEnum.
 */
public class FooDemoEnumXmlDeserializer extends KmipDataTypeXmlDeserializer<FooDemoEnum> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);

    @Override
    public FooDemoEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Expected XML element object for FooDemoEnum");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Invalid Tag for FooDemoEnum");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
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

        FooDemoEnum fooDemoEnum = new FooDemoEnum(FooDemoEnum.fromName(spec, description));
        if (!fooDemoEnum.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("FooDemoEnum '%s' not supported for spec %s", description, spec));
        }

        return fooDemoEnum;
    }
}
```

## 6. TTLV Serializer

```java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * TTLV serializer for FooDemoEnum.
 */
public class FooDemoEnumTtlvSerializer extends KmipDataTypeTtlvSerializer<FooDemoEnum> {

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

## 7. TTLV Deserializer

```java
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * TTLV deserializer for FooDemoEnum.
 */
public class FooDemoEnumTtlvDeserializer extends KmipDataTypeTtlvDeserializer<FooDemoEnum> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);

    @Override
    public FooDemoEnum deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != encodingType.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for FooDemoEnum", encodingType.getTypeValue()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int value = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        FooDemoEnum fooDemoEnum = new FooDemoEnum(FooDemoEnum.fromValue(spec, value));

        if (!fooDemoEnum.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return fooDemoEnum;
    }
}
```

## 8. Testing

### 8.1 Domain Test

```java
package org.purpleBean.kmip.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipEnumerationSuite;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("FooDemoEnum Domain Tests")
class FooDemoEnumTest extends AbstractKmipEnumerationSuite<FooDemoEnum> {

    @Override
    protected Class<FooDemoEnum> type() {
        return FooDemoEnum.class;
    }

    @Override
    protected FooDemoEnum createDefault() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_1);
    }

    @Override
    protected FooDemoEnum createEqualToDefault() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_1);
    }

    @Override
    protected FooDemoEnum createDifferentFromDefault() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_2);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.ENUMERATION;
    }

    @Override
    protected boolean supportsRegistryBehavior() {
        return true;
    }

    @Override
    protected void assertLookupBehaviour() {
        // Lookup by name/value
        FooDemoEnum.Value byName = FooDemoEnum.fromName(KmipSpec.UnknownVersion, "X-Enum-Custom");
        FooDemoEnum.Value byVal = FooDemoEnum.fromValue(KmipSpec.UnknownVersion, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);

        // Lookup by name/value with unsupported version
        assertThatThrownBy(() -> FooDemoEnum.fromName(KmipSpec.UnsupportedVersion, "X-Enum-Custom"));
    }

    @Override
    protected void assertEnumerationRegistryBehavior() {
        // Valid registration in FooDemoEnum requires 8XXXXXXX (hex) range per implementation
        FooDemoEnum.Value custom = FooDemoEnum.register(0x80000010, "X-Enum-Custom", 
            Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0));
        
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.UnknownVersion)).isTrue();
        assertThat(custom.isSupportedFor(KmipSpec.UnsupportedVersion)).isFalse();

        // Negative cases: invalid range, empty description, empty versions
        assertThatThrownBy(() -> 
            FooDemoEnum.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> 
            FooDemoEnum.register(0x00000001, "Bad-Range", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> 
            FooDemoEnum.register(0x80000011, "   ", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0)))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> 
            FooDemoEnum.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
```

### 8.2 JSON Serialization Test

```java
package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("FooDemoEnum JSON Serialization")
class FooDemoEnumJsonTest extends AbstractJsonSerializationSuite<FooDemoEnum> {
    @Override
    protected Class<FooDemoEnum> type() {
        return FooDemoEnum.class;
    }

    @Override
    protected FooDemoEnum createDefault() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_1);
    }

    @Override
    protected FooDemoEnum createVariant() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_2);
    }
}
```

### 8.3 XML Serialization Test

```java
package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("FooDemoEnum XML Serialization")
class FooDemoEnumXmlTest extends AbstractXmlSerializationSuite<FooDemoEnum> {
    @Override
    protected Class<FooDemoEnum> type() {
        return FooDemoEnum.class;
    }

    @Override
    protected FooDemoEnum createDefault() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_1);
    }

    @Override
    protected FooDemoEnum createVariant() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_2);
    }
}
```

### 8.4 TTLV Serialization Test

```java
package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("FooDemoEnum TTLV Serialization")
class FooDemoEnumTtlvTest extends AbstractTtlvSerializationSuite<FooDemoEnum> {
    @Override
    protected Class<FooDemoEnum> type() {
        return FooDemoEnum.class;
    }

    @Override
    protected FooDemoEnum createDefault() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_1);
    }

    @Override
    protected FooDemoEnum createVariant() {
        return new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_2);
    }
}
```

### 8.5 Benchmark Subject

```java
package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.nio.ByteBuffer;

public class FooDemoEnumBenchmarkSubject extends KmipBenchmarkSubject {
    private ObjectMapper json;
    private ObjectMapper xml;
    private TtlvMapper ttlv;

    private FooDemoEnum obj;

    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;

    public FooDemoEnumBenchmarkSubject() throws Exception {
        this.setup();
    }
    
    @Override
    public String name() {
        return "FooDemoEnum";
    }

    @Override
    public void setup() throws Exception {
        json = new JsonMapper();
        json.findAndRegisterModules();
        json.registerModule(new JavaTimeModule());
        json.registerModule(new KmipJsonModule());
        
        xml = new XmlMapper();
        xml.findAndRegisterModules();
        xml.registerModule(new JavaTimeModule());
        xml.registerModule(new KmipXmlModule());
        
        ttlv = new TtlvMapper();
        ttlv.registerModule(new KmipTtlvModule());

        obj = new FooDemoEnum(FooDemoEnum.Standard.PLACEHOLDER_1);

        // Pre-serialize to ensure all mappers are initialized
        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }

    @Override
    public String jsonSerialize() throws Exception {
        return json.writeValueAsString(obj);
    }

    @Override
    public Object jsonDeserialize() throws Exception {
        return json.readValue(jsonStr, FooDemoEnum.class);
    }

    @Override
    public String xmlSerialize() throws Exception {
        return xml.writeValueAsString(obj);
    }

    @Override
    public Object xmlDeserialize() throws Exception {
        return xml.readValue(xmlStr, FooDemoEnum.class);
    }

    @Override
    public ByteBuffer ttlvSerialize() throws Exception {
        return ttlv.writeValueAsByteBuffer(obj);
    }

    @Override
    public Object ttlvDeserialize() throws Exception {
        return ttlv.readValue(ttlvBuf.duplicate(), FooDemoEnum.class);
    }
}
```

## 8. Service Registration

Register the serializers in the appropriate service files:

### `META-INF/services/org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer`
```
org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration.FooDemoEnumJsonSerializer
```

### `META-INF/services/org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer`
```
org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration.FooDemoEnumJsonDeserializer
```

### `META-INF/services/org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer`
```
org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration.FooDemoEnumXmlSerializer
```

### `META-INF/services/org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer`
```
org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration.FooDemoEnumXmlDeserializer
```

### `META-INF/services/org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer`
```
org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration.FooDemoEnumTtlvSerializer
```

### `META-INF/services/org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer`
```
org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration.FooDemoEnumTtlvDeserializer
```

## Key Features

1. **Type Safety**: Strongly-typed enum values with compile-time checking
2. **KMIP Versioning**: Support for different KMIP spec versions
3. **Extensibility**: Ability to register custom enum values at runtime
4. **Thread Safety**: Thread-safe value registration and lookup
5. **Validation**: Comprehensive input validation
6. **Serialization**: Support for JSON and XML formats
7. **Immutability**: All value objects are immutable
8. **Documentation**: Comprehensive JavaDoc and error messages

## Usage Examples

### Creating an instance
```java
// Using standard value
FooDemoEnum demo = new FooDemoEnum(FooDemoEnum.Standard.DEMO_ONE);

// Looking up by name (with KMIP version check)
FooDemoEnum demo = new FooDemoEnum(FooDemoEnum.fromName(KmipSpec.V1_4, "DemoOne"));

// Looking up by value (with KMIP version check)
FooDemoEnum demo = new FooDemoEnum(FooDemoEnum.fromValue(KmipSpec.V1_4, 0x00000001));
```

### Registering a custom value
```java
// Register a custom value (only once, typically at startup)
FooDemoEnum.Value custom = FooDemoEnum.register(
    0x80000001, 
    "CustomValue", 
    Set.of(KmipSpec.V1_3, KmipSpec.V1_4)
);

// Use the custom value
FooDemoEnum customDemo = new FooDemoEnum(custom);
```

### Serialization/Deserialization
```java
// JSON serialization
ObjectMapper mapper = new ObjectMapper();
String json = mapper.writeValueAsString(demo);
// Produces: {"tag":"FooDemoEnum","type":"Enumeration","value":"DemoOne"}

// JSON deserialization
FooDemoEnum deserialized = mapper.readValue(json, FooDemoEnum.class);
```

## Best Practices

1. **Version Support**: Always specify which KMIP versions each enum value supports
2. **Validation**: Validate all inputs, especially for extension values
3. **Immutability**: Never modify enum values after creation
4. **Thread Safety**: Be aware of thread safety when registering custom values
5. **Error Messages**: Provide clear, descriptive error messages
6. **Testing**: Test all edge cases, especially around version compatibility
7. **Documentation**: Document the purpose and usage of each enum value
