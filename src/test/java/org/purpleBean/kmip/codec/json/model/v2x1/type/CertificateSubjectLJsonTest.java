package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateSubjectL;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectL Json Serialization Tests")
class CertificateSubjectLJsonTest extends AbstractJsonSerializationTestSuite<CertificateSubjectL> {

  @Override
  public Class<CertificateSubjectL> type() {
    return CertificateSubjectL.class;
  }

  @Override
  public CertificateSubjectL createDefault() {
    return CertificateSubjectL.of("default-string");
  }

  @Override
  public CertificateSubjectL createVariant() {
    return CertificateSubjectL.of("variant-string");
  }
}