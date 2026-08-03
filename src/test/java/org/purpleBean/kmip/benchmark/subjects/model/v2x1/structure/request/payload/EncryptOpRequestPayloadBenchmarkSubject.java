package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.EncryptOpRequestPayload;

public class EncryptOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<EncryptOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public EncryptOpRequestPayloadBenchmarkSubject() throws Exception {
    EncryptOpRequestPayload subject = EncryptOpRequestPayload
        .builder()
        .build();
    initialize(subject, EncryptOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "EncryptOpRequestPayload";
  }
}