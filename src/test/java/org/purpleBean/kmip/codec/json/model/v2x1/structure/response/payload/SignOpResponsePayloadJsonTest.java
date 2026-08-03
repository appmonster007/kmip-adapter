package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SignOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SignOpResponsePayload Json Serialization Tests")
class SignOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SignOpResponsePayload> {

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