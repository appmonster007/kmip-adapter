package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("SignatureData TTLV Serialization Tests")
class SignatureDataTtlvTest extends AbstractTtlvSerializationTestSuite<SignatureData> {

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
