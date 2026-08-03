package org.purpleBean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.CreateKeyPairOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CreateKeyPairOpResponsePayload Xml Serialization Tests")
class CreateKeyPairOpResponsePayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CreateKeyPairOpResponsePayload> {

  @Override
  public Class<CreateKeyPairOpResponsePayload> type() {
    return CreateKeyPairOpResponsePayload.class;
  }

  @Override
  public CreateKeyPairOpResponsePayload createDefault() {
    return CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier.of("priv"))
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier.of("pub"))
        .build();
  }

  @Override
  public CreateKeyPairOpResponsePayload createVariant() {
    return CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier.of("priv"))
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier.of("pub"))
        .build();
  }
}
