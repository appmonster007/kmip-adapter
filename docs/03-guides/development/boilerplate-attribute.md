# KMIP Attribute Implementation Guide

This guide demonstrates the differences between implementing simple data types (KmipDataType only) and full attributes (KmipDataType + KmipAttribute) using actual generated code examples.

## Overview: KmipDataType vs KmipAttribute

### When to implement KmipDataType only
- Simple value wrappers that don't represent object attributes
- Internal data structures used in serialization
- Composite types that aren't directly managed object properties

### When to implement both KmipDataType and KmipAttribute
- Properties of managed objects (certificates, keys, etc.)
- Values that have lifecycle and state-dependent behavior
- Attributes that need metadata and factory methods

## 1. Simple Data Type: FooDataType (KmipDataType only)

**File:** `src/main/java/org/purpleBean/kmip/common/FooDataType.java`

```java
package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.time.OffsetDateTime;
import java.util.Set;

/**
 * KMIP FooDataType - simple data type implementation.
 */
@Data
@Builder
public class FooDataType implements KmipDataType {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FOO_DATA_TYPE);
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, FooDataType.class);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    public static FooDataType of(@NonNull OffsetDateTime value) {
        return FooDataType.builder().value(value).build();
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }
}
```

## 2. Full Attribute: ActivationDate (KmipDataType + KmipAttribute)

**File:** `src/main/java/org/purpleBean/kmip/common/ActivationDate.java`

```java
package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP ActivationDate attribute - full attribute implementation.
 */
@Data
@Builder
public class ActivationDate implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            // Dual registration - key difference from FooDataType
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ActivationDate.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ActivationDate.class, ActivationDate::of);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    // Attribute factory method - required for KmipAttribute
    public static ActivationDate of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof OffsetDateTime dateTime)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new ActivationDate(dateTime);
    }

    // KmipDataType interface methods
    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }

    // KmipAttribute interface methods - additional to KmipDataType
    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.builder().encodingType(encodingType).value(value).build();
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }

    // State-aware behavior methods
    @Override
    public boolean isClientModifiable(@NonNull State state) {
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    // Lifecycle policy methods
    @Override
    public boolean isClientDeletable() {
        return false;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return false;
    }

    @Override
    public boolean isAlwaysPresent() {
        return false;
    }

    @Override
    public boolean isServerInitializable() {
        return true;
    }

    @Override
    public boolean isClientInitializable() {
        return true;
    }

    // Custom equals/hashCode for OffsetDateTime precision
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationDate that = (ActivationDate) o;
        return this.value.withNano(0).equals(that.value.withNano(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.withNano(0));
    }
}
```

## 3. Key Differences Summary

| Aspect | FooDataType (KmipDataType) | ActivationDate (KmipAttribute) |
|--------|---------------------------|--------------------------------|
| **Registration** | Single: `KmipDataType.register()` | Dual: Both `KmipDataType.register()` and `KmipAttribute.register()` |
| **Factory Method** | Simple `of(value)` | Attribute factory `of(AttributeName, AttributeValue)` |
| **Metadata Methods** | Basic KMIP methods only | Additional: `getAttributeValue()`, `getAttributeName()`, `getCanonicalName()` |
| **State Awareness** | None | State-dependent modifiability checks |
| **Lifecycle Methods** | None | Initialization, deletion, multi-instance policies |
| **Use Case** | Internal data structures | Managed object properties |

## 4. Testing Patterns

### Simple Data Type Test (FooDataType)

**File:** `src/test/java/org/purpleBean/kmip/common/FooDataTypeTest.java`

```java
package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.EncodingType;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FooDataType Domain Tests")
class FooDataTypeTest {

    @Test
    @DisplayName("Should create FooDataType with expected encoding type")
    void testDefaultCreation() {
        OffsetDateTime now = OffsetDateTime.now();
        FooDataType fooDataType = FooDataType.of(now);
        
        assertThat(fooDataType.getValue()).isEqualTo(now);
        assertThat(fooDataType.getEncodingType()).isEqualTo(EncodingType.DATE_TIME);
    }
}
```

### Full Attribute Test (ActivationDate)

**File:** `src/test/java/org/purpleBean/kmip/common/ActivationDateTest.java`

```java
package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeAttributeSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("ActivationDate Domain Tests")
class ActivationDateTest extends AbstractKmipDataTypeAttributeSuite<ActivationDate> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ActivationDate> type() {
        return ActivationDate.class;
    }

    @Override
    protected ActivationDate createDefault() {
        return ActivationDate.builder().value(FIXED_TIME).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.DATE_TIME;
    }

    @Override
    protected boolean expectAlwaysPresent() {
        return false;
    }

    @Override
    protected boolean expectServerInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientInitializable() {
        return true;
    }

    @Override
    protected boolean expectClientDeletable() {
        return false;
    }

    @Override
    protected boolean expectMultiInstanceAllowed() {
        return false;
    }

    @Override
    protected State stateForServerModifiableTrue() {
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForServerModifiableFalse() {
        return new State(State.Standard.ACTIVE);
    }

    @Override
    protected State stateForClientModifiableTrue() {
        return new State(State.Standard.PRE_ACTIVE);
    }

    @Override
    protected State stateForClientModifiableFalse() {
        return new State(State.Standard.ACTIVE);
    }
}
```

## 5. Serialization Examples

Both types use similar serialization patterns, but attributes have additional validation:

### JSON Serializer (ActivationDate)

**File:** `src/main/java/org/purpleBean/kmip/codec/json/serializer/kmip/common/ActivationDateJsonSerializer.java`

```java
package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ActivationDate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ActivationDateJsonSerializer extends KmipDataTypeJsonSerializer<ActivationDate> {

    @Override
    public void serialize(ActivationDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;

        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new UnsupportedEncodingException(
                String.format("%s is not supported for KMIP spec %s", value.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(value.getKmipTag());
        gen.writeStringField("type", value.getEncodingType().getDescription());
        gen.writeObjectField("value", value.getValue());
        gen.writeEndObject();
    }
}
```

## 6. Implementation Checklist

### For KmipDataType only (like FooDataType):
- [ ] Define static `kmipTag` and `encodingType`
- [ ] Define `supportedVersions` set
- [ ] Register with `KmipDataType.register()` in static block
- [ ] Implement `getKmipTag()`, `getEncodingType()`, `isSupported()`
- [ ] Add factory method `of(value)`
- [ ] Create simple domain test

### For KmipAttribute (like ActivationDate):
- [ ] All KmipDataType requirements above
- [ ] **Additional:** Register with `KmipAttribute.register()` in static block
- [ ] **Additional:** Implement attribute factory `of(AttributeName, AttributeValue)`
- [ ] **Additional:** Implement `getAttributeValue()`, `getAttributeName()`, `getCanonicalName()`
- [ ] **Additional:** Implement state-aware methods: `isClientModifiable()`, `isServerModifiable()`
- [ ] **Additional:** Implement lifecycle methods: `isAlwaysPresent()`, `isServerInitializable()`, etc.
- [ ] **Additional:** Create test extending `AbstractKmipDataTypeAttributeSuite`

## 7. Best Practices

1. **Use builder pattern** with Lombok for immutable objects
2. **Validate inputs** in factory methods and constructors
3. **Handle OffsetDateTime precision** in equals/hashCode if needed
4. **Follow state-dependent behavior** patterns for modifiability
5. **Use comprehensive test suites** for full attribute coverage
6. **Register both interfaces** when implementing KmipAttribute
