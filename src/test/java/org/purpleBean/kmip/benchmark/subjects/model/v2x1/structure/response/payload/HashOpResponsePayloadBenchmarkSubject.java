package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.HashOpResponsePayload;

public class HashOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<HashOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public HashOpResponsePayloadBenchmarkSubject() throws Exception {
    HashOpResponsePayload subject = HashOpResponsePayload
        .builder()
        .build();
    initialize(subject, HashOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "HashOpResponsePayload";
  }
}