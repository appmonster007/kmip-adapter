package org.purpleBean.kmip.codec.json.model.v3_0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.DeactivateOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeactivateOpResponsePayload Json Serialization Tests")
class DeactivateOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<DeactivateOpResponsePayload> {

  @Override
  public Class<DeactivateOpResponsePayload> type() {
    return DeactivateOpResponsePayload.class;
  }

  @Override
  public DeactivateOpResponsePayload createDefault() {
    return DeactivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public DeactivateOpResponsePayload createVariant() {
    return DeactivateOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}