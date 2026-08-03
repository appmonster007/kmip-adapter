package org.purpleBean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CreateSplitKeyOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CreateSplitKeyOpResponsePayload Json Serialization Tests")
class CreateSplitKeyOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<CreateSplitKeyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CreateSplitKeyOpResponsePayload> type() {
    return CreateSplitKeyOpResponsePayload.class;
  }

  @Override
  public CreateSplitKeyOpResponsePayload createDefault() {
    return CreateSplitKeyOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }

  @Override
  public CreateSplitKeyOpResponsePayload createVariant() {
    return CreateSplitKeyOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174002"))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174003"))
        .build();
  }
}
