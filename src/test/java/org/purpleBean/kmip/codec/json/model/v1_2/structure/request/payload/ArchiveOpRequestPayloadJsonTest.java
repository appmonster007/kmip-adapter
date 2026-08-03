package org.purpleBean.kmip.codec.json.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.ArchiveOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ArchiveOpRequestPayload Json Serialization Tests")
class ArchiveOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ArchiveOpRequestPayload> {

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
