# Serialization Guide

This guide covers the serialization and deserialization of KMIP objects in various formats (JSON, XML, TTLV).

## Table of Contents
- [Supported Formats](#supported-formats)
- [JSON Serialization](#json-serialization)
- [XML Serialization](#xml-serialization)
- [TTLV Serialization](#ttlv-serialization)
- [Custom Serializers](#custom-serializers)
- [Versioning](#versioning)
- [Performance Considerations](#performance-considerations)

## Supported Formats

The KMIP Adapter supports the following serialization formats:

1. **JSON**: For web APIs and configuration
2. **XML**: For legacy systems and SOAP-based web services
3. **TTLV**: Binary format for KMIP protocol messages

## JSON Serialization

### Basic Usage

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// Create and configure ObjectMapper
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new KmipJsonModule())
    .enable(SerializationFeature.INDENT_OUTPUT);

// Serialize to JSON
String json = mapper.writeValueAsString(yourKmipObject);

// Deserialize from JSON
YourKmipType obj = mapper.readValue(json, YourKmipType.class);
```

### Custom JSON Serializer Example

```java
public class YourTypeJsonSerializer extends JsonSerializer<YourType> {
    @Override
    public void serialize(YourType value, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeNumberField("value", value.getValue());
        gen.writeEndObject();
    }
}
```

## XML Serialization

### Basic Usage

```java
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// Create and configure XmlMapper
XmlMapper xmlMapper = new XmlMapper()
    .registerModule(new KmipXmlModule())
    .enable(SerializationFeature.INDENT_OUTPUT);

// Serialize to XML
String xml = xmlMapper.writeValueAsString(yourKmipObject);

// Deserialize from XML
YourKmipType obj = xmlMapper.readValue(xml, YourKmipType.class);
```

## TTLV Serialization

### Basic Usage

```java
import org.purpleBean.kmip.codec.ttlv.TtlvEncoder;
import org.purpleBean.kmip.codec.ttlv.TtlvDecoder;

// Serialize to TTLV
byte[] ttlv = TtlvEncoder.encode(yourKmipObject);

// Deserialize from TTLV
YourKmipType obj = TtlvDecoder.decode(ttlv, YourKmipType.class);
```

## Custom Serializers

### Registering Custom Serializers

```java
public class YourModule extends SimpleModule {
    public YourModule() {
        addSerializer(YourType.class, new YourTypeJsonSerializer());
        addDeserializer(YourType.class, new YourTypeJsonDeserializer());
    }
}

// Register the module
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new YourModule());
```

## Versioning

KMIP objects can be versioned to handle different KMIP specification versions:

```java
public class YourType implements KmipDataType {
    @JsonIgnore
    private KmipSpec spec = KmipContext.getSpec();
    
    // Version-specific behavior
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return spec.isAtLeast(KmipSpec.V1_2);
    }
}
```

## Performance Considerations

1. **Reuse ObjectMapper Instances**:
   - ObjectMapper is thread-safe after configuration
   - Create and reuse a single instance

2. **Pool Large Objects**:
   - Consider object pooling for large or frequently created objects

3. **Use Streaming API for Large Documents**:
   - For very large documents, use Jackson's streaming API

4. **Profile Serialization**:
   - Use JMH to benchmark serialization performance
   - Identify and optimize hot spots

## Testing Serialization

Always test serialization/deserialization round-trips:

```java
@Test
void testJsonRoundTrip() throws Exception {
    YourType original = createTestObject();
    String json = mapper.writeValueAsString(original);
    YourType deserialized = mapper.readValue(json, YourType.class);
    assertEquals(original, deserialized);
}
```

## Common Issues

1. **Circular References**:
   - Use `@JsonIdentityInfo` to handle circular references
   - Or use `@JsonManagedReference` and `@JsonBackReference`

2. **Version Compatibility**:
   - Test with different KMIP specification versions
   - Handle missing fields gracefully

3. **Performance Bottlenecks**:
   - Monitor memory usage during serialization
   - Watch for excessive object creation

4. **Character Encoding**:
   - Always specify UTF-8 encoding for text-based formats
   - Handle BOM (Byte Order Mark) in XML if needed
