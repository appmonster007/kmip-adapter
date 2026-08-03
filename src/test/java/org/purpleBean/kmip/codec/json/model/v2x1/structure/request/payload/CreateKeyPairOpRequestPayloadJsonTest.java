package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.CreateKeyPairOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateKeyPairOpRequestPayload Json Serialization Tests")
class CreateKeyPairOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateKeyPairOpRequestPayload> {

  @Override
  public Class<CreateKeyPairOpRequestPayload> type() {
    return CreateKeyPairOpRequestPayload.class;
  }

  @Override
  public CreateKeyPairOpRequestPayload createDefault() {
    return CreateKeyPairOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public CreateKeyPairOpRequestPayload createVariant() {
    return CreateKeyPairOpRequestPayload
        .builder()
        .build();
  }
}