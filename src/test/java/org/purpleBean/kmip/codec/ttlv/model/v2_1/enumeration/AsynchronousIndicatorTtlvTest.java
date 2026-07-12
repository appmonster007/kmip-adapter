package org.purpleBean.kmip.codec.ttlv.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousIndicator TTLV Serialization")
class AsynchronousIndicatorTtlvTest extends AbstractTtlvSerializationTestSuite<AsynchronousIndicator> {
    @Override
    public Class<AsynchronousIndicator> type() {
        return AsynchronousIndicator.class;
    }

    @Override
    public AsynchronousIndicator createDefault() {
        return AsynchronousIndicator.Standard.MANDATORY.inst();
    }

    @Override
    public AsynchronousIndicator createVariant() {
        return AsynchronousIndicator.Standard.OPTIONAL.inst();
    }
}
