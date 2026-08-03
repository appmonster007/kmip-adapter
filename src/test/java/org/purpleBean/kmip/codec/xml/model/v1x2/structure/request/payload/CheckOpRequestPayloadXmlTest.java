package org.purplebean.kmip.codec.xml.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.UsageLimitsCount;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CheckOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CheckOpRequestPayload Xml Serialization Tests")
class CheckOpRequestPayloadXmlTest
    extends AbstractXmlSerializationTestSuite<CheckOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CheckOpRequestPayload> type() {
    return CheckOpRequestPayload.class;
  }

  @Override
  public CheckOpRequestPayload createDefault() {
    return CheckOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .usageLimitsCount(UsageLimitsCount.of(100L))
        .cryptographicUsageMask(CryptographicUsageMask.of(7))
        .leaseTime(LeaseTime.of(3600))
        .build();
  }

  @Override
  public CheckOpRequestPayload createVariant() {
    return CheckOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174001"))
        .usageLimitsCount(UsageLimitsCount.of(200L))
        .cryptographicUsageMask(CryptographicUsageMask.of(2))
        .leaseTime(LeaseTime.of(7200))
        .build();
  }
}
