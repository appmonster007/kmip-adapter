package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryFunction TTLV Serialization")
class QueryFunctionTtlvTest extends AbstractTtlvSerializationTestSuite<QueryFunction> {
  @Override
  public Class<QueryFunction> type() {
    return QueryFunction.class;
  }

  @Override
  public QueryFunction createDefault() {
    return QueryFunction.Standard.QUERY_SERVER_INFORMATION.inst();
  }

  @Override
  public QueryFunction createVariant() {
    return QueryFunction.Standard.QUERY_OPERATIONS.inst();
  }
}
