package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.MacOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacOpResponsePayload Ttlv Serialization Tests")
class MacOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<MacOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<MacOpResponsePayload> type() {
    return MacOpResponsePayload.class;
  }

  @Override
  public MacOpResponsePayload createDefault() {
    return MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .macData(MacData.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public MacOpResponsePayload createVariant() {
    return MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .macData(MacData.of(new byte[] {4, 5, 6}))
        .build();
  }
}
