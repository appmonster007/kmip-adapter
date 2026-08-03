package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.MacVerifyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacVerifyOpRequestPayload Json Serialization Tests")
class MacVerifyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<MacVerifyOpRequestPayload> {

  @Override
  public Class<MacVerifyOpRequestPayload> type() {
    return MacVerifyOpRequestPayload.class;
  }

  @Override
  public MacVerifyOpRequestPayload createDefault() {
    return MacVerifyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public MacVerifyOpRequestPayload createVariant() {
    return MacVerifyOpRequestPayload
        .builder()
        .build();
  }
}