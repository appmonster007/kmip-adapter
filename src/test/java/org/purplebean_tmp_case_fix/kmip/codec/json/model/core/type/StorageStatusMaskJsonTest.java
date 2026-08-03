package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.StorageStatusMask;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("StorageStatusMask JSON Serialization Tests")
class StorageStatusMaskJsonTest extends AbstractJsonSerializationTestSuite<StorageStatusMask> {

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
        .value(2)
        .build();
  }
}