package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.MacOpRequestPayload;

/**
 * Benchmark subject for {@link MacOpRequestPayload}.
 */
public class MacOpRequestPayloadBenchmarkSubject extends KmipBenchmarkSubject<MacOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link MacOpRequestPayloadBenchmarkSubject}.
   */
  public MacOpRequestPayloadBenchmarkSubject() throws Exception {
    MacOpRequestPayload subject = MacOpRequestPayload
        .builder()
        .build();
    initialize(subject, MacOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "MacOpRequestPayload";
  }
}