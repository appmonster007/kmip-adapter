package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.request.RequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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