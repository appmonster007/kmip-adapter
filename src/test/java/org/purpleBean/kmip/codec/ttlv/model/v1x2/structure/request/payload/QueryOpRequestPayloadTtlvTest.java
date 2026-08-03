package org.purpleBean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.QueryOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryOpRequestPayload Ttlv Serialization Tests")
class QueryOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<QueryOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<QueryOpRequestPayload> type() {
    return QueryOpRequestPayload.class;
  }

  @Override
  public QueryOpRequestPayload createDefault() {
    return QueryOpRequestPayload
        .builder()
        .queryFunction(QueryFunction.of(QueryFunction.Standard.QUERY_OPERATIONS))
        .build();
  }

  @Override
  public QueryOpRequestPayload createVariant() {
    return QueryOpRequestPayload
        .builder()
        .queryFunction(QueryFunction.of(QueryFunction.Standard.QUERY_OBJECTS))
        .build();
  }
}
