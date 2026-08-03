package org.purpleBean.kmip.codec.json.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.LocateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LocateOpResponsePayload Json Serialization Tests")
class LocateOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<LocateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<LocateOpResponsePayload> type() {
    return LocateOpResponsePayload.class;
  }

  @Override
  public LocateOpResponsePayload createDefault() {
    return LocateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public LocateOpResponsePayload createVariant() {
    return LocateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174002"))
        .build();
  }
}
