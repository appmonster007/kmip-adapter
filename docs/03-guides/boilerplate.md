# KMIP Boilerplate: Copy-Ready Templates

This guide provides minimal, copy-ready boilerplate aligned with the current code in `src/main`. Use these templates to add new enumerations, attributes, structures, and codec support (JSON/XML/TTLV), plus unit/integration tests.

All examples match current interfaces and module registration:
- Interfaces: `KmipDataType`, `KmipAttribute`, `KmipStructure`
- Context: `KmipContext` (thread-local, static API)
- Spec: `KmipSpec`
- Tags: `KmipTag`
- Encoding types: `EncodingType`
- Modules: `KmipJsonModule`, `KmipXmlModule`, `KmipTtlvModule`, and `TtlvMapper`

---

# KMIP Object Creation & Testing Checklist

## New Object Implementation Checklist

### 1. Core Implementation
- [ ] Create the class implementing appropriate interface (`KmipDataType`, `KmipAttribute`, or `KmipStructure`)
- [ ] Add proper `@NonNull` annotations for required fields
- [ ] Implement `getKmipTag()` with proper tag registration
- [ ] Implement `getEncodingType()` with correct encoding type
- [ ] For structures, implement `getValues()` with proper ordering
- [ ] Implement `isSupportedFor(KmipSpec)` with version checks
- [ ] Add proper Javadoc for the class and public methods
- [ ] Add `@Builder` with custom build() method for validation

### 2. Serialization/Deserialization
- [ ] Create JSON serializer/deserializer
- [ ] Create XML serializer/deserializer
- [ ] Create TTLV serializer/deserializer
- [ ] Register all serializers in their respective modules
- [ ] Handle version-specific serialization if needed

### 3. Test Coverage
- [ ] Unit tests for object creation and validation
- [ ] Serialization/deserialization tests for all formats (JSON/XML/TTLV)
- [ ] Version compatibility tests
- [ ] Edge case tests (null values, min/max values, etc.)
- [ ] Error handling tests

### 4. Integration
- [ ] Add to appropriate module registration
- [ ] Update documentation if this is a public API
- [ ] Add example usage in relevant documentation

## Test Case Scenarios

### 1. Basic Functionality
- [ ] Object creation with valid parameters
- [ ] Object equality and hash code
- [ ] String representation (toString)
- [ ] Copy constructor/builder if applicable

### 2. Validation Tests
- [ ] Required field validation
- [ ] Value range validation
- [ ] Version compatibility validation
- [ ] Cross-field validation

### 3. Serialization Tests
- [ ] Round-trip serialization/deserialization for all formats
- [ ] Version-specific serialization behavior
- [ ] Handling of optional fields
- [ ] Error cases (invalid input, missing fields, etc.)

### 4. Edge Cases
- [ ] Null values for optional fields
- [ ] Empty collections/arrays
- [ ] Minimum/maximum values
- [ ] Special characters in strings
- [ ] Timezone handling for dates/times

### 5. Performance
- [ ] Serialization/deserialization performance
- [ ] Memory usage for large objects
- [ ] Thread safety if applicable

### 6. Integration Tests
- [ ] Integration with other components
- [ ] End-to-end workflow tests
- [ ] Backward compatibility tests

---

## 1) Enumeration Template (value-based with registry)

