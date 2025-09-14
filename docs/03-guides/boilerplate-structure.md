# Boilerplate: Structures and Per-Class Tests

This guide provides concise, copy-ready boilerplates for adding a new KMIP structure and its per-class tests across codecs. Use the minimal Foo examples, then see the extended DemoStructure for a full pattern.

## Minimal Boilerplate (Copy-Ready)

### Main Code: `Foo` (implements `KmipStructure`)

```java
package org.purpleBean.kmip.foo.structure;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class Foo implements KmipStructure {
    private static final KmipTag.Value FOO_TAG = KmipTag.register(
            0x540200, "Foo", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)
    );

    @NonNull FooStatus status;        // replace with your enum/type
    FooAttribute attribute;           // nullable, optional

    @Override public KmipTag getKmipTag() { return new KmipTag(FOO_TAG); }
    @Override public EncodingType getEncodingType() { return EncodingType.STRUCTURE; }
    @Override public boolean isSupportedFor(@NonNull KmipSpec spec) { return true; }
    @Override public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(status);
        if (attribute != null) values.add(attribute);
        return values;
    }
}
```

### Factory (optional for tests): `FooFactory`

```java
package org.purpleBean.kmip.foo;

public final class FooFactory {
    private FooFactory() {}
    public static Foo createFoo() {
        return Foo.builder()
                .status(new FooStatus(FooStatus.Standard.READY))
                .attribute(FooAttribute.builder().value("example").build())
                .build();
    }
}
```

### Usage

```java
KmipContext.setSpec(KmipSpec.V1_2);
try {
    Foo foo = FooFactory.createFoo();
    // serialize or process foo
} finally {
    KmipContext.clear();
}
```

## Per-Class Test Boilerplates (Copy-Ready)

### JSON Test: `src/test/java/org/purpleBean/kmip/codec/json/FooJsonTest.java`

```java
package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("Foo JSON Tests")
class FooJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize Foo")
    void roundTrip() {
        Foo original = FooFactory.createFoo();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, Foo.class);
    }

    @Test
    @DisplayName("Structure: expected JSON fields present")
    void structure_expectFields() {
        Foo foo = FooFactory.createFoo();
        SerializationTestUtils.testJsonSerialization(jsonMapper, foo, json -> {
            SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
            // assertThat(json).contains("\"Foo\"");
        });
    }
}
```

### TTLV Test: `src/test/java/org/purpleBean/kmip/codec/ttlv/FooTtlvTest.java`

```java
package org.purpleBean.kmip.codec.ttlv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Foo TTLV Tests")
class FooTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = new TtlvMapper();

    FooTtlvTest() {
        ttlvMapper.registerModule(new KmipTtlvModule());
    }

    @Test
    @DisplayName("Round-trip: Foo TTLV")
    void roundTrip() throws IOException {
        Foo original = FooFactory.createFoo();
        ByteBuffer buf = ttlvMapper.writeValueAsByteBuffer(original);
        Foo deserialized = ttlvMapper.readValue(buf, Foo.class);
        assertThat(deserialized).isEqualTo(original);
    }
}
```

### XML Test: `src/test/java/org/purpleBean/kmip/codec/xml/FooXmlTest.java`

```java
package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("Foo XML Tests")
class FooXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize Foo")
    void roundTrip() {
        Foo original = FooFactory.createFoo();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, Foo.class);
    }

    @Test
    @DisplayName("Structure: expected XML fields present")
    void structure_expectFields() {
        Foo foo = FooFactory.createFoo();
        SerializationTestUtils.testXmlSerialization(xmlMapper, foo, xml -> {
            // assertThat(xml).contains("<Foo>");
        });
    }
}
```

## Extended Example: DemoStructure

For a fuller example with custom tag registration and richer assertions, adapt your `Foo` to the earlier DemoStructure pattern.

## Testing

