package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CancellationResult;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CancellationResult TTLV Serialization")
class CancellationResultTtlvTest extends AbstractTtlvSerializationSuite<CancellationResult> {
    @Override
    protected Class<CancellationResult> type() {
        return CancellationResult.class;
    }

    @Override
    protected CancellationResult createDefault() {
        return new CancellationResult(CancellationResult.Standard.CANCELED);
    }

    @Override
    protected CancellationResult createVariant() {
        return new CancellationResult(CancellationResult.Standard.UNABLE_TO_CANCEL);
    }
}
