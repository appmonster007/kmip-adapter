package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("BatchErrorContinuationOption XML Serialization")
class BatchErrorContinuationOptionXmlTest extends AbstractXmlSerializationSuite<BatchErrorContinuationOption> {
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
