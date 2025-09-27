package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeName JSON Serialization Tests")
class AttributeNameJsonTest extends AbstractJsonSerializationSuite<AttributeName> {

    @Override
    protected Class<AttributeName> type() {
        return AttributeName.class;
    }

    @Override
    protected AttributeName createDefault() {
        return AttributeName.builder().value("attribute name").build();
    }

    @Override
    protected AttributeName createVariant() {
        return AttributeName.builder().value("attribute name variant").build();
    }
}
