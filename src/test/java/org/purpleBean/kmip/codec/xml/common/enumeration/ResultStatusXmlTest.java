package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResultStatus XML Serialization")
class ResultStatusXmlTest extends AbstractXmlSerializationTestSuite<ResultStatus> {
    @Override
    protected Class<ResultStatus> type() {
        return ResultStatus.class;
    }

    @Override
    protected ResultStatus createDefault() {
        return ResultStatus.Standard.SUCCESS.inst();
    }

    @Override
    protected ResultStatus createVariant() {
        return ResultStatus.Standard.OPERATION_FAILED.inst();
    }
}
