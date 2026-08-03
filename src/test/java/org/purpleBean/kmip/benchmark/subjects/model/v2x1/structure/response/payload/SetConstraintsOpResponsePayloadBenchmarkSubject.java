package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;

public class SetConstraintsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetConstraintsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public SetConstraintsOpResponsePayloadBenchmarkSubject() throws Exception {
    SetConstraintsOpResponsePayload subject = SetConstraintsOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, SetConstraintsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetConstraintsOpResponsePayload";
  }
}