package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProtectionStorageMask Ttlv Serialization Tests")
class ProtectionStorageMaskTtlvTest
    extends AbstractTtlvSerializationTestSuite<ProtectionStorageMask> {

  @Override
  public Class<ProtectionStorageMask> type() {
    return ProtectionStorageMask.class;
  }

  @Override
  public ProtectionStorageMask createDefault() {
    return ProtectionStorageMask.of(123);
  }

  @Override
  public ProtectionStorageMask createVariant() {
    return ProtectionStorageMask.of(456);
  }
}