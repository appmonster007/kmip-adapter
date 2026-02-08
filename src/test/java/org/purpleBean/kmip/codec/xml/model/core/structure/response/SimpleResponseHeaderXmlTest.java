package org.purpleBean.kmip.codec.xml.model.core.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleResponseHeader Xml Serialization Tests")
class SimpleResponseHeaderXmlTest extends AbstractXmlSerializationTestSuite<SimpleResponseHeader> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<SimpleResponseHeader> type() {
        return SimpleResponseHeader.class;
    }

    @Override
    public SimpleResponseHeader createDefault() {
        return SimpleResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
                .build();
    }

    @Override
    public SimpleResponseHeader createVariant() {
        return SimpleResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
                .build();
    }
}
