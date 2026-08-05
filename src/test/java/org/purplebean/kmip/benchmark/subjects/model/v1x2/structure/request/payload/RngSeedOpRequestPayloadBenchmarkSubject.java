package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RngSeedOpRequestPayload;

/**
 * Benchmark subject for {@link RngSeedOpRequestPayload}.
 */
public class RngSeedOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RngSeedOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RngSeedOpRequestPayloadBenchmarkSubject}.
   */
  public RngSeedOpRequestPayloadBenchmarkSubject() throws Exception {
    RngSeedOpRequestPayload subject = RngSeedOpRequestPayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .build();
    initialize(subject, RngSeedOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "RngSeedOpRequestPayload";
  }
}
