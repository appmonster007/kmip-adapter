package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ArchiveOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ArchiveOpRequestPayload Ttlv Serialization Tests")
class ArchiveOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ArchiveOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<ArchiveOpRequestPayload> type() {
    return ArchiveOpRequestPayload.class;
  }

  @Override
  public ArchiveOpRequestPayload createDefault() {
    return ArchiveOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
  }

  @Override
  public ArchiveOpRequestPayload createVariant() {
    return ArchiveOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .build();
  }
}
