package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ProtectionPeriod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProtectionPeriod Ttlv Serialization Tests")
class ProtectionPeriodTtlvTest extends AbstractTtlvSerializationTestSuite<ProtectionPeriod> {

  @Override
  public Class<ProtectionPeriod> type() {
    return ProtectionPeriod.class;
  }

  @Override
  public ProtectionPeriod createDefault() {
    return ProtectionPeriod.of(12345L);
  }

  @Override
  public ProtectionPeriod createVariant() {
    return ProtectionPeriod.of(54321L);
  }
}