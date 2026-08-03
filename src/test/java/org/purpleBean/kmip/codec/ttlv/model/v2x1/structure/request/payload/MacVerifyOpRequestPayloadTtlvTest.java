package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.MacVerifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacVerifyOpRequestPayload Ttlv Serialization Tests")
class MacVerifyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<MacVerifyOpRequestPayload> {

  @Override
  public Class<MacVerifyOpRequestPayload> type() {
    return MacVerifyOpRequestPayload.class;
  }

  @Override
  public MacVerifyOpRequestPayload createDefault() {
    return MacVerifyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public MacVerifyOpRequestPayload createVariant() {
    return MacVerifyOpRequestPayload
        .builder()
        .build();
  }
}