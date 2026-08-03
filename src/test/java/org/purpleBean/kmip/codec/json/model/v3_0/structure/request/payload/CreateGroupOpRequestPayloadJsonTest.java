package org.purpleBean.kmip.codec.json.model.v3_0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateGroupOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateGroupOpRequestPayload Json Serialization Tests")
class CreateGroupOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateGroupOpRequestPayload> {

  @Override
  public Class<CreateGroupOpRequestPayload> type() {
    return CreateGroupOpRequestPayload.class;
  }

  @Override
  public CreateGroupOpRequestPayload createDefault() {
    return CreateGroupOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
  }

  @Override
  public CreateGroupOpRequestPayload createVariant() {
    return CreateGroupOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
  }
}