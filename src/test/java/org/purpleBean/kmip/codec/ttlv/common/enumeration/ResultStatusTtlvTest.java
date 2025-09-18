package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ResultStatus TTLV Serialization")
class ResultStatusTtlvTest extends AbstractTtlvSerializationSuite<ResultStatus> {
    @Override
    protected Class<ResultStatus> type() {
        return ResultStatus.class;
    }

    @Override
    protected ResultStatus createDefault() {
        return new ResultStatus(ResultStatus.Standard.PLACEHOLDER_1);
    }

    @Override
    protected ResultStatus createVariant() {
        return new ResultStatus(ResultStatus.Standard.PLACEHOLDER_2);
    }
}
