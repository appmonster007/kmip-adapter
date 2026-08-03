package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SetAttributeOpResponsePayload Ttlv Serialization Tests")
class SetAttributeOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SetAttributeOpResponsePayload> {

  @Override
  public Class<SetAttributeOpResponsePayload> type() {
    return SetAttributeOpResponsePayload.class;
  }

  @Override
  public SetAttributeOpResponsePayload createDefault() {
    return SetAttributeOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-1")
        .build());
  }

  @Override
  public SetAttributeOpResponsePayload createVariant() {
    return SetAttributeOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-variant")
        .build());
  }
}