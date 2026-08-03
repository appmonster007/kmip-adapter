package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.Constraints;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.GetConstraintsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetConstraintsOpResponsePayload Json Serialization Tests")
class GetConstraintsOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<GetConstraintsOpResponsePayload> {

  @Override
  public Class<GetConstraintsOpResponsePayload> type() {
    return GetConstraintsOpResponsePayload.class;
  }

  @Override
  public GetConstraintsOpResponsePayload createDefault() {
    return GetConstraintsOpResponsePayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }

  @Override
  public GetConstraintsOpResponsePayload createVariant() {
    return GetConstraintsOpResponsePayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
  }
}