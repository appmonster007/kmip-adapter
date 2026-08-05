package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v1x2.structure.response.payload.PollOpResponsePayload;

/**
 * Benchmark subject for {@link PollOpResponsePayload}.
 */
public class PollOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<PollOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link PollOpResponsePayloadBenchmarkSubject}.
   */
  public PollOpResponsePayloadBenchmarkSubject() throws Exception {
    PollOpResponsePayload subject = PollOpResponsePayload
        .builder()
        .build();  // TODO: Create a default instance
    initialize(subject, PollOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "PollOpResponsePayload";
  }
}