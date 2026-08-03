package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ProtectionPeriod;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionPeriod Xml Serialization Tests")
class ProtectionPeriodXmlTest extends AbstractXmlSerializationTestSuite<ProtectionPeriod> {

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