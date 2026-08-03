package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.DeactivateOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivateOpResponsePayload Ttlv Serialization Tests")
class DeactivateOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DeactivateOpResponsePayload> {

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