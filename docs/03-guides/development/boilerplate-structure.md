# KMIP Boilerplate: FooDemoStructure

This guide provides a copy-ready template to add a new KMIP structure named `FooDemoStructure` with:

- FooDemoAttribute: required (non-null), implements `KmipAttribute`
- FooDemoEnum: optional (nullable), implements `KmipDataType` (enumeration style)

It includes structure implementation, serializers/deserializers for JSON/XML/TTLV, module registration pointers, and tests.

---

## Files to create

- Structure
  - `src/main/java/org/purpleBean/kmip/common/structure/FooDemoStructure.java`

- Serializers
  - JSON: `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/structure/FooDemoStructureJsonSerializer.java`
  - XML: `src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/structure/FooDemoStructureXmlSerializer.java`
  - TTLV: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/structure/FooDemoStructureTtlvSerializer.java`

- Deserializers
  - JSON: `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/structure/FooDemoStructureJsonDeserializer.java`
  - XML: `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/structure/FooDemoStructureXmlDeserializer.java`
  - TTLV: `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/structure/FooDemoStructureTtlvDeserializer.java`

- Tests
  - Unit: `src/test/java/org/purpleBean/kmip/common/structure/FooDemoStructureTest.java`
  - JSON: `src/test/java/org/purpleBean/kmip/codec/json/common/structure/FooDemoStructureJsonTest.java`
  - XML: `src/test/java/org/purpleBean/kmip/codec/xml/common/structure/FooDemoStructureXmlTest.java`
  - TTLV: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/structure/FooDemoStructureTtlvTest.java`

Prerequisites: `FooDemoEnum` and `FooDemoAttribute` exist (see `boilerplate-enum.md` and `boilerplate-attribute.md`). Modules available: `KmipJsonModule`, `KmipXmlModule`, `KmipTtlvModule`, `TtlvMapper`. Core interfaces: `KmipDataType`, `KmipAttribute`, `KmipStructure`, `KmipSpec`, `KmipTag`, `EncodingType`, `KmipContext`.

---

## 1) Structure implementation

Contract:

- Implements `KmipStructure`.
- Tag: register a unique `KmipTag.Value` for `FooDemoStructure`.
- Encoding: `EncodingType.STRUCTURE`.
- Fields and nullability:
  - `FooDemoAttribute attribute`: required (`@NonNull`).
  - `FooDemoEnum mode`: optional (nullable).
- `getValues()`: deterministic order – `attribute` first (required), then `mode` if present.
- `isSupportedFor(spec)`: verify tag and child support.
- Builder validation: enforce required attribute; optionally validate against `KmipContext.getSpec()`.

Skeleton: `src/main/java/org/purpleBean/kmip/common/structure/FooDemoStructure.java`

```java
package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.attribute.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class FooDemoStructure implements KmipStructure {

    private static final KmipTag.Value FOO_DEMO_STRUCTURE_TAG = KmipTag.register(
            0x54F00D, "FooDemoStructure", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)
    );

    @NonNull private final FooDemoAttribute attribute; // required
    private final FooDemoEnum mode;                    // optional

    @Override
    public KmipTag getKmipTag() { return new KmipTag(FOO_DEMO_STRUCTURE_TAG); }

    @Override
    public EncodingType getEncodingType() { return EncodingType.STRUCTURE; }

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(attribute);
        if (mode != null) values.add(mode);
        return values;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        if (!FOO_DEMO_STRUCTURE_TAG.isSupportedFor(spec)) return false;
        if (!attribute.isSupportedFor(spec)) return false;
        return mode == null || mode.isSupportedFor(spec);
    }

    public static class FooDemoStructureBuilder {
        public FooDemoStructure build() {
            Objects.requireNonNull(attribute, "FooDemoAttribute 'attribute' is required");
            KmipSpec spec = KmipContext.getSpec();
            if (!new KmipTag(FOO_DEMO_STRUCTURE_TAG).isSupportedFor(spec)) {
                throw new IllegalStateException("FooDemoStructure not supported for spec " + spec);
            }
            if (!attribute.isSupportedFor(spec)) {
                throw new IllegalStateException("FooDemoAttribute not supported for spec " + spec);
            }
            if (mode != null && !mode.isSupportedFor(spec)) {
                throw new IllegalStateException("FooDemoEnum mode not supported for spec " + spec);
            }
            return new FooDemoStructure(attribute, mode);
        }
    }
}
```

