package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectCn;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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