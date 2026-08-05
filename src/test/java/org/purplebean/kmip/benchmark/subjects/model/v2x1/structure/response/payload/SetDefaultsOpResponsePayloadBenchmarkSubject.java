package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.SetDefaultsOpResponsePayload;

/**
 * Benchmark subject for {@link SetDefaultsOpResponsePayload}.
 */
public class SetDefaultsOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<SetDefaultsOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link SetDefaultsOpResponsePayloadBenchmarkSubject}.
   */
  public SetDefaultsOpResponsePayloadBenchmarkSubject() throws Exception {
    SetDefaultsOpResponsePayload subject = SetDefaultsOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, SetDefaultsOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "SetDefaultsOpResponsePayload";
  }
}