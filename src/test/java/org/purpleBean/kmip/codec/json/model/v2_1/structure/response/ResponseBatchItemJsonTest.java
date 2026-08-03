package org.purpleBean.kmip.codec.json.model.v2_1.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResponseBatchItem Json Serialization Tests")
class ResponseBatchItemJsonTest extends AbstractJsonSerializationTestSuite<ResponseBatchItem> {

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