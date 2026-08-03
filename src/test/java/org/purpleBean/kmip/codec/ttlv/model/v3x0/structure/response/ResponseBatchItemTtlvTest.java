package org.purplebean.kmip.codec.ttlv.model.v3x0.structure.response;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.structure.response.ResponseBatchItem;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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