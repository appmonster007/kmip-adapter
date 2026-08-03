package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.EncryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("EncryptOpRequestPayload Json Serialization Tests")
class EncryptOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<EncryptOpRequestPayload> {

  @Override
  public Class<EncryptOpRequestPayload> type() {
    return EncryptOpRequestPayload.class;
  }

  @Override
  public EncryptOpRequestPayload createDefault() {
    return EncryptOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public EncryptOpRequestPayload createVariant() {
    return EncryptOpRequestPayload
        .builder()
        .build();
  }
}