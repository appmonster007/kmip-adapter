package org.purplebean.kmip.codec.xml.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateKeyPairOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

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
