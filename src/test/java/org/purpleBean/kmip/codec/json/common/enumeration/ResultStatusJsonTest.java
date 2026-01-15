package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResultStatus JSON Serialization")
class ResultStatusJsonTest extends AbstractJsonSerializationTestSuite<ResultStatus> {
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