---

## 2) JSON serializer/deserializer

Serializer: `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/structure/FooDemoStructureJsonSerializer.java`

Expectations:

- Validate `isSupportedFor(KmipContext.getSpec())`.
- Emit tag object, type string, then child values in order: `attribute`, `mode?`.

Deserializer: `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/structure/FooDemoStructureJsonDeserializer.java`

Expectations:

- Parse child nodes.
- Enforce required `attribute`; allow missing/`null` `mode`.
- Build via `FooDemoStructure.builder()`.

Code:

```java
// File: src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/structure/FooDemoStructureJsonSerializer.java
package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class FooDemoStructureJsonSerializer extends KmipDataTypeJsonSerializer<FooDemoStructure> {

    @Override
    public void serialize(FooDemoStructure value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec)
            );
        }

        List<KmipDataType> fields = value.getValues();
        for (KmipDataType field : fields) {
            if (field != null && !field.isSupportedFor(spec)) {
                throw new UnsupportedEncodingException(
                        String.format("%s in %s is not supported for KMIP spec %s",
                                field.getKmipTag().getDescription(), value.getKmipTag().getDescription(), spec)
                );
            }
        }

        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeFieldName("value");
        gen.writeStartArray();
        for (KmipDataType field : fields) {
            if (field != null) {
                gen.writeObject(field);
            }
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
```

```java
// File: src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/structure/FooDemoStructureJsonDeserializer.java
package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.attribute.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.util.NoSuchElementException;

public class FooDemoStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<FooDemoStructure> {

    @Override
    public FooDemoStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "Expected object for FooDemoStructure");
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray()) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "FooDemoStructure 'value' must be an array");
            return null;
        }

        FooDemoStructure.FooDemoStructureBuilder builder = FooDemoStructure.builder();

        for (JsonNode valueNode : values.valueStream().toList()) {
            KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
            switch (nodeTag) {
                case KmipTag.Standard.FOO_DEMO_ATTRIBUTE ->
                        builder.attribute(p.getCodec().treeToValue(valueNode, FooDemoAttribute.class));
                case KmipTag.Standard.FOO_DEMO_ENUM ->
                        builder.mode(p.getCodec().treeToValue(valueNode, FooDemoEnum.class));
                default -> throw new IllegalArgumentException();
            }
        }

        FooDemoStructure structure = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!structure.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return structure;
    }
}
```

---

## 3) XML serializer/deserializer

Serializer: `src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/structure/FooDemoStructureXmlSerializer.java`

- Validate spec support.
- Start element for the structure tag, write `attribute`, then `mode` if present, and end element.

Deserializer: `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/structure/FooDemoStructureXmlDeserializer.java`

- Read child nodes, enforce required `attribute`, allow optional `mode`, build via builder.

Code:

```java
// File: src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/structure/FooDemoStructureXmlSerializer.java
package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class FooDemoStructureXmlSerializer extends JsonSerializer<FooDemoStructure> {
    @Override
    public void serialize(FooDemoStructure value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("FooDemoStructure not supported for spec " + spec);
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        List<KmipDataType> values = value.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null) {
                serializers.defaultSerializeField(
                        kmipDataType.getKmipTag().getDescription(),
                        kmipDataType,
                        gen
                );
            }
        }

        xmlGen.writeEndObject();
    }
}
```

```java
// File: src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/structure/FooDemoStructureXmlDeserializer.java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.attribute.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.util.Map;

public class FooDemoStructureXmlDeserializer extends JsonDeserializer<FooDemoStructure> {
    @Override
    public FooDemoStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "Expected XML object for FooDemoStructure");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        FooDemoStructure.FooDemoStructureBuilder builder = FooDemoStructure.builder();

        for (Map.Entry<String, JsonNode> entry : node.propertyStream().toList()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            switch (nodeTag) {
                case KmipTag.Standard.FOO_DEMO_ATTRIBUTE ->
                        builder.attribute(p.getCodec().treeToValue(entry.getValue(), FooDemoAttribute.class));
                case KmipTag.Standard.FOO_DEMO_ENUM ->
                        builder.mode(p.getCodec().treeToValue(entry.getValue(), FooDemoEnum.class));
                default -> throw new IllegalArgumentException();
            }
        }

        FooDemoStructure structure = builder.build();

        if (!structure.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "FooDemoStructure not supported for spec " + spec);
        }
        return structure;
    }
}
```

