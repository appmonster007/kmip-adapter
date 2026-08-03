package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.MacOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacOpRequestPayload Ttlv Serialization Tests")
class MacOpRequestPayloadTtlvTest extends AbstractTtlvSerializationTestSuite<MacOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<MacOpRequestPayload> type() {
    return MacOpRequestPayload.class;
  }

  @Override
  public MacOpRequestPayload createDefault() {
    return MacOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public MacOpRequestPayload createVariant() {
    return MacOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
