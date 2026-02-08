package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResultReason XML Serialization")
class ResultReasonXmlTest extends AbstractXmlSerializationTestSuite<ResultReason> {
    @Override
    public Class<ResultReason> type() {
        return ResultReason.class;
    }

    @Override
    public ResultReason createDefault() {
        return ResultReason.Standard.ITEM_NOT_FOUND.inst();
    }

    @Override
    public ResultReason createVariant() {
        return ResultReason.Standard.RESPONSE_TOO_LARGE.inst();
    }
}
