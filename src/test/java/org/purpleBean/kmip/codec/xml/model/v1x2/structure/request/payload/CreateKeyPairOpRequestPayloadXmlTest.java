package org.purpleBean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.CreateKeyPairOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateKeyPairOpRequestPayload Xml Serialization Tests")
class CreateKeyPairOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CreateKeyPairOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<CreateKeyPairOpRequestPayload> type() {
    return CreateKeyPairOpRequestPayload.class;
  }

  @Override
  public CreateKeyPairOpRequestPayload createDefault() {
    return CreateKeyPairOpRequestPayload
        .builder()
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
  public CreateKeyPairOpRequestPayload createVariant() {
    return CreateKeyPairOpRequestPayload
        .builder()
        .commonTemplateAttribute(CommonTemplateAttribute
            .builder()
            .build())
        .privateKeyTemplateAttribute(PrivateKeyTemplateAttribute
            .builder()
            .build())
        .build();
  }
}