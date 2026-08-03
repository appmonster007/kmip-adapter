package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ReKeyKeyPairOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReKeyKeyPairOpRequestPayload Ttlv Serialization Tests")
class ReKeyKeyPairOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ReKeyKeyPairOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ReKeyKeyPairOpRequestPayload> type() {
    return ReKeyKeyPairOpRequestPayload.class;
  }

  @Override
  public ReKeyKeyPairOpRequestPayload createDefault() {
    return ReKeyKeyPairOpRequestPayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid")
            .build())
        .offset(Offset
            .builder()
            .value(100)
            .build())
        .commonTemplateAttribute(CommonTemplateAttribute
            .builder()
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
  public ReKeyKeyPairOpRequestPayload createVariant() {
    return ReKeyKeyPairOpRequestPayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid2")
            .build())
        .offset(Offset
            .builder()
            .value(200)
            .build())
        .build();
  }
}