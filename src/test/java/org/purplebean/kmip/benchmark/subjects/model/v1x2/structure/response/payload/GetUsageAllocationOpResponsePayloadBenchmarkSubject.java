package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetUsageAllocationOpResponsePayload;

/**
 * Benchmark subject for {@link GetUsageAllocationOpResponsePayload}.
 */
public class GetUsageAllocationOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetUsageAllocationOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link GetUsageAllocationOpResponsePayloadBenchmarkSubject}.
   */
  public GetUsageAllocationOpResponsePayloadBenchmarkSubject() throws Exception {
    GetUsageAllocationOpResponsePayload subject = GetUsageAllocationOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .build();
    initialize(subject, GetUsageAllocationOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "GetUsageAllocationOpResponsePayload";
  }
}