```java
@ExtendWith(MockitoExtension.class)
@DisplayName("Foo Tests")
class FooTest extends BaseKmipTest {
    
    private FooStatus validStatus;
    private FooAttribute validAttribute;
    
    @BeforeEach
    void setUp() {
        validStatus = new FooStatus(FooStatus.Standard.READY);
        validAttribute = FooAttribute.builder()
            .value("test-value")
            .build();
        KmipContext.setSpec(KmipSpec.V1_2);
    }
    
    @AfterEach
    void tearDown() {
        KmipContext.clear();
    }
    
    @Nested
    @DisplayName("Construction Tests")
    class ConstructionTests {
        
        @Test
        @DisplayName("Should create valid Foo with all fields")
        void shouldCreateValidFoo() {
            // When
            Foo structure = Foo.builder()
                .status(validStatus)
                .attribute(validAttribute)
                .build();
                
            // Then
            assertThat(structure)
                .isNotNull()
                .extracting(
                    Foo::getStatus,
                    Foo::getAttribute,
                    s -> s.isSupportedFor(KmipSpec.V1_2),
                    s -> s.isSupportedFor(KmipSpec.V1_0)
                )
                .containsExactly(
                    validStatus,
                    validAttribute,
                    true,
                    false
                );
                
            // Verify KMIP tag and encoding type
            assertThat(structure.getKmipTag())
                .isNotNull()
                .extracting(KmipTag::getTagName)
                .isEqualTo("Foo");
                
            assertThat(structure.getEncodingType())
                .isEqualTo(EncodingType.STRUCTURE);
        }
        
        @Test
        @DisplayName("Should include all values in getValues()")
        void shouldIncludeAllValuesInGetValues() {
            // Given
            Foo structure = Foo.builder()
                .status(validStatus)
                .attribute(validAttribute)
                .build();
                
            // When
            List<KmipDataType> values = structure.getValues();
            
            // Then
            assertThat(values)
                .hasSize(2)
                .containsExactly(validStatus, validAttribute);
        }
        
        @Test
        @DisplayName("Should fail with null status (if @NonNull)")
        void shouldFailWithNullStatus() {
            assertThatNullPointerException()
                .isThrownBy(() -> Foo.builder()
                    .status(null)
                    .attribute(validAttribute)
                    .build()
                )
                .withMessage("status is marked non-null but is null");
        }
        
        @Test
        @DisplayName("Should handle null attribute (optional)")
        void shouldFailWithNullDemoAttribute() {
            Foo structureWithNull = Foo.builder()
                .status(validStatus)
                .attribute(null)
                .build();
            assertThat(structureWithNull.getAttribute()).isNull();
        }
    }
    
    @Nested
    @DisplayName("Equality and HashCode Tests")
    class EqualityTests {
        
        @Test
        @DisplayName("Should create with valid fields (equality smoke)")
        void shouldCreateWithValidFields() {
            // Test with non-null demoAttribute
            Foo structure = Foo.builder()
                .status(validStatus)
                .attribute(validAttribute)
                .build();
            
            assertThat(structure).isNotNull();
            assertThat(structure.getStatus()).isEqualTo(validStatus);
            assertThat(structure.getAttribute()).isEqualTo(validAttribute);
            
            // Test with null demoAttribute
            Foo structureWithNullAttr = Foo.builder()
                .status(validStatus)
                .attribute(null)
                .build();
                
            assertThat(structureWithNullAttr).isNotNull();
            assertThat(structureWithNullAttr.getStatus()).isEqualTo(validStatus);
            assertThat(structureWithNullAttr.getAttribute()).isNull();
        }
        
        @Test
        @DisplayName("Should be equal with same field values")
        void shouldBeEqualWithSameFieldValues() {
            // Given
            Foo structure1 = Foo.builder()
                .status(validStatus)
                .attribute(validAttribute)
                .build();
                
            Foo structure2 = Foo.builder()
                .status(validStatus)
                .attribute(validAttribute)
                .build();
                
            // Then
            assertThat(structure1)
                .isEqualTo(structure2)
                .hasSameHashCodeAs(structure2);
        }
        
        @Test
        @DisplayName("Should not be equal with different status")
        void shouldNotBeEqualWithDifferentStatus() {
            // Given
            DemoStructure structure1 = DemoStructure.builder()
                .status(validStatus)
                .demoAttribute(validAttribute)
                .build();
                
            DemoStructure structure2 = DemoStructure.builder()
                .status(new DemoStatus(DemoStatus.Standard.ERROR))
                .demoAttribute(validAttribute)
                .build();
                
            // Then
            assertThat(structure1).isNotEqualTo(structure2);
        }
        
        @Test
        @DisplayName("Should have consistent hashCode")
        void shouldHaveConsistentHashCode() {
            // Given
            DemoStructure structure = DemoStructure.builder()
                .status(validStatus)
                .demoAttribute(validAttribute)
                .build();
                
            // When/Then
            assertThat(structure.hashCode())
                .isEqualTo(structure.hashCode())
                .isEqualTo(DemoStructure.builder()
                    .status(validStatus)
                    .demoAttribute(validAttribute)
                    .build()
                    .hashCode()
                );
        }
    }
    
    @Nested
    @DisplayName("Serialization Tests")
    class SerializationTests {
        
        @Test
        @DisplayName("Should serialize to JSON and deserialize back")
        void shouldRoundTripJson() throws Exception {
            // Given
            DemoStructure original = DemoStructure.builder()
                .status(validStatus)
                .demoAttribute(validAttribute)
                .build();
                
            // When
            String json = jsonMapper.writeValueAsString(original);
            DemoStructure deserialized = jsonMapper.readValue(json, DemoStructure.class);
            
            // Then
            assertThat(deserialized).isEqualTo(original);
        }
        
        @Test
        @DisplayName("Should serialize to XML and deserialize back")
        void shouldRoundTripXml() throws Exception {
            // Given
            DemoStructure original = DemoStructure.builder()
                .status(validStatus)
                .demoAttribute(validAttribute)
                .build();
                
            // When
            String xml = xmlMapper.writeValueAsString(original);
            DemoStructure deserialized = xmlMapper.readValue(xml, DemoStructure.class);
            
            // Then
            assertThat(deserialized).isEqualTo(original);
        }
    }
    
    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {
        
        @Test
        @DisplayName("Should validate status against current KMIP spec")
        void shouldValidateStatusAgainstKmipSpec() {
            // Given
            DemoStatus unsupportedStatus = new DemoStatus(DemoStatus.Standard.ERROR);
            
            // When/Then
            assertThatIllegalArgumentException()
                .isThrownBy(() -> DemoStructure.builder()
                    .status(unsupportedStatus)
                    .demoAttribute(validAttribute)
                    .build()
                )
                .withMessageContaining("not supported for KMIP spec");
        }
        
        @Test
        @DisplayName("Should include all validation messages for multiple issues")
        void shouldIncludeAllValidationMessages() {
            // When/Then
            assertThatThrownBy(() -> DemoStructure.builder().build())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("status is marked non-null but is null")
                .hasMessageContaining("demoAttribute is marked non-null but is null");
        }
        
        @Test
        @DisplayName("Should require status")
        void shouldRequireStatus() {
            assertThatThrownBy(() -> DemoStructure.builder()
                .demoAttribute(validAttribute)
                .build())
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("status");
                
            // Verify demoAttribute can be null when status is provided
            assertThatCode(() -> DemoStructure.builder()
                .status(validStatus)
                .demoAttribute(null)
                .build())
                .doesNotThrowAnyException();
        }
    }
}

## Performance Considerations

1. **Memory Usage**
   - Immutable design reduces memory churn
   - Uses lazy initialization for expensive operations
   - Minimizes object creation during serialization/deserialization

2. **Performance**
   - Efficient field access with final fields
   - Optimized for read-heavy workloads
   - Caches expensive computations

3. **Thread Safety**
   - All operations are thread-safe
   - No shared mutable state
   - Safe for concurrent access

## Implementation Notes

### Design Principles
1. **Immutable Design**
   - All fields are final and set via constructor
   - Thread-safe by default
   - Predictable state management

2. **Type Safety**
   - Strong typing for all fields
   - Runtime validation of values
   - Compile-time safety through generics

3. **Extensibility**
   - Designed for inheritance
   - Protected constructor for subclasses
   - Overridable methods for customization

### Extension Patterns

#### 1. Adding New Fields
```java
public class ExtendedDemoStructure extends DemoStructure {
    private final String additionalInfo;
    
