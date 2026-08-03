package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.GetConstraintsOpRequestPayload;

public class GetConstraintsOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetConstraintsOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public GetConstraintsOpRequestPayloadBenchmarkSubject() throws Exception {
    GetConstraintsOpRequestPayload subject = GetConstraintsOpRequestPayload
        .builder()
        .build();
    initialize(subject, GetConstraintsOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "GetConstraintsOpRequestPayload";
  }
}