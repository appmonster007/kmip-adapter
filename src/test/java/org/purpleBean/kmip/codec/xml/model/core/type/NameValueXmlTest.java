package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.NameValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NameValue XML Serialization Tests")
class NameValueXmlTest extends AbstractXmlSerializationTestSuite<NameValue> {

  @Override
  public Class<NameValue> type() {
    return NameValue.class;
  }

  @Override
  public NameValue createDefault() {
    return NameValue.of("some-name");
  }

  @Override
  public NameValue createVariant() {
    return NameValue.of("some-variant-name");
  }
}
