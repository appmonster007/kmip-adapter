package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetAttributeOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetAttributeOpResponsePayload Json Serialization Tests")
class SetAttributeOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetAttributeOpResponsePayload> {

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