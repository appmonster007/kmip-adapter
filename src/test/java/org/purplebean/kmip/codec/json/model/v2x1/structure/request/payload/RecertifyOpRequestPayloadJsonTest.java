package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.RecertifyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RecertifyOpRequestPayload Json Serialization Tests")
class RecertifyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<RecertifyOpRequestPayload> {

  @Override
  public Class<RecertifyOpRequestPayload> type() {
    return RecertifyOpRequestPayload.class;
  }

  @Override
  public RecertifyOpRequestPayload createDefault() {
    return RecertifyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public RecertifyOpRequestPayload createVariant() {
    return RecertifyOpRequestPayload
        .builder()
        .build();
  }
}