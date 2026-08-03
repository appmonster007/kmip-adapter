package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.EncryptOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("EncryptOpRequestPayload Ttlv Serialization Tests")
class EncryptOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<EncryptOpRequestPayload> {

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