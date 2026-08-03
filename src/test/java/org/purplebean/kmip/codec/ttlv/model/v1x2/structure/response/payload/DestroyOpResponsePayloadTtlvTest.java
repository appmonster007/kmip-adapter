package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DestroyOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DestroyOpResponsePayload Ttlv Serialization Tests")
class DestroyOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DestroyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DestroyOpResponsePayload> type() {
    return DestroyOpResponsePayload.class;
  }

  @Override
  public DestroyOpResponsePayload createDefault() {
    return DestroyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public DestroyOpResponsePayload createVariant() {
    return DestroyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
