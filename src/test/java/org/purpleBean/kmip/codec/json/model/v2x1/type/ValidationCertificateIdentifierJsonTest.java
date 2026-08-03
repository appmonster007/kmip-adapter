package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ValidationCertificateIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ValidationCertificateIdentifier Json Serialization Tests")
class ValidationCertificateIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<ValidationCertificateIdentifier> {

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