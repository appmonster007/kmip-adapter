package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeIndex XML Serialization Tests")
class AttributeIndexXmlTest extends AbstractXmlSerializationTestSuite<AttributeIndex> {

  @Override
  public Class<AttributeIndex> type() {
    return AttributeIndex.class;
  }

  @Override
  public AttributeIndex createDefault() {
    return AttributeIndex
        .builder()
        .value(10)
        .build();
  }

  @Override
  public AttributeIndex createVariant() {
    return AttributeIndex
        .builder()
        .value(50)
        .build();
  }
}
