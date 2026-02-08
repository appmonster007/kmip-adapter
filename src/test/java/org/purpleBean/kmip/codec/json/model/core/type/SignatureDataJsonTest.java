package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("SignatureData JSON Serialization Tests")
class SignatureDataJsonTest extends AbstractJsonSerializationTestSuite<SignatureData> {

    @Override
    public Class<SignatureData> type() {
        return SignatureData.class;
    }

    @Override
    public SignatureData createDefault() {
        return SignatureData.of(ByteBuffer.wrap("test signature data".getBytes()));
    }

    @Override
    public SignatureData createVariant() {
        return SignatureData.of(ByteBuffer.wrap("variant signature data".getBytes()));
    }
}
