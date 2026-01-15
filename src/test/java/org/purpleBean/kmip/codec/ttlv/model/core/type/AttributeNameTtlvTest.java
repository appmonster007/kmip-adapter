package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttributeName TTLV Serialization Tests")
class AttributeNameTtlvTest extends AbstractTtlvSerializationTestSuite<AttributeName> {

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
