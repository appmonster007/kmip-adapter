package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.CreateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CreateOpResponsePayload Ttlv Serialization Tests")
class CreateOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<CreateOpResponsePayload> {

  @Override
  public Class<CreateOpResponsePayload> type() {
    return CreateOpResponsePayload.class;
  }

  @Override
  public CreateOpResponsePayload createDefault() {
    return CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public CreateOpResponsePayload createVariant() {
    return CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}
