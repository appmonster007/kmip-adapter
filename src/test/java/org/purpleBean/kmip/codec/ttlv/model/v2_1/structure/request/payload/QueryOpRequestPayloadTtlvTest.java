package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.QueryOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryOpRequestPayload Ttlv Serialization Tests")
class QueryOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<QueryOpRequestPayload> {

  @Override
  public Class<QueryOpRequestPayload> type() {
    return QueryOpRequestPayload.class;
  }

  @Override
  public QueryOpRequestPayload createDefault() {
    return QueryOpRequestPayload
        .builder()
        .queryFunction(QueryFunction.Standard.QUERY_OPERATIONS.inst())
        .build();
  }

  @Override
  public QueryOpRequestPayload createVariant() {
    return QueryOpRequestPayload
        .builder()
        .queryFunction(QueryFunction.Standard.QUERY_OPERATIONS.inst())
        .build();
  }
}