package org.purplebean.kmip.benchmark.subjects.model.v1x2.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CheckOpRequestPayload;

public class CheckOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CheckOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CheckOpRequestPayloadBenchmarkSubject() throws Exception {

    CheckOpRequestPayload subject = CheckOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .cryptographicUsageMask(CryptographicUsageMask.of(3))
        .leaseTime(LeaseTime.of(3600))
        .build();
    initialize(subject, CheckOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "CheckOpRequestPayload";
  }
}
