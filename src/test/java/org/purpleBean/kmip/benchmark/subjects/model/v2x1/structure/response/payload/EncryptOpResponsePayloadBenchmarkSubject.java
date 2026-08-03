package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.EncryptOpResponsePayload;

public class EncryptOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<EncryptOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public EncryptOpResponsePayloadBenchmarkSubject() throws Exception {
    EncryptOpResponsePayload subject = EncryptOpResponsePayload
        .builder()
        .build();
    initialize(subject, EncryptOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "EncryptOpResponsePayload";
  }
}