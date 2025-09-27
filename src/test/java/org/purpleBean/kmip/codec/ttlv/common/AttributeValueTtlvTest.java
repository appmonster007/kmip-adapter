package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("AttributeValue TTLV Serialization Tests")
class AttributeValueTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<AttributeValue> type() {
        return AttributeValue.class;
    }

    @Override
    protected AttributeValue createDefault() {
        return AttributeValue.of(FIXED_TIME);
    }

    @Override
    protected AttributeValue createVariant() {
        return AttributeValue.of(FIXED_TIME.plusDays(1));
    }
}
