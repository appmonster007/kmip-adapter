package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ResultMessage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResultMessage JSON Serialization Tests")
class ResultMessageJsonTest extends AbstractJsonSerializationTestSuite<ResultMessage> {

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