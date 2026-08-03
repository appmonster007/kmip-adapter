package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateIssuerDistinguishedName;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerDistinguishedName JSON Serialization Tests")
class CertificateIssuerDistinguishedNameJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateIssuerDistinguishedName> {

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