package org.purpleBean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.LocateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LocateOpRequestPayload Json Serialization Tests")
class LocateOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<LocateOpRequestPayload> {

  @Override
  public Class<LocateOpRequestPayload> type() {
    return LocateOpRequestPayload.class;
  }

  @Override
  public LocateOpRequestPayload createDefault() {
    return LocateOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public LocateOpRequestPayload createVariant() {
    return LocateOpRequestPayload
        .builder()
        .build();
  }
}