package org.purplebean.kmip.codec.xml.model.v2x1.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.ResponseBatchItem;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResponseBatchItem Xml Serialization Tests")
class ResponseBatchItemXmlTest extends AbstractXmlSerializationTestSuite<ResponseBatchItem> {

  @Override
  public Class<ResponseBatchItem> type() {
    return ResponseBatchItem.class;
  }

  @Override
  public ResponseBatchItem createDefault() {
    return ResponseBatchItem
        .builder()
        .build();
  }

  @Override
  public ResponseBatchItem createVariant() {
    return ResponseBatchItem
        .builder()
        .build();
  }
}