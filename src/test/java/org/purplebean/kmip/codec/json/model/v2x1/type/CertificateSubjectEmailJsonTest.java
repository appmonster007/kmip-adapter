package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectEmail;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectEmail Json Serialization Tests")
class CertificateSubjectEmailJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateSubjectEmail> {

  @Override
  public Class<CertificateSubjectEmail> type() {
    return CertificateSubjectEmail.class;
  }

  @Override
  public CertificateSubjectEmail createDefault() {
    return CertificateSubjectEmail.of("default-string");
  }

  @Override
  public CertificateSubjectEmail createVariant() {
    return CertificateSubjectEmail.of("variant-string");
  }
}