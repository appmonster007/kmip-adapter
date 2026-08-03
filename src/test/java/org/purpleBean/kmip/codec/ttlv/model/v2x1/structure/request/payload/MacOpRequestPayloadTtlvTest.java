package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.MacOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacOpRequestPayload Ttlv Serialization Tests")
class MacOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<MacOpRequestPayload> {

  @Override
  public Class<MacOpRequestPayload> type() {
    return MacOpRequestPayload.class;
  }

  @Override
  public MacOpRequestPayload createDefault() {
    return MacOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public MacOpRequestPayload createVariant() {
    return MacOpRequestPayload
        .builder()
        .build();
  }
}