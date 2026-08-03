package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.QueryAsynchronousRequestsOpResponsePayload;

public class QueryAsynchronousRequestsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryAsynchronousRequestsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public QueryAsynchronousRequestsOpResponsePayloadBenchmarkSubject() throws Exception {
    QueryAsynchronousRequestsOpResponsePayload subject = QueryAsynchronousRequestsOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, QueryAsynchronousRequestsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "QueryAsynchronousRequestsOpResponsePayload";
  }
}