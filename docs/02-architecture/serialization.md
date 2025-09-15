# Serialization Framework

## Overview

The KMIP Adapter provides a robust, type-safe serialization framework that supports multiple formats while maintaining strict KMIP protocol compliance. The framework is built on a modular architecture that enables seamless integration with various serialization formats and custom types.

## Supported Formats

| Format | Module Class | Description | Use Case |
|--------|--------------|-------------|----------|
| TTLV | `KmipTtlvModule` | Native KMIP binary format (Tag-Type-Length-Value) | High-performance, compact binary communication with KMIP servers |
| JSON | `KmipJsonModule` | Human-readable JSON format | Web APIs, debugging, and configuration |
| XML | `KmipXmlModule` | Standard XML format | Integration with XML-based systems and tools |

See also: Boilerplates for [Structure](../03-guides/development/boilerplate-structure.md), [Attribute](../03-guides/development/boilerplate-attribute.md), and [Enumeration](../03-guides/development/boilerplate-enum.md) for copy-ready, spec-aligned templates.

## Core Components

### `KmipContext`
Thread-local context that manages the current KMIP specification version for the calling thread. Use static methods to set/get/clear the spec.

```java
// Set KMIP specification version for current thread
KmipContext.setSpec(KmipSpec.V1_2);

try {
    // Perform serialization/deserialization with this spec...
} finally {
    // Always clear when done
    KmipContext.clear();
}
```

## Test Layout and Reuse

