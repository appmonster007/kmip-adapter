package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.PutOpResponsePayload;

public class PutOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<PutOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public PutOpResponsePayloadBenchmarkSubject() throws Exception {
    PutOpResponsePayload subject = PutOpResponsePayload
        .builder()
        .build();
    initialize(subject, PutOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "PutOpResponsePayload";
  }
}
