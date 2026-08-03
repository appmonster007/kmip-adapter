package org.purplebean.kmip.codec.json.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RevokeOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RevokeOpResponsePayload Json Serialization Tests")
class RevokeOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<RevokeOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RevokeOpResponsePayload> type() {
    return RevokeOpResponsePayload.class;
  }

  @Override
  public RevokeOpResponsePayload createDefault() {
    return RevokeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public RevokeOpResponsePayload createVariant() {
    return RevokeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
