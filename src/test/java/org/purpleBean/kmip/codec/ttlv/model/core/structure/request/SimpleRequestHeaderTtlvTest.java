package org.purpleBean.kmip.codec.ttlv.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SimpleRequestHeader TTLV Serialization")
class SimpleRequestHeaderTtlvTest extends AbstractTtlvSerializationTestSuite<SimpleRequestHeader> {

    @Override
    public Class<SimpleRequestHeader> type() {
        return SimpleRequestHeader.class;
    }

    @Override
    public SimpleRequestHeader createDefault() {
        return SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(1, 2))
                .build();
    }

    @Override
    public SimpleRequestHeader createVariant() {
        return SimpleRequestHeader.builder()
                .protocolVersion(ProtocolVersion.of(2, 0))
                .build();
    }

    @Override
    public boolean unsupportedSpecShouldFailSerialize() {
        return false; // model supports UnsupportedVersion
    }
}
