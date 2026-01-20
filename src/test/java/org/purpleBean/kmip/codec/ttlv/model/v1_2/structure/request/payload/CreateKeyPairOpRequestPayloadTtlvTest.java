package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateKeyPairOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateKeyPairOpRequestPayload Ttlv Serialization Tests")
class CreateKeyPairOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<CreateKeyPairOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<CreateKeyPairOpRequestPayload> type() {
        return CreateKeyPairOpRequestPayload.class;
    }

    @Override
    protected CreateKeyPairOpRequestPayload createDefault() {
        return CreateKeyPairOpRequestPayload.builder()
                .commonTemplateAttribute(CommonTemplateAttribute.builder().build())
                .privateKeyTemplateAttribute(PrivateKeyTemplateAttribute.builder().build())
                .publicKeyTemplateAttribute(PublicKeyTemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected CreateKeyPairOpRequestPayload createVariant() {
        return CreateKeyPairOpRequestPayload.builder()
                .commonTemplateAttribute(CommonTemplateAttribute.builder().build())
                .privateKeyTemplateAttribute(PrivateKeyTemplateAttribute.builder().build())
                .build();
    }
}