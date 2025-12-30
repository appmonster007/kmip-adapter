package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.Boolean JSON Serialization Tests")
class AttributeValueBooleanJsonTest extends AbstractJsonSerializationSuite<AttributeValue.Boolean> {

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
