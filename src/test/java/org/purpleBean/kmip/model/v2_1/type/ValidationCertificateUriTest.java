package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


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