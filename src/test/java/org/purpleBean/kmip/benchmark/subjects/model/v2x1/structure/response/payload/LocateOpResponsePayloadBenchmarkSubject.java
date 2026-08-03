package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.LocateOpResponsePayload;

public class LocateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LocateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public LocateOpResponsePayloadBenchmarkSubject() throws Exception {
    LocateOpResponsePayload subject = LocateOpResponsePayload
        .builder()
        .build();
    initialize(subject, LocateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "LocateOpResponsePayload";
  }
}