    @Builder(builderMethodName = "extendedBuilder")
    public ExtendedDemoStructure(
        @NonNull DemoStatus status, 
        DemoAttribute demoAttribute,
        String additionalInfo
    ) {
        super(status, demoAttribute);
        this.additionalInfo = additionalInfo;
    }
    
    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>(super.getValues());
        if (additionalInfo != null) {
            values.add(new StringAttribute(additionalInfo));
        }
        return values;
    }
}
```

#### 2. Custom Validation
```java
public class ValidatingDemoStructure extends DemoStructure {
    public ValidatingDemoStructure(
        @NonNull DemoStatus status, 
        DemoAttribute demoAttribute
    ) {
        super(status, demoAttribute);
        validate();
    }
    
    private void validate() {
        if (getStatus().getStandard() == DemoStatus.Standard.ERROR 
            && getDemoAttribute() == null) {
            throw new IllegalStateException(
                "Error status requires a demo attribute for context");
        }
    }
}
```

#### 3. Custom Serialization
```java
@JsonSerialize(using = CustomDemoStructureSerializer.class)
@JsonDeserialize(using = CustomDemoStructureDeserializer.class)
public class CustomDemoStructure extends DemoStructure {
    public CustomDemoStructure(
        @NonNull DemoStatus status, 
        DemoAttribute demoAttribute
    ) {
        super(status, demoAttribute);
    }
}
```

### Best Practices

1. **When to Extend**
   - Add new fields or behavior
   - Modify serialization/deserialization
   - Add custom validation rules
   - Implement domain-specific logic

2. **When to Use Composition**
   - Need to combine multiple structures
   - Require runtime flexibility
   - Implement multiple interfaces
   - Need to share behavior across unrelated types

3. **Performance Considerations**
   - Cache expensive computations
   - Lazy initialization for heavy resources
   - Prefer composition over deep inheritance
   - Consider object pooling for high-frequency instantiation

4. **Testing Extensions**
   - Test all custom validations
   - Verify serialization/deserialization
   - Check thread safety
   - Test edge cases and error conditions

### Common Patterns

1. **Decorator Pattern**
```java
public class DemoStructureDecorator implements KmipStructure {
    private final DemoStructure delegate;
    
