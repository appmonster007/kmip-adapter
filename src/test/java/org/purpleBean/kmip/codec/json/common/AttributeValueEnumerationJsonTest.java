package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueEnumeration;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.Enumeration JSON Serialization Tests")
class AttributeValueEnumerationJsonTest extends AbstractJsonSerializationSuite<AttributeValueEnumeration> {

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
