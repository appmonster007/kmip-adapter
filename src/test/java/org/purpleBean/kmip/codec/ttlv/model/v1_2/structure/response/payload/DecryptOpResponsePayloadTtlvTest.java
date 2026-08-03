package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DecryptOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DecryptOpResponsePayload Ttlv Serialization Tests")
class DecryptOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DecryptOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DecryptOpResponsePayload> type() {
    return DecryptOpResponsePayload.class;
  }

  @Override
  public DecryptOpResponsePayload createDefault() {
    return DecryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public DecryptOpResponsePayload createVariant() {
    return DecryptOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .data(DataByteString.of(new byte[] {4, 5, 6}))
        .build();
  }
}
