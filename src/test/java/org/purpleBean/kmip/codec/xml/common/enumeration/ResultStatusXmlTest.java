package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("ResultStatus XML Serialization")
class ResultStatusXmlTest extends AbstractXmlSerializationSuite<ResultStatus> {
    @Override
    protected Class<ResultStatus> type() {
        return ResultStatus.class;
    }

    @Override
    protected ResultStatus createDefault() {
        return new ResultStatus(ResultStatus.Standard.SUCCESS);
    }

    @Override
    protected ResultStatus createVariant() {
        return new ResultStatus(ResultStatus.Standard.OPERATION_FAILED);
    }
}
