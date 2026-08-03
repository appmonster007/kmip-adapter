package org.purpleBean.kmip.codec.xml.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.ReKeyKeyPairOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReKeyKeyPairOpResponsePayload Xml Serialization Tests")
class ReKeyKeyPairOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ReKeyKeyPairOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<ReKeyKeyPairOpResponsePayload> type() {
    return ReKeyKeyPairOpResponsePayload.class;
  }

  @Override
  public ReKeyKeyPairOpResponsePayload createDefault() {
    return ReKeyKeyPairOpResponsePayload
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
  public ReKeyKeyPairOpResponsePayload createVariant() {
    return ReKeyKeyPairOpResponsePayload
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