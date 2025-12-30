package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.Integer JSON Serialization Tests")
class AttributeValueIntegerJsonTest extends AbstractJsonSerializationSuite<AttributeValue.Integer> {

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
