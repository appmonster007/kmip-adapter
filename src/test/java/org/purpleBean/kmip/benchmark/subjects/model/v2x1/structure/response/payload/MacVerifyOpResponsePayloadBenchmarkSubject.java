package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.response.payload.MacVerifyOpResponsePayload;

public class MacVerifyOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<MacVerifyOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public MacVerifyOpResponsePayloadBenchmarkSubject() throws Exception {
    MacVerifyOpResponsePayload subject = MacVerifyOpResponsePayload
        .builder()
        .build();
    initialize(subject, MacVerifyOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "MacVerifyOpResponsePayload";
  }
}