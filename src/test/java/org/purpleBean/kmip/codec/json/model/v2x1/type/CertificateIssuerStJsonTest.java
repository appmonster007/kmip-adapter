package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerSt;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerSt Json Serialization Tests")
class CertificateIssuerStJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerSt> {

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