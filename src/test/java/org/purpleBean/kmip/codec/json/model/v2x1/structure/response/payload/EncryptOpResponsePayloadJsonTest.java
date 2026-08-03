package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.EncryptOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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