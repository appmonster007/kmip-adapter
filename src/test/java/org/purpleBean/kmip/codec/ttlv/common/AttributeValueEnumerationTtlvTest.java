package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeValue.Enumeration TTLV Serialization Tests")
class AttributeValueEnumerationTtlvTest extends AbstractTtlvSerializationSuite<AttributeValue.Enumeration> {

    @Override
    protected Class<AttributeValue.Enumeration> type() {
        return AttributeValue.Enumeration.class;
    }

    @Override
    protected AttributeValue.Enumeration createDefault() {
        return AttributeValue.Enumeration.of(123);
    }

    @Override
    protected AttributeValue.Enumeration createVariant() {
        return AttributeValue.Enumeration.of(456);
    }
}
