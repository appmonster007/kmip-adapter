package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectDistinguishedName TTLV Serialization Tests")
class CertificateSubjectDistinguishedNameTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateSubjectDistinguishedName> {

  @Override
  public Class<CertificateSubjectDistinguishedName> type() {
    return CertificateSubjectDistinguishedName.class;
  }

  @Override
  public CertificateSubjectDistinguishedName createDefault() {
    return CertificateSubjectDistinguishedName
        .builder()
        .value("test-subject-dn")
        .build();
  }

  @Override
  public CertificateSubjectDistinguishedName createVariant() {
    return CertificateSubjectDistinguishedName
        .builder()
        .value("another-subject-dn")
        .build();
  }
}