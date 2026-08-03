package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpRequestPayload Json Serialization Tests")
class QueryAsynchronousRequestsOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<QueryAsynchronousRequestsOpRequestPayload> {

  @Override
  public Class<QueryAsynchronousRequestsOpRequestPayload> type() {
    return QueryAsynchronousRequestsOpRequestPayload.class;
  }

  @Override
  public QueryAsynchronousRequestsOpRequestPayload createDefault() {
    return QueryAsynchronousRequestsOpRequestPayload
        .builder()
        .build();
  }

  @Override
  public QueryAsynchronousRequestsOpRequestPayload createVariant() {
    return QueryAsynchronousRequestsOpRequestPayload
        .builder()
        .build();
  }
}