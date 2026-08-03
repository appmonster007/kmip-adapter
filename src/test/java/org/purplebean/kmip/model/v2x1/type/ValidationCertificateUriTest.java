package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ValidationCertificateUri Domain Tests")
class ValidationCertificateUriTest extends AbstractKmipDataTypeTestSuite<ValidationCertificateUri> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationCertificateUri> type() {
    return ValidationCertificateUri.class;
  }

  @Override
  protected ValidationCertificateUri createDefault() {
    return ValidationCertificateUri.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}