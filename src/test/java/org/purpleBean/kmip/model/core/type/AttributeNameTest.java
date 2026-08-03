package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AttributeName Domain Tests")
class AttributeNameTest extends AbstractKmipDataTypeTestSuite<AttributeName> {

  @Override
  protected Class<AttributeName> type() {
    return AttributeName.class;
  }

  @Override
  protected AttributeName createDefault() {
    return AttributeName
        .builder()
        .value("attribute name")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}
