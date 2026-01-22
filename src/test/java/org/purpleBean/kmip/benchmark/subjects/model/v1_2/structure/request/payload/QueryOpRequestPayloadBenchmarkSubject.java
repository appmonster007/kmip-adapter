package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.QueryOpRequestPayload;

public class QueryOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<QueryOpRequestPayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public QueryOpRequestPayloadBenchmarkSubject() throws Exception {
        QueryOpRequestPayload subject = QueryOpRequestPayload.builder()
                .queryFunction(QueryFunction.of(QueryFunction.Standard.QUERY_OPERATIONS))
                .build();
        initialize(subject, QueryOpRequestPayload.class);
    }

    @Override
    public String name() {
        return "QueryOpRequestPayload";
    }
}
