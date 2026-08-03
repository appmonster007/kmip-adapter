package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ValidationAuthorityCountry Domain Tests")
class ValidationAuthorityCountryTest
    extends AbstractKmipDataTypeTestSuite<ValidationAuthorityCountry> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationAuthorityCountry> type() {
    return ValidationAuthorityCountry.class;
  }

  @Override
  protected ValidationAuthorityCountry createDefault() {
    return ValidationAuthorityCountry.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}