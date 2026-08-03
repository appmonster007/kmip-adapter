package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ImportOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ImportOpResponsePayload Json Serialization Tests")
class ImportOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<ImportOpResponsePayload> {

  @Override
  public Class<ImportOpResponsePayload> type() {
    return ImportOpResponsePayload.class;
  }

  @Override
  public ImportOpResponsePayload createDefault() {
    return ImportOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-1")
        .build());
  }

  @Override
  public ImportOpResponsePayload createVariant() {
    return ImportOpResponsePayload.of(UniqueIdentifier
        .builder()
        .value("test-uid-variant")
        .build());
  }
}