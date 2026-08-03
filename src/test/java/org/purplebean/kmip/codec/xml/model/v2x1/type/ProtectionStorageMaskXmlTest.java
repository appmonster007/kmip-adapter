package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ProtectionStorageMask;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionStorageMask Xml Serialization Tests")
class ProtectionStorageMaskXmlTest
    extends AbstractXmlSerializationTestSuite<ProtectionStorageMask> {

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