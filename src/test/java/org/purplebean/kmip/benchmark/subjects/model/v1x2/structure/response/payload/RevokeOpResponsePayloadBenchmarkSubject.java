package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RevokeOpResponsePayload;

/**
 * Benchmark subject for {@link RevokeOpResponsePayload}.
 */
public class RevokeOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<RevokeOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link RevokeOpResponsePayloadBenchmarkSubject}.
   */
  public RevokeOpResponsePayloadBenchmarkSubject() throws Exception {
    RevokeOpResponsePayload subject = RevokeOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
    initialize(subject, RevokeOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "RevokeOpResponsePayload";
  }
}
