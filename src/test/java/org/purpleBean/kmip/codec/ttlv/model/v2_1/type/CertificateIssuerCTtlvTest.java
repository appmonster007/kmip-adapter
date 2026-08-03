package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.CertificateIssuerC;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuerC Ttlv Serialization Tests")
class CertificateIssuerCTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateIssuerC> {

  @Override
  public Class<CertificateIssuerC> type() {
    return CertificateIssuerC.class;
  }

  @Override
  public CertificateIssuerC createDefault() {
    return CertificateIssuerC.of("default-string");
  }

  @Override
  public CertificateIssuerC createVariant() {
    return CertificateIssuerC.of("variant-string");
  }
}