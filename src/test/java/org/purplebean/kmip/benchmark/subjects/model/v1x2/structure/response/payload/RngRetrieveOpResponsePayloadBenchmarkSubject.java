package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;

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
