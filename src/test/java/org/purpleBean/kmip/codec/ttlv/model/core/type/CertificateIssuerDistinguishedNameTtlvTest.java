package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateIssuerDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIssuerDistinguishedName TTLV Serialization Tests")
class CertificateIssuerDistinguishedNameTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateIssuerDistinguishedName> {

  @Override
  public Class<CertificateIssuerDistinguishedName> type() {
    return CertificateIssuerDistinguishedName.class;
  }

  @Override
  public CertificateIssuerDistinguishedName createDefault() {
    return CertificateIssuerDistinguishedName.of("CN=Test Issuer");
  }

  @Override
  public CertificateIssuerDistinguishedName createVariant() {
    return CertificateIssuerDistinguishedName.of("CN=Another Issuer");
  }
}