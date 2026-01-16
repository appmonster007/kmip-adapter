package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.NonceValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("NonceValue Xml Serialization Tests")
class NonceValueXmlTest extends AbstractXmlSerializationTestSuite<NonceValue> {

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
        return NonceValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));  // TODO: Create a default instance
    }

    @Override
    protected NonceValue createVariant() {
        return NonceValue.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));  // TODO: Create a variant instance
    }
}