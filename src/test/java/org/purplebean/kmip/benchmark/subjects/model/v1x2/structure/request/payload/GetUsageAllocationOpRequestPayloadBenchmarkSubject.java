package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetUsageAllocationOpRequestPayload;

public class GetUsageAllocationOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<GetUsageAllocationOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public GetUsageAllocationOpRequestPayloadBenchmarkSubject() throws Exception {
    GetUsageAllocationOpRequestPayload subject = GetUsageAllocationOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .build();
    initialize(subject, GetUsageAllocationOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "GetUsageAllocationOpRequestPayload";
  }
}
