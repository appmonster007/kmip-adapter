package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("QueryFunction JSON Serialization")
class QueryFunctionJsonTest extends AbstractJsonSerializationSuite<QueryFunction> {
    @Override
    protected Class<QueryFunction> type() {
        return QueryFunction.class;
    }

    @Override
    protected QueryFunction createDefault() {
        return new QueryFunction(QueryFunction.Standard.QUERY_SERVER_INFORMATION);
    }

    @Override
    protected QueryFunction createVariant() {
        return new QueryFunction(QueryFunction.Standard.QUERY_OPERATIONS);
    }
}
