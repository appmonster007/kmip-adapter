package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;

public class RngRetrieveOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RngRetrieveOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public RngRetrieveOpResponsePayloadBenchmarkSubject() throws Exception {
    RngRetrieveOpResponsePayload subject = RngRetrieveOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, RngRetrieveOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "RngRetrieveOpResponsePayload";
  }
}
