package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ReKeyKeyPairOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReKeyKeyPairOpRequestPayload Xml Serialization Tests")
class ReKeyKeyPairOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<ReKeyKeyPairOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
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