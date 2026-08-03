package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.ExportOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ExportOpRequestPayload Ttlv Serialization Tests")
class ExportOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<ExportOpRequestPayload> {

  @Override
  public Class<ExportOpRequestPayload> type() {
    return ExportOpRequestPayload.class;
  }

  @Override
  public ExportOpRequestPayload createDefault() {
    return ExportOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public ExportOpRequestPayload createVariant() {
    return ExportOpRequestPayload
        .builder()
        .build();
  }
}