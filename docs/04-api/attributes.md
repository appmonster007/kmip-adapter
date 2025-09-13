# KMIP Attributes

## Overview

Attributes in KMIP represent properties and characteristics of managed objects. This document covers the standard attributes and how to work with them.

## Core Attribute Example

### ActivationDateAttribute

Represents the Activation Date attribute in KMIP.

**Properties:**
- `value` (OffsetDateTime): The activation timestamp
- `tag` (KmipTag): `ActivationDate`
- `encoding` (EncodingType): `DateTime`

**Example:**
```java
// Construct the attribute
OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
ActivationDateAttribute attr = new ActivationDateAttribute(now);

// Accessors
OffsetDateTime value = attr.getValue();
KmipTag tag = attr.getTag();           // KmipTag.ACTIVATION_DATE
EncodingType type = attr.getEncoding(); // EncodingType.DATE_TIME
```

## Attribute Patterns and Customization

- Attributes implement `KmipAttribute` and therefore also inherit from `KmipDataType`.
- Each attribute must provide a KMIP tag and encoding type.
- Validation should be performed in constructors for required fields.
- Version-aware behavior (when applicable) should rely on `KmipContext.getSpec()`.

If you need custom attributes, follow the patterns in `KMIP_IMPLEMENTATION_GUIDE.md` and ensure:
- Strong typing for the attribute value
- Correct `KmipTag` mapping
- Proper `EncodingType`

## Serialization Examples

### JSON

```java
ObjectMapper mapper = new ObjectMapper();
mapper.findAndRegisterModules();
mapper.registerModule(new KmipJsonModule());

ActivationDateAttribute attr = new ActivationDateAttribute(
    OffsetDateTime.of(2024,1,1,0,0,0,0, ZoneOffset.UTC)
);

String json = mapper.writeValueAsString(attr);
ActivationDateAttribute roundTrip = mapper.readValue(json, ActivationDateAttribute.class);
```

### XML

```java
XmlMapper xmlMapper = new XmlMapper();
xmlMapper.findAndRegisterModules();
xmlMapper.registerModule(new KmipXmlModule());

String xml = xmlMapper.writeValueAsString(attr);
ActivationDateAttribute fromXml = xmlMapper.readValue(xml, ActivationDateAttribute.class);
```

### TTLV

```java
KmipContext.setSpec(KmipSpec.V1_4);
try {
    TtlvMapper ttlvMapper = new TtlvMapper();
    ttlvMapper.registerModule(new KmipTtlvModule());

    byte[] ttlv = ttlvMapper.writeValueAsBytes(attr);
    ActivationDateAttribute fromTtlv = ttlvMapper.readValue(ttlv, ActivationDateAttribute.class);
} finally {
    KmipContext.clear();
}
```

## Best Practices

1. **Strong Typing**: Use dedicated attribute classes (e.g., `ActivationDateAttribute`).
2. **Validation**: Validate constructor inputs; annotate required fields with Lombok `@NonNull` where used.
3. **Immutability**: Prefer final fields and immutable objects.
4. **Version Awareness**: For version-dependent behavior, rely on `KmipContext` and document requirements.
5. **Serialization**: Register the appropriate modules for JSON, XML, and TTLV.
6. **Error Handling**: Throw `IllegalArgumentException` for invalid inputs and `UnsupportedEncodingException` for unsupported spec features in serializers.
