package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueEnumeration;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeValue.Enumeration TTLV Serialization Tests")
class AttributeValueEnumerationTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeValueEnumeration> {

    @Override
    protected Class<AttributeValueEnumeration> type() {
        return AttributeValueEnumeration.class;
    }

    @Override
    protected AttributeValueEnumeration createDefault() {
        return AttributeValueEnumeration.of(123);
    }

    @Override
    protected AttributeValueEnumeration createVariant() {
        return AttributeValueEnumeration.of(456);
    }
}
