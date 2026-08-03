package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectCn;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectCn Json Serialization Tests")
class CertificateSubjectCnJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateSubjectCn> {

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