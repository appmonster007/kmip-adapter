package org.purpleBean.kmip.codec.ttlv.common.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.common.structure.request.SimpleRequestHeader;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("SimpleRequestHeader TTLV Serialization")
class SimpleRequestHeaderTtlvTest extends AbstractTtlvSerializationSuite<SimpleRequestHeader> {

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
