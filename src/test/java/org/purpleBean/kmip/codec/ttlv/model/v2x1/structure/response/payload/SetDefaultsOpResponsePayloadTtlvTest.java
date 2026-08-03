package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetDefaultsOpResponsePayload Ttlv Serialization Tests")
class SetDefaultsOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetDefaultsOpResponsePayload> {

  @Override
  public Class<SetDefaultsOpResponsePayload> type() {
    return SetDefaultsOpResponsePayload.class;
  }

  @Override
  public SetDefaultsOpResponsePayload createDefault() {
    return SetDefaultsOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public SetDefaultsOpResponsePayload createVariant() {
    return SetDefaultsOpResponsePayload
        .builder()
        .build();
  }
}