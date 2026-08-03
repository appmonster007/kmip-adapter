package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerOu;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerOu Json Serialization Tests")
class CertificateIssuerOuJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerOu> {

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