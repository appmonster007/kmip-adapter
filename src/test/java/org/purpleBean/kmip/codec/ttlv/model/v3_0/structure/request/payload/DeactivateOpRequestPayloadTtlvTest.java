package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.DeactivateOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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