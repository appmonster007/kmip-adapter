package org.purpleBean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.MacOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MacOpResponsePayload Json Serialization Tests")
class MacOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<MacOpResponsePayload> {

  @Override
  public Class<MacOpResponsePayload> type() {
    return MacOpResponsePayload.class;
  }

  @Override
  public MacOpResponsePayload createDefault() {
    return MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }

  @Override
  public MacOpResponsePayload createVariant() {
    return MacOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .build();
  }
}