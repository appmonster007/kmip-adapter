package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MaximumItems;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("MaximumItems XML Serialization Tests")
class MaximumItemsXmlTest extends AbstractXmlSerializationTestSuite<MaximumItems> {

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