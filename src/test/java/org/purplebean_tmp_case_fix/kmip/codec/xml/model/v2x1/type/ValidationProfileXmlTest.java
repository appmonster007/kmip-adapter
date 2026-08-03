package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationProfile;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationProfile Xml Serialization Tests")
class ValidationProfileXmlTest extends AbstractXmlSerializationTestSuite<ValidationProfile> {

  @Override
  public Class<ValidationProfile> type() {
    return ValidationProfile.class;
  }

  @Override
  public ValidationProfile createDefault() {
    return ValidationProfile.of("default-string");
  }

  @Override
  public ValidationProfile createVariant() {
    return ValidationProfile.of("variant-string");
  }
}