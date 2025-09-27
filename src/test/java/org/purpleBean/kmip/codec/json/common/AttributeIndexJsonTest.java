package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("AttributeIndex JSON Serialization Tests")
class AttributeIndexJsonTest extends AbstractJsonSerializationSuite<AttributeIndex> {

    @Override
    protected Class<AttributeIndex> type() {
        return AttributeIndex.class;
    }

    @Override
    protected AttributeIndex createDefault() {
        return AttributeIndex.builder().value(10).build();
    }

    @Override
    protected AttributeIndex createVariant() {
        return AttributeIndex.builder().value(50).build();
    }
}
