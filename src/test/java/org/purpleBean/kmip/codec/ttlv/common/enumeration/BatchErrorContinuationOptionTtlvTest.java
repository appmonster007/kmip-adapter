package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("BatchErrorContinuationOption TTLV Serialization")
class BatchErrorContinuationOptionTtlvTest extends AbstractTtlvSerializationTestSuite<BatchErrorContinuationOption> {
    @Override
    protected Class<BatchErrorContinuationOption> type() {
        return BatchErrorContinuationOption.class;
    }

    @Override
    protected BatchErrorContinuationOption createDefault() {
        return BatchErrorContinuationOption.Standard.CONTINUE.inst();
    }

    @Override
    protected BatchErrorContinuationOption createVariant() {
        return BatchErrorContinuationOption.Standard.STOP.inst();
    }
}
