package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.v2x1.structure.Right;
import org.purplebean.kmip.model.v2x1.structure.Rights;
import org.purplebean.kmip.model.v2x1.structure.request.payload.DelegatedLoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

/**
 * Benchmark subject for {@link DelegatedLoginOpRequestPayload}.
 */
public class DelegatedLoginOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DelegatedLoginOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  /**
   * Constructs a new {@link DelegatedLoginOpRequestPayloadBenchmarkSubject}.
   */
  public DelegatedLoginOpRequestPayloadBenchmarkSubject() throws Exception {
    DelegatedLoginOpRequestPayload subject = DelegatedLoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .rights(Rights
            .builder()
            .right(Right.builder().build())
            .build())
        .build();
    initialize(subject, DelegatedLoginOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DelegatedLoginOpRequestPayload";
  }
}