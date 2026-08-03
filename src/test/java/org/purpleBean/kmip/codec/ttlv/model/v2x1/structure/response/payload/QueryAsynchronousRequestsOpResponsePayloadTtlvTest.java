package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpResponsePayload Ttlv Serialization Tests")
class QueryAsynchronousRequestsOpResponsePayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<QueryAsynchronousRequestsOpResponsePayload> {

  @Override
  public Class<QueryAsynchronousRequestsOpResponsePayload> type() {
    return QueryAsynchronousRequestsOpResponsePayload.class;
  }

  @Override
  public QueryAsynchronousRequestsOpResponsePayload createDefault() {
    return QueryAsynchronousRequestsOpResponsePayload
        .builder()
        .build();
  }

  @Override
  public QueryAsynchronousRequestsOpResponsePayload createVariant() {
    return QueryAsynchronousRequestsOpResponsePayload
        .builder()
        .build();
  }
}