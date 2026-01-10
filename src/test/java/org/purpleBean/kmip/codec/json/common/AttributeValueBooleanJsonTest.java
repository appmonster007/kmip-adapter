package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueBoolean;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeValue.Boolean JSON Serialization Tests")
class AttributeValueBooleanJsonTest extends AbstractJsonSerializationSuite<AttributeValueBoolean> {

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
