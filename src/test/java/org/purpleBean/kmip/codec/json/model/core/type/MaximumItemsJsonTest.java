package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("MaximumItems JSON Serialization Tests")
class MaximumItemsJsonTest extends AbstractJsonSerializationTestSuite<MaximumItems> {

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