package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ArchiveOpRequestPayload;

/**
 * Benchmark subject for {@link ArchiveOpRequestPayload}.
 */
public class ArchiveOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<ArchiveOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link ArchiveOpRequestPayloadBenchmarkSubject}.
   */
  public ArchiveOpRequestPayloadBenchmarkSubject() throws Exception {
    ArchiveOpRequestPayload subject = ArchiveOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
    initialize(subject, ArchiveOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "ArchiveOpRequestPayload";
  }
}
