package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("QueryFunction TTLV Serialization")
class QueryFunctionTtlvTest extends AbstractTtlvSerializationTestSuite<QueryFunction> {
    @Override
    protected Class<QueryFunction> type() {
        return QueryFunction.class;
    }

    @Override
    protected QueryFunction createDefault() {
        return QueryFunction.Standard.QUERY_SERVER_INFORMATION.inst();
    }

    @Override
    protected QueryFunction createVariant() {
        return QueryFunction.Standard.QUERY_OPERATIONS.inst();
    }
}
