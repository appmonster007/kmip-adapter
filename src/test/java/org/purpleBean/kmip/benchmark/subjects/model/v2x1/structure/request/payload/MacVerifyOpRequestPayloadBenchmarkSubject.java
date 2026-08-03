package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.MacVerifyOpRequestPayload;

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