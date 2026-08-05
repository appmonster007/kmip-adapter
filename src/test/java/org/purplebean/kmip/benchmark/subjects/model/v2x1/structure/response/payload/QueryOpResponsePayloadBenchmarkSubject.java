package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v2x1.structure.response.payload.QueryOpResponsePayload;

/**
 * Benchmark subject for {@link QueryOpResponsePayload}.
 */
public class QueryOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link QueryOpResponsePayloadBenchmarkSubject}.
   */
  public QueryOpResponsePayloadBenchmarkSubject() throws Exception {
    QueryOpResponsePayload subject = QueryOpResponsePayload
        .builder()
        .operation(Operation.Standard.QUERY.inst())
        .build();
    initialize(subject, QueryOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "QueryOpResponsePayload";
  }
}