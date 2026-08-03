package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.MacOpResponsePayload;

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