package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttributeName XML Serialization Tests")
class AttributeNameXmlTest extends AbstractXmlSerializationTestSuite<AttributeName> {

  @Override
  public Class<AttributeName> type() {
    return AttributeName.class;
  }

  @Override
  public AttributeName createDefault() {
    return AttributeName
        .builder()
        .value("attribute name")
        .build();
  }

  @Override
  public AttributeName createVariant() {
    return AttributeName
        .builder()
        .value("attribute name variant")
        .build();
  }
}
