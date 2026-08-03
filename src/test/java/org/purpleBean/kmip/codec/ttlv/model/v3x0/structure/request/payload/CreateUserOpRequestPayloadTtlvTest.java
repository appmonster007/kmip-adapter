package org.purpleBean.kmip.codec.ttlv.model.v3x0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.CreateUserOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateUserOpRequestPayload Ttlv Serialization Tests")
class CreateUserOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateUserOpRequestPayload> {

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