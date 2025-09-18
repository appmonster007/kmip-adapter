package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("QueryFunction TTLV Serialization")
class QueryFunctionTtlvTest extends AbstractTtlvSerializationSuite<QueryFunction> {
    @Override
    protected Class<QueryFunction> type() {
        return QueryFunction.class;
    }

    @Override
    protected QueryFunction createDefault() {
        return new QueryFunction(QueryFunction.Standard.PLACEHOLDER_1);
    }

    @Override
    protected QueryFunction createVariant() {
        return new QueryFunction(QueryFunction.Standard.PLACEHOLDER_2);
    }
}
