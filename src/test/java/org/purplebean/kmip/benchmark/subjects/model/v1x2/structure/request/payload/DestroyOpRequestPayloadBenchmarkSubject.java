package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DestroyOpRequestPayload;

/**
 * Benchmark subject for {@link DestroyOpRequestPayload}.
 */
public class DestroyOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<DestroyOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DestroyOpRequestPayloadBenchmarkSubject}.
   */
  public DestroyOpRequestPayloadBenchmarkSubject() throws Exception {
    DestroyOpRequestPayload subject = DestroyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
    initialize(subject, DestroyOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "DestroyOpRequestPayload";
  }
}
