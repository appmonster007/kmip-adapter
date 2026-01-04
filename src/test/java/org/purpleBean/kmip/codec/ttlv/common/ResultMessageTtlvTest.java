package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ResultMessage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ResultMessage TTLV Serialization Tests")
class ResultMessageTtlvTest extends AbstractTtlvSerializationSuite<ResultMessage> {

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