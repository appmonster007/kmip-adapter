package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationCertificateUri Xml Serialization Tests")
class ValidationCertificateUriXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationCertificateUri> {

  @Override
  public Class<ValidationCertificateUri> type() {
    return ValidationCertificateUri.class;
  }

  @Override
  public ValidationCertificateUri createDefault() {
    return ValidationCertificateUri.of("default-string");
  }

  @Override
  public ValidationCertificateUri createVariant() {
    return ValidationCertificateUri.of("variant-string");
  }
}