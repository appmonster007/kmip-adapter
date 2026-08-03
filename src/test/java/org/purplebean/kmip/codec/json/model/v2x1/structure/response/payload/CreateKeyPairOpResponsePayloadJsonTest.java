package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.CreateKeyPairOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateKeyPairOpResponsePayload Json Serialization Tests")
class CreateKeyPairOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateKeyPairOpResponsePayload> {

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
