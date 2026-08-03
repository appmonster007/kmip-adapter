package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DecryptOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DecryptOpRequestPayload Ttlv Serialization Tests")
class DecryptOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DecryptOpRequestPayload> {

  @Override
  public Class<DecryptOpRequestPayload> type() {
    return DecryptOpRequestPayload.class;
  }

  @Override
  public DecryptOpRequestPayload createDefault() {
    return DecryptOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public DecryptOpRequestPayload createVariant() {
    return DecryptOpRequestPayload
        .builder()
        .build();
  }
}