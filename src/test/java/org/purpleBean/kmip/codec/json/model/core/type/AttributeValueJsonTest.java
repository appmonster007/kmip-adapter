package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeValue Json Serialization Tests")
class AttributeValueJsonTest extends AbstractJsonSerializationTestSuite<AttributeValue> {

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