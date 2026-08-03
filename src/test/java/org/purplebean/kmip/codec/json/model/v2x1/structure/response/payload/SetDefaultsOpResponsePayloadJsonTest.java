package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetDefaultsOpResponsePayload Json Serialization Tests")
class SetDefaultsOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetDefaultsOpResponsePayload> {

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