    public DemoStructureDecorator(DemoStructure delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }
    
    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>(delegate.getValues());
        // Add custom values or behavior
        return values;
    }
}
```

2. **Factory Pattern**
```java
public class DemoStructureFactory {
    public static DemoStructure createWithDefaults() {
        return DemoStructure.builder()
            .status(new DemoStatus(DemoStatus.Standard.READY))
            .build();
    }
    
    public static DemoStructure createWithError(String message) {
        return DemoStructure.builder()
            .status(new DemoStatus(DemoStatus.Standard.ERROR))
            .demoAttribute(DemoAttribute.builder()
                .value(message)
                .build())
            .build();
    }
}
```

3. **Template Method**
```java
public abstract class AbstractDemoStructure extends DemoStructure {
    protected AbstractDemoStructure(
        @NonNull DemoStatus status, 
        DemoAttribute demoAttribute
    ) {
        super(status, demoAttribute);
        validate();
    }
    
    protected abstract void validate();
    
    @Override
    public List<KmipDataType> getValues() {
        return processValues(super.getValues());
    }
    
    protected List<KmipDataType> processValues(List<KmipDataType> values) {
        // Default implementation, can be overridden
        return values;
    }
}
```

### Error Handling

1. **Validation**
   - Validate early, fail fast
   - Provide clear error messages
   - Include context in error messages
   - Use appropriate exception types

2. **Recovery**
   - Provide recovery mechanisms
   - Document error conditions
   - Include error codes for programmatic handling
   - Log detailed diagnostics

### Testing Strategy

1. **Unit Tests**
   - Test all constructors and factory methods
   - Verify immutability
   - Test edge cases and boundary conditions
   - Verify serialization/deserialization

2. **Integration Tests**
   - Test with real KMIP server
   - Verify compatibility with different KMIP versions
   - Test error conditions and recovery

3. **Performance Tests**
   - Measure serialization/deserialization performance
   - Test memory usage under load
   - Profile critical sections

## Usage Examples

### Creating a DemoStructure

```java
// Create a new DemoStructure
DemoStructure structure = DemoStructure.builder()
    .status(new DemoStatus(DemoStatus.Standard.READY))
    .demoAttribute(DemoAttribute.builder()
        .value("example-value")
        .build())
    .build();

// Check if supported in current KMIP spec
if (!structure.isSupportedFor(KmipContext.getSpec())) {
    throw new IllegalStateException("Unsupported KMIP version");
}

