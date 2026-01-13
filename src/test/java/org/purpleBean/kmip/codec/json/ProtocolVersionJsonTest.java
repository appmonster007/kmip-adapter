package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProtocolVersion JSON Serialization")
class ProtocolVersionJsonTest extends AbstractJsonSerializationTestSuite<ProtocolVersion> {

    @Override
    protected Class<ProtocolVersion> type() {
        return ProtocolVersion.class;
    }

    @Override
    protected ProtocolVersion createDefault() {
        return ProtocolVersion.of(1, 2);
    }

    @Override
    protected ProtocolVersion createVariant() {
        return ProtocolVersion.of(2, 0);
    }

    @Override
    protected boolean unsupportedSpecShouldFailSerialize() {
        return false;
    }
}
