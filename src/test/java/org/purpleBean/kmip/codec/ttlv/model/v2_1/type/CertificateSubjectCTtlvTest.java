package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectC;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectC Ttlv Serialization Tests")
class CertificateSubjectCTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectC> {

  @Override
  public Class<CertificateSubjectC> type() {
    return CertificateSubjectC.class;
  }

  @Override
  public CertificateSubjectC createDefault() {
    return CertificateSubjectC.of("default-string");
  }

  @Override
  public CertificateSubjectC createVariant() {
    return CertificateSubjectC.of("variant-string");
  }
}