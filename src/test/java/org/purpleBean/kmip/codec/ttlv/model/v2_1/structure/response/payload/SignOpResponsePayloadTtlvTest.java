package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SignOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SignOpResponsePayload Ttlv Serialization Tests")
class SignOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<SignOpResponsePayload> {

  @Override
  public Class<SignOpResponsePayload> type() {
    return SignOpResponsePayload.class;
  }

  @Override
  public SignOpResponsePayload createDefault() {
    return SignOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public SignOpResponsePayload createVariant() {
    return SignOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}