package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMinor;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationVersionMinor Xml Serialization Tests")
class ValidationVersionMinorXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationVersionMinor> {

  @Override
  public Class<ValidationVersionMinor> type() {
    return ValidationVersionMinor.class;
  }

  @Override
  public ValidationVersionMinor createDefault() {
    return ValidationVersionMinor.of(123);
  }

  @Override
  public ValidationVersionMinor createVariant() {
    return ValidationVersionMinor.of(456);
  }
}