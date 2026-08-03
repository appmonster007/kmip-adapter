package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.StorageStatusMask;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("StorageStatusMask TTLV Serialization Tests")
class StorageStatusMaskTtlvTest extends AbstractTtlvSerializationTestSuite<StorageStatusMask> {

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