package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeleteAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeleteAttributeOpResponsePayload Json Serialization Tests")
class DeleteAttributeOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<DeleteAttributeOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<DeleteAttributeOpResponsePayload> type() {
        return DeleteAttributeOpResponsePayload.class;
    }

    @Override
    protected DeleteAttributeOpResponsePayload createDefault() {
        return DeleteAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attribute(Attribute.of(AttributeName.of("test-attribute"), AttributeValueTextString.of("test-value")))
                .build();
    }

    @Override
    protected DeleteAttributeOpResponsePayload createVariant() {
        return DeleteAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .attribute(Attribute.of(AttributeName.of("variant-attribute"), AttributeValueTextString.of("variant-value")))
                .build();
    }
}
