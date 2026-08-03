package org.purplebean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.ProtectionLevel;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProtectionLevel TTLV Serialization")
class ProtectionLevelTtlvTest extends AbstractTtlvSerializationTestSuite<ProtectionLevel> {
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
