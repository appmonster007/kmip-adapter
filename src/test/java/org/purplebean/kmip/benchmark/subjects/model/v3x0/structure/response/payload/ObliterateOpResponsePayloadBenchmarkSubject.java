package org.purplebean.kmip.benchmark.subjects.model.v3x0.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v3x0.structure.response.payload.ObliterateOpResponsePayload;

/**
 * Benchmark subject for {@link ObliterateOpResponsePayload}.
 */
public class ObliterateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ObliterateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link ObliterateOpResponsePayloadBenchmarkSubject}.
   */
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