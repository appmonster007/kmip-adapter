package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;
import org.purplebean.kmip.model.v2x1.structure.request.payload.QueryOpRequestPayload;

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