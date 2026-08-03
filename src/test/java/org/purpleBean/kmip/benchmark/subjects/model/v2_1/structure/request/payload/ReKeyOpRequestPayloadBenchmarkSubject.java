package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.ReKeyOpRequestPayload;

public class ReKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ReKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ReKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    ReKeyOpRequestPayload subject = ReKeyOpRequestPayload
        .builder()
        .build();
    initialize(subject, ReKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ReKeyOpRequestPayload";
  }
}