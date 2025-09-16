# KMIP Structure Boilerplate (Authoritative)

This guide is the authoritative, copy-ready blueprint for creating a new KMIP structure called `FooDemoStructure`, modeled on the production `SampleStructure` and its serializers/deserializers/tests.

It covers:
- Core implementation (main/java)
- JSON/XML/TTLV serializers and deserializers
- Unit and codec tests using the reusable test suites
- Optional performance tests (JMH)
- Import style and legacy references to avoid

Use this as your single source of truth.

---

## 1) Core Structure Implementation

File: `src/main/java/org/purpleBean/kmip/common/structure/FooDemoStructure.java`

```java
package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
public class FooDemoStructure implements KmipStructure {

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_STRUCTURE); // add this entry
    private final EncodingType encodingType = EncodingType.STRUCTURE;
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    @NonNull
    private final FooDemoAttribute attribute; // required
    private final FooDemoEnum mode;           // optional

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        if (!supportedVersions.contains(spec)) return false;
        if (!attribute.isSupportedFor(spec)) return false;
        if (mode != null && !mode.isSupportedFor(spec)) return false;
        return true;
    }

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> list = new ArrayList<>();
        list.add(attribute);
        if (mode != null) list.add(mode);
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FooDemoStructure that)) return false;
        return Objects.equals(attribute, that.attribute) && Objects.equals(mode, that.mode);
    }

    @Override
    public int hashCode() { return Objects.hash(attribute, mode); }
}
```

Notes
- Add `FOO_DEMO_STRUCTURE` to `KmipTag.Standard` with a unique value/description.
- `getValues()` order is deterministic: required first, then optional.
- `isSupportedFor` aggregates child support and local tag support.

---

## 2) JSON Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/structure/FooDemoStructureJsonSerializer.java`

```java
package org.purpleBean.kmip.codec.json.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FooDemoStructureJsonSerializer extends StdSerializer<FooDemoStructure> {
    public FooDemoStructureJsonSerializer() { super(FooDemoStructure.class); }

    @Override
    public void serialize(FooDemoStructure value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) throw new UnsupportedEncodingException();

        // Follow SampleStructure: write tag object, type, then value array with children
        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeFieldName("value");
        gen.writeStartArray();
        for (KmipDataType child : value.getValues()) {
            if (child != null) gen.writeObject(child);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
```

Deserializer: `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/structure/FooDemoStructureJsonDeserializer.java`

```java
package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.util.NoSuchElementException;

public class FooDemoStructureJsonDeserializer extends StdDeserializer<FooDemoStructure> {

    public FooDemoStructureJsonDeserializer() { super(FooDemoStructure.class); }

    @Override
    public FooDemoStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        KmipTag.Value tag = p.getCodec().treeToValue(node, KmipTag.class).getValue();
        if (!node.isObject() || tag != KmipTag.Standard.FOO_DEMO_STRUCTURE) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "Expected object for FooDemoStructure");
            return null;
        }

        JsonNode values = node.get("value");
        if (values == null || !values.isArray() || values.isEmpty()) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "FooDemoStructure 'value' must be an array");
            return null;
        }

        var builder = FooDemoStructure.builder();
        for (JsonNode child : values) {
            KmipTag.Value childTag = p.getCodec().treeToValue(child, KmipTag.class).getValue();
            switch (childTag) {
                case KmipTag.Standard.FOO_DEMO_ATTRIBUTE -> builder.attribute(p.getCodec().treeToValue(child, FooDemoAttribute.class));
                case KmipTag.Standard.FOO_DEMO_ENUM -> builder.mode(p.getCodec().treeToValue(child, FooDemoEnum.class));
                default -> throw new IllegalArgumentException();
            }
        }

        FooDemoStructure result = builder.build();
        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupportedFor(spec)) throw new NoSuchElementException();
        return result;
    }
}
```

---

## 3) XML Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/structure/FooDemoStructureXmlSerializer.java`

```java
package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FooDemoStructureXmlSerializer extends JsonSerializer<FooDemoStructure> {
    @Override
    public void serialize(FooDemoStructure value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) throw new UnsupportedEncodingException();
        if (!(gen instanceof ToXmlGenerator xmlGen)) throw new IllegalStateException("Expected ToXmlGenerator");

        String elementName = value.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(value);

        // Children as nested elements using provider to default serialize each child
        for (KmipDataType kmipDataType : value.getValues()) {
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

Deserializer: `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/structure/FooDemoStructureXmlDeserializer.java`

```java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.util.NoSuchElementException;

