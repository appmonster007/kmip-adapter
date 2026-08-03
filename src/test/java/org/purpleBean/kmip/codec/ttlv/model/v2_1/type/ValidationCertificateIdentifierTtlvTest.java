package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ValidationCertificateIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationCertificateIdentifier Ttlv Serialization Tests")
class ValidationCertificateIdentifierTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationCertificateIdentifier> {

  @Override
  public Class<ValidationCertificateIdentifier> type() {
    return ValidationCertificateIdentifier.class;
  }

  @Override
  public ValidationCertificateIdentifier createDefault() {
    return ValidationCertificateIdentifier.of("default-string");
  }

  @Override
  public ValidationCertificateIdentifier createVariant() {
    return ValidationCertificateIdentifier.of("variant-string");
  }
}