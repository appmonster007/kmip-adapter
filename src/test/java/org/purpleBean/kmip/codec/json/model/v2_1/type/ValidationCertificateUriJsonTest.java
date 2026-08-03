package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateUri;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationCertificateUri Json Serialization Tests")
class ValidationCertificateUriJsonTest
    extends AbstractJsonSerializationTestSuite<ValidationCertificateUri> {

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