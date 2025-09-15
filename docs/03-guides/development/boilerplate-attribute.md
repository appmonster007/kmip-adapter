# KMIP Attribute Boilerplate

This guide explains, step by step, how to add a new KMIP Attribute. As a concrete example, we will build `FooDemoAttribute` that contains exactly one required field:

- name: `dateTime`
- type: `java.time.OffsetDateTime`

Follow this blueprint to create your new attribute with minimal surprises.

Table of contents
1. Core attribute implementation
2. JSON codec (serializer/deserializer)
3. XML codec (serializer/deserializer)
4. TTLV codec (serializer/deserializer)
5. Tests (core, JSON, XML, TTLV, edge cases)
6. Registration and wiring notes
7. Gotchas and checklist

## 1. Core attribute implementation

Create a new class alongside the other common attributes.

File: `src/main/java/org/purpleBean/kmip/common/FooDemoAttribute.java`

```java
package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class FooDemoAttribute implements KmipAttribute {

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ATTRIBUTE);
    private final EncodingType encodingType = EncodingType.DATE_TIME;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    @NonNull
    private final OffsetDateTime dateTime;

    @Override
    public boolean isClientModifiable(@NonNull State state) {
        // Only PRE_ACTIVE is modifiable
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        // Only PRE_ACTIVE is modifiable
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooDemoAttribute that = (FooDemoAttribute) o;
        // Compare OffsetDateTime up to seconds
        return this.dateTime.withNano(0).equals(that.dateTime.withNano(0));
    }

    @Override
    public int hashCode() {
        // Use only up to seconds for hash code
        return Objects.hash(dateTime.withNano(0));
    }
}
```

Notes
- `@NonNull` on `dateTime` makes it required via Lombok builder.
- PRE_ACTIVE-only mutability for client and server.
- Equality and hashing truncate to seconds to ignore nanos.

## 2. JSON codec

Serializer file: `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/FooDemoAttributeJsonSerializer.java`

```java
package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.FooDemoAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FooDemoAttributeJsonSerializer extends KmipDataTypeJsonSerializer<FooDemoAttribute> {

    @Override
    public void serialize(FooDemoAttribute value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeStringField("value", value.getDateTime().toString());
        gen.writeEndObject();
    }
}
```

Deserializer file: `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/FooDemoAttributeJsonDeserializer.java`

```java
package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.FooDemoAttribute;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class FooDemoAttributeJsonDeserializer extends KmipDataTypeJsonDeserializer<FooDemoAttribute> {

    @Override
    public FooDemoAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();

        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();

        if (!node.isObject() || tag != KmipTag.Standard.FOO_DEMO_ATTRIBUTE) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Expected object for FooDemoAttribute");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Missing or non-text 'value' for FooDemoAttribute");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }

        return attribute;
    }
}
```

## 3. XML codec

Serializer file: `src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/FooDemoAttributeXmlSerializer.java`

```java
package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.FooDemoAttribute;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FooDemoAttributeXmlSerializer extends JsonSerializer<FooDemoAttribute> {

    @Override
    public void serialize(FooDemoAttribute value, JsonGenerator gen, SerializerProvider provider) throws IOException {
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
        xmlGen.writeStringField("value", value.getDateTime().toString());
        xmlGen.writeEndObject();
    }
}
```

Deserializer file: `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/FooDemoAttributeXmlDeserializer.java`

```java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.State;

import java.io.IOException;
import java.time.OffsetDateTime;

public class FooDemoAttributeXmlDeserializer extends JsonDeserializer<FooDemoAttribute> {

    @Override
    public FooDemoAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Expected object for FooDemoAttribute");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !EncodingType.DATE_TIME.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(State.class, "Missing or invalid '@type' attribute for FooDemoAttribute");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Missing or non-text 'value' for FooDemoAttribute");
            return null;
        }

        return FooDemoAttribute.builder().dateTime(OffsetDateTime.parse(valueNode.asText())).build();
    }
}
```

## 4. TTLV codec

Serializer file: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/FooDemoAttributeTtlvSerializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.FooDemoAttribute;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class FooDemoAttributeTtlvSerializer implements TtlvSerializer<FooDemoAttribute> {
    @Override
    public ByteBuffer serialize(FooDemoAttribute value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(FooDemoAttribute value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException();
        }

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = EncodingType.DATE_TIME.getTypeValue();
        byte[] payload = mapper.writeValueAsByteBuffer(value.getDateTime()).array();

        return TtlvObject.builder()
                .tag(tag)
                .type(type)
                .value(payload)
                .build();
    }
}
```

Deserializer file: `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/FooDemoAttributeTtlvDeserializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.FooDemoAttribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class FooDemoAttributeTtlvDeserializer implements TtlvDeserializer<FooDemoAttribute> {
    EncodingType type = EncodingType.DATE_TIME;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ATTRIBUTE);

    @Override
    public FooDemoAttribute deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes())
                && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }
        ByteBuffer bb = ByteBuffer.wrap(obj.getValue()).order(TtlvConstants.BYTE_ORDER);
        OffsetDateTime dt = mapper.readValue(bb, OffsetDateTime.class);

        KmipSpec spec = KmipContext.getSpec();
        FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(dt).build();

        if (!attribute.isSupportedFor(spec)) {
            throw new NoSuchElementException();
        }
        return attribute;
    }
}
```

## 5. Tests

The following tests provide canonical coverage for an attribute with a single required `OffsetDateTime` field. Update names/packages for `FooDemoAttribute` and keep assertions aligned.

Core tests: `src/test/java/org/purpleBean/kmip/common/FooDemoAttributeTest.java`

```java
package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.*;

