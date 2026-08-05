package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RngRetrieveOpRequestPayload;

/**
 * Benchmark subject for {@link RngRetrieveOpRequestPayload}.
 */
public class RngRetrieveOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RngRetrieveOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RngRetrieveOpRequestPayloadBenchmarkSubject}.
   */
  public RngRetrieveOpRequestPayloadBenchmarkSubject() throws Exception {
    RngRetrieveOpRequestPayload subject = RngRetrieveOpRequestPayload
        .builder()
        .dataLength(DataLength.of(16))
        .build();
    initialize(subject, RngRetrieveOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "RngRetrieveOpRequestPayload";
  }
}
