package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetConstraintsOpResponsePayload;

/**
 * Benchmark subject for {@link SetConstraintsOpResponsePayload}.
 */
public class SetConstraintsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetConstraintsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link SetConstraintsOpResponsePayloadBenchmarkSubject}.
   */
  public SetConstraintsOpResponsePayloadBenchmarkSubject() throws Exception {
    SetConstraintsOpResponsePayload subject = SetConstraintsOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, SetConstraintsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetConstraintsOpResponsePayload";
  }
}