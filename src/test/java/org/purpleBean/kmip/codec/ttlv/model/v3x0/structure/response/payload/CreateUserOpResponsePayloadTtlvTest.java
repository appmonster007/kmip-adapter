package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.CreateUserOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateUserOpResponsePayload Ttlv Serialization Tests")
class CreateUserOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateUserOpResponsePayload> {

  @Override
  public Class<CreateUserOpResponsePayload> type() {
    return CreateUserOpResponsePayload.class;
  }

  @Override
  public CreateUserOpResponsePayload createDefault() {
    return CreateUserOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public CreateUserOpResponsePayload createVariant() {
    return CreateUserOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}