package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.InteropOpResponsePayload;

public class InteropOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<InteropOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public InteropOpResponsePayloadBenchmarkSubject() throws Exception {
    InteropOpResponsePayload subject = InteropOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, InteropOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "InteropOpResponsePayload";
  }
}