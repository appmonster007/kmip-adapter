package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.QueryAsynchronousRequestsOpRequestPayload;

public class QueryAsynchronousRequestsOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<QueryAsynchronousRequestsOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public QueryAsynchronousRequestsOpRequestPayloadBenchmarkSubject() throws Exception {
    QueryAsynchronousRequestsOpRequestPayload subject = QueryAsynchronousRequestsOpRequestPayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, QueryAsynchronousRequestsOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "QueryAsynchronousRequestsOpRequestPayload";
  }
}