---

## 4) TTLV serializer/deserializer

Serializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/structure/FooDemoStructureTtlvSerializer.java`

- Validate spec support.
- Write structure with tag `FooDemoStructure`, serialize `attribute`, and conditionally serialize `mode`.

Deserializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/structure/FooDemoStructureTtlvDeserializer.java`

- Expect structure, read children, enforce required `attribute`, allow optional `mode`, then build.

Code:

```java
// File: src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/structure/FooDemoStructureTtlvSerializer.java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class FooDemoStructureTtlvSerializer implements TtlvSerializer<FooDemoStructure> {
    @Override
    public ByteBuffer serialize(FooDemoStructure value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    private TtlvObject serializeToTtlvObject(FooDemoStructure value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        List<KmipDataType> nestedValues = value.getValues();
        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = value.getEncodingType().getTypeValue();

        List<ByteBuffer> nestedObjects = new ArrayList<>();
        for (KmipDataType object : nestedValues) {
            if (object != null) {
                nestedObjects.add(mapper.writeValueAsByteBuffer(object));
            }
        }

        int totalLength = nestedObjects.stream().mapToInt(ByteBuffer::remaining).sum();
        ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
        nestedObjects.forEach(payloadBuffer::put);
        byte[] payload = payloadBuffer.array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
```

```java
// File: src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/structure/FooDemoStructureTtlvDeserializer.java
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.attribute.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class FooDemoStructureTtlvDeserializer implements TtlvDeserializer<FooDemoStructure> {
    EncodingType type = EncodingType.STRUCTURE;

    @Override
    public FooDemoStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        // Optionally validate obj.getType() == type.getTypeValue()

        List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());

        KmipSpec spec = KmipContext.getSpec();
        FooDemoStructure.FooDemoStructureBuilder builder = FooDemoStructure.builder();

        for (TtlvObject ttlvObject : nestedObjects) {
            KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
            switch (nodeTag) {
                case KmipTag.Standard.FOO_DEMO_ATTRIBUTE ->
                        builder.attribute(mapper.readValue(ttlvObject.toByteBuffer(), FooDemoAttribute.class));
                case KmipTag.Standard.FOO_DEMO_ENUM ->
                        builder.mode(mapper.readValue(ttlvObject.toByteBuffer(), FooDemoEnum.class));
                default -> throw new IllegalArgumentException();
            }
        }

        FooDemoStructure structure = builder.build();

        if (!structure.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return structure;
    }
}
```

---

## 5) Module registration

Ensure serializers/deserializers are registered:

- JSON module: add `FooDemoStructure` serializer/deserializer.
- XML module: add `FooDemoStructure` serializer/deserializer.
- TTLV: register with `KmipTtlvModule`/`TtlvMapper`.

Examples:

```java
// JSON
addSerializer(FooDemoStructure.class, new FooDemoStructureJsonSerializer());
addDeserializer(FooDemoStructure.class, new FooDemoStructureJsonDeserializer());

// XML
addSerializer(FooDemoStructure.class, new FooDemoStructureXmlSerializer());
addDeserializer(FooDemoStructure.class, new FooDemoStructureXmlDeserializer());

// TTLV
addSerializer(FooDemoStructure.class, new FooDemoStructureTtlvSerializer());
addDeserializer(FooDemoStructure.class, new FooDemoStructureTtlvDeserializer());
```

---

## 6) Tests

Unit: `FooDemoStructureTest.java`

- Required field enforcement (`attribute` non-null).
- `mode` nullable support.
- `getValues()` ordering: `[attribute, mode?]`.
- `isSupportedFor()` respects tag and children.

JSON: `FooDemoStructureJsonTest.java`

- Round-trip with `attribute` only and with `attribute + mode`.
- Set spec via `KmipContext.setSpec(KmipSpec.V1_2)`.

XML: `FooDemoStructureXmlTest.java`

- Same coverage as JSON; verify element ordering and optionality.

TTLV: `FooDemoStructureTtlvTest.java`

- Round-trip bytes for both cases; validate tag and child TLVs.

Code:

