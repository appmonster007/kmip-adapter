package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("AsynchronousIndicator TTLV Serialization Tests")
class AsynchronousIndicatorTtlvTest extends AbstractTtlvSerializationSuite<AsynchronousIndicator> {

    @Override
    protected Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    protected AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.builder().value(true).build();
    }

    @Override
    protected AsynchronousIndicator createVariant() {
        return AsynchronousIndicator.builder().value(false).build();
    }
}