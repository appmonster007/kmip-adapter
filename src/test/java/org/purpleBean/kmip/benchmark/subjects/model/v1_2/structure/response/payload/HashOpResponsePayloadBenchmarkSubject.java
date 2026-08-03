package org.purpleBean.kmip.benchmark.subjects.model.v1_2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.HashOpResponsePayload;

public class HashOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<HashOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public HashOpResponsePayloadBenchmarkSubject() throws Exception {
    HashOpResponsePayload subject = HashOpResponsePayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, HashOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "HashOpResponsePayload";
  }
}
