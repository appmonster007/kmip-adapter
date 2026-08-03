package org.purpleBean.kmip.codec.json.model.v3_0.structure.request;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.structure.request.RequestBatchItem;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RequestBatchItem Json Serialization Tests")
class RequestBatchItemJsonTest extends AbstractJsonSerializationTestSuite<RequestBatchItem> {

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