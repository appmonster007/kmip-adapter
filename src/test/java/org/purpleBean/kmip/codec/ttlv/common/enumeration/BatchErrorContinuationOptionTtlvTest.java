package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("BatchErrorContinuationOption TTLV Serialization")
class BatchErrorContinuationOptionTtlvTest extends AbstractTtlvSerializationSuite<BatchErrorContinuationOption> {
    @Override
    protected Class<BatchErrorContinuationOption> type() {
        return BatchErrorContinuationOption.class;
    }

    @Override
    protected BatchErrorContinuationOption createDefault() {
        return new BatchErrorContinuationOption(BatchErrorContinuationOption.Standard.PLACEHOLDER_1);
    }

    @Override
    protected BatchErrorContinuationOption createVariant() {
        return new BatchErrorContinuationOption(BatchErrorContinuationOption.Standard.PLACEHOLDER_2);
    }
}
