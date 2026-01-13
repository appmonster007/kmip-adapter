package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.InvocationFieldLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InvocationFieldLength TTLV Serialization Tests")
class InvocationFieldLengthTtlvTest extends AbstractTtlvSerializationTestSuite<InvocationFieldLength> {

    @Override
    protected Class<InvocationFieldLength> type() {
        return InvocationFieldLength.class;
    }

    @Override
    protected InvocationFieldLength createDefault() {
        return InvocationFieldLength.of(128);
    }

    @Override
    protected InvocationFieldLength createVariant() {
        return InvocationFieldLength.of(256);
    }
}