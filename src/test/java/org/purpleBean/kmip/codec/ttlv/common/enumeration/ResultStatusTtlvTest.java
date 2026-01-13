package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResultStatus TTLV Serialization")
class ResultStatusTtlvTest extends AbstractTtlvSerializationTestSuite<ResultStatus> {
    @Override
    protected Class<ResultStatus> type() {
        return ResultStatus.class;
    }

    @Override
    protected ResultStatus createDefault() {
        return new ResultStatus(ResultStatus.Standard.OPERATION_FAILED);
    }

    @Override
    protected ResultStatus createVariant() {
        return new ResultStatus(ResultStatus.Standard.OPERATION_PENDING);
    }
}
