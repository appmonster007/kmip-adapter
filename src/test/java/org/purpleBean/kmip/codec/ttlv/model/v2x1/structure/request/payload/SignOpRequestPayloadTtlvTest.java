package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SignOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SignOpRequestPayload Ttlv Serialization Tests")
class SignOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SignOpRequestPayload> {

  @Override
  public Class<SignOpRequestPayload> type() {
    return SignOpRequestPayload.class;
  }

  @Override
  public SignOpRequestPayload createDefault() {
    return SignOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public SignOpRequestPayload createVariant() {
    return SignOpRequestPayload
        .builder()
        .build();
  }
}