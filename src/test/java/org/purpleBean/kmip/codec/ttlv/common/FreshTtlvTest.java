package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Fresh;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Fresh TTLV Serialization Tests")
class FreshTtlvTest extends AbstractTtlvSerializationTestSuite<Fresh> {

    @Override
    protected Class<Fresh> type() {
        return Fresh.class;
    }

    @Override
    protected Fresh createDefault() {
        return Fresh.builder().value(true).build();
    }

    @Override
    protected Fresh createVariant() {
        return Fresh.builder().value(false).build();
    }
}