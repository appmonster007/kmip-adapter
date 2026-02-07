package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeValue Ttlv Serialization Tests")
class AttributeValueTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValue> {

    @Override
    protected Class<AttributeValue> type() {
        return AttributeValue.class;
    }

    @Override
    protected AttributeValue createDefault() {
        return AttributeValue.ofTextString("default-string");
    }

    @Override
    protected AttributeValue createVariant() {
        return AttributeValue.ofInteger(123);
    }
}