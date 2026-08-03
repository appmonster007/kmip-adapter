package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.MacVerifyOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacVerifyOpResponsePayload Json Serialization Tests")
class MacVerifyOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<MacVerifyOpResponsePayload> {

  @Override
  public Class<MacVerifyOpResponsePayload> type() {
    return MacVerifyOpResponsePayload.class;
  }

  @Override
  public MacVerifyOpResponsePayload createDefault() {
    return MacVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public MacVerifyOpResponsePayload createVariant() {
    return MacVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}