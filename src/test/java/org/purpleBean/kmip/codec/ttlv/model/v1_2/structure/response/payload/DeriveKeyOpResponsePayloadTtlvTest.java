package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DeriveKeyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeriveKeyOpResponsePayload Ttlv Serialization Tests")
class DeriveKeyOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<DeriveKeyOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<DeriveKeyOpResponsePayload> type() {
        return DeriveKeyOpResponsePayload.class;
    }

    @Override
    protected DeriveKeyOpResponsePayload createDefault() {
        return DeriveKeyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .templateAttribute(TemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected DeriveKeyOpResponsePayload createVariant() {
        return DeriveKeyOpResponsePayload.builder()
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid2").build())
                .build();
    }
}
