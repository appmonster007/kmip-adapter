package org.purpleBean.kmip.codec.ttlv.model.v1_2.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.SignOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SignOpResponsePayload Ttlv Serialization Tests")
class SignOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SignOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<SignOpResponsePayload> type() {
    return SignOpResponsePayload.class;
  }

  @Override
  public SignOpResponsePayload createDefault() {
    return SignOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .signatureData(SignatureData.of(new byte[] {1, 2, 3}))
        .build();
  }

  @Override
  public SignOpResponsePayload createVariant() {
    return SignOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .signatureData(SignatureData.of(new byte[] {4, 5, 6}))
        .build();
  }
}
