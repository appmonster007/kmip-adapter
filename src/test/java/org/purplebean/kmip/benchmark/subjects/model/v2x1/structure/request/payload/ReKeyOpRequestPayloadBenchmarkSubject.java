package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.request.payload.ReKeyOpRequestPayload;

/**
 * Benchmark subject for {@link ReKeyOpRequestPayload}.
 */
public class ReKeyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ReKeyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link ReKeyOpRequestPayloadBenchmarkSubject}.
   */
  public ReKeyOpRequestPayloadBenchmarkSubject() throws Exception {
    ReKeyOpRequestPayload subject = ReKeyOpRequestPayload
        .builder()
        .build();
    initialize(subject, ReKeyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ReKeyOpRequestPayload";
  }
}