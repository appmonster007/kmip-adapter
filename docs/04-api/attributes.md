# KMIP Attributes

See also: [Tests Index](../03-guides/tests-index.md) • [Boilerplate: Attribute Implementation Guide](../03-guides/development/boilerplate-attribute.md)

## Overview

Attributes in KMIP represent properties and characteristics of managed objects. This document covers the standard attributes and how to work with them.

## Data Types vs Attributes

### FooDataType (KmipDataType only)

Simple data type implementation that only implements `KmipDataType`.

**Properties:**
- `value` (OffsetDateTime): The datetime value
- `kmipTag` (KmipTag): `FOO_DATA_TYPE`
- `encodingType` (EncodingType): `DATE_TIME`

**Example:**
```java
// Construct the data type
OffsetDateTime now = OffsetDateTime.now();
FooDataType fooData = FooDataType.of(now);

// Accessors
OffsetDateTime value = fooData.getValue();
KmipTag tag = fooData.getKmipTag();           // FOO_DATA_TYPE tag
EncodingType type = fooData.getEncodingType(); // EncodingType.DATE_TIME
boolean supported = fooData.isSupported();    // checks current KmipContext
```

### ActivationDate (KmipDataType + KmipAttribute)

Full attribute implementation that implements both `KmipDataType` and `KmipAttribute`.

**Properties:**
- `value` (OffsetDateTime): The activation timestamp
- `kmipTag` (KmipTag): `ACTIVATION_DATE`
- `encodingType` (EncodingType): `DATE_TIME`

**Key Differences from FooDataType:**
- **Dual Registration**: Registers with both `KmipDataType.register()` and `KmipAttribute.register()`
- **Attribute Factory**: Provides `of(AttributeName, AttributeValue)` factory method
- **Attribute Metadata**: Implements `getAttributeValue()`, `getAttributeName()`, `getCanonicalName()`
- **State-Aware Behavior**: Implements modifiability checks based on object state
- **Lifecycle Methods**: Defines initialization, modification, and deletion policies

**Example:**
```java
// Construct the attribute
OffsetDateTime now = OffsetDateTime.now();
ActivationDate attr = ActivationDate.builder().value(now).build();

// KmipDataType interface
OffsetDateTime value = attr.getValue();
KmipTag tag = attr.getKmipTag();           // ACTIVATION_DATE tag
EncodingType type = attr.getEncodingType(); // EncodingType.DATE_TIME
boolean supported = attr.isSupported();    // checks current KmipContext

// KmipAttribute interface
AttributeValue attrValue = attr.getAttributeValue();
AttributeName attrName = attr.getAttributeName();
String canonicalName = attr.getCanonicalName();

// State-aware behavior
State preActive = new State(State.Standard.PRE_ACTIVE);
State active = new State(State.Standard.ACTIVE);
boolean canModifyPreActive = attr.isClientModifiable(preActive); // true
boolean canModifyActive = attr.isClientModifiable(active);       // false

// Lifecycle policies
boolean alwaysPresent = attr.isAlwaysPresent();           // false
boolean serverInit = attr.isServerInitializable();       // true
boolean clientInit = attr.isClientInitializable();       // true
boolean deletable = attr.isClientDeletable();            // false
boolean multiInstance = attr.isMultiInstanceAllowed();   // false
```

## Implementation Patterns

### KmipDataType vs KmipAttribute

**When to implement KmipDataType only (like FooDataType):**
- Simple value wrappers that don't represent object attributes
- Internal data structures used in serialization
- Composite types that aren't directly managed object properties

**When to implement both KmipDataType and KmipAttribute (like ActivationDate):**
- Properties of managed objects (certificates, keys, etc.)
- Values that have lifecycle and state-dependent behavior
- Attributes that need metadata and factory methods

### Required Implementation Elements for KmipAttribute

```java
public class CustomAttribute implements KmipDataType, KmipAttribute {
    // 1. Static metadata
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CUSTOM_ATTRIBUTE);
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_2);

    // 2. Dual registration in static block
    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CustomAttribute.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CustomAttribute.class, CustomAttribute::of);
        }
    }

    // 3. Attribute factory method
    public static CustomAttribute of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof ExpectedType)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new CustomAttribute((ExpectedType) attributeValue.getValue());
    }

    // 4. Attribute metadata methods
    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.builder().encodingType(encodingType).value(value).build();
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    // 5. State-aware behavior methods
    @Override
    public boolean isClientModifiable(@NonNull State state) {
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    // 6. Lifecycle policy methods
    @Override
    public boolean isAlwaysPresent() { return false; }
    @Override
    public boolean isServerInitializable() { return true; }
    @Override
    public boolean isClientInitializable() { return true; }
    // ... other lifecycle methods
}
```

### Testing Patterns

**For KmipDataType only (like FooDataType):**
```java
class FooDataTypeTest {
    @Test
    void testDefaultCreation() {
        FooDataType fooDataType = FooDataType.of(OffsetDateTime.now());
        assertThat(fooDataType.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
    }
}
```

**For KmipAttribute (like ActivationDate):**
```java
class ActivationDateTest extends AbstractKmipDataTypeAttributeSuite<ActivationDate> {
    @Override
    protected ActivationDate createDefault() {
        return ActivationDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return new State(State.Standard.ACTIVE);
    }
    // ... other test suite methods
}
```

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
