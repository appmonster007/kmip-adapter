package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationAuthorityCountry;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationAuthorityCountry Xml Serialization Tests")
class ValidationAuthorityCountryXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationAuthorityCountry> {

  @Override
  public Class<ValidationAuthorityCountry> type() {
    return ValidationAuthorityCountry.class;
  }

  @Override
  public ValidationAuthorityCountry createDefault() {
    return ValidationAuthorityCountry.of("default-string");
  }

  @Override
  public ValidationAuthorityCountry createVariant() {
    return ValidationAuthorityCountry.of("variant-string");
  }
}