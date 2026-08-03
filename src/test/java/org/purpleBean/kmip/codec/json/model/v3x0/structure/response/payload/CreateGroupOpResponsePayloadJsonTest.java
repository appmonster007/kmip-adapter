package org.purpleBean.kmip.codec.json.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.CreateGroupOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateGroupOpResponsePayload Json Serialization Tests")
class CreateGroupOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateGroupOpResponsePayload> {

  @Override
  public Class<CreateGroupOpResponsePayload> type() {
    return CreateGroupOpResponsePayload.class;
  }

  @Override
  public CreateGroupOpResponsePayload createDefault() {
    return CreateGroupOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public CreateGroupOpResponsePayload createVariant() {
    return CreateGroupOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}