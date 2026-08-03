package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerEmail;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuerEmail Ttlv Serialization Tests")
class CertificateIssuerEmailTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateIssuerEmail> {

  @Override
  public Class<CertificateIssuerEmail> type() {
    return CertificateIssuerEmail.class;
  }

  @Override
  public CertificateIssuerEmail createDefault() {
    return CertificateIssuerEmail.of("default-string");
  }

  @Override
  public CertificateIssuerEmail createVariant() {
    return CertificateIssuerEmail.of("variant-string");
  }
}