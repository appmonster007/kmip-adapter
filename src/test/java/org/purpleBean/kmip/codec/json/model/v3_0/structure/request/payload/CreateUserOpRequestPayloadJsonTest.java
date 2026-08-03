package org.purpleBean.kmip.codec.json.model.v3_0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.CreateUserOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateUserOpRequestPayload Json Serialization Tests")
class CreateUserOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateUserOpRequestPayload> {

  @Override
  public Class<CreateUserOpRequestPayload> type() {
    return CreateUserOpRequestPayload.class;
  }

  @Override
  public CreateUserOpRequestPayload createDefault() {
    return CreateUserOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
  }

  @Override
  public CreateUserOpRequestPayload createVariant() {
    return CreateUserOpRequestPayload
        .builder()
        .attributes(Attributes.of(java.util.List.of()))
        .build();
  }
}