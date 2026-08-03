package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.MacOpRequestPayload;

public class MacOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<MacOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public MacOpRequestPayloadBenchmarkSubject() throws Exception {
    MacOpRequestPayload subject = MacOpRequestPayload
        .builder()
        .build();
    initialize(subject, MacOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "MacOpRequestPayload";
  }
}