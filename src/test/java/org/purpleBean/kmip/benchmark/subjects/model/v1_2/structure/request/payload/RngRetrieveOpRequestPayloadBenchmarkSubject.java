package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngRetrieveOpRequestPayload;

public class RngRetrieveOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RngRetrieveOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public RngRetrieveOpRequestPayloadBenchmarkSubject() throws Exception {
    RngRetrieveOpRequestPayload subject = RngRetrieveOpRequestPayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
    initialize(subject, RngRetrieveOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "RngRetrieveOpRequestPayload";
  }
}
