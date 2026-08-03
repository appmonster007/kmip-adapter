package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.MaximumItems;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MaximumItems TTLV Serialization Tests")
class MaximumItemsTtlvTest extends AbstractTtlvSerializationTestSuite<MaximumItems> {

  @Override
  public Class<MaximumItems> type() {
    return MaximumItems.class;
  }

  @Override
  public MaximumItems createDefault() {
    return MaximumItems
        .builder()
        .value(100)
        .build();
  }

  @Override
  public MaximumItems createVariant() {
    return MaximumItems
        .builder()
        .value(200)
        .build();
  }
}