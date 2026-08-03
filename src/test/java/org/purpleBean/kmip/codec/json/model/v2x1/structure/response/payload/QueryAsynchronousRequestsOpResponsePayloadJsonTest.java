package org.purplebean.kmip.codec.json.model.v2x1.structure.response.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QueryAsynchronousRequestsOpResponsePayload Json Serialization Tests")
class QueryAsynchronousRequestsOpResponsePayloadJsonTest
    extends AbstractJsonSerializationTestSuite<QueryAsynchronousRequestsOpResponsePayload> {

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