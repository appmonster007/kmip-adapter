package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v1x2.structure.response.payload.NotifyOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NotifyOpResponsePayload Ttlv Serialization Tests")
class NotifyOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<NotifyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<NotifyOpResponsePayload> type() {
    return NotifyOpResponsePayload.class;
  }

  @Override
  public NotifyOpResponsePayload createDefault() {
    return NotifyOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public NotifyOpResponsePayload createVariant() {
    return NotifyOpResponsePayload
        .builder()
        .build();
  }
}
