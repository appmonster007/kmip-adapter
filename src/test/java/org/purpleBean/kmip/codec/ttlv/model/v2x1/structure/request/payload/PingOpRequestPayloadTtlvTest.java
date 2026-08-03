package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PingOpRequestPayload Ttlv Serialization Tests")
class PingOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<PingOpRequestPayload> {

  @Override
  public Class<PingOpRequestPayload> type() {
    return PingOpRequestPayload.class;
  }

  @Override
  public PingOpRequestPayload createDefault() {
    return PingOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public PingOpRequestPayload createVariant() {
    return PingOpRequestPayload
        .builder()
        .build();
  }
}