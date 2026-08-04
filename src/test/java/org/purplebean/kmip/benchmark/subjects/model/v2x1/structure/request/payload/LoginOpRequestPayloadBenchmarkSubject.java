package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure.request.payload;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;

public class LoginOpRequestPayloadBenchmarkSubject
    extends KmipBenchmarkSubject<LoginOpRequestPayload> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

  public LoginOpRequestPayloadBenchmarkSubject() throws Exception {
    LoginOpRequestPayload subject = LoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .usageLimits(UsageLimits
            .builder()
            .usageLimitsTotal(UsageLimitsTotal.of(100L))
            .usageLimitsUnit(UsageLimitsUnit.of(UsageLimitsUnit.Standard.OBJECT))
            .build())
        .build();
    initialize(subject, LoginOpRequestPayload.class);
  }

  @Override
  public String name() {
    return "LoginOpRequestPayload";
  }
}