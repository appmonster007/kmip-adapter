package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectCn;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectCn Ttlv Serialization Tests")
class CertificateSubjectCnTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateSubjectCn> {

  @Override
  public Class<CertificateSubjectCn> type() {
    return CertificateSubjectCn.class;
  }

  @Override
  public CertificateSubjectCn createDefault() {
    return CertificateSubjectCn.of("default-string");
  }

  @Override
  public CertificateSubjectCn createVariant() {
    return CertificateSubjectCn.of("variant-string");
  }
}