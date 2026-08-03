package org.purpleBean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.GetAttributesOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("GetAttributesOpRequestPayload Json Serialization Tests")
class GetAttributesOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<GetAttributesOpRequestPayload> {

  @Override
  public Class<GetAttributesOpRequestPayload> type() {
    return GetAttributesOpRequestPayload.class;
  }

  @Override
  public GetAttributesOpRequestPayload createDefault() {
    return GetAttributesOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public GetAttributesOpRequestPayload createVariant() {
    return GetAttributesOpRequestPayload
        .builder()
        .build();
  }
}