package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.RngSeedOpResponsePayload;

public class RngSeedOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RngSeedOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public RngSeedOpResponsePayloadBenchmarkSubject() throws Exception {
    RngSeedOpResponsePayload subject = RngSeedOpResponsePayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
    initialize(subject, RngSeedOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "RngSeedOpResponsePayload";
  }
}