// Get all values as a list
List<KmipDataType> values = structure.getValues();
```

### Error Handling

```java
try {
    // Operations that might fail
    DemoStructure structure = deserializeFromSomewhere();
    if (!structure.isSupportedFor(KmipContext.getSpec())) {
        throw new IllegalArgumentException("Unsupported KMIP version");
    }
} catch (IOException e) {
    // Handle I/O errors
    logger.error("Failed to process DemoStructure", e);
    throw new RuntimeException("Processing failed", e);
} catch (IllegalArgumentException e) {
    // Handle validation errors
    logger.warn("Invalid DemoStructure: {}", e.getMessage());
    throw e;
}
```

This file provides comprehensive documentation and examples for the `DemoStructure` structure type. The examples align with the project's codec patterns and module registration.

Note: DemoStructure is a Structure, so XML may omit the `type` attribute, and a `value` attribute is not used; inner values are nested elements.

## JSON

```java
package org.purpleBean.kmip.codec.json.serializer.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.demo.structure.DemoStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DemoStructureJsonSerializer extends KmipDataTypeJsonSerializer<DemoStructure> {
    @Override
    public void serialize(DemoStructure value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("DemoStructure not supported for spec " + spec);
        }
        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeFieldName("value");
        gen.writeStartArray();
        for (KmipDataType v : value.getValues()) {
            if (v != null) {
                gen.writeObject(v);
            }
        }
        gen.writeEndArray();
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
import org.purpleBean.kmip.demo.structure.DemoStructure;

import java.io.IOException;

public class DemoStructureJsonDeserializer extends KmipDataTypeJsonDeserializer<DemoStructure> {
    @Override
    public DemoStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (!node.isObject()) {
            ctxt.reportInputMismatch(DemoStructure.class, "Expected JSON object");
            return null;
        }
        KmipSpec spec = KmipContext.getSpec();
        JsonNode array = node.get("value");
        if (array == null || !array.isArray() || array.isEmpty() || array.size() > 2) {
            ctxt.reportInputMismatch(DemoStructure.class, "Structure 'value' must be an array of 1-2 items");
            return null;
        }
        
        // First element is always DemoStatus
        if (!array.has(0)) {
            ctxt.reportInputMismatch(DemoStructure.class, "Missing required DemoStatus in value array");
            return null;
        }
        
        DemoStatus status = p.getCodec().treeToValue(array.get(0), DemoStatus.class);
        DemoStructure.DemoStructureBuilder builder = DemoStructure.builder().status(status);
        
        // Second element is optional DemoAttribute
        if (array.size() > 1 && !array.get(1).isNull()) {
            DemoAttribute attr = p.getCodec().treeToValue(array.get(1), DemoAttribute.class);
            builder.demoAttribute(attr);
        }
        
        DemoStructure structure = builder.build();
        if (!structure.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(DemoStructure.class, "Structure not supported for spec %s", spec);
            return null;
        }
        return structure;
    }
}
```

Register in `KmipJsonModule` constructor:

```java
addSerializer(DemoStructure.class, new DemoStructureJsonSerializer());
addDeserializer(DemoStructure.class, new DemoStructureJsonDeserializer());
```

## XML

```java
package org.purpleBean.kmip.codec.xml.serializer.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.demo.structure.DemoStructure;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DemoStructureXmlSerializer extends JsonSerializer<DemoStructure> {
    @Override
    public void serialize(DemoStructure value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("DemoStructure not supported for spec " + spec);
        }
        ToXmlGenerator xml = (ToXmlGenerator) gen;
        xml.setNextName(QName.valueOf(value.getKmipTag().getDescription()));
        xml.writeStartObject(value);
        // For structures, omit type and value attributes; write children as elements
        for (KmipDataType v : value.getValues()) {
            if (v != null) {
                xml.writeObject(v);
            }
        }
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
import org.purpleBean.kmip.demo.structure.DemoStructure;

import java.io.IOException;

public class DemoStructureXmlDeserializer extends JsonDeserializer<DemoStructure> {
    @Override
    public DemoStructure deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        if (!node.isObject()) {
            ctxt.reportInputMismatch(DemoStructure.class, "Expected XML object");
            return null;
        }
        KmipSpec spec = KmipContext.getSpec();
        // Status is required
        JsonNode statusNode = node.get("DemoStatus");
        if (statusNode == null) {
            ctxt.reportInputMismatch(DemoStructure.class, "Missing required DemoStatus element");
            return null;
        }
        
        DemoStatus status = codec.treeToValue(statusNode, DemoStatus.class);
        DemoStructure.DemoStructureBuilder builder = DemoStructure.builder().status(status);
        
        // DemoAttribute is optional
        JsonNode attrNode = node.get("DemoAttribute");
        if (attrNode != null && !attrNode.isNull()) {
            DemoAttribute attr = codec.treeToValue(attrNode, DemoAttribute.class);
            builder.demoAttribute(attr);
        }
        
        DemoStructure structure = builder.build();
        if (!structure.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(DemoStructure.class, "Structure not supported for spec %s", spec);
            return null;
        }
        return structure;
    }
}
```

Register in `KmipXmlModule` constructor:

```java
addSerializer(DemoStructure.class, new DemoStructureXmlSerializer());
addDeserializer(DemoStructure.class, new DemoStructureXmlDeserializer());
```

## TTLV

```java
package org.purpleBean.kmip.codec.ttlv.serializer.demo;

