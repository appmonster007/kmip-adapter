package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.structure.ProtocolVersion;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtocolVersion XML Serialization")
class ProtocolVersionXmlTest extends AbstractXmlSerializationTestSuite<ProtocolVersion> {

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
