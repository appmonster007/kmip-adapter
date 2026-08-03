package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.EncryptOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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