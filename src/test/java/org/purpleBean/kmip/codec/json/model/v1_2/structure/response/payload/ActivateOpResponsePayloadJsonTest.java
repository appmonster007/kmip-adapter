package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ActivateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ActivateOpResponsePayload Json Serialization Tests")
class ActivateOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<ActivateOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ActivateOpResponsePayload> type() {
        return ActivateOpResponsePayload.class;
    }

    @Override
    protected ActivateOpResponsePayload createDefault() {
        return ActivateOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
    }

    @Override
    protected ActivateOpResponsePayload createVariant() {
        return ActivateOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .build();
    }
}
