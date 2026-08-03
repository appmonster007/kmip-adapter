package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;

public class SetDefaultsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetDefaultsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public SetDefaultsOpResponsePayloadBenchmarkSubject() throws Exception {
    SetDefaultsOpResponsePayload subject = SetDefaultsOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, SetDefaultsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetDefaultsOpResponsePayload";
  }
}