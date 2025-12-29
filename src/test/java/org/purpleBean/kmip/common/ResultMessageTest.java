package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.util.List;

@DisplayName("ResultMessage Domain Tests")
class ResultMessageTest extends AbstractKmipDataTypeSuite<ResultMessage> {

    @Override
    protected Class<ResultMessage> type() {
        return ResultMessage.class;
    }

    @Override
    protected ResultMessage createDefault() {
        return ResultMessage.builder().value("Success").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}