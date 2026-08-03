package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.structure.CertificateIdentifier;
import org.purplebean.kmip.model.core.type.Issuer;
import org.purplebean.kmip.model.core.type.SerialNumber;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateIdentifier Json Serialization Tests")
class CertificateIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateIdentifier> {

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