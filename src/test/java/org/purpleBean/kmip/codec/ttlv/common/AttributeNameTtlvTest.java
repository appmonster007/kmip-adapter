package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AttributeName TTLV Serialization Tests")
class AttributeNameTtlvTest extends AbstractTtlvSerializationSuite<AttributeName> {

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
