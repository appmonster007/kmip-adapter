package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResultStatus TTLV Serialization")
class ResultStatusTtlvTest extends AbstractTtlvSerializationTestSuite<ResultStatus> {
    @Override
    public Class<ResultStatus> type() {
        return ResultStatus.class;
    }

    @Override
    public ResultStatus createDefault() {
        return ResultStatus.Standard.OPERATION_FAILED.inst();
    }

    @Override
    public ResultStatus createVariant() {
        return ResultStatus.Standard.OPERATION_PENDING.inst();
    }
}
