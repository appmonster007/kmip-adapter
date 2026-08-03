package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.SignOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SignOpRequestPayload Json Serialization Tests")
class SignOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<SignOpRequestPayload> {

  @Override
  public Class<SignOpRequestPayload> type() {
    return SignOpRequestPayload.class;
  }

  @Override
  public SignOpRequestPayload createDefault() {
    return SignOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public SignOpRequestPayload createVariant() {
    return SignOpRequestPayload
        .builder()
        .build();
  }
}