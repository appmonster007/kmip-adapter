package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.MacOpResponsePayload;

public class MacOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public MacOpResponsePayloadBenchmarkSubject() throws Exception {
    MacOpResponsePayload subject = MacOpResponsePayload
        .builder()
        .build();
    initialize(subject, MacOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "MacOpResponsePayload";
  }
}