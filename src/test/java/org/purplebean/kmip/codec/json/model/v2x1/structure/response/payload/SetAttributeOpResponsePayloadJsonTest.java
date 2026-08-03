package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetAttributeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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