```java
package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Value-based enumeration pattern with standard and custom values.
 */
public final class YourEnumeration implements KmipDataType {

    // Required by KmipDataType
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.YOUR_ENUMERATION_TAG);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    // Backing registries
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    // Standard values
    public enum Standard implements Value {
        VALUE_1(0x01, "Value1", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)),
        VALUE_2(0x02, "Value2", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        Standard(int value, String description, Set<KmipSpec> supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Collections.unmodifiableSet(supportedVersions);
        }
        @Override public int getValue() { return value; }
        @Override public String getDescription() { return description; }
        @Override public boolean isSupportedFor(KmipSpec spec) { return supportedVersions.contains(spec); }
    }

    // Value contract
    public interface Value {
        int getValue();
        String getDescription();
        boolean isSupportedFor(KmipSpec spec);
    }

    // Register standard values
    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.getValue(), s);
            DESCRIPTION_REGISTRY.put(s.getDescription().toUpperCase(Locale.ROOT), s);
        }
    }

    // Factory: register custom extension value
    public static synchronized Value register(int code, String description, Set<KmipSpec> versions) {
        Objects.requireNonNull(description, "description");
        Objects.requireNonNull(versions, "versions");
        if (VALUE_REGISTRY.containsKey(code)) {
            throw new IllegalArgumentException("Duplicate value: " + code);
        }
        Value v = new Value() {
            @Override public int getValue() { return code; }
            @Override public String getDescription() { return description; }
            @Override public boolean isSupportedFor(KmipSpec spec) { return versions.contains(spec); }
        };
        VALUE_REGISTRY.put(code, v);
        DESCRIPTION_REGISTRY.put(description.toUpperCase(Locale.ROOT), v);
        return v;
    }

    // Lookups (spec-aware)
    public static Value fromValue(KmipSpec spec, int code) {
        Value v = VALUE_REGISTRY.get(code);
        if (v == null || !v.isSupportedFor(spec)) {
            throw new IllegalArgumentException("Unknown or unsupported value: " + code + " for spec " + spec);
        }
        return v;
    }

    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name.toUpperCase(Locale.ROOT));
        if (v == null || !v.isSupportedFor(spec)) {
            throw new IllegalArgumentException("Unknown or unsupported name: " + name + " for spec " + spec);
        }
        return v;
    }

    // Instance wrapper
    private final Value value;
    public YourEnumeration(Value value) { this.value = Objects.requireNonNull(value, "value"); }
    public Value getValue() { return value; }

    // KmipDataType
    @Override public KmipTag getKmipTag() { return kmipTag; }
    @Override public EncodingType getEncodingType() { return encodingType; }
    @Override public boolean isSupportedFor(KmipSpec spec) { return value.isSupportedFor(spec); }
}
```

## 2) Attribute Template (implements KmipAttribute)

This mirrors the style used by `ActivationDateAttribute`.

```java
package org.purpleBean.kmip.common.attribute;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
public class YourAttribute implements KmipAttribute {

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.YOUR_ATTRIBUTE_TAG);
    private final EncodingType encodingType = EncodingType.TEXT_STRING; // adjust
    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    @NonNull private final String value; // adjust type

    @Override public boolean isClientModifiable(@NonNull State state) {
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }
    @Override public boolean isServerModifiable(@NonNull State state) {
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }
    @Override public boolean isSupportedFor(@NonNull KmipSpec spec) { return supportedVersions.contains(spec); }
}
```
---

## 3) Structure Template (implements KmipStructure)

```java
package org.purpleBean.kmip.common.structure;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;

/**
 * Example structure demonstrating a KMIP structure implementation.
 * Replace 'YourStructure' and field names with your actual structure details.
 */
@Data
@Builder
public class YourStructure implements KmipStructure {
    // Register a custom tag for this structure (or use Standard if appropriate)
    private static final KmipTag.Value YOUR_STRUCTURE_TAG = KmipTag.register(
            0x540102, "YourStructure", Set.of(KmipSpec.V1_2, KmipSpec.V1_4)
    );

    // Required fields with proper nullability
    @NonNull private final YourEnumeration status;  // Using YourEnumeration from template
    private final YourAttribute customAttribute;     // Using YourAttribute from template

    // Required by KmipDataType
    @Override public KmipTag getKmipTag() { return new KmipTag(YOUR_STRUCTURE_TAG); }
    @Override public EncodingType getEncodingType() { return EncodingType.STRUCTURE; }

    /**
     * Returns all child values in the order they should be serialized.
     */
    @Override 
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(status);
        if (customAttribute != null) {
            values.add(customAttribute);
        }
        return values;
    }

    /**
     * Validates if this structure is supported in the given KMIP specification.
     */
    @Override 
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        // Check if this structure is supported in the given spec
        if (!YOUR_STRUCTURE_TAG.isSupportedFor(spec)) {
            return false;
        }
        
        // Check if all child values are supported in the given spec
        return status.isSupportedFor(spec) && 
               (customAttribute == null || customAttribute.isSupportedFor(spec));
    }

    /**
     * Custom builder with validation.
     */
    public static class YourStructureBuilder {
        public YourStructure build() {
            // Add any validation logic here
if (status == null) {
                throw new IllegalStateException("Status is required");
            }
            // Validate that the status is supported in the current spec
            if (!status.isSupportedFor(KmipContext.getSpec())) {
                throw new IllegalStateException("Status is not supported in " + KmipContext.getSpec());
            }
            return new YourStructure(status, customAttribute);
        }
    }
}
```

