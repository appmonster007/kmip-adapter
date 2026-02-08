package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResultStatus JSON Serialization")
class ResultStatusJsonTest extends AbstractJsonSerializationTestSuite<ResultStatus> {
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
