package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerSt;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuerSt Ttlv Serialization Tests")
class CertificateIssuerStTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateIssuerSt> {

  @Override
  public Class<CertificateIssuerSt> type() {
    return CertificateIssuerSt.class;
  }

  @Override
  public CertificateIssuerSt createDefault() {
    return CertificateIssuerSt.of("default-string");
  }

  @Override
  public CertificateIssuerSt createVariant() {
    return CertificateIssuerSt.of("variant-string");
  }
}