import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.ttlv.TtlvObject;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvSerializer;
import org.purpleBean.kmip.demo.structure.DemoStructure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class DemoStructureTtlvSerializer implements TtlvSerializer<DemoStructure> {
    @Override
    public ByteBuffer serialize(DemoStructure value, TtlvMapper mapper) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new UnsupportedEncodingException("DemoStructure not supported for spec " + spec);
        }
        // Structure writes its children via mapper serialization
        byte[] tag = value.getKmipTag().getTagBytes();
        // For structures, we don't write a value attribute
        byte type = value.getEncodingType().getTypeValue();
        // Always serialize status first
        List<ByteBuffer> nestedObjects = new ArrayList<>();
        for (KmipDataType v : value.getValues()) {
            if (v != null) {
                nestedObjects.add(mapper.writeValueAsByteBuffer(v));
            }
        }

        int totalLength = nestedObjects.stream().mapToInt(ByteBuffer::remaining).sum();
        ByteBuffer payloadBuffer = ByteBuffer.allocate(totalLength);
        nestedObjects.forEach(payloadBuffer::put);
        byte[] payload = payloadBuffer.array();

        return TtlvObject.builder().tag(tag).type(type).value(payload).build().toByteBuffer();
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
import org.purpleBean.kmip.demo.structure.DemoStructure;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DemoStructureTtlvDeserializer implements TtlvDeserializer<DemoStructure> {
    @Override
    public DemoStructure deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
        KmipSpec spec = KmipContext.getSpec();
        // Deserialize status (required)
        if (!ttlvBuffer.hasRemaining()) {
            throw new IOException("Missing required DemoStatus in TTLV structure");
        }
        DemoStatus status = mapper.readValue(ttlvBuffer, DemoStatus.class);
        
        // Build structure with optional demoAttribute
        DemoStructure.DemoStructureBuilder builder = DemoStructure.builder().status(status);
        
        // Deserialize demoAttribute if present
        if (ttlvBuffer.hasRemaining()) {
            DemoAttribute attr = mapper.readValue(ttlvBuffer, DemoAttribute.class);
            builder.demoAttribute(attr);
        }
        
        return builder.build();

        /* 
        Or use below builder pattern with switch case 
        to deserialize objects with optional or unordered fields / ttlv buffers
        
            List<TtlvObject> nestedObjects = TtlvObject.fromBytesMultiple(obj.getValue());
            DemoStructureBuilder builder = DemoStructure.builder();
    
            for (TtlvObject ttlvObject : nestedObjects) {
                KmipTag.Value nodeTag = KmipTag.fromBytes(spec, ttlvObject.getTag());
                setValue(builder, nodeTag, ttlvObject, mapper);
            }
    
            DemoStructure structure = builder.build();
        */
        
        if (!structure.isSupportedFor(spec)) {
            throw new IllegalArgumentException("Structure not supported for spec " + spec);
        }
        return structure;
    }

private void setValue(DemoStructureBuilder builder, KmipTag.Value nodeTag, TtlvObject ttlvObject, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.DEMO_STATUS ->
                    builder.status(mapper.readValue(ttlvObject.toByteBuffer(), DemoStatus.class));
            case KmipTag.Standard.DEMO_ATTRIBUTE ->
                    builder.demoAttribute(mapper.readValue(ttlvObject.toByteBuffer(), DemoAttribute.class));
            default -> throw new IllegalArgumentException();
        }
    }
}
```

Register with a TTLV module (e.g., in `KmipTtlvModule` constructor or a custom module):

```java
addSerializer(DemoStructure.class, new DemoStructureTtlvSerializer());
addDeserializer(DemoStructure.class, new DemoStructureTtlvDeserializer());
```
