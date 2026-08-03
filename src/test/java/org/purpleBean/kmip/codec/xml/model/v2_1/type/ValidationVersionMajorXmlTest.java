package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationVersionMajor Xml Serialization Tests")
class ValidationVersionMajorXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationVersionMajor> {

  @Override
  public Class<ValidationVersionMajor> type() {
    return ValidationVersionMajor.class;
  }

  @Override
  public ValidationVersionMajor createDefault() {
    return ValidationVersionMajor.of(123);
  }

  @Override
  public ValidationVersionMajor createVariant() {
    return ValidationVersionMajor.of(456);
  }
}