package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.StorageStatusMask;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("StorageStatusMask XML Serialization Tests")
class StorageStatusMaskXmlTest extends AbstractXmlSerializationTestSuite<StorageStatusMask> {

  @Override
  public Class<StorageStatusMask> type() {
    return StorageStatusMask.class;
  }

  @Override
  public StorageStatusMask createDefault() {
    return StorageStatusMask
        .builder()
        .value(1)
        .build();
  }

  @Override
  public StorageStatusMask createVariant() {
    return StorageStatusMask
        .builder()
        .value(3)
        .build();
  }
}