### Usage Example

```java
// Create a new structure
YourStructure structure = YourStructure.builder()
    .status(new YourEnumeration(YourEnumeration.Standard.VALUE_1))  // Using YourEnumeration from template
    .customAttribute(YourAttribute.builder()
        .value("example-value")
        .build())
    .build();

// Check if supported in current spec
KmipContext.setSpec(KmipSpec.V1_2);
boolean isSupported = structure.isSupportedFor(KmipContext.getSpec());
```

### Key Points

1. **Tag Registration**: Each structure should have a unique tag registered with `KmipTag.register()`
2. **Immutability**: Fields are marked as `final` with `@Data` and `@Builder` for safe construction
3. **Null Safety**: Use `@NonNull` for required fields
4. **Spec Support**: Implement `isSupportedFor()` to check version compatibility
5. **Validation**: Add validation in the builder's `build()` method
6. **Documentation**: Include Javadoc for public API methods

For complete implementation including serialization/deserialization, see the `DemoBundle` example in `boilerplate-structure.md`.

---

## 4) JSON/XML/TTLV Registration + Round-trip

```java
KmipContext.setSpec(KmipSpec.V1_2);

// JSON round-trip
ObjectMapper json = new ObjectMapper();
json.findAndRegisterModules();
json.registerModule(new KmipJsonModule());
ProtocolVersion original = ProtocolVersion.of(1, 2);
String jsonText = json.writeValueAsString(original);
ProtocolVersion fromJson = json.readValue(jsonText, ProtocolVersion.class);

// XML round-trip
XmlMapper xml = new XmlMapper();
xml.findAndRegisterModules();
xml.registerModule(new KmipXmlModule());
String xmlText = xml.writeValueAsString(original);
ProtocolVersion fromXml = xml.readValue(xmlText, ProtocolVersion.class);

// TTLV round-trip
TtlvMapper ttlv = new TtlvMapper();
ttlv.registerModule(new KmipTtlvModule());
byte[] bytes = ttlv.writeValueAsBytes(original);
ProtocolVersion fromTtlv = ttlv.readValue(bytes, ProtocolVersion.class);

KmipContext.clear();

```

---

## 5) JSON Serializer/Deserializer Template (Enumeration)

```java
package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.YourEnumeration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class YourEnumerationJsonSerializer extends KmipDataTypeJsonSerializer<YourEnumeration> {
    @Override
    public void serialize(YourEnumeration value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("YourEnumeration not supported for spec " + spec);
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
package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.YourEnumeration;

import java.io.IOException;
import java.util.NoSuchElementException;

public class YourEnumerationJsonDeserializer extends KmipDataTypeJsonDeserializer<YourEnumeration> {
    @Override
    public YourEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(YourEnumeration.class, "Expected JSON object");
            return null;
        }

        // Validate tag and type if needed (see KMIP_SERIALIZATION_GUIDE.md)
        KmipSpec spec = KmipContext.getSpec();

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(YourEnumeration.class, "Field 'value' must be textual");
            return null;
        }

        String name = valueNode.asText();
        try {
            YourEnumeration.Value v = YourEnumeration.fromName(spec, name);
            return new YourEnumeration(v);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            ctxt.reportInputMismatch(YourEnumeration.class, "Unknown value '%s' for spec %s", name, spec);
            return null;
        }
    }
}
```

