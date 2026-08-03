package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.MacVerifyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MacVerifyOpRequestPayload Ttlv Serialization Tests")
class MacVerifyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<MacVerifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<MacVerifyOpRequestPayload> type() {
    return MacVerifyOpRequestPayload.class;
  }

  @Override
  public MacVerifyOpRequestPayload createDefault() {
    return MacVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .macData(MacData.of(new byte[] {4, 5, 6}))
        .build();
  }

  @Override
  public MacVerifyOpRequestPayload createVariant() {
    return MacVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .data(DataByteString.of(new byte[] {7, 8, 9}))
        .macData(MacData.of(new byte[] {10, 11, 12}))
        .build();
  }
}
