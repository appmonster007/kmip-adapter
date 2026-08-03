package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;

public class PollOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<PollOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public PollOpResponsePayloadBenchmarkSubject() throws Exception {
    PollOpResponsePayload subject = PollOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, PollOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "PollOpResponsePayload";
  }
}