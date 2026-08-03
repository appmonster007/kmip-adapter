package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.CertificateIssuerCn;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIssuerCn Json Serialization Tests")
class CertificateIssuerCnJsonTest extends AbstractJsonSerializationTestSuite<CertificateIssuerCn> {

  @Override
  public Class<CertificateIssuerCn> type() {
    return CertificateIssuerCn.class;
  }

  @Override
  public CertificateIssuerCn createDefault() {
    return CertificateIssuerCn.of("default-string");
  }

  @Override
  public CertificateIssuerCn createVariant() {
    return CertificateIssuerCn.of("variant-string");
  }
}