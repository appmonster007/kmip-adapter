package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DiscoverVersionsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DiscoverVersionsOpResponsePayload Json Serialization Tests")
class DiscoverVersionsOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<DiscoverVersionsOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<DiscoverVersionsOpResponsePayload> type() {
        return DiscoverVersionsOpResponsePayload.class;
    }

    @Override
    protected DiscoverVersionsOpResponsePayload createDefault() {
        return DiscoverVersionsOpResponsePayload.builder()
                .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
                .build();
    }

    @Override
    protected DiscoverVersionsOpResponsePayload createVariant() {
        return DiscoverVersionsOpResponsePayload.builder()
                .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(3)))
                .build();
    }
}