@DisplayName("FooDemoAttribute Tests")
class FooDemoAttributeTest extends BaseKmipTest {

    @Nested
    @DisplayName("Construction and Basic Properties")
    class ConstructionAndBasicProperties {

        @Test
        @DisplayName("Should create FooDemoAttribute with builder")
        void shouldCreateWithBuilder() {
            OffsetDateTime dateTime = OffsetDateTime.now();
            FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(dateTime).build();
            assertThat(attribute.getDateTime()).isEqualTo(dateTime);
            assertThat(attribute.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.FOO_DEMO_ATTRIBUTE);
            assertThat(attribute.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        }

        @Test
        @DisplayName("Should handle various date formats")
        void shouldHandleVariousDateFormats() {
            OffsetDateTime[] testDates = {
                    OffsetDateTime.now(),
                    OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC),
                    OffsetDateTime.of(2023, 12, 31, 23, 59, 59, 999_999_999, ZoneOffset.of("+05:30")),
                    KmipTestDataFactory.BoundaryData.epochDateTime()
            };
            for (OffsetDateTime dateTime : testDates) {
                FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(dateTime).build();
                assertThat(attribute.getDateTime()).isEqualTo(dateTime);
            }
        }

        @Test
        @DisplayName("Should handle null dateTime")
        void shouldHandleNullDateTime() {
            assertThatThrownBy(() -> FooDemoAttribute.builder().dateTime(null).build())
                    .isInstanceOf(NullPointerException.class)
                    .hasMessageContaining("dateTime is marked non-null but is null");
        }
    }

    @Nested
    @DisplayName("KMIP Structure Properties")
    class KmipStructureProperties {

        @Test
        @DisplayName("Should have correct KMIP tag and encoding type")
        void shouldHaveCorrectKmipTag() {
            FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build();
            assertThat(attribute.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.FOO_DEMO_ATTRIBUTE);
            assertThat(attribute.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
            assertThat(attribute.isSupportedFor(defaultSpec)).isTrue();
        }
    }

    @Nested
    @DisplayName("Equality and Hash Code")
    class EqualityAndHashCode {

        @Test
        @DisplayName("Should be equal when dateTime matches (second precision)")
        void shouldBeEqualWhenDateTimeMatches() {
            OffsetDateTime dateTime = OffsetDateTime.now();
            FooDemoAttribute a = FooDemoAttribute.builder().dateTime(dateTime).build();
            FooDemoAttribute b = FooDemoAttribute.builder().dateTime(dateTime).build();
            assertThat(a).isEqualTo(b);
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }
    }
}
```

JSON tests: `src/test/java/org/purpleBean/kmip/codec/json/common/FooDemoAttributeJsonTest.java`

```java
package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("FooDemoAttribute JSON Tests")
class FooDemoAttributeJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: FooDemoAttribute JSON")
    void roundTrip() {
        FooDemoAttribute original = FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, FooDemoAttribute.class);
    }

    @Test
    @DisplayName("Round-trip: various date inputs")
    void roundTrip_variousDates() {
        List<FooDemoAttribute> dates = List.of(
                FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build(),
                FooDemoAttribute.builder().dateTime(OffsetDateTime.now()).build(),
                FooDemoAttribute.builder().dateTime(KmipTestDataFactory.createRandomActivationDateAttribute().getDateTime()).build()
        );
        for (FooDemoAttribute d : dates) {
            SerializationTestUtils.performJsonRoundTrip(jsonMapper, d, FooDemoAttribute.class);
        }
    }

    @Test
    @DisplayName("Structure: expected JSON fields present")
    void structure_expectFields() {
        FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build();
        SerializationTestUtils.testJsonSerialization(
                jsonMapper,
                attribute,
                json -> {
                    SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    assertThat(json).contains("\"FooDemoAttribute\"");
                }
        );
    }

    @Test
    @DisplayName("UnsupportedVersion context: JSON serialization should fail")
    void unsupportedVersion_jsonSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> jsonMapper.writeValueAsString(FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build()))
                        .isInstanceOf(Exception.class));
    }
}
```

XML tests: `src/test/java/org/purpleBean/kmip/codec/xml/common/FooDemoAttributeXmlTest.java`

```java
package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("FooDemoAttribute XML Tests")
class FooDemoAttributeXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: FooDemoAttribute XML")
    void roundTrip() {
        FooDemoAttribute original = FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, FooDemoAttribute.class);
    }

    @Test
    @DisplayName("Structure: expected XML fields present")
    void structure_expectFields() {
        FooDemoAttribute attribute = FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build();
        SerializationTestUtils.testXmlSerialization(
                xmlMapper,
                attribute,
                xml -> {
                    assertThat(xml).contains("<FooDemoAttribute");
                    assertThat(xml).contains("type=\"DateTime\"");
                }
        );
    }

    @Test
    @DisplayName("UnsupportedVersion context: XML serialization should fail")
    void unsupportedVersion_xmlSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> xmlMapper.writeValueAsString(FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build()))
                        .isInstanceOf(Exception.class));
    }
}
```

TTLV tests: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/FooDemoAttributeTtlvTest.java`

