package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.QueryOpResponsePayload;

public class QueryOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

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