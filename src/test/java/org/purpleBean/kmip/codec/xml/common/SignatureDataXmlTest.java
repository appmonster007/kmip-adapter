package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SignatureData;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.nio.ByteBuffer;

@DisplayName("SignatureData XML Serialization Tests")
class SignatureDataXmlTest extends AbstractXmlSerializationSuite<SignatureData> {

    @Override
    protected Class<SignatureData> type() {
        return SignatureData.class;
    }

    @Override
    protected SignatureData createDefault() {
        return SignatureData.of(ByteBuffer.wrap("test signature data".getBytes()));
    }

    @Override
    protected SignatureData createVariant() {
        return SignatureData.of(ByteBuffer.wrap("variant signature data".getBytes()));
    }
}