```java
package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("FooDemoAttribute TTLV Tests")
class FooDemoAttributeTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = buildMapper();

    private TtlvMapper buildMapper() {
        TtlvMapper mapper = new TtlvMapper();
        mapper.registerModule(new KmipTtlvModule());
        return mapper;
    }

    @Test
    @DisplayName("Round-trip: FooDemoAttribute TTLV")
    void roundTrip() {
        FooDemoAttribute original = FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build();
        assertRoundTrip(original);
    }

    @Test
    @DisplayName("UnsupportedVersion context: TTLV serialization should fail")
    void unsupportedVersion_ttlvSerializationFails() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> ttlvMapper.writeValueAsByteBuffer(FooDemoAttribute.builder().dateTime(KmipTestDataFactory.BoundaryData.epochDateTime()).build()))
                        .isInstanceOf(Exception.class));
    }

    private void assertRoundTrip(FooDemoAttribute original) {
        ByteBuffer buffer;
        try {
            buffer = ttlvMapper.writeValueAsByteBuffer(original);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize to TTLV", e);
        }
        FooDemoAttribute deserialized;
        try {
            deserialized = ttlvMapper.readValue(buffer, FooDemoAttribute.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize from TTLV", e);
        }
        assertThat(deserialized.getDateTime().toInstant().truncatedTo(ChronoUnit.SECONDS))
                .isEqualTo(original.getDateTime().toInstant().truncatedTo(ChronoUnit.SECONDS));
    }
}
```

## 6. Registration and wiring notes

- Ensure your JSON, XML and TTLV modules register the new serializers/deserializers (check how `KmipJsonModule`, `KmipXmlModule`, and `KmipTtlvModule` handle attribute modules in the codebase).
- Add the new `KmipTag.Standard.FOO_DEMO_ATTRIBUTE` entry in `KmipTag` with a unique tag value and human-readable description (e.g., `"FooDemoAttribute"`). This is required by all codecs and tests above.
- Keep package structure parallel to existing attribute files so auto-discovery and test utilities continue to work.

### 6.1 JSON module registration

File: `src/main/java/org/purpleBean/kmip/codec/json/KmipJsonModule.java`

Add imports near the top:

```java
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.codec.json.serializer.kmip.common.FooDemoAttributeJsonSerializer;
import org.purpleBean.kmip.codec.json.deserializer.kmip.common.FooDemoAttributeJsonDeserializer;
```

Then register inside the constructor:

```java
addSerializer(FooDemoAttribute.class, new FooDemoAttributeJsonSerializer());
addDeserializer(FooDemoAttribute.class, new FooDemoAttributeJsonDeserializer());
```

### 6.2 XML module registration

File: `src/main/java/org/purpleBean/kmip/codec/xml/KmipXmlModule.java`

Add imports near the top:

```java
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.codec.xml.serializer.kmip.common.FooDemoAttributeXmlSerializer;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.common.FooDemoAttributeXmlDeserializer;
```

Then register inside the constructor:

```java
addSerializer(FooDemoAttribute.class, new FooDemoAttributeXmlSerializer());
addDeserializer(FooDemoAttribute.class, new FooDemoAttributeXmlDeserializer());
```

### 6.3 TTLV module registration

File: `src/main/java/org/purpleBean/kmip/codec/ttlv/KmipTtlvModule.java`

Add imports near the top:

```java
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.FooDemoAttributeTtlvSerializer;
import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.FooDemoAttributeTtlvDeserializer;
```

Then register inside the constructor:

```java
addSerializer(FooDemoAttribute.class, new FooDemoAttributeTtlvSerializer());
addDeserializer(FooDemoAttribute.class, new FooDemoAttributeTtlvDeserializer());
```

## 7. Gotchas and checklist

- Field required: `dateTime` must be `@NonNull` and validated by Lombok builder.
- Equality/HashCode: truncate to seconds to avoid flakes.
- JSON shape: object with `tag`, `type`, `value` fields; not a bare string.
- XML shape: element name from `KmipTag` description; `type` and `value` as attributes.
- TTLV: use `EncodingType.DATE_TIME` and delegate `OffsetDateTime` payload to mapper.
- Spec compatibility: guard in every serializer and TTLV serializer using `KmipContext.getSpec()`.
- Unsupported version behavior: throw as in the references and add failing tests in UnsupportedVersion context.
- Tests: coverage for construction, equality, codec round-trips, structure checks, and version guards.

By following this blueprint pattern exactly, `FooDemoAttribute` will integrate cleanly across codecs with predictable behavior.
