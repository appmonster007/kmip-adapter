package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeValue.Integer JSON Serialization Tests")
class AttributeValueIntegerJsonTest extends AbstractJsonSerializationTestSuite<AttributeValueInteger> {

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
