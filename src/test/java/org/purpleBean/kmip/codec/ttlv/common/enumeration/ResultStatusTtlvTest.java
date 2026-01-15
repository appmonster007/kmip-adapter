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
        return ResultStatus.Standard.OPERATION_FAILED.inst();
    }

    @Override
    protected ResultStatus createVariant() {
        return ResultStatus.Standard.OPERATION_PENDING.inst();
    }
}
