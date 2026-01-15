package org.purpleBean.kmip.codec.xml.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SimpleRequestHeader XML Serialization")
class SimpleRequestHeaderXmlTest extends AbstractXmlSerializationTestSuite<SimpleRequestHeader> {

    @Override
    protected Class<SimpleRequestHeader> type() {
        return SimpleRequestHeader.class;
    }

    @Override
    protected SimpleRequestHeader createDefault() {
        return SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
    }

    @Override
    protected SimpleRequestHeader createVariant() {
        return SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(2, 0))
                .build();
    }

    @Override
    protected boolean unsupportedSpecShouldFailSerialize() {
        return false; // model supports UnsupportedVersion
    }
}
