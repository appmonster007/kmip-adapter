package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SignOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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