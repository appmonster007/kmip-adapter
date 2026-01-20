package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ReKeyKeyPairOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReKeyKeyPairOpResponsePayload Ttlv Serialization Tests")
class ReKeyKeyPairOpResponsePayloadTtlvTest extends AbstractTtlvSerializationTestSuite<ReKeyKeyPairOpResponsePayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<ReKeyKeyPairOpResponsePayload> type() {
        return ReKeyKeyPairOpResponsePayload.class;
    }

    @Override
    protected ReKeyKeyPairOpResponsePayload createDefault() {
        return ReKeyKeyPairOpResponsePayload.builder()
                .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier.builder().value("private-uid").build())
                .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier.builder().value("public-uid").build())
                .privateKeyTemplateAttribute(PrivateKeyTemplateAttribute.builder().build())
                .publicKeyTemplateAttribute(PublicKeyTemplateAttribute.builder().build())
                .build();
    }

    @Override
    protected ReKeyKeyPairOpResponsePayload createVariant() {
        return ReKeyKeyPairOpResponsePayload.builder()
                .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier.builder().value("private-uid2").build())
                .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier.builder().value("public-uid2").build())
                .build();
    }
}