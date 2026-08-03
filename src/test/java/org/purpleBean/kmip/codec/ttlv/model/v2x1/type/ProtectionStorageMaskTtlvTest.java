package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ProtectionStorageMask;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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