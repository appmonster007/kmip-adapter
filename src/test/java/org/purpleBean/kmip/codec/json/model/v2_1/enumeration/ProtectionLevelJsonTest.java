package org.purpleBean.kmip.codec.json.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.ProtectionLevel;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ProtectionLevel JSON Serialization")
class ProtectionLevelJsonTest extends AbstractJsonSerializationTestSuite<ProtectionLevel> {
  @Override
  public Class<ProtectionLevel> type() {
    return ProtectionLevel.class;
  }

  @Override
  public ProtectionLevel createDefault() {
    return ProtectionLevel.Standard.HIGH.inst();
  }

  @Override
  public ProtectionLevel createVariant() {
    return ProtectionLevel.Standard.LOW.inst();
  }
}