public class FooDemoStructureXmlDeserializer extends JsonDeserializer<FooDemoStructure> {
    @Override
    public FooDemoStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "Expected XML element object for FooDemoStructure");
            return null;
        }
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() || !EncodingType.STRUCTURE.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(FooDemoStructure.class, "Missing or invalid '@type' attribute for FooDemoStructure");
            return null;
        }
        FooDemoAttribute attribute = p.getCodec().treeToValue(node.get("attribute"), FooDemoAttribute.class);
        FooDemoEnum mode = node.has("mode") && !node.get("mode").isNull()
                ? p.getCodec().treeToValue(node.get("mode"), FooDemoEnum.class)
                : null;

        FooDemoStructure result = FooDemoStructure.builder().attribute(attribute).mode(mode).build();
        KmipSpec spec = KmipContext.getSpec();
        if (!result.isSupportedFor(spec)) throw new NoSuchElementException();
        return result;
    }
}
```

---

## 4) TTLV Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/structure/FooDemoStructureTtlvSerializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class FooDemoStructureTtlvSerializer implements TtlvSerializer<FooDemoStructure> {
    @Override
    public ByteBuffer serialize(FooDemoStructure value, TtlvMapper mapper) throws IOException {
        return serializeToTtlvObject(value, mapper).toByteBuffer();
    }

    public TtlvObject serializeToTtlvObject(FooDemoStructure value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) throw new UnsupportedEncodingException();

        byte[] tag = value.getKmipTag().getTagBytes();
        byte type = EncodingType.STRUCTURE.getTypeValue();

        byte[] c1 = mapper.writeValueAsByteBuffer(value.getAttribute()).array();
        byte[] c2 = value.getMode() != null ? mapper.writeValueAsByteBuffer(value.getMode()).array() : new byte[0];

        byte[] payload = ByteBuffer.allocate(c1.length + c2.length)
                .put(c1)
                .put(c2)
                .array();

        return TtlvObject.builder().tag(tag).type(type).value(payload).build();
    }
}
```

Deserializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/structure/FooDemoStructureTtlvDeserializer.java`

```java
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.structure;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class FooDemoStructureTtlvDeserializer implements TtlvDeserializer<FooDemoStructure> {
    EncodingType type = EncodingType.STRUCTURE;
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_STRUCTURE);

    @Override
    public FooDemoStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        if (Arrays.equals(obj.getTag(), kmipTag.getTagBytes()) && obj.getType() != type.getTypeValue()) {
            throw new IllegalArgumentException(String.format("Expected %s type for %s", type.getTypeValue(), kmipTag.getDescription()));
        }

        List<TtlvObject> nested = TtlvObject.fromBytesMultiple(obj.getValue());
        var builder = FooDemoStructure.builder();
        KmipSpec spec = KmipContext.getSpec();
        for (TtlvObject child : nested) {
            KmipTag.Value childTag = KmipTag.fromBytes(spec, child.getTag());
            switch (childTag) {
                case KmipTag.Standard.FOO_DEMO_ATTRIBUTE -> builder.attribute(mapper.readValue(child.toByteBuffer(), FooDemoAttribute.class));
                case KmipTag.Standard.FOO_DEMO_ENUM -> builder.mode(mapper.readValue(child.toByteBuffer(), FooDemoEnum.class));
                default -> throw new IllegalArgumentException();
            }
        }

        FooDemoStructure result = builder.build();
        if (!result.isSupportedFor(spec)) throw new NoSuchElementException();
        return result;
    }
}
```

---

## 5) Unit Tests

### Domain (reusable suite)

File: `src/test/java/org/purpleBean/kmip/common/structure/FooDemoStructureTest.java`

```java
package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("FooDemoStructure Domain Tests")
class FooDemoStructureTest extends AbstractKmipStructureSuite<FooDemoStructure> {
    @Override protected Class<FooDemoStructure> type() { return FooDemoStructure.class; }

    @Override protected FooDemoStructure createDefault() {
        var attr = FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
        var mode = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE);
        return FooDemoStructure.builder().attribute(attr).mode(mode).build();
    }

    @Override protected EncodingType expectedEncodingType() { return EncodingType.STRUCTURE; }
    @Override protected int expectedMinComponentCount() { return 1; }

    @Override protected void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
        if (values.size() > 1) assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
    }
}
```

---

## 6) Codec Tests

Place these under codec packages and extend abstract suites.

- JSON: `src/test/java/org/purpleBean/kmip/codec/json/common/structure/FooDemoStructureJsonTest.java`
- XML: `src/test/java/org/purpleBean/kmip/codec/xml/common/structure/FooDemoStructureXmlTest.java`
- TTLV: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/structure/FooDemoStructureTtlvTest.java`

```java
// JSON
package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.structure.FooDemoStructure;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoStructure JSON Serialization")
class FooDemoStructureJsonTest extends AbstractJsonSerializationSuite<FooDemoStructure> {
    @Override protected Class<FooDemoStructure> type() { return FooDemoStructure.class; }
    @Override protected FooDemoStructure createDefault() {
        var attr = FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
        var mode = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE);
        return FooDemoStructure.builder().attribute(attr).mode(mode).build();
    }
}
```

```java
// XML
package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.structure.FooDemoStructure;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoStructure XML Serialization")
class FooDemoStructureXmlTest extends AbstractXmlSerializationSuite<FooDemoStructure> {
    @Override protected Class<FooDemoStructure> type() { return FooDemoStructure.class; }
    @Override protected FooDemoStructure createDefault() {
        var attr = FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
        var mode = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE);
        return FooDemoStructure.builder().attribute(attr).mode(mode).build();
    }
}
```

```java
// TTLV
package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.structure.FooDemoStructure;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoStructure TTLV Serialization")
class FooDemoStructureTtlvTest extends AbstractTtlvSerializationSuite<FooDemoStructure> {
    @Override protected Class<FooDemoStructure> type() { return FooDemoStructure.class; }
    @Override protected FooDemoStructure createDefault() {
        var attr = FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
        var mode = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE);
        return FooDemoStructure.builder().attribute(attr).mode(mode).build();
    }
}
```

---

## 7) Performance Benchmarks (JMH)

Authoritative reference: `SampleStructureBenchmarkSubject`. Below is a complete boilerplate for `FooDemoStructure`.

Subject: `src/test/java/org/purpleBean/kmip/benchmark/subjects/FooDemoStructureBenchmarkSubject.java`

```java
package org.purpleBean.kmip.benchmark.subjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.codec.json.KmipJsonModule;
import org.purpleBean.kmip.codec.ttlv.KmipTtlvModule;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.xml.KmipXmlModule;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.common.enumeration.FooDemoEnum;
import org.purpleBean.kmip.common.structure.FooDemoStructure;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class FooDemoStructureBenchmarkSubject implements KmipBenchmarkSubject {
    private ObjectMapper json; private XmlMapper xml; private TtlvMapper ttlv;
    private FooDemoStructure obj; private String jsonStr; private String xmlStr; private ByteBuffer ttlvBuf;

    @Override public String name() { return "FooDemoStructure"; }

    @Override public void setup() throws Exception {
        KmipContext.setSpec(KmipSpec.V1_2);
        json = new ObjectMapper(); json.findAndRegisterModules(); json.registerModule(new JavaTimeModule()); json.registerModule(new KmipJsonModule());
        xml = new XmlMapper(); xml.findAndRegisterModules(); xml.registerModule(new JavaTimeModule()); xml.registerModule(new KmipXmlModule());
        ttlv = new TtlvMapper(); ttlv.registerModule(new KmipTtlvModule());

        var attr = FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
        var mode = new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE);
        obj = FooDemoStructure.builder().attribute(attr).mode(mode).build();

        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override public void tearDown() { KmipContext.clear(); }
    @Override public String jsonSerialize() throws Exception { return json.writeValueAsString(obj); }
    @Override public Object jsonDeserialize() throws Exception { return json.readValue(jsonStr, FooDemoStructure.class); }
    @Override public String xmlSerialize() throws Exception { return xml.writeValueAsString(obj); }
    @Override public Object xmlDeserialize() throws Exception { return xml.readValue(xmlStr, FooDemoStructure.class); }
    @Override public ByteBuffer ttlvSerialize() throws Exception { return ttlv.writeValueAsByteBuffer(obj); }
    @Override public Object ttlvDeserialize() throws Exception { return ttlv.readValue(ttlvBuf.duplicate(), FooDemoStructure.class); }
}
```

ServiceLoader registration:

```
org.purpleBean.kmip.benchmark.subjects.FooDemoStructureBenchmarkSubject
```

Runner (same as attribute/enum guides):

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

- Prefer imports, not FQNs, in tests and examples.
- Avoid JsonCodec/XmlCodec/TtlvCodec (do not exist in this repo).
- For tag registry and lookup tests (non-structure), see `src/test/java/org/purpleBean/kmip/KmipTagTest.java`.
