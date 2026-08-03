package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ExportOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ExportOpRequestPayload Json Serialization Tests")
class ExportOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ExportOpRequestPayload> {

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