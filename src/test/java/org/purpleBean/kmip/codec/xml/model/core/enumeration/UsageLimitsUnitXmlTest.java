package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("UsageLimitsUnit XML Serialization")
class UsageLimitsUnitXmlTest extends AbstractXmlSerializationTestSuite<UsageLimitsUnit> {
  @Override
  public Class<UsageLimitsUnit> type() {
    return UsageLimitsUnit.class;
  }

  @Override
  public UsageLimitsUnit createDefault() {
    return UsageLimitsUnit.Standard.BYTE.inst();
  }

  @Override
  public UsageLimitsUnit createVariant() {
    return UsageLimitsUnit.Standard.OBJECT.inst();
  }
}
