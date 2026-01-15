package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CancellationResult;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CancellationResult TTLV Serialization")
class CancellationResultTtlvTest extends AbstractTtlvSerializationTestSuite<CancellationResult> {
    @Override
    protected Class<CancellationResult> type() {
        return CancellationResult.class;
    }

    @Override
    protected CancellationResult createDefault() {
        return CancellationResult.Standard.CANCELED.inst();
    }

    @Override
    protected CancellationResult createVariant() {
        return CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
    }
}
