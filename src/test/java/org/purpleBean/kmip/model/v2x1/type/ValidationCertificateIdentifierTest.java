package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ValidationCertificateIdentifier Domain Tests")
class ValidationCertificateIdentifierTest
    extends AbstractKmipDataTypeTestSuite<ValidationCertificateIdentifier> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationCertificateIdentifier> type() {
    return ValidationCertificateIdentifier.class;
  }

  @Override
  protected ValidationCertificateIdentifier createDefault() {
    return ValidationCertificateIdentifier.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}