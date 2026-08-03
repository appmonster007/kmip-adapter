package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ValidationAuthorityUri Domain Tests")
class ValidationAuthorityUriTest extends AbstractKmipDataTypeTestSuite<ValidationAuthorityUri> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationAuthorityUri> type() {
    return ValidationAuthorityUri.class;
  }

  @Override
  protected ValidationAuthorityUri createDefault() {
    return ValidationAuthorityUri.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}