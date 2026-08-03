package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.request.payload.DeactivateOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeactivateOpRequestPayload Ttlv Serialization Tests")
class DeactivateOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DeactivateOpRequestPayload> {

  @Override
  public Class<DeactivateOpRequestPayload> type() {
    return DeactivateOpRequestPayload.class;
  }

  @Override
  public DeactivateOpRequestPayload createDefault() {
    return DeactivateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-1")
            .build())
        .build();
  }

  @Override
  public DeactivateOpRequestPayload createVariant() {
    return DeactivateOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("test-uid-2")
            .build())
        .build();
  }
}