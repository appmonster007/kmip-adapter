package org.purplebean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerOu;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuerOu Ttlv Serialization Tests")
class CertificateIssuerOuTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateIssuerOu> {

  @Override
  public Class<CertificateIssuerOu> type() {
    return CertificateIssuerOu.class;
  }

  @Override
  public CertificateIssuerOu createDefault() {
    return CertificateIssuerOu.of("default-string");
  }

  @Override
  public CertificateIssuerOu createVariant() {
    return CertificateIssuerOu.of("variant-string");
  }
}