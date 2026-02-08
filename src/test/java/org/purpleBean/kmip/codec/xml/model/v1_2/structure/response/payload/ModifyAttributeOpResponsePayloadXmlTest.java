package org.purpleBean.kmip.codec.xml.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ModifyAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ModifyAttributeOpResponsePayload Xml Serialization Tests")
class ModifyAttributeOpResponsePayloadXmlTest extends AbstractXmlSerializationTestSuite<ModifyAttributeOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<ModifyAttributeOpResponsePayload> type() {
        return ModifyAttributeOpResponsePayload.class;
    }

    @Override
    public ModifyAttributeOpResponsePayload createDefault() {
        return ModifyAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
                .attribute(Attribute.of(AttributeName.of("test-attribute"), AttributeValue.ofTextString("test-value")))
                .build();
    }

    @Override
    public ModifyAttributeOpResponsePayload createVariant() {
        return ModifyAttributeOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
                .attribute(Attribute.of(AttributeName.of("variant-attribute"), AttributeValue.ofTextString("variant-value")))
                .build();
    }
}
