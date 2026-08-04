package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ProtectionPeriod;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProtectionPeriod Ttlv Serialization Tests")
class ProtectionPeriodTtlvTest extends AbstractTtlvSerializationTestSuite<ProtectionPeriod> {

  @Override
  public Class<ProtectionPeriod> type() {
    return ProtectionPeriod.class;
  }

  @Override
  public ProtectionPeriod createDefault() {
    return ProtectionPeriod.of(12345);
  }

  @Override
  public ProtectionPeriod createVariant() {
    return ProtectionPeriod.of(54321);
  }
}