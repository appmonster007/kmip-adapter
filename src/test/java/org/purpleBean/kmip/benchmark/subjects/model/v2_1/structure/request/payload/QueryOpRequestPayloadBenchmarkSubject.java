package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.QueryOpRequestPayload;

public class QueryOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public QueryOpRequestPayloadBenchmarkSubject() throws Exception {
    QueryOpRequestPayload subject = QueryOpRequestPayload
        .builder()
        .queryFunction(QueryFunction.Standard.QUERY_OPERATIONS.inst())
        .build();
    initialize(subject, QueryOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "QueryOpRequestPayload";
  }
}