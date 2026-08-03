package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetConstraintsOpResponsePayload Json Serialization Tests")
class SetConstraintsOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetConstraintsOpResponsePayload> {

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