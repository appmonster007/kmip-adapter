package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.PingOpRequestPayload;

public class PingOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<PingOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public PingOpRequestPayloadBenchmarkSubject() throws Exception {
    PingOpRequestPayload subject = PingOpRequestPayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, PingOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "PingOpRequestPayload";
  }
}