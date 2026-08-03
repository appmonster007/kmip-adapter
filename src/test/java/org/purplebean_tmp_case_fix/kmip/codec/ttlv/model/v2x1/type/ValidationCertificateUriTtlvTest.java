package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateUri;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationCertificateUri Ttlv Serialization Tests")
class ValidationCertificateUriTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationCertificateUri> {

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