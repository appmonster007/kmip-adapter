package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LocateOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LocateOpResponsePayload Json Serialization Tests")
class LocateOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<LocateOpResponsePayload> {

  @Override
  public Class<LocateOpResponsePayload> type() {
    return LocateOpResponsePayload.class;
  }

  @Override
  public LocateOpResponsePayload createDefault() {
    return LocateOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public LocateOpResponsePayload createVariant() {
    return LocateOpResponsePayload
        .builder()
        .build();
  }
}
