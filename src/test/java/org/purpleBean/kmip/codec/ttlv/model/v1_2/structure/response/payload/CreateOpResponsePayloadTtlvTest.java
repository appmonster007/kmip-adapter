package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CreateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateOpResponsePayload Ttlv Serialization Tests")
class CreateOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<CreateOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CreateOpResponsePayload> type() {
        return CreateOpResponsePayload.class;
    }

    @Override
    protected CreateOpResponsePayload createDefault() {
        return CreateOpResponsePayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected CreateOpResponsePayload createVariant() {
        return CreateOpResponsePayload.builder()
                .objectType(ObjectType.Standard.PUBLIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid2").build())
                .build();
    }
}