```java
// File: src/test/java/org/purpleBean/kmip/common/structure/FooDemoStructureTest.java
package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.attribute.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FooDemoStructure Tests")
class FooDemoStructureTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create FooDemoStructure with builder")
        void shouldCreateFooDemoStructureWithBuilder() {
            FooDemoAttribute attribute = KmipTestDataFactory.createFooDemoAttribute();
            FooDemoEnum mode = KmipTestDataFactory.createFooDemoEnum();

            FooDemoStructure structure = FooDemoStructure.builder()
                    .attribute(attribute)
                    .mode(mode)
                    .build();

            assertThat(structure.getAttribute()).isEqualTo(attribute);
            assertThat(structure.getMode()).isEqualTo(mode);
            assertThat(structure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
        }

        @Test
        @DisplayName("Should reject null attribute")
        void shouldRejectNullAttribute() {
            FooDemoEnum mode = KmipTestDataFactory.createFooDemoEnum();

            assertThatThrownBy(() -> FooDemoStructure.builder().attribute(null).mode(mode).build())
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("attribute is marked non-null but is null");
        }

        @Test
        @DisplayName("Should accept null mode")
        void shouldAcceptNullMode() {
            FooDemoAttribute attribute = KmipTestDataFactory.createFooDemoAttribute();

            FooDemoStructure structure = FooDemoStructure.builder()
                    .attribute(attribute)
                    .mode(null)
                    .build();

            assertNotNull(structure);
            assertEquals(attribute, structure.getAttribute());
            assertNull(structure.getMode());
        }

        @Test
        @DisplayName("UnsupportedVersion context: FooDemoStructure JSON serialization should fail")
        void unsupportedVersion_jsonSerializationShouldFail() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(
                            () -> jsonMapper.writeValueAsString(KmipTestDataFactory.createFooDemoStructure()))
                            .isInstanceOf(Exception.class));
        }

        @Test
        @DisplayName("UnsupportedVersion context: FooDemoStructure XML serialization should fail")
        void unsupportedVersion_xmlSerializationShouldFail() {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(
                            () -> xmlMapper.writeValueAsString(KmipTestDataFactory.createFooDemoStructure()))
                            .isInstanceOf(Exception.class));
        }
    }

    @Nested
    @DisplayName("KMIP Structure Properties")
    class KmipStructureProperties {

        @Test
        @DisplayName("Should have correct encoding type")
        void shouldHaveCorrectEncodingType() {
            FooDemoStructure structure = KmipTestDataFactory.createFooDemoStructure();
            assertThat(structure.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
        }

        @Test
        @DisplayName("Should support KMIP specification")
        void shouldSupportKmipSpecification() {
            FooDemoStructure structure = KmipTestDataFactory.createFooDemoStructure();
            assertThat(structure.isSupportedFor(defaultSpec)).isTrue();
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {

        @Test
        @DisplayName("Should serialize and deserialize JSON correctly")
        void shouldSerializeAndDeserializeJsonCorrectly() {
            FooDemoStructure original = KmipTestDataFactory.createFooDemoStructure();
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, FooDemoStructure.class);
        }

        @Test
        @DisplayName("Should serialize and deserialize XML correctly")
        void shouldSerializeAndDeserializeXmlCorrectly() {
            FooDemoStructure original = KmipTestDataFactory.createFooDemoStructure();
            SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, FooDemoStructure.class);
        }
    }
}
```

```java
// File: src/test/java/org/purpleBean/kmip/codec/json/common/structure/FooDemoStructureJsonTest.java
package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.structure.FooDemoStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FooDemoStructure JSON Tests")
class FooDemoStructureJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize FooDemoStructure")
    void roundTrip() {
        FooDemoStructure original = KmipTestDataFactory.createFooDemoStructure();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, FooDemoStructure.class);
    }

    @Test
    @DisplayName("Structure: expected JSON fields present for FooDemoStructure")
    void structure_expectFields() {
        FooDemoStructure structure = KmipTestDataFactory.createFooDemoStructure();
        SerializationTestUtils.testJsonSerialization(
                jsonMapper,
                structure,
                json -> {
                    SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    assertThat(json).contains("\"FooDemoStructure\"");
                });
    }

    @Test
    @DisplayName("UnsupportedVersion context: FooDemoStructure JSON serialization should fail")
    void unsupportedVersion_jsonSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> jsonMapper.writeValueAsString(KmipTestDataFactory.createFooDemoStructure()))
                        .isInstanceOf(Exception.class));
    }
}
```

