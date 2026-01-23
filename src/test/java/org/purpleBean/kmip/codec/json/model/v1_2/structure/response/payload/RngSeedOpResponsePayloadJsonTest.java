package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RngSeedOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RngSeedOpResponsePayload Json Serialization Tests")
class RngSeedOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<RngSeedOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<RngSeedOpResponsePayload> type() {
        return RngSeedOpResponsePayload.class;
    }

    @Override
    protected RngSeedOpResponsePayload createDefault() {
        return RngSeedOpResponsePayload.builder()
                .dataLength(DataLength.of(16))
                .build();
    }

    @Override
    protected RngSeedOpResponsePayload createVariant() {
        return RngSeedOpResponsePayload.builder()
                .dataLength(DataLength.of(32))
                .build();
    }
}
