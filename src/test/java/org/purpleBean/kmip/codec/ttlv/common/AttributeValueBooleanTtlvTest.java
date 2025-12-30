package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Boolean TTLV Serialization Tests")
class AttributeValueBooleanTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.Boolean> {

    @Override
    protected Class<AttributeValue.Boolean> type() {
        return AttributeValue.Boolean.class;
    }

    @Override
    protected AttributeValue.Boolean createDefault() {
        return AttributeValue.Boolean.of(true);
    }

    @Override
    protected AttributeValue.Boolean createVariant() {
        return AttributeValue.Boolean.of(false);
    }
}
