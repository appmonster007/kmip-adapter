package org.purplebean.kmip.codec.json.model.core.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestBatchItem;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SimpleRequestBatchItem JSON Serialization")
class SimpleRequestBatchItemJsonTest
    extends AbstractJsonSerializationTestSuite<SimpleRequestBatchItem> {

  @Override
  public Class<SimpleRequestBatchItem> type() {
    return SimpleRequestBatchItem.class;
  }

  @Override
  public SimpleRequestBatchItem createDefault() {
    return SimpleRequestBatchItem
        .builder()
        .requestPayloadStructure(SimpleRequestPayload.of())
        .build();
  }

  @Override
  public SimpleRequestBatchItem createVariant() {
    return SimpleRequestBatchItem
        .builder()
        .requestPayloadStructure(SimpleRequestPayload.of())
        .build();
  }

  @Override
  public boolean unsupportedSpecShouldFailSerialize() {
    return false; // model supports UnsupportedVersion
  }
}
