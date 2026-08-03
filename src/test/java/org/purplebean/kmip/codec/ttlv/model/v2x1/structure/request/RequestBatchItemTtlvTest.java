package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.RequestBatchItem;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RequestBatchItem Ttlv Serialization Tests")
class RequestBatchItemTtlvTest extends AbstractTtlvSerializationTestSuite<RequestBatchItem> {

  @Override
  public Class<RequestBatchItem> type() {
    return RequestBatchItem.class;
  }

  @Override
  public RequestBatchItem createDefault() {
    return RequestBatchItem
        .builder()
        .build();
  }

  @Override
  public RequestBatchItem createVariant() {
    return RequestBatchItem
        .builder()
        .build();
  }
}