package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.GetAttributesOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetAttributesOpRequestPayload Ttlv Serialization Tests")
class GetAttributesOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<GetAttributesOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<GetAttributesOpRequestPayload> type() {
        return GetAttributesOpRequestPayload.class;
    }

    @Override
    public GetAttributesOpRequestPayload createDefault() {
        return GetAttributesOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attributeName(AttributeName.of("Attribute1"))
                .build();
    }

    @Override
    public GetAttributesOpRequestPayload createVariant() {
        return GetAttributesOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .attributeName(AttributeName.of("AttributeA"))
                .attributeName(AttributeName.of("AttributeB"))
                .build();
    }
}
