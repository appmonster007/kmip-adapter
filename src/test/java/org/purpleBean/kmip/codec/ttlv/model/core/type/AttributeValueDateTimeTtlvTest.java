package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValueDateTime;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@DisplayName("AttributeValue.DateTime TTLV Serialization Tests")
class AttributeValueDateTimeTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValueDateTime> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

    @Override
    protected Class<AttributeValueDateTime> type() {
        return AttributeValueDateTime.class;
    }

    @Override
    protected AttributeValueDateTime createDefault() {
        return AttributeValueDateTime.of(FIXED_TIME);
    }

    @Override
    protected AttributeValueDateTime createVariant() {
        return AttributeValueDateTime.of(FIXED_TIME.plusDays(1));
    }
}
