package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ModifyAttributeOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ModifyAttributeOpRequestPayload Ttlv Serialization Tests")
class ModifyAttributeOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ModifyAttributeOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ModifyAttributeOpRequestPayload> type() {
        return ModifyAttributeOpRequestPayload.class;
    }

    @Override
    protected ModifyAttributeOpRequestPayload createDefault() {
        return ModifyAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attribute(Attribute.of(AttributeName.of("test-attribute"), AttributeValueTextString.of("test-value")))
                .build();
    }

    @Override
    protected ModifyAttributeOpRequestPayload createVariant() {
        return ModifyAttributeOpRequestPayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .attribute(Attribute.of(AttributeName.of("variant-attribute"), AttributeValueTextString.of("variant-value")))
                .build();
    }
}
