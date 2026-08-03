package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.EncryptOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("EncryptOpResponsePayload Json Serialization Tests")
class EncryptOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<EncryptOpResponsePayload> {

  @Override
  public Class<EncryptOpResponsePayload> type() {
    return EncryptOpResponsePayload.class;
  }

  @Override
  public EncryptOpResponsePayload createDefault() {
    return EncryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public EncryptOpResponsePayload createVariant() {
    return EncryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}