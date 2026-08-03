package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.response.ResponseBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResponseBatchItem Ttlv Serialization Tests")
class ResponseBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<ResponseBatchItem> {

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