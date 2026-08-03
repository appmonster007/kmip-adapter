package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.Description;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Description Xml Serialization Tests")
class DescriptionXmlTest extends AbstractXmlSerializationTestSuite<Description> {

  @Override
  public Class<Description> type() {
    return Description.class;
  }

  @Override
  public Description createDefault() {
    return Description.of("default-string");
  }

  @Override
  public Description createVariant() {
    return Description.of("variant-string");
  }
}