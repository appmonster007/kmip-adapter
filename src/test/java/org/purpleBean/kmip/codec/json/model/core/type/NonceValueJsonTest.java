package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.NonceValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("NonceValue Json Serialization Tests")
class NonceValueJsonTest extends AbstractJsonSerializationTestSuite<NonceValue> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<NonceValue> type() {
        return NonceValue.class;
    }

    @Override
    protected NonceValue createDefault() {
        return NonceValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected NonceValue createVariant() {
        return NonceValue.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}