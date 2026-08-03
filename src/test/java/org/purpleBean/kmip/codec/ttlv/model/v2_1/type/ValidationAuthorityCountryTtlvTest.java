package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationAuthorityCountry;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationAuthorityCountry Ttlv Serialization Tests")
class ValidationAuthorityCountryTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationAuthorityCountry> {

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