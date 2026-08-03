package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CreateKeyPairOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateKeyPairOpResponsePayload Ttlv Serialization Tests")
class CreateKeyPairOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateKeyPairOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CreateKeyPairOpResponsePayload> type() {
    return CreateKeyPairOpResponsePayload.class;
  }

  @Override
  public CreateKeyPairOpResponsePayload createDefault() {
    return CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid")
            .build())
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier
            .builder()
            .value("public-uid")
            .build())
        .privateKeyTemplateAttribute(PrivateKeyTemplateAttribute
            .builder()
            .build())
        .publicKeyTemplateAttribute(PublicKeyTemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public CreateKeyPairOpResponsePayload createVariant() {
    return CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid2")
            .build())
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier
            .builder()
            .value("public-uid2")
            .build())
        .build();
  }
}