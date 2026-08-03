package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationLevel Xml Serialization Tests")
class ValidationLevelXmlTest extends AbstractXmlSerializationTestSuite<ValidationLevel> {

  @Override
  public Class<ValidationLevel> type() {
    return ValidationLevel.class;
  }

  @Override
  public ValidationLevel createDefault() {
    return ValidationLevel.of(123);
  }

  @Override
  public ValidationLevel createVariant() {
    return ValidationLevel.of(456);
  }
}