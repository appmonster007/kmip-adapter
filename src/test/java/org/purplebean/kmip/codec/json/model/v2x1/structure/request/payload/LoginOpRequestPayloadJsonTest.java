package org.purplebean.kmip.codec.json.model.v2x1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purplebean.kmip.model.core.structure.UsageLimits;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UsageLimitsTotal;
import org.purplebean.kmip.model.v2x1.structure.request.payload.LoginOpRequestPayload;
import org.purplebean.kmip.model.v2x1.type.RequestCount;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LoginOpRequestPayload Json Serialization Tests")
class LoginOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<LoginOpRequestPayload> {

  @Override
  public Class<LoginOpRequestPayload> type() {
    return LoginOpRequestPayload.class;
  }

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  public LoginOpRequestPayload createDefault() {
    return LoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(3600))
        .requestCount(RequestCount.of(10))
        .usageLimits(UsageLimits
            .builder()
            .usageLimitsTotal(UsageLimitsTotal.of(100L))
            .usageLimitsUnit(UsageLimitsUnit.of(UsageLimitsUnit.Standard.OBJECT))
            .build())
        .build();
  }

  @Override
  public LoginOpRequestPayload createVariant() {
    return LoginOpRequestPayload
        .builder()
        .leaseTime(LeaseTime.of(7200))
        .requestCount(RequestCount.of(20))
        .usageLimits(UsageLimits
            .builder()
            .usageLimitsTotal(UsageLimitsTotal.of(200L))
            .usageLimitsUnit(UsageLimitsUnit.of(UsageLimitsUnit.Standard.BYTE))
            .build())
        .build();
  }
}