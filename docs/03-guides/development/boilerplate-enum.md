# KMIP Enumeration Boilerplate (Authoritative)

This guide is the authoritative, copy-ready blueprint for creating a new KMIP enumeration called `FooDemoEnum`, modeled on the production `State` enumeration and its serializers/deserializers/tests.

It covers:
- Core implementation (main/java)
- JSON/XML/TTLV serializers and deserializers
- Unit and codec tests using the reusable test suites
- Optional performance tests (JMH)
- Import style and legacy references to avoid

Use this as your single source of truth.

---

## 1) Core Enumeration Implementation

File: `src/main/java/org/purpleBean/kmip/common/enumeration/FooDemoEnum.java`

```java
package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Modeled after State: registry-backed, spec-aware KMIP enumeration.
 */
@Data
public class FooDemoEnum implements KmipEnumeration {

    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        // Pre-register standard values
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM); // add this entry to KmipTag.Standard
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

    public String getDescription() { return value.getDescription(); }
    public boolean isCustom() { return value.isCustom(); }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) { return value.isSupportedFor(spec); }

    /**
     * Register an extension value. Mirror range/validation from production patterns.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        if (!isValidExtensionValue(value)) {
            throw new IllegalArgumentException("Extension value must be in vendor range 0x80000000 - 0xFFFFFFFF");
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
        return Integer.compareUnsigned(value, 0x80000000) >= 0; // value >= 0x80000000 (unsigned)
    }

    /** Lookup by integer value, filtered by spec support. */
    public static Value fromValue(@NonNull KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for %d in KMIP spec %s", value, spec)));
    }

    /** Lookup by description (case-sensitive in registry), filtered by spec support. */
    public static Value fromName(@NonNull KmipSpec spec, @NonNull String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for '%s' in KMIP spec %s", name, spec)));
    }

    /** Values registered via register(...). */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    // ----- Value hierarchy modeled after State -----
    public interface Value {
        int getValue();
        String getDescription();
        boolean isSupportedFor(KmipSpec spec);
        boolean isCustom();
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public enum Standard implements Value {
        EXAMPLE_ONE(0x00000001, "ExampleOne", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)),
        EXAMPLE_TWO(0x00000002, "ExampleTwo", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = false;

        @Override
        public boolean isSupportedFor(KmipSpec spec) { return supportedVersions.contains(spec); }
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
        public boolean isSupportedFor(KmipSpec spec) { return supportedVersions.contains(spec); }
    }
}
```

Notes
- Add `FOO_DEMO_ENUM` to `KmipTag.Standard` with a unique value/description.
- `register(...)` uses the vendor extension range (0x80000000 – 0xFFFFFFFF) like production patterns.
- Lookups filter by spec support and throw `NoSuchElementException` for unknowns.

---

## 2) JSON Codec

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
            ctxt.reportInputMismatch(FooDemoEnum.class, "JSON node cannot be null for FooDemoEnum deserialization");
            return null;
        }
        KmipTag tag = p.getCodec().treeToValue(node, KmipTag.class);
        if (!node.isObject() || tag.getValue() != KmipTag.Standard.FOO_DEMO_ENUM) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Expected object with FooDemoEnum tag");
            return null;
        }
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Missing or invalid 'type' for FooDemoEnum");
            return null;
        }
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Missing or non-text 'value' for FooDemoEnum");
            return null;
        }
        String description = valueNode.asText();
        if (description.trim().isEmpty()) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "FooDemoEnum value cannot be empty");
            return null;
        }
        KmipSpec spec = KmipContext.getSpec();
        FooDemoEnum.Value enumValue;
        try {
            enumValue = FooDemoEnum.fromName(spec, description);
        } catch (NoSuchElementException e) {
            ctxt.reportInputMismatch(FooDemoEnum.class, "Unknown FooDemoEnum value '" + description + "' for spec " + spec);
            return null;
        }
        FooDemoEnum result = new FooDemoEnum(enumValue);
        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException("FooDemoEnum '" + description + "' is not supported for spec " + spec);
        }
        return result;
    }
}
```

---

## 3) XML Codec

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
        JsonNode node = p.readValueAsTree();
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

---

## 4) TTLV Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/enumeration/FooDemoEnumTtlvSerializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class FooDemoEnumTtlvSerializer implements TtlvSerializer<FooDemoEnum> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ENUM);

    @Override
    public ByteBuffer serialize(FooDemoEnum value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(FooDemoEnum value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = kmipTag.getTagBytes();
        byte type = EncodingType.ENUMERATION.getTypeValue();
        byte[] payload = ByteBuffer.allocate(4).putInt(value.getValue().getValue()).array();

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
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        int raw = bb.getInt();

        KmipSpec spec = KmipContext.getSpec();
        FooDemoEnum.Value enumValue = FooDemoEnum.fromValue(spec, raw);
        FooDemoEnum result = new FooDemoEnum(enumValue);

        if (!result.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return result;
    }
}
```

---

## 5) Unit Tests

### Core Enumeration Test (reusable suite)

