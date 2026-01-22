package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetAttributesOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("GetAttributesOpResponsePayload Ttlv Serialization Tests")
class GetAttributesOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<GetAttributesOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<GetAttributesOpResponsePayload> type() {
        return GetAttributesOpResponsePayload.class;
    }

    @Override
    protected GetAttributesOpResponsePayload createDefault() {
        return GetAttributesOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attribute(Attribute.of(AttributeName.of("Attribute1"), AttributeValueTextString.of("Value1")))
                .build();
    }

    @Override
    protected GetAttributesOpResponsePayload createVariant() {
        return GetAttributesOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .attribute(Attribute.of(AttributeName.of("AttributeA"), AttributeValueTextString.of("ValueA")))
                .attribute(Attribute.of(AttributeName.of("AttributeB"), AttributeValueTextString.of("ValueB")))
                .build();
    }
}
