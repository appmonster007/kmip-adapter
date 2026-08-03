package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Sensitive;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Sensitive Xml Serialization Tests")
class SensitiveXmlTest extends AbstractXmlSerializationTestSuite<Sensitive> {

  @Override
  public Class<Sensitive> type() {
    return Sensitive.class;
  }

  @Override
  public Sensitive createDefault() {
    return Sensitive.of(true);
  }

  @Override
  public Sensitive createVariant() {
    return Sensitive.of(false);
  }
}