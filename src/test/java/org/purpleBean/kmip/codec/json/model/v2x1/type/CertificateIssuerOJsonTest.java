package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.CertificateIssuerO;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerO Json Serialization Tests")
class CertificateIssuerOJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerO> {

  @Override
  public Class<CertificateIssuerO> type() {
    return CertificateIssuerO.class;
  }

  @Override
  public CertificateIssuerO createDefault() {
    return CertificateIssuerO.of("default-string");
  }

  @Override
  public CertificateIssuerO createVariant() {
    return CertificateIssuerO.of("variant-string");
  }
}