package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateSubjectL;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectL Ttlv Serialization Tests")
class CertificateSubjectLTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectL> {

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