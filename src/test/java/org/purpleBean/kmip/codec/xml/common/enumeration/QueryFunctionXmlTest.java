package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("QueryFunction XML Serialization")
class QueryFunctionXmlTest extends AbstractXmlSerializationSuite<QueryFunction> {
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
        return new QueryFunction(QueryFunction.Standard.QUERY_OPERATIONAL_CAPABILITIES);
    }
}