File: `src/test/java/org/purpleBean/kmip/common/enumeration/FooDemoEnumTest.java`

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
    protected Class<FooDemoEnum> type() { return FooDemoEnum.class; }

    @Override
    protected FooDemoEnum createDefault() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE); }

    @Override
    protected FooDemoEnum createEqualToDefault() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE); }

    @Override
    protected FooDemoEnum createDifferentFromDefault() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_TWO); }

    @Override
    protected EncodingType expectedEncodingType() { return EncodingType.ENUMERATION; }

    // Opt-in registry behavior (split positive/negative)
    @Override
    protected boolean supportsRegistryBehavior() { return true; }

    @Override
    protected void assertEnumerationRegistryBehaviorPositive() {
        FooDemoEnum.Value custom = FooDemoEnum.register(0x80000010, "X-Enum-Custom", Set.of(KmipSpec.V1_2));
        assertThat(custom.isCustom()).isTrue();
        assertThat(custom.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(custom.isSupportedFor(KmipSpec.V1_2)).isTrue();

        FooDemoEnum.Value byName = FooDemoEnum.fromName(KmipSpec.V1_2, "X-Enum-Custom");
        FooDemoEnum.Value byVal = FooDemoEnum.fromValue(KmipSpec.V1_2, 0x80000010);
        assertThat(byName.getDescription()).isEqualTo("X-Enum-Custom");
        assertThat(byVal.getValue()).isEqualTo(0x80000010);
    }

    @Override
    protected void assertEnumerationRegistryBehaviorNegative() {
        assertThatThrownBy(() -> FooDemoEnum.register(0x7FFFFFFF, "Bad-Range", Set.of(KmipSpec.V1_2)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> FooDemoEnum.register(0x80000011, "   ", Set.of(KmipSpec.V1_2)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> FooDemoEnum.register(0x80000012, "X-Empty-Versions", Set.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
```

---

## 6) Codec Tests

Place these under codec packages and extend the abstract suites for each format.

### JSON

File: `src/test/java/org/purpleBean/kmip/codec/json/common/enumeration/FooDemoEnumJsonTest.java`

```java
package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("FooDemoEnum JSON Serialization")
class FooDemoEnumJsonTest extends AbstractJsonSerializationSuite<FooDemoEnum> {
    @Override protected Class<FooDemoEnum> type() { return FooDemoEnum.class; }
    @Override protected FooDemoEnum createDefault() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE); }
    @Override protected FooDemoEnum createVariant() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_TWO); }
}
```

### XML

File: `src/test/java/org/purpleBean/kmip/codec/xml/common/enumeration/FooDemoEnumXmlTest.java`

```java
package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("FooDemoEnum XML Serialization")
class FooDemoEnumXmlTest extends AbstractXmlSerializationSuite<FooDemoEnum> {
    @Override protected Class<FooDemoEnum> type() { return FooDemoEnum.class; }
    @Override protected FooDemoEnum createDefault() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE); }
    @Override protected FooDemoEnum createVariant() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_TWO); }
}
```

### TTLV

File: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/enumeration/FooDemoEnumTtlvTest.java`

```java
package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("FooDemoEnum TTLV Serialization")
class FooDemoEnumTtlvTest extends AbstractTtlvSerializationSuite<FooDemoEnum> {
    @Override protected Class<FooDemoEnum> type() { return FooDemoEnum.class; }
    @Override protected FooDemoEnum createDefault() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE); }
    @Override protected FooDemoEnum createVariant() { return new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_TWO); }
}
```

---

## 7) Performance Benchmarks (JMH)

Authoritative reference: `StateBenchmarkSubject`. Below is a ready-to-adapt boilerplate for `FooDemoEnum`.

Subject class: `src/test/java/org/purpleBean/kmip/benchmark/subjects/FooDemoEnumBenchmarkSubject.java`

```java
package org.purpleBean.kmip.benchmark.subjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.nio.ByteBuffer;

public class FooDemoEnumBenchmarkSubject implements KmipBenchmarkSubject {
    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;
    private FooDemoEnum obj;
    private String jsonStr;
    private String xmlStr;
    private ByteBuffer ttlvBuf;

    @Override public String name() { return "FooDemoEnum"; }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(KmipSpec.V1_2);
        json = new ObjectMapper(); json.findAndRegisterModules(); json.registerModule(new JavaTimeModule()); json.registerModule(new KmipJsonModule());
        xml = new XmlMapper(); xml.findAndRegisterModules(); xml.registerModule(new JavaTimeModule()); xml.registerModule(new KmipXmlModule());
        ttlv = new TtlvMapper(); ttlv.registerModule(new KmipTtlvModule());

        obj = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE);

        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override public void tearDown() { KmipContext.clear(); }
    @Override public String jsonSerialize() throws Exception { return json.writeValueAsString(obj); }
    @Override public Object jsonDeserialize() throws Exception { return json.readValue(jsonStr, FooDemoEnum.class); }
    @Override public String xmlSerialize() throws Exception { return xml.writeValueAsString(obj); }
    @Override public Object xmlDeserialize() throws Exception { return xml.readValue(xmlStr, FooDemoEnum.class); }
    @Override public ByteBuffer ttlvSerialize() throws Exception { return ttlv.writeValueAsByteBuffer(obj); }
    @Override public Object ttlvDeserialize() throws Exception { return ttlv.readValue(ttlvBuf.duplicate(), FooDemoEnum.class); }
}
```

ServiceLoader registration: `src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject`

```
org.purpleBean.kmip.benchmark.subjects.FooDemoEnumBenchmarkSubject
```

Recommended runner (same as attribute guide):

```bash
mvn -q -DskipTests test-compile \
  exec:java \
  -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner" \
  -Dbench.include=KmipSerializationBenchmark
```

Outputs:
- Raw JMH JSON: `target/jmh-results.json`
- Markdown summary: `target/jmh-report.md`

---

## 8) Import Style and Legacy References

- Prefer imports, not FQNs, in tests:
  - `import java.util.Set;` / `Set.of(...)`
  - `import static org.assertj.core.api.Assertions.*;`
- Avoid legacy/fictitious classes: JsonCodec, XmlCodec, TtlvCodec (do not exist in this repo).
- Use the canonical testing suites and mappers as shown above.
- For tag registry and lookup examples (non-enumeration), refer to `src/test/java/org/purpleBean/kmip/KmipTagTest.java`.
