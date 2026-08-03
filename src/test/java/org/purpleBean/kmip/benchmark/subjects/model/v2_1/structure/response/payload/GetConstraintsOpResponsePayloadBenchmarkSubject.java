package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Constraints;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.GetConstraintsOpResponsePayload;

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