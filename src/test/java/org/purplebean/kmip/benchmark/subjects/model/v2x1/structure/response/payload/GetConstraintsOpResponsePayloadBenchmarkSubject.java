package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Constraints;
import org.purplebean.kmip.model.v2x1.structure.response.payload.GetConstraintsOpResponsePayload;

public class GetConstraintsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetConstraintsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public GetConstraintsOpResponsePayloadBenchmarkSubject() throws Exception {
    GetConstraintsOpResponsePayload subject = GetConstraintsOpResponsePayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
    initialize(subject, GetConstraintsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetConstraintsOpResponsePayload";
  }
}