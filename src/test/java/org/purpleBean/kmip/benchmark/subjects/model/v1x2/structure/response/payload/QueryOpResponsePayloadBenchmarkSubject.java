package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.Operation;
import org.purplebean.kmip.model.v1x2.structure.response.payload.QueryOpResponsePayload;

public class QueryOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public QueryOpResponsePayloadBenchmarkSubject() throws Exception {
    QueryOpResponsePayload subject = QueryOpResponsePayload
        .builder()
        .operation(Operation.of(Operation.Standard.QUERY))
        .build();
    initialize(subject, QueryOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "QueryOpResponsePayload";
  }
}
