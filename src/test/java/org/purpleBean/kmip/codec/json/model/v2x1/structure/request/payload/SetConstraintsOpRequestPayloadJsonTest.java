package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SetConstraintsOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SetConstraintsOpRequestPayload Json Serialization Tests")
class SetConstraintsOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SetConstraintsOpRequestPayload> {

  @Override
  public Class<SetConstraintsOpRequestPayload> type() {
    return SetConstraintsOpRequestPayload.class;
  }

  @Override
  public SetConstraintsOpRequestPayload createDefault() {
    return SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }

  @Override
  public SetConstraintsOpRequestPayload createVariant() {
    return SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }
}