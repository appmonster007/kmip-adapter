package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.QueryFunction;
import org.purplebean.kmip.model.v1x2.structure.request.payload.QueryOpRequestPayload;

/**
 * Benchmark subject for {@link QueryOpRequestPayload}.
 */
public class QueryOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link QueryOpRequestPayloadBenchmarkSubject}.
   */
  public QueryOpRequestPayloadBenchmarkSubject() throws Exception {
    QueryOpRequestPayload subject = QueryOpRequestPayload
        .builder()
        .queryFunction(QueryFunction.of(QueryFunction.Standard.QUERY_OPERATIONS))
        .build();
    initialize(subject, QueryOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "QueryOpRequestPayload";
  }
}
