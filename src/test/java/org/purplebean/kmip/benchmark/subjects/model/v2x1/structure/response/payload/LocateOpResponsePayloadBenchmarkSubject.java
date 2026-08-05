package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.response.payload.LocateOpResponsePayload;

/**
 * Benchmark subject for {@link LocateOpResponsePayload}.
 */
public class LocateOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LocateOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  /**
   * Constructs a new {@link LocateOpResponsePayloadBenchmarkSubject}.
   */
  public LocateOpResponsePayloadBenchmarkSubject() throws Exception {
    LocateOpResponsePayload subject = LocateOpResponsePayload
        .builder()
        .build();
    initialize(subject, LocateOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "LocateOpResponsePayload";
  }
}
