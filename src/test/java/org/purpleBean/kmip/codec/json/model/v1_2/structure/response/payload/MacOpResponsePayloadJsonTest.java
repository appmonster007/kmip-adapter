package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.MacOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacOpResponsePayload Json Serialization Tests")
class MacOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<MacOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<MacOpResponsePayload> type() {
        return MacOpResponsePayload.class;
    }

    @Override
    public MacOpResponsePayload createDefault() {
        return MacOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .macData(MacData.of(new byte[]{1, 2, 3}))
                .build();
    }

    @Override
    public MacOpResponsePayload createVariant() {
        return MacOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .macData(MacData.of(new byte[]{4, 5, 6}))
                .build();
    }
}
