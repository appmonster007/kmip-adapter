package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetConstraintsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetConstraintsOpResponsePayload Ttlv Serialization Tests")
class SetConstraintsOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetConstraintsOpResponsePayload> {

  @Override
  public Class<SetConstraintsOpResponsePayload> type() {
    return SetConstraintsOpResponsePayload.class;
  }

  @Override
  public SetConstraintsOpResponsePayload createDefault() {
    return SetConstraintsOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public SetConstraintsOpResponsePayload createVariant() {
    return SetConstraintsOpResponsePayload
        .builder()
        .build();
  }
}