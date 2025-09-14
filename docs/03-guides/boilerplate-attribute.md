# Boilerplate: Attributes and Per-Class Tests

Minimal, copy-ready patterns for adding a new KMIP attribute and its tests.

## Minimal Boilerplate (Copy-Ready)

### Main Code: `FooAttribute` (implements `KmipAttribute`)

```java
package org.purpleBean.kmip.foo.attribute;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime; // replace with your value type
import java.util.Set;

@Data
@Builder
public class FooAttribute implements KmipAttribute {
    private static final KmipTag.Value FOO_ATTRIBUTE_TAG = KmipTag.register(
            0x540201, "FooAttribute", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2)
    );

    @NonNull private final OffsetDateTime value; // replace with your type

    // KmipDataType contract
    @Override public KmipTag getKmipTag() { return new KmipTag(FOO_ATTRIBUTE_TAG); }
    @Override public EncodingType getEncodingType() { return EncodingType.DATE_TIME; } // adjust
    @Override public boolean isSupportedFor(@NonNull KmipSpec spec) { return true; }

    // KmipAttribute contract — tailor these per-attribute semantics
    @Override public boolean isAlwaysPresent() { return false; }
    @Override public boolean isServerInitializable() { return false; }
    @Override public boolean isClientInitializable() { return true; }
    @Override public boolean isServerModifiable(State state) { return false; }
    @Override public boolean isClientModifiable(State state) { return true; }
    @Override public boolean isClientDeletable() { return true; }
    @Override public boolean isMultiInstanceAllowed() { return false; }
}
```

### Factory (optional for tests): `FooFactory`

```java
package org.purpleBean.kmip.foo;

import java.time.OffsetDateTime;

public final class FooFactory {
    private FooFactory() {}

    public static FooAttribute createFooAttribute() {
        return FooAttribute.builder()
                .value(OffsetDateTime.now())
                .build();
    }
}
```

## Per-Class Test Boilerplates (Copy-Ready)

### JSON Test: `src/test/java/org/purpleBean/kmip/codec/json/common/FooAttributeJsonTest.java`

```java
package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.foo.FooFactory;
import org.purpleBean.kmip.foo.attribute.FooAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("FooAttribute JSON Tests")
class FooAttributeJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize FooAttribute")
    void roundTrip() {
        FooAttribute original = FooFactory.createFooAttribute();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, FooAttribute.class);
    }
}
```

### TTLV Test: `src/test/java/org/purpleBean/kmip/codec/ttlv/common/FooAttributeTtlvTest.java`

```java
package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.foo.FooFactory;
import org.purpleBean.kmip.foo.attribute.FooAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FooAttribute TTLV Tests")
class FooAttributeTtlvTest extends BaseKmipTest {

    private final TtlvMapper ttlvMapper = new TtlvMapper();

    FooAttributeTtlvTest() { ttlvMapper.registerModule(new KmipTtlvModule()); }

    @Test
    @DisplayName("Round-trip: FooAttribute TTLV")
    void roundTrip() throws Exception {
        FooAttribute original = FooFactory.createFooAttribute();
        var buf = ttlvMapper.writeValueAsByteBuffer(original);
        FooAttribute deserialized = ttlvMapper.readValue(buf, FooAttribute.class);
        assertThat(deserialized.getValue().toInstant().getEpochSecond())
            .isEqualTo(original.getValue().toInstant().getEpochSecond());
    }
}
```

### XML Test: `src/test/java/org/purpleBean/kmip/codec/xml/common/FooAttributeXmlTest.java`

```java
package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.foo.FooFactory;
import org.purpleBean.kmip.foo.attribute.FooAttribute;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("FooAttribute XML Tests")
class FooAttributeXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Serialize FooAttribute to XML and validate structure")
    void serialize_structure() {
        // For some request-like structures, prefer structure checks over round-trip
        FooAttribute original = FooFactory.createFooAttribute();
        SerializationTestUtils.testXmlSerialization(xmlMapper, original, xml -> {
            // assertThat(xml).contains("<FooAttribute>");
        });
    }
}
```
