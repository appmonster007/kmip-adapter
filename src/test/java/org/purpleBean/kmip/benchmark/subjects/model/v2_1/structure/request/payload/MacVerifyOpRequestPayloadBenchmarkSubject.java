package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure.request.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.MacVerifyOpRequestPayload;

public class MacVerifyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacVerifyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public MacVerifyOpRequestPayloadBenchmarkSubject() throws Exception {
    MacVerifyOpRequestPayload subject = MacVerifyOpRequestPayload
        .builder()
        .build();
    initialize(subject, MacVerifyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "MacVerifyOpRequestPayload";
  }
}