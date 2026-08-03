package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectSt;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectSt Json Serialization Tests")
class CertificateSubjectStJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateSubjectSt> {

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