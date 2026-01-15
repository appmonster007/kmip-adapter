package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionBenchmarkSubject extends KmipBenchmarkSubject<QueryFunction> {

    public QueryFunctionBenchmarkSubject() throws Exception {
        QueryFunction queryFunction = QueryFunction.Standard.QUERY_SERVER_INFORMATION.inst();
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
