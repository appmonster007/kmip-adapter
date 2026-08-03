package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AttributeIndex Domain Tests")
class AttributeIndexTest extends AbstractKmipDataTypeTestSuite<AttributeIndex> {

  @Override
  protected Class<AttributeIndex> type() {
    return AttributeIndex.class;
  }

  @Override
  protected AttributeIndex createDefault() {
    return AttributeIndex
        .builder()
        .value(10)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}
