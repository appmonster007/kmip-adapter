package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngSeedOpResponsePayload;

/**
 * Benchmark subject for {@link RngSeedOpResponsePayload}.
 */
public class RngSeedOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RngSeedOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RngSeedOpResponsePayloadBenchmarkSubject}.
   */
  public RngSeedOpResponsePayloadBenchmarkSubject() throws Exception {
    RngSeedOpResponsePayload subject = RngSeedOpResponsePayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
    initialize(subject, RngSeedOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "RngSeedOpResponsePayload";
  }
}
