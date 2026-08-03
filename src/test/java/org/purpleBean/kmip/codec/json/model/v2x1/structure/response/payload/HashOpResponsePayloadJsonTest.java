package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.HashOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashOpResponsePayload Json Serialization Tests")
class HashOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<HashOpResponsePayload> {

  @Override
  public Class<HashOpResponsePayload> type() {
    return HashOpResponsePayload.class;
  }

  @Override
  public HashOpResponsePayload createDefault() {
    return HashOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public HashOpResponsePayload createVariant() {
    return HashOpResponsePayload
        .builder()
        .build();
  }
}