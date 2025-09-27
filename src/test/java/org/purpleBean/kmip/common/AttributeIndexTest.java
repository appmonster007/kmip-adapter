package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("AttributeIndex Domain Tests")
class AttributeIndexTest extends AbstractKmipDataTypeSuite<AttributeIndex> {

    @Override
    protected Class<AttributeIndex> type() {
        return AttributeIndex.class;
    }

    @Override
    protected AttributeIndex createDefault() {
        return AttributeIndex.builder().value(10).build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.INTEGER;
    }
}
