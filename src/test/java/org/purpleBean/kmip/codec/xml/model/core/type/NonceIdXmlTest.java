package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.NonceId;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("NonceId Xml Serialization Tests")
class NonceIdXmlTest extends AbstractXmlSerializationTestSuite<NonceId> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<NonceId> type() {
        return NonceId.class;
    }

    @Override
    protected NonceId createDefault() {
        return NonceId.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected NonceId createVariant() {
        return NonceId.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}