```java
// File: src/test/java/org/purpleBean/kmip/codec/xml/common/structure/FooDemoStructureXmlTest.java
package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.structure.FooDemoStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FooDemoStructure XML Tests")
class FooDemoStructureXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize FooDemoStructure")
    void roundTrip() {
        FooDemoStructure original = KmipTestDataFactory.createFooDemoStructure();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, FooDemoStructure.class);
    }

    @Test
    @DisplayName("Structure: expected XML fields present for FooDemoStructure")
    void structure_expectFields() {
        FooDemoStructure structure = KmipTestDataFactory.createFooDemoStructure();
        SerializationTestUtils.testXmlSerialization(
                xmlMapper,
                structure,
                xml -> {
                    assertThat(xml).contains("<FooDemoStructure>");
                }
        );
    }

    @Test
    @DisplayName("UnsupportedVersion context: FooDemoStructure XML serialization should fail")
    void unsupportedVersion_xmlSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> xmlMapper.writeValueAsString(KmipTestDataFactory.createFooDemoStructure()))
                        .isInstanceOf(Exception.class));
    }
}
```

```java
// File: src/test/java/org/purpleBean/kmip/codec/ttlv/common/structure/FooDemoStructureTtlvTest.java
package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.structure.FooDemoStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

@DisplayName("FooDemoStructure TTLV Tests")
class FooDemoStructureTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Round-trip: FooDemoStructure TTLV")
    void roundTrip_fooDemoStructure() throws IOException {
        FooDemoStructure original = KmipTestDataFactory.createFooDemoStructure();
        ByteBuffer buf = ttlvMapper.writeValueAsByteBuffer(original);
        FooDemoStructure restored = ttlvMapper.readValue(buf, FooDemoStructure.class);
        org.assertj.core.api.Assertions.assertThat(restored).isEqualTo(original);
    }

    @Test
    @DisplayName("UnsupportedVersion context: FooDemoStructure TTLV serialization should fail")
    void unsupportedVersion_ttlvSerializationShouldFail() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> org.assertj.core.api.Assertions.assertThatThrownBy(
                                () -> ttlvMapper.writeValueAsByteBuffer(KmipTestDataFactory.createFooDemoStructure()))
                        .isInstanceOf(Exception.class));
    }
}
```

---

## 7) Example usage

```java
KmipContext.setSpec(KmipSpec.V1_2);

FooDemoAttribute attribute = FooDemoAttribute.builder()
        // set required values
        .build();

FooDemoEnum mode = null; // optional
// mode = new FooDemoEnum(FooDemoEnum.Standard.SOME_VALUE);

FooDemoStructure structure = FooDemoStructure.builder()
        .attribute(attribute)
        .mode(mode)
        .build();

// JSON
com.fasterxml.jackson.databind.ObjectMapper json = new com.fasterxml.jackson.databind.ObjectMapper();
json.findAndRegisterModules();
json.registerModule(new org.purpleBean.kmip.codec.json.KmipJsonModule());
String jsonText = json.writeValueAsString(structure);
FooDemoStructure fromJson = json.readValue(jsonText, FooDemoStructure.class);

// XML
com.fasterxml.jackson.dataformat.xml.XmlMapper xml = new com.fasterxml.jackson.dataformat.xml.XmlMapper();
xml.findAndRegisterModules();
xml.registerModule(new org.purpleBean.kmip.codec.xml.KmipXmlModule());
String xmlText = xml.writeValueAsString(structure);
FooDemoStructure fromXml = xml.readValue(xmlText, FooDemoStructure.class);

// TTLV
org.purpleBean.kmip.codec.ttlv.TtlvMapper ttlv = new org.purpleBean.kmip.codec.ttlv.TtlvMapper();
ttlv.registerModule(new org.purpleBean.kmip.codec.ttlv.KmipTtlvModule());
byte[] bytes = ttlv.writeValueAsBytes(structure);
FooDemoStructure fromTtlv = ttlv.readValue(bytes, FooDemoStructure.class);

KmipContext.clear();
```

---

## 8) Checklist

- Implementation
  - Class with tag registration
  - `attribute` required; `mode` optional
  - `getValues()` ordered `[attribute, mode?]`
  - `isSupportedFor()` checks tag and children
  - Builder enforces non-null and spec compatibility

- Serialization
  - JSON/XML/TTLV serializers/deserializers created and registered

- Tests
  - Unit + round-trip tests across JSON/XML/TTLV

- Documentation
  - This guide is up to date
