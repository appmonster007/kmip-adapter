package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectSt;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectSt Ttlv Serialization Tests")
class CertificateSubjectStTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateSubjectSt> {

  @Override
  public Class<CertificateSubjectSt> type() {
    return CertificateSubjectSt.class;
  }

  @Override
  public CertificateSubjectSt createDefault() {
    return CertificateSubjectSt.of("default-string");
  }

  @Override
  public CertificateSubjectSt createVariant() {
    return CertificateSubjectSt.of("variant-string");
  }
}