package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ValidationAuthorityCountry;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationAuthorityCountry Json Serialization Tests")
class ValidationAuthorityCountryJsonTest
    extends AbstractJsonSerializationTestSuite<ValidationAuthorityCountry> {

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