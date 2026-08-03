package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.QueryOpResponsePayload;

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
