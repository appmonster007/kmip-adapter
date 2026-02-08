package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RegisterOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RegisterOpResponsePayload Json Serialization Tests")
class RegisterOpResponsePayloadJsonTest extends AbstractJsonSerializationTestSuite<RegisterOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<RegisterOpResponsePayload> type() {
        return RegisterOpResponsePayload.class;
    }

    @Override
    public RegisterOpResponsePayload createDefault() {
        return RegisterOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    public RegisterOpResponsePayload createVariant() {
        return RegisterOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid2").build())
                .build();
    }
}