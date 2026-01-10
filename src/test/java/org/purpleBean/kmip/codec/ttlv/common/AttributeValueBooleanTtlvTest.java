package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueBoolean;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Boolean TTLV Serialization Tests")
class AttributeValueBooleanTtlvTest extends AbstractTtlvSerializationSuite<AttributeValueBoolean> {

    @Override
    protected Class<AttributeValueBoolean> type() {
        return AttributeValueBoolean.class;
    }

    @Override
    protected AttributeValueBoolean createDefault() {
        return AttributeValueBoolean.of(true);
    }

    @Override
    protected AttributeValueBoolean createVariant() {
        return AttributeValueBoolean.of(false);
    }
}
