package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerC;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerC Json Serialization Tests")
class CertificateIssuerCJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerC> {

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