- Per-class tests mirror runtime packages under each codec for ease of discovery:
  - JSON: `src/test/java/org/purpleBean/kmip/codec/json/common/.../*JsonTest.java`
  - TTLV: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/.../*TtlvTest.java`
  - XML: `src/test/java/org/purpleBean/kmip/codec/xml/common/.../*XmlTest.java`
- Request structures have codec tests under `.../structure/request/*` and unit tests under `src/test/java/org/purpleBean/kmip/common/structure/request/*`.
- Shared test base `BaseKmipTest` configures JSON/XML mappers with `JavaTimeModule`, `KmipJsonModule`, `KmipXmlModule`, and manages `KmipContext` lifecycle.
- Note: Some XML tests validate serialization structure instead of full round-trips where deserializer array semantics differ from serializer output (e.g., request message items). This is intentional and documented in the tests.

See also: Boilerplates for [Structure](../03-guides/development/boilerplate-structure.md), [Attribute](../03-guides/development/boilerplate-attribute.md), and [Enumeration](../03-guides/development/boilerplate-enum.md) for end-to-end examples with tests and registration.

### Serialization Modules

1. **`KmipTtlvModule`**
   - Handles native KMIP TTLV format
   - Supports all standard KMIP data types
   - Optimized for performance and memory efficiency

2. **`KmipJsonModule`**
   - Provides JSON serialization with proper type mapping
   - Supports pretty-printing for readability
   - Configurable naming strategies

3. **`KmipXmlModule`**
   - Enables XML-based serialization
   - Supports XML namespaces and schemas
   - Configurable XML formatting

## Basic Usage

### JSON Serialization Example

```java
// 1. Configure ObjectMapper with JavaTime and KMIP JSON modules
ObjectMapper jsonMapper = new ObjectMapper();
jsonMapper.findAndRegisterModules();
jsonMapper.registerModule(new JavaTimeModule());
jsonMapper.registerModule(new KmipJsonModule());
jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

// 2. Set KMIP spec using thread-local context
KmipContext.setSpec(KmipSpec.V1_2);
try {
    // 3. Create a sample KMIP object
    ProtocolVersion version = ProtocolVersion.of(1, 2);

    // 4. Serialize to JSON
    String json = jsonMapper.writeValueAsString(version);

    // 5. Deserialize back to object
    ProtocolVersion deserialized = jsonMapper.readValue(json, ProtocolVersion.class);
} finally {
    // 6. Clean up context
    KmipContext.clear();
}
```

### TTLV Serialization Example

```java
// 1. Set KMIP spec for current thread
KmipContext.setSpec(KmipSpec.V1_2);
try {
    // 2. Create TTLV mapper and register the KMIP TTLV module
    TtlvMapper ttlvMapper = new TtlvMapper();
    ttlvMapper.registerModule(new KmipTtlvModule());

    // 3. Create a sample KMIP object
    State state = new State(State.Standard.ACTIVE);

    // 4. Serialize to TTLV bytes
    byte[] ttlvData = ttlvMapper.writeValueAsBytes(state);

    // 5. Deserialize back to object
    State roundTripped = ttlvMapper.readValue(ttlvData, State.class);
} finally {
    KmipContext.clear();
}
```

## Advanced Configuration

### Custom Type Registration

```java
// 1. Create custom serializers/deserializers
public class CustomTypeSerializer extends StdSerializer<CustomType> {
    public CustomTypeSerializer() {
        super(CustomType.class);
    }

    @Override
    public void serialize(CustomType value, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        // Custom serialization logic
    }
}

// 2. Create a custom module
SimpleModule customModule = new SimpleModule("CustomKMIP")
    .addSerializer(CustomType.class, new CustomTypeSerializer())
    .addDeserializer(CustomType.class, new CustomTypeDeserializer());

// 3. Register with ObjectMapper
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new KmipJsonModule())
    .registerModule(customModule);

// 4. (Optional) Register your custom module with ObjectMapper as shown above.
```

### Performance Notes

- `TtlvMapper` does not take a config object; register serializers via `KmipTtlvModule`.
- Reuse `ObjectMapper`/`XmlMapper` instances; they are thread-safe after configuration.
- Always register `JavaTimeModule` and the relevant KMIP module (`KmipJsonModule` / `KmipXmlModule`) when working with date/time types.
- Keep `KmipContext` scope minimal and always clear it after use.

## Best Practices

1. **Context Management**
   - Always use try-with-resources or try-finally to ensure proper cleanup
   - Set the appropriate KMIP version before serialization/deserialization
   - Avoid sharing contexts between threads

2. **Error Handling**
   - Catch specific exceptions (e.g., `KmipSerializationException`)
   - Provide meaningful error messages
   - Implement retry logic for transient failures

3. **Performance**
   - Reuse ObjectMapper instances (they are thread-safe after configuration)
   - Consider using object pooling for TTLV encoders/decoders
   - Disable validation in production for better performance

4. **Versioning**
   - Always specify the KMIP version explicitly
   - Test with all supported KMIP versions
   - Handle version-specific features gracefully

5. **Security**
   - Validate all input before deserialization
   - Use secure parsers with proper configuration
   - Limit depth and size of parsed structures

## Troubleshooting

### Common Issues

1. **Version Context Not Set/Cleared**
   - Ensure `KmipContext.setSpec(...)` is called before operations that depend on KMIP version.
   - Always call `KmipContext.clear()` in a `finally` block when done.

2. **Missing TTLV Module Registration**
   - Before using `TtlvMapper`, register `new KmipTtlvModule()`:
   ```java
   TtlvMapper ttlvMapper = new TtlvMapper();
   ttlvMapper.registerModule(new KmipTtlvModule());
   ```

3. **Type Not Registered**
   - If you see "No serializer registered for type", verify the type is supported by the registered module.

4. **Invalid Data**
   - Use types that exist in this project, e.g., `ProtocolVersion`, `State`, or `SampleStructure`:
   ```java
   SampleStructure obj = ttlvMapper.readValue(bytes, SampleStructure.class);
   ```

3. **Performance Bottlenecks**
   - Enable debug logging to identify slow operations
   - Profile memory usage during large serialization operations
   - Consider using streaming APIs for large objects

### Version-Specific Serialization

```java
public class CustomTypeSerializer extends JsonSerializer<CustomType> {
    @Override
    public void serialize(
        CustomType value, 
        JsonGenerator gen, 
        SerializerProvider provider
    ) throws IOException {
        KmipSpec spec = KmipContext.getSpec();
        
        gen.writeStartObject();
        
        // Version-specific serialization
        if (spec.getMajor() == 1 && spec.getMinor() == 2) {
            gen.writeNumberField("value", value.getValue());
        } else {
            // KMIP 1.4+ uses a different format
            gen.writeObjectField("extendedValue", value.getExtendedValue());
        }
        
        gen.writeEndObject();
    }
}
```

## Additional Examples

### Serializing a `SampleStructure` across formats

```java
// JSON
ObjectMapper json = new ObjectMapper();
json.findAndRegisterModules();
json.registerModule(new JavaTimeModule());
json.registerModule(new KmipJsonModule());
String jsonOut = json.writeValueAsString(sample);

// XML
XmlMapper xml = new XmlMapper();
xml.findAndRegisterModules();
xml.registerModule(new JavaTimeModule());
xml.registerModule(new KmipXmlModule());
String xmlOut = xml.writeValueAsString(sample);

// TTLV
TtlvMapper ttlv = new TtlvMapper();
ttlv.registerModule(new KmipTtlvModule());
byte[] ttlvOut = ttlv.writeValueAsBytes(sample);
```
## Performance Considerations

### Object Reuse

For high-performance scenarios:

- Reuse configured `ObjectMapper`/`XmlMapper` instances (they are thread-safe after configuration).
- Reuse a configured `TtlvMapper` instance and register `KmipTtlvModule` once.
- Keep `KmipContext` scope minimal (set spec before work; clear in finally).

```java
// Configure once
ObjectMapper json = new ObjectMapper();
json.findAndRegisterModules();
json.registerModule(new JavaTimeModule());
json.registerModule(new KmipJsonModule());
XmlMapper xml = new XmlMapper();
xml.findAndRegisterModules();
xml.registerModule(new JavaTimeModule());
xml.registerModule(new KmipXmlModule());
TtlvMapper ttlv = new TtlvMapper();
ttlv.registerModule(new KmipTtlvModule());

// Reuse across operations
String jsonOut = json.writeValueAsString(obj);
byte[] ttlvOut = ttlv.writeValueAsBytes(obj);
```

### Thread Safety

- `KmipContext` uses thread-local storage via static methods; set the spec per thread and always clear it.
- Jackson mappers are thread-safe after configuration.
- `TtlvMapper` is stateless after registration (safe to reuse across threads).

## Error Handling

When you encounter errors:

- Ensure the current thread’s KMIP spec is set with `KmipContext.setSpec(...)` and cleared in a `finally` block.
- Verify the appropriate module is registered:
  - JSON: `new KmipJsonModule()` on `ObjectMapper`
  - XML: `new KmipXmlModule()` on `XmlMapper`
  - TTLV: `new KmipTtlvModule()` on `TtlvMapper`
- Confirm the type you are serializing/deserializing is supported by those modules (e.g., `ProtocolVersion`, `State`, `ActivationDateAttribute`, `SampleStructure`).


### Error Handling Examples

1. **Basic Deserialization with Context Management**
   ```java
   public <T> T deserialize(byte[] ttlvData, Class<T> type) {
       KmipContext.setSpec(KmipSpec.V1_4);  // Set appropriate spec version
       try {
           TtlvMapper ttlvMapper = new TtlvMapper();
           ttlvMapper.registerModule(new KmipTtlvModule());
           
           return ttlvMapper.readValue(ttlvData, type);
       } catch (Exception e) {
           log.error("Failed to deserialize data", e);
           throw new RuntimeException("Deserialization failed", e);
       } finally {
           KmipContext.clear();  // Always clear the context
       }
   }
   ```

2. **Version-Aware Deserialization**
   ```java
   public <T> T deserializeWithFallback(byte[] data, Class<T> type) {
       // Try with supported specs in order of preference
       KmipSpec[] supportedSpecs = {KmipSpec.V1_4, KmipSpec.V1_2};
       
       for (KmipSpec spec : supportedSpecs) {
           KmipContext.setSpec(spec);
           try {
               TtlvMapper ttlvMapper = new TtlvMapper();
               ttlvMapper.registerModule(new KmipTtlvModule());
               
               return ttlvMapper.readValue(data, type);
           } catch (Exception e) {
               log.debug("Failed to deserialize with spec {}", spec, e);
               // Try next spec
           } finally {
               KmipContext.clear();
           }
       }
       throw new IllegalStateException("Failed to deserialize with any supported spec");
   }
   ```

## Best Practices

1. **Versioning**: Always specify the KMIP version when creating a codec context
2. **Validation**: Validate objects before serialization
3. **Error Handling**: Handle serialization errors appropriately
4. **Performance**: Reuse objects and buffers when possible
5. **Thread Safety**: Be aware of thread safety requirements
6. **Testing**: Test serialization round-trips for all types
7. **Documentation**: Document custom serialization logic including:
   - Supported KMIP versions
   - Thread safety guarantees
   - Performance characteristics
   - Any special handling for edge cases
