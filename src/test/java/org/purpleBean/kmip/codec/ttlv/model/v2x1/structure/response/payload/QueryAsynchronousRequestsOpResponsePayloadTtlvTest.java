package org.purplebean.kmip.codec.ttlv.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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