package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


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