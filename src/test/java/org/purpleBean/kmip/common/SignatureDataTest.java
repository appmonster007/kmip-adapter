package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.nio.ByteBuffer;

@DisplayName("SignatureData Domain Tests")
class SignatureDataTest extends AbstractKmipDataTypeTestSuite<SignatureData> {

    @Override
    protected Class<SignatureData> type() {
        return SignatureData.class;
    }

    @Override
    protected SignatureData createDefault() {
        return SignatureData.of(ByteBuffer.wrap("test signature data".getBytes()));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}
