package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateSubjectOu;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectOu Json Serialization Tests")
class CertificateSubjectOuJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateSubjectOu> {

  @Override
  public Class<CertificateSubjectOu> type() {
    return CertificateSubjectOu.class;
  }

  @Override
  public CertificateSubjectOu createDefault() {
    return CertificateSubjectOu.of("default-string");
  }

  @Override
  public CertificateSubjectOu createVariant() {
    return CertificateSubjectOu.of("variant-string");
  }
}