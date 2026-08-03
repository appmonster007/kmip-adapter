package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RecoverOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RecoverOpRequestPayload Ttlv Serialization Tests")
class RecoverOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<RecoverOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<RecoverOpRequestPayload> type() {
    return RecoverOpRequestPayload.class;
  }

  @Override
  public RecoverOpRequestPayload createDefault() {
    return RecoverOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public RecoverOpRequestPayload createVariant() {
    return RecoverOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
