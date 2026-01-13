package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeValue.TextString JSON Serialization Tests")
class AttributeValueTextStringJsonTest extends AbstractJsonSerializationTestSuite<AttributeValueTextString> {

    @Override
    protected Class<AttributeValueTextString> type() {
        return AttributeValueTextString.class;
    }

    @Override
    protected AttributeValueTextString createDefault() {
        return AttributeValueTextString.of("test");
    }

    @Override
    protected AttributeValueTextString createVariant() {
        return AttributeValueTextString.of("variant");
    }
}
