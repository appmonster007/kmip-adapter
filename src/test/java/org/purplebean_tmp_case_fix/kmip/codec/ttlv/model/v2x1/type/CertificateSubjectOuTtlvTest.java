package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectOu;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectOu Ttlv Serialization Tests")
class CertificateSubjectOuTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateSubjectOu> {

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