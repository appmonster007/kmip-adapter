package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetAttributeListOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetAttributeListOpRequestPayload Json Serialization Tests")
class GetAttributeListOpRequestPayloadJsonTest extends AbstractJsonSerializationTestSuite<GetAttributeListOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<GetAttributeListOpRequestPayload> type() {
        return GetAttributeListOpRequestPayload.class;
    }

    @Override
    protected GetAttributeListOpRequestPayload createDefault() {
        return GetAttributeListOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .build();
    }

    @Override
    protected GetAttributeListOpRequestPayload createVariant() {
        return GetAttributeListOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .build();
    }
}
