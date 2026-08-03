package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.Constraints;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.SetConstraintsOpRequestPayload;

public class SetConstraintsOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetConstraintsOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public SetConstraintsOpRequestPayloadBenchmarkSubject() throws Exception {
    SetConstraintsOpRequestPayload subject = SetConstraintsOpRequestPayload
        .builder()
        .constraints(Constraints.of(java.util.List.of()))
        .build();
    initialize(subject, SetConstraintsOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "SetConstraintsOpRequestPayload";
  }
}