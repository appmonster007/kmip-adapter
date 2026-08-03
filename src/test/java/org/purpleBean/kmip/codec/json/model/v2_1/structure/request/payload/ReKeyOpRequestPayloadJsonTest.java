package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ReKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReKeyOpRequestPayload Json Serialization Tests")
class ReKeyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ReKeyOpRequestPayload> {

  @Override
  public Class<ReKeyOpRequestPayload> type() {
    return ReKeyOpRequestPayload.class;
  }

  @Override
  public ReKeyOpRequestPayload createDefault() {
    return ReKeyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public ReKeyOpRequestPayload createVariant() {
    return ReKeyOpRequestPayload
        .builder()
        .build();
  }
}