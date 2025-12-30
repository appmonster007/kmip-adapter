package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("AttributeValue.DateTime TTLV Serialization Tests")
class AttributeValueDateTimeTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.DateTime> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<AttributeValue.DateTime> type() {
        return AttributeValue.DateTime.class;
    }

    @Override
    protected AttributeValue.DateTime createDefault() {
        return AttributeValue.DateTime.of(FIXED_TIME);
    }

    @Override
    protected AttributeValue.DateTime createVariant() {
        return AttributeValue.DateTime.of(FIXED_TIME.plusDays(1));
    }
}
