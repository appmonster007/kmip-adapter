package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectO;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectO Ttlv Serialization Tests")
class CertificateSubjectOTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectO> {

  @Override
  public Class<CertificateSubjectO> type() {
    return CertificateSubjectO.class;
  }

  @Override
  public CertificateSubjectO createDefault() {
    return CertificateSubjectO.of("default-string");
  }

  @Override
  public CertificateSubjectO createVariant() {
    return CertificateSubjectO.of("variant-string");
  }
}