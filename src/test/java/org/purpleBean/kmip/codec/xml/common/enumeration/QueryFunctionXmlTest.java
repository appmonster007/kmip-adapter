package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.QueryFunction;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QueryFunction XML Serialization")
class QueryFunctionXmlTest extends AbstractXmlSerializationTestSuite<QueryFunction> {
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
