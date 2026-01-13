package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousCorrelationValue TTLV Serialization Tests")
class AsynchronousCorrelationValueTtlvTest extends AbstractTtlvSerializationTestSuite<AsynchronousCorrelationValue> {

    @Override
    protected Class<AsynchronousCorrelationValue> type() {
        return AsynchronousCorrelationValue.class;
    }

    @Override
    protected AsynchronousCorrelationValue createDefault() {
        return AsynchronousCorrelationValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected AsynchronousCorrelationValue createVariant() {
        return AsynchronousCorrelationValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}