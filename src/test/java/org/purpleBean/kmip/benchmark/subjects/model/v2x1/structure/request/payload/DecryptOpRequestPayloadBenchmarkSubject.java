package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.request.payload.DecryptOpRequestPayload;

public class DecryptOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DecryptOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public DecryptOpRequestPayloadBenchmarkSubject() throws Exception {
    DecryptOpRequestPayload subject = DecryptOpRequestPayload
        .builder()
        .build();
    initialize(subject, DecryptOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DecryptOpRequestPayload";
  }
}