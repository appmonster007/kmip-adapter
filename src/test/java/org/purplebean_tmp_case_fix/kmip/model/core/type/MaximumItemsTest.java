package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("MaximumItems Domain Tests")
class MaximumItemsTest extends AbstractKmipDataTypeTestSuite<MaximumItems> {

  @Override
  protected Class<MaximumItems> type() {
    return MaximumItems.class;
  }

  @Override
  protected MaximumItems createDefault() {
    return MaximumItems
        .builder()
        .value(100)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}