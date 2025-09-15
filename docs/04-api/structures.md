# KMIP Structures

See also: [Tests Index](../03-guides/tests-index.md) • [Boilerplate: Structure](../03-guides/development/boilerplate-structure.md) • [Boilerplate: Attribute](../03-guides/development/boilerplate-attribute.md) • [Boilerplate: Enumeration](../03-guides/development/boilerplate-enum.md)

## Overview

Structures in KMIP represent complex data types that group related information. This document covers the core structures and their usage.

## Core Structures

### FooDemoStructure

Represents a demonstration composite structure for boilerplate patterns.

Properties:
- `attribute` (FooDemoAttribute): Required attribute field
- `mode` (FooDemoEnum): Optional enumeration field

Example:
```java
// Required attribute
FooDemoAttribute attribute = FooDemoAttribute.builder()
    .dateTime(java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC))
    .build();

// Optional enum (may be null)
FooDemoEnum mode = null; // or: new FooDemoEnum(FooDemoEnum.Standard.EXAMPLE_ONE)

// Build structure
FooDemoStructure foo = FooDemoStructure.builder()
    .attribute(attribute)
    .mode(mode)
    .build();
```

See also: Boilerplates for [Structure](../03-guides/development/boilerplate-structure.md), [Attribute](../03-guides/development/boilerplate-attribute.md), and [Enumeration](../03-guides/development/boilerplate-enum.md).

<!-- KeyWrappingData example omitted; keep API guide focused on implemented structures in this project. -->

<!-- TemplateAttribute example omitted; not part of current implemented types shown in docs. -->

## Message Structures

### ExampleRequestMessage

Represents a KMIP request message.

**Properties:**
- `requestHeader` (RequestHeader): Message header
- `batchItems` (List<RequestBatchItem>): List of batch items

**Example:**
```java
ExampleRequestHeader header = ExampleRequestHeader.builder()
    .protocolVersion(ProtocolVersion.of(1, 2))
    .build();

ExampleRequestBatchItem item = ExampleRequestBatchItem.builder()
    .operation("CREATE")
    .build();

ExampleRequestMessage request = ExampleRequestMessage.builder()
    .requestHeader(header)
    .batchItems(List.of(item))
    .build();
```

<!-- ResponseMessage example omitted; request-side structures are the canonical starting point in this project. -->

## Custom Structures

Follow the structure patterns in [Implementation Guide](../03-guides/implementation.md):
- Implement `KmipStructure`.
- Provide `getKmipTag()` and `getEncodingType()`.
- Expose contained KMIP values via `getValues()` where appropriate.
- Perform validation in constructors/builders; rely on `KmipContext.getSpec()` for version-specific checks.

### Registering Custom Structures

```java
// Create a module for custom serializers/deserializers
module.addSerializer(CustomStructure.class, new CustomStructureSerializer());
module.addDeserializer(CustomStructure.class, new CustomStructureDeserializer());

// Register the module with the object mapper
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(module);

// TTLV registration happens via TtlvMapper module
TtlvMapper ttlvMapper = new TtlvMapper();
ttlvMapper.registerModule(new KmipTtlvModule());
```

## Structure Serialization

```java
// JSON
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.findAndRegisterModules();
objectMapper.registerModule(new KmipJsonModule());
String json = objectMapper.writeValueAsString(request);

// XML
XmlMapper xmlMapper = new XmlMapper();
xmlMapper.findAndRegisterModules();
xmlMapper.registerModule(new KmipXmlModule());
String xml = xmlMapper.writeValueAsString(request);

// TTLV
TtlvMapper ttlvMapper = new TtlvMapper();
ttlvMapper.registerModule(new KmipTtlvModule());
byte[] ttlv = ttlvMapper.writeValueAsBytes(request);

```

### Deserialization

```java
// JSON
ExampleRequestMessage fromJson = objectMapper.readValue(json, ExampleRequestMessage.class);

// XML
ExampleRequestMessage fromXml = xmlMapper.readValue(xml, ExampleRequestMessage.class);

// TTLV
ExampleRequestMessage fromTtlv = ttlvMapper.readValue(ttlv, ExampleRequestMessage.class);
```

<!-- Validation helpers are enforced through constructors/builders and serializers. Add explicit checks where needed per KMIP guides. -->

## Best Practices

1. **Immutability**: Make structures immutable when possible
2. **Validation**: Validate all inputs in constructors and setters
3. **Documentation**: Document the purpose and constraints of each structure
4. **Thread Safety**: Ensure thread safety for shared structures
5. **Error Handling**: Provide meaningful error messages
6. **Testing**: Write comprehensive tests for all structures
7. **Versioning**: Consider versioning for backward compatibility
8. **Performance**: Be mindful of object creation in hot paths
9. **Null Safety**: Use `@NonNull` annotations and validate parameters
10. **Serialization**: Ensure proper serialization of all fields
