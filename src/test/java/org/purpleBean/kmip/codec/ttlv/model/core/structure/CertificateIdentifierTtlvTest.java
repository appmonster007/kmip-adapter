package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.CertificateIdentifier;
import org.purpleBean.kmip.model.core.type.Issuer;
import org.purpleBean.kmip.model.core.type.SerialNumber;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateIdentifier Ttlv Serialization Tests")
class CertificateIdentifierTtlvTest
    extends AbstractTtlvSerializationTestSuite<CertificateIdentifier> {

  @Override
  public Class<CertificateIdentifier> type() {
    return CertificateIdentifier.class;
  }

  @Override
  public CertificateIdentifier createDefault() {
    return CertificateIdentifier
        .builder()
        .issuer(Issuer.of("CN=Test Issuer"))
        .serialNumber(SerialNumber.of("12345"))
        .build();
  }
}