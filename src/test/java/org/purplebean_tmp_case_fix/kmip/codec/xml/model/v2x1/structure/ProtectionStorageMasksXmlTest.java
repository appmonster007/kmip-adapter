package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.ProtectionStorageMasks;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProtectionStorageMasks Xml Serialization Tests")
class ProtectionStorageMasksXmlTest
    extends AbstractXmlSerializationTestSuite<ProtectionStorageMasks> {

  @Override
  public Class<ProtectionStorageMasks> type() {
    return ProtectionStorageMasks.class;
  }

  @Override
  public ProtectionStorageMasks createDefault() {
    return ProtectionStorageMasks
        .builder()
        .build();
  }

  @Override
  public ProtectionStorageMasks createVariant() {
    return ProtectionStorageMasks
        .builder()
        .build();
  }
}