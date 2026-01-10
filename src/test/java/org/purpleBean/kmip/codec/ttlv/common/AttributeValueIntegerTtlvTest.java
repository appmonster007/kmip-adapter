package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Integer TTLV Serialization Tests")
class AttributeValueIntegerTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueInteger> {

    @Override
    protected Class<AttributeValueInteger> type() {
        return AttributeValueInteger.class;
    }

    @Override
    protected AttributeValueInteger createDefault() {
        return AttributeValueInteger.of(123);
    }

    @Override
    protected AttributeValueInteger createVariant() {
        return AttributeValueInteger.of(456);
    }
}
