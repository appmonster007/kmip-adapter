package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.HashOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("HashOpResponsePayload Ttlv Serialization Tests")
class HashOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<HashOpResponsePayload> {

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