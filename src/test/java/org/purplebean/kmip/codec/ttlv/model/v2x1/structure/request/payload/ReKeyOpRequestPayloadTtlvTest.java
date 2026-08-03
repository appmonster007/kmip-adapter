package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ReKeyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReKeyOpRequestPayload Ttlv Serialization Tests")
class ReKeyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ReKeyOpRequestPayload> {

  @Override
  public Class<ReKeyOpRequestPayload> type() {
    return ReKeyOpRequestPayload.class;
  }

  @Override
  public ReKeyOpRequestPayload createDefault() {
    return ReKeyOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public ReKeyOpRequestPayload createVariant() {
    return ReKeyOpRequestPayload
        .builder()
        .build();
  }
}