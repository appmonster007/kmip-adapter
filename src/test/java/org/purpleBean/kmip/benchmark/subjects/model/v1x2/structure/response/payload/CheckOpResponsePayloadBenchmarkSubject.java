package org.purpleBean.kmip.benchmark.subjects.model.v1x2.structure.response.payload;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.CheckOpResponsePayload;

public class CheckOpResponsePayloadBenchmarkSubject
    extends KmipBenchmarkSubject<CheckOpResponsePayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CheckOpResponsePayloadBenchmarkSubject() throws Exception {
    CheckOpResponsePayload subject = CheckOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .cryptographicUsageMask(CryptographicUsageMask.of(1))
        .leaseTime(LeaseTime.of(3600))
        .build();
    initialize(subject, CheckOpResponsePayload.class);
  }

  @Override
  public String name() {
    return "CheckOpResponsePayload";
  }
}
