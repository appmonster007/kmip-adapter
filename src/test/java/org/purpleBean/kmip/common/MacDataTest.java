package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.nio.ByteBuffer;

@DisplayName("MacData Domain Tests")
class MacDataTest extends AbstractKmipDataTypeTestSuite<MacData> {

    @Override
    protected Class<MacData> type() {
        return MacData.class;
    }

    @Override
    protected MacData createDefault() {
        return MacData.of(ByteBuffer.wrap("test mac data".getBytes()));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}