---

## 6) TTLV Serializer/Deserializer Template (Enumeration)

```java
package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.common.enumeration.YourEnumeration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class YourEnumerationTtlvSerializer implements TtlvSerializer<YourEnumeration> {
    @Override
    public ByteBuffer serialize(YourEnumeration value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("YourEnumeration not supported for spec " + spec);
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
package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.common.enumeration.YourEnumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class YourEnumerationTtlvDeserializer implements TtlvDeserializer<YourEnumeration> {
    @Override
    public YourEnumeration deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        // Validate tag and type as needed
        KmipSpec spec = KmipContext.getSpec();
        if (obj.getValue().length != 4) {
            throw new IllegalArgumentException("Enumeration value must be 4 bytes");
        }
        ByteBuffer payload = ByteBuffer.wrap(obj.getValue());
        int intValue = payload.getInt();
        YourEnumeration.Value v = YourEnumeration.fromValue(spec, intValue);
        return new YourEnumeration(v);
    }
}
```

---

## 7) XML Serializer/Deserializer Template (Enumeration)

```java
package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.YourEnumeration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class YourEnumerationXmlSerializer extends JsonSerializer<YourEnumeration> {
    @Override
    public void serialize(YourEnumeration value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("YourEnumeration not supported for spec " + spec);
        }
        ToXmlGenerator xml = (ToXmlGenerator) gen;
        String element = value.getKmipTag().getDescription();
        xml.setNextName(javax.xml.namespace.QName.valueOf(element));
        xml.writeStartObject(value);
        
        // for structure type encoding 'type' field is optional 
        // and value field should not be attributed 
        xml.setNextIsAttribute(true);
        xml.writeStringField("type", value.getEncodingType().getDescription());
        xml.setNextIsAttribute(true);
        xml.writeStringField("value", value.getValue().getDescription());
        xml.writeEndObject();
    }
}
```

```java
package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.YourEnumeration;

import java.io.IOException;
import java.util.NoSuchElementException;

public class YourEnumerationXmlDeserializer extends JsonDeserializer<YourEnumeration> {
    @Override
    public YourEnumeration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        if (!node.isObject()) {
            ctxt.reportInputMismatch(YourEnumeration.class, "Expected XML object");
            return null;
        }
        KmipSpec spec = KmipContext.getSpec();
        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(YourEnumeration.class, "Field 'value' must be textual");
            return null;
        }
        String name = valueNode.asText();
        try {
            YourEnumeration.Value v = YourEnumeration.fromName(spec, name);
            return new YourEnumeration(v);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            ctxt.reportInputMismatch(YourEnumeration.class, "Unknown value '%s' for spec %s", name, spec);
            return null;
        }
    }
}
```

---

## 7) Unit Test Boilerplate

```java
@DisplayName("YourEnumeration Tests")
class YourEnumerationTest extends BaseKmipTest {

    @Test
    void shouldCreateStandardValue() {
        KmipContext.setSpec(KmipSpec.V1_2);
        try {
            YourEnumeration e = new YourEnumeration(YourEnumeration.Standard.VALUE_1);
            assertThat(e.isSupportedFor(KmipContext.getSpec())).isTrue();
        } finally {
            KmipContext.clear();
        }
    }
}
```

Round-trip tests can follow `ProtocolVersion` examples from `KMIP_SERIALIZATION_GUIDE.md` using `KmipJsonModule`, `KmipXmlModule`, and `TtlvMapper` with `KmipTtlvModule`.

---

## 7.1) Boilerplate Tests for State (based on existing tests)

```java
@DisplayName("State Tests")
class StateBoilerplateTest extends BaseKmipTest {

    @Nested
    @DisplayName("Standard State Operations")
    class StandardStateOperations {
        @Test
        void shouldCreateStateWithStandardValue() {
            State state = new State(State.Standard.ACTIVE);
            assertThat(state.getValue()).isInstanceOf(State.Standard.class);
            assertThat(state.getKmipTag().getValue()).isEqualTo(KmipTag.Standard.STATE);
            assertThat(state.getEncodingType()).isEqualTo(EncodingType.ENUMERATION);
        }

        @ParameterizedTest
        @EnumSource(State.Standard.class)
        void shouldHandleAllStandardStatesCorrectly(State.Standard std) {
            State state = new State(std);
            assertThat(state.getValue()).isInstanceOf(State.Standard.class);
            assertThat(((State.Standard) state.getValue()).getValue()).isEqualTo(std.getValue());
        }

        @Test
        void shouldSupportVersionCompatibilityForStandardStates() {
            State state = new State(State.Standard.ACTIVE);
            assertThat(state.isSupportedFor(KmipSpec.V1_2)).isTrue();
        }
    }

    @Nested
    @DisplayName("Custom State Operations")
    class CustomStateOperations {
        @Test
        void shouldRegisterAndCreateCustomStateSuccessfully() {
            State.Value custom = State.register(-1000010, "Custom", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));
            State state = new State(custom);
            assertThat(state.getValue()).isSameAs(custom);
        }

        @Test
        void shouldLookupCustomStateByNameAndValue() {
            String name = "CustomLookup";
            int code = -1000020;
            State.register(code, name, Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2));

            State.Value byName = State.fromName(KmipSpec.V1_2, name);
            State.Value byValue = State.fromValue(KmipSpec.V1_2, code);
            assertThat(byName).isEqualTo(byValue);
        }
    }

    @Nested
    @DisplayName("Lookup Operations")
    class LookupOperations {
        @Test
        void shouldFindStandardByValueAndName() {
            State.Value v = State.fromValue(KmipSpec.V1_2, State.Standard.ACTIVE.getValue());
            assertThat(v).isEqualTo(State.Standard.ACTIVE);

            State.Value n = State.fromName(KmipSpec.V1_2, State.Standard.DEACTIVATED.getDescription());
            assertThat(n).isEqualTo(State.Standard.DEACTIVATED);
        }
    }

    @Nested
    @DisplayName("Serialization")
    class Serialization {
        @Test
        void shouldJsonRoundTripStandard() throws Exception {
            ObjectMapper mapper = new ObjectMapper().registerModule(new KmipJsonModule());
            State original = new State(State.Standard.COMPROMISED);
            String json = mapper.writeValueAsString(original);
            State back = mapper.readValue(json, State.class);
            assertThat(back).isEqualTo(original);
        }

        @Test
        void shouldXmlRoundTripStandard() throws Exception {
            XmlMapper mapper = new XmlMapper().registerModule(new KmipXmlModule());
            State original = new State(State.Standard.ACTIVE);
            String xml = mapper.writeValueAsString(original);
            State back = mapper.readValue(xml, State.class);
            assertThat(back).isEqualTo(original);
        }

        @Test
        void shouldTtlvRoundTripStandard() throws Exception {
            KmipContext.setSpec(KmipSpec.V1_2);
            try {
                TtlvMapper ttlv = new TtlvMapper();
                ttlv.registerModule(new KmipTtlvModule());
                State original = new State(State.Standard.ACTIVE);
                byte[] bytes = ttlv.writeValueAsBytes(original);
                State back = ttlv.readValue(bytes, State.class);
                assertThat(back).isEqualTo(original);
            } finally {
                KmipContext.clear();
            }
        }
    }
}
```

---

## 7.2) Boilerplate Tests for SimpleRequestMessage (based on existing tests)

```java
@DisplayName("SimpleRequest Tests")
class SimpleRequestBoilerplateTest {

    private SimpleRequestMessage requestMessage;
    private SimpleRequestHeader requestHeader;
    private SimpleRequestBatchItem batchItem1;
    private SimpleRequestBatchItem batchItem2;

    @BeforeEach
    void setUp() {
        ProtocolVersion protocolVersion = ProtocolVersion.of(1, 4);
        requestHeader = SimpleRequestHeader.builder().protocolVersion(protocolVersion).build();
        batchItem1 = SimpleRequestBatchItem.builder().build();
        batchItem2 = SimpleRequestBatchItem.builder().build();
        requestMessage = SimpleRequestMessage.builder()
            .requestHeader(requestHeader)
            .requestBatchItem(batchItem1)
            .requestBatchItem(batchItem2)
            .build();
    }

    @Test
    void shouldHaveCorrectStructure() {
        assertThat(requestMessage.getKmipTag()).isEqualTo(new KmipTag(KmipTag.Standard.REQUEST_MESSAGE));
        assertThat(requestMessage.getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
        assertThat(requestMessage.getRequestHeader()).isEqualTo(requestHeader);
        assertThat(requestMessage.getRequestBatchItems()).containsExactly(batchItem1, batchItem2);
        assertThat(requestMessage.isSupportedFor(KmipSpec.V1_2)).isTrue();

        List<KmipDataType> values = requestMessage.getValues();
        assertThat(values).hasSize(3);
        assertThat(values.get(0)).isEqualTo(requestHeader);
        assertThat(values.subList(1, 3)).containsExactly(batchItem1, batchItem2);
    }

    @Test
    void shouldRejectNullRequestHeader() {
        assertThatThrownBy(() -> SimpleRequestMessage.builder().requestHeader(null).build())
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("requestHeader");
    }

    @Test
    void shouldSerializeToJson() throws Exception {
        ObjectMapper json = new ObjectMapper().registerModule(new KmipJsonModule());
        String text = json.writeValueAsString(requestMessage);
        assertThat(text).contains("\"tag\":\"RequestMessage\"");
        assertThat(text).contains("\"RequestHeader\"").contains("\"BatchItem\"");
    }

    @Test
    void shouldSerializeToXml() throws Exception {
        XmlMapper xml = new XmlMapper().registerModule(new KmipXmlModule());
        String out = xml.writeValueAsString(requestMessage);
        assertThat(out).contains("<RequestMessage>").contains("<RequestHeader>");
        assertThat(out).contains("<ProtocolVersionMajor type=\"Integer\" value=\"1\"/>");
        assertThat(out).contains("<ProtocolVersionMinor type=\"Integer\" value=\"4\"/>");
    }

    @Test
    void shouldHandleEmptyBatchItems() {
        SimpleRequestMessage msg = SimpleRequestMessage.builder().requestHeader(requestHeader).build();
        assertThat(msg.getRequestBatchItems()).isEmpty();
        assertThat(msg.getValues()).hasSize(1);
    }

    @Test
    void shouldHandleNullBatchItemsInBuilder() {
        SimpleRequestMessage msg = SimpleRequestMessage.builder()
            .requestHeader(requestHeader)
            .requestBatchItem(null)
            .build();
        assertThat(msg.getRequestBatchItems()).hasSize(1);
        assertThat(msg.getRequestBatchItems().get(0)).isNull();
        assertThat(msg.getValues()).hasSize(2);
    }

    @Test
    void shouldClearBatchItemsViaBuilder() {
        SimpleRequestMessage msg = SimpleRequestMessage.builder()
            .requestHeader(requestHeader)
            .clearRequestBatchItems()
            .build();
        assertThat(msg.getRequestBatchItems()).isEmpty();
        assertThat(msg.getValues()).hasSize(1);
    }
}
```

## 8) Quick Checklist

- Create type (Enumeration/Attribute/Structure) implementing required interfaces

---

# Concrete Boilerplate Samples (Copy-Ready)

See dedicated codec templates for each sample:
- DemoStatus codecs: `docs/03-guides/boilerplate-enum.md`
- DemoBundle codecs: `docs/03-guides/boilerplate-structure.md`

---
