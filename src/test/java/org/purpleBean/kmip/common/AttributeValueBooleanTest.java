package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeValue.Boolean Domain Tests")
class AttributeValueBooleanTest extends AbstractKmipDataTypeSuite<AttributeValue.Boolean> {

    @Override
    protected Class<AttributeValue.Boolean> type() {
        return AttributeValue.Boolean.class;
    }

    @Override
    protected AttributeValue.Boolean createDefault() {
        return AttributeValue.Boolean.of(true);
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BOOLEAN;
    }
}
