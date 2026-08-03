package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectEmail;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectEmail Ttlv Serialization Tests")
class CertificateSubjectEmailTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateSubjectEmail> {

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