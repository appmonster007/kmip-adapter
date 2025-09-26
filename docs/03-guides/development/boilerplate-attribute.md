# KMIP Attribute Boilerplate (Authoritative)

This guide is the authoritative, copy-ready blueprint for creating a new KMIP Attribute called `FooDemoAttribute`, modeled on `ActivationDateAttribute`. It has a single required field:

- name: `dateTime`
- type: `java.time.OffsetDateTime`

It covers:
- Core implementation (main/java)
- JSON/XML/TTLV serializers and deserializers
- Unit and codec tests using the reusable test suites
- Optional performance tests (JMH)
- Import style and legacy references to avoid

Use this as your single source of truth.

---

## 1) Core Attribute Implementation

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

    // Use a real Standard entry you add in KmipTag.Standard
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DEMO_ATTRIBUTE);
    private final EncodingType encodingType = EncodingType.DATE_TIME;

    // Template supported versions — adjust as needed
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    // Capability flags — mirror ActivationDate semantics
    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    @NonNull
    private final OffsetDateTime dateTime;

    @Override
    public boolean isClientModifiable(@NonNull State state) {
        // PRE_ACTIVE is modifiable
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        // PRE_ACTIVE is modifiable
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
        return Objects.hash(dateTime.withNano(0));
    }
}
```

Notes
- Add `FOO_DEMO_ATTRIBUTE` to `KmipTag.Standard` with a unique value/description.
- Equality and hashing truncate to seconds to avoid flakiness.
- Adjust supported versions to your needs; keep `UnknownVersion` if appropriate.

---

## 2) JSON Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/FooDemoAttributeJsonSerializer.java`

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

Deserializer: `src/main/java/org/purpleBean/kmip/codec/json/deserializer/kmip/common/FooDemoAttributeJsonDeserializer.java`

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

---

## 3) XML Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/xml/serializer/kmip/common/FooDemoAttributeXmlSerializer.java`

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

Deserializer: `src/main/java/org/purpleBean/kmip/codec/xml/deserializer/kmip/common/FooDemoAttributeXmlDeserializer.java`

```java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.FooDemoAttribute;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

public class FooDemoAttributeXmlDeserializer extends JsonDeserializer<FooDemoAttribute> {
    @Override
    public FooDemoAttribute deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Expected XML element object for FooDemoAttribute");
            return null;
        }
        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Missing or invalid '@type' attribute for FooDemoAttribute");
            return null;
        }
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FooDemoAttribute.class, "Missing or non-text '@value' attribute for FooDemoAttribute");
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

---

## 4) TTLV Codec

Serializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/serializer/kmip/common/FooDemoAttributeTtlvSerializer.java`

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

public class FooDemoAttributeTtlvSerializer extends KmipDataTypeTtlvSerializer<FooDemoAttribute> {
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

Deserializer: `src/main/java/org/purpleBean/kmip/codec/ttlv/deserializer/kmip/common/FooDemoAttributeTtlvDeserializer.java`

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

public class FooDemoAttributeTtlvDeserializer extends KmipDataTypeTtlvDeserializer<FooDemoAttribute> {
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
## 5) Registration (ServiceLoader)

Register your serializers/deserializers via Java ServiceLoader. Add fully-qualified class names (one per line) to these files in `src/main/resources/META-INF/services/`:

- JSON
    - `org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer`
        - `org.purpleBean.kmip.codec.json.serializer.kmip.common.FooDemoAttributeJsonSerializer`
    - `org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer`
        - `org.purpleBean.kmip.codec.json.deserializer.kmip.common.FooDemoAttributeJsonDeserializer`

- XML
    - `org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer`
        - `org.purpleBean.kmip.codec.xml.serializer.kmip.common.FooDemoAttributeXmlSerializer`
    - `org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer`
        - `org.purpleBean.kmip.codec.xml.deserializer.kmip.common.FooDemoAttributeXmlDeserializer`

- TTLV
    - `org.purpleBean.kmip.codec.ttlv.serializer.kmip.KmipDataTypeTtlvSerializer`
        - `org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.FooDemoAttributeTtlvSerializer`
    - `org.purpleBean.kmip.codec.ttlv.deserializer.kmip.KmipDataTypeTtlvDeserializer`
        - `org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.FooDemoAttributeTtlvDeserializer`

Notes
- You do NOT need to modify `KmipJsonModule`, `KmipXmlModule`, or `KmipTtlvModule`. They auto-discover providers via ServiceLoader.
- JSON/XML base classes (`KmipDataTypeJsonSerializer/Deserializer`, `KmipDataTypeXmlSerializer/Deserializer`) and TTLV abstract contracts (`KmipDataTypeTtlvSerializer`, `KmipDataTypeTtlvDeserializer`) infer the handled type automatically.

---


---

## 6) Unit Tests

### Core Attribute Test (reusable suite)

File: `src/test/java/org/purpleBean/kmip/common/FooDemoAttributeTest.java`

```java
package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureAttributeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoAttribute Domain Tests")
class FooDemoAttributeTest extends AbstractKmipAttributeStructureSuite<FooDemoAttribute> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<FooDemoAttribute> type() { return FooDemoAttribute.class; }

    @Override
    protected FooDemoAttribute createDefault() { return FooDemoAttribute.builder().dateTime(FIXED_TIME).build(); }

    @Override
    protected EncodingType expectedEncodingType() { return EncodingType.DATE_TIME; }

    // Capability flags
    @Override protected boolean expectAlwaysPresent() { return false; }
    @Override protected boolean expectServerInitializable() { return true; }
    @Override protected boolean expectClientInitializable() { return true; }
    @Override protected boolean expectClientDeletable() { return false; }
    @Override protected boolean expectMultiInstanceAllowed() { return false; }

    // State-dependent mutability
    @Override protected State stateForServerModifiableTrue() { return new State(State.Standard.PRE_ACTIVE); }
    @Override protected State stateForServerModifiableFalse() { return new State(State.Standard.ACTIVE); }
    @Override protected State stateForClientModifiableTrue() { return new State(State.Standard.PRE_ACTIVE); }
    @Override protected State stateForClientModifiableFalse() { return new State(State.Standard.ACTIVE); }
}
```

---

## 7) Codec Tests

Place these under codec packages and extend abstract suites.

### JSON

File: `src/test/java/org/purpleBean/kmip/codec/json/common/FooDemoAttributeJsonTest.java`

```java
package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoAttribute JSON Serialization")
class FooDemoAttributeJsonTest extends AbstractJsonSerializationSuite<FooDemoAttribute> {
    @Override protected Class<FooDemoAttribute> type() { return FooDemoAttribute.class; }
    @Override protected FooDemoAttribute createDefault() {
        return FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
    }
}
```

### XML

File: `src/test/java/org/purpleBean/kmip/codec/xml/common/FooDemoAttributeXmlTest.java`

```java
package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoAttribute XML Serialization")
class FooDemoAttributeXmlTest extends AbstractXmlSerializationSuite<FooDemoAttribute> {
    @Override protected Class<FooDemoAttribute> type() { return FooDemoAttribute.class; }
    @Override protected FooDemoAttribute createDefault() {
        return FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
    }
}
```

### TTLV

File: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/FooDemoAttributeTtlvTest.java`

```java
package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.FooDemoAttribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("FooDemoAttribute TTLV Serialization")
class FooDemoAttributeTtlvTest extends AbstractTtlvSerializationSuite<FooDemoAttribute> {
    @Override protected Class<FooDemoAttribute> type() { return FooDemoAttribute.class; }
    @Override protected FooDemoAttribute createDefault() {
        return FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();
    }
}
```

---

## 8) Performance Benchmarks (JMH)

This project includes a pluggable JMH harness that auto-discovers benchmark subjects via Java ServiceLoader. Below is a ready-to-adapt boilerplate subject for `FooDemoAttribute`.

Subject class: `src/test/java/org/purpleBean/kmip/benchmark/subjects/FooDemoAttributeBenchmarkSubject.java`

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
import org.purpleBean.kmip.common.FooDemoAttribute;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class FooDemoAttributeBenchmarkSubject implements KmipBenchmarkSubject {
    private ObjectMapper json;
    private XmlMapper xml;
    private TtlvMapper ttlv;
    private FooDemoAttribute obj;
    @Getter
    private String jsonStr;
    @Getter
    private String xmlStr;
    @Getter
    private ByteBuffer ttlvBuf;
    
    public FooDemoAttributeBenchmarkSubject() throws Exception {
        this.setup();
    }

    @Override public String name() { return "FooDemoAttribute"; }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(KmipSpec.V1_2);
        json = new ObjectMapper(); json.findAndRegisterModules(); json.registerModule(new JavaTimeModule()); json.registerModule(new KmipJsonModule());
        xml = new XmlMapper(); xml.findAndRegisterModules(); xml.registerModule(new JavaTimeModule()); xml.registerModule(new KmipXmlModule());
        ttlv = new TtlvMapper(); ttlv.registerModule(new KmipTtlvModule());

        obj = FooDemoAttribute.builder().dateTime(OffsetDateTime.of(2024,1,2,3,4,5,0, ZoneOffset.UTC)).build();

        jsonStr = json.writeValueAsString(obj);
        xmlStr = xml.writeValueAsString(obj);
        ttlvBuf = ttlv.writeValueAsByteBuffer(obj);
    }

    @Override public void tearDown() { KmipContext.clear(); }
    @Override public String jsonSerialize() throws Exception { return json.writeValueAsString(obj); }
    @Override public Object jsonDeserialize() throws Exception { return json.readValue(jsonStr, FooDemoAttribute.class); }
    @Override public String xmlSerialize() throws Exception { return xml.writeValueAsString(obj); }
    @Override public Object xmlDeserialize() throws Exception { return xml.readValue(xmlStr, FooDemoAttribute.class); }
    @Override public ByteBuffer ttlvSerialize() throws Exception { return ttlv.writeValueAsByteBuffer(obj); }
    @Override public Object ttlvDeserialize() throws Exception { return ttlv.readValue(ttlvBuf.duplicate(), FooDemoAttribute.class); }
}
```

ServiceLoader registration: `src/test/resources/META-INF/services/org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject`

```
org.purpleBean.kmip.benchmark.subjects.FooDemoAttributeBenchmarkSubject
```

Recommended runner:

```bash
mvn -q -DskipTests test-compile \
  exec:java \
  -Dexec.mainClass="org.purpleBean.kmip.benchmark.JmhBenchmarkRunner" \
  -Dbench.include=KmipSerializationBenchmark
```

Outputs (defaults)
- Raw JMH JSON: `target/jmh-results.json`
- Markdown summary: `target/jmh-report.md`

You can customize paths via `-Dbench.result` and `-Dbench.report`. See `docs/04-performance/performance-testing-guide.md` for more.

---

## 9) Import Style and Legacy References

- Prefer imports, not FQNs, in tests:
  - `import java.util.NoSuchElementException;`
  - `import java.util.Set;` / `Set.of(...)`
  - `import static org.assertj.core.api.Assertions.*;`
- Avoid legacy/fictitious classes: JsonCodec, XmlCodec, TtlvCodec (do not exist in this repo).
- Use the canonical testing suites and mappers as shown above.
- For tag registry and lookup examples, refer to `src/test/java/org/purpleBean/kmip/KmipTagTest.java`.
