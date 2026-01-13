package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ResultMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResultMessage XML Serialization Tests")
class ResultMessageXmlTest extends AbstractXmlSerializationTestSuite<ResultMessage> {

    @Override
    protected Class<ResultMessage> type() {
        return ResultMessage.class;
    }

    @Override
    protected ResultMessage createDefault() {
        return ResultMessage.builder().value("Success").build();
    }

    @Override
    protected ResultMessage createVariant() {
        return ResultMessage.builder().value("Failure").build();
    }
}