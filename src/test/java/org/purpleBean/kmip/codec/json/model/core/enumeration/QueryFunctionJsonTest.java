package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QueryFunction JSON Serialization")
class QueryFunctionJsonTest extends AbstractJsonSerializationTestSuite<QueryFunction> {
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
