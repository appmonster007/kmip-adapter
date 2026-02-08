package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("NonceId Json Serialization Tests")
class NonceIdJsonTest extends AbstractJsonSerializationTestSuite<NonceId> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<NonceId> type() {
        return NonceId.class;
    }

    @Override
    public NonceId createDefault() {
        return NonceId.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    public NonceId createVariant() {
        return NonceId.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}