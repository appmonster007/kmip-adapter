package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SignatureData;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("SignatureData JSON Serialization Tests")
class SignatureDataJsonTest extends AbstractJsonSerializationTestSuite<SignatureData> {

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
