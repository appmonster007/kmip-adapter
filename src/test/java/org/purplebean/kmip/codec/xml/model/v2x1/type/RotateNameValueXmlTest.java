package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.RotateNameValue;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("RotateNameValue Xml Serialization Tests")
class RotateNameValueXmlTest extends AbstractXmlSerializationTestSuite<RotateNameValue> {

  @Override
  public Class<RotateNameValue> type() {
    return RotateNameValue.class;
  }

  @Override
  public RotateNameValue createDefault() {
    return RotateNameValue.of("default-string");
  }

  @Override
  public RotateNameValue createVariant() {
    return RotateNameValue.of("variant-string");
  }
}