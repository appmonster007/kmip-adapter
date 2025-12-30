package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Integer TTLV Serialization Tests")
class AttributeValueIntegerTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.Integer> {

    @Override
    protected Class<AttributeValue.Integer> type() {
        return AttributeValue.Integer.class;
    }

    @Override
    protected AttributeValue.Integer createDefault() {
        return AttributeValue.Integer.of(123);
    }

    @Override
    protected AttributeValue.Integer createVariant() {
        return AttributeValue.Integer.of(456);
    }
}
