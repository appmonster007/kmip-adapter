package org.purpleBean.kmip.benchmark.subjects.model.v3x0.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v3x0.structure.response.payload.ObliterateOpResponsePayload;

public class ObliterateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ObliterateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public ObliterateOpResponsePayloadBenchmarkSubject() throws Exception {
    ObliterateOpResponsePayload subject = ObliterateOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, ObliterateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "ObliterateOpResponsePayload";
  }
}