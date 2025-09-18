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
        return new CancellationResult(CancellationResult.Standard.PLACEHOLDER_1);
    }

    @Override
    protected CancellationResult createVariant() {
        return new CancellationResult(CancellationResult.Standard.PLACEHOLDER_2);
    }
}
