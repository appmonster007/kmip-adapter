package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionBenchmarkSubject extends KmipBenchmarkSubject<QueryFunction> {

    public QueryFunctionBenchmarkSubject() throws Exception {
        QueryFunction queryFunction = new QueryFunction(QueryFunction.Standard.QUERY_SERVER_INFORMATION);
        initialize(queryFunction, QueryFunction.class);
    }

    @Override
    public String name() {
        return "QueryFunction";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
