package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectC;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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