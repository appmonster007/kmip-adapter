package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateLength;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateLength XML Serialization Tests")
class CertificateLengthXmlTest extends AbstractXmlSerializationTestSuite<CertificateLength> {

  @Override
  public Class<CertificateLength> type() {
    return CertificateLength.class;
  }

  @Override
  public CertificateLength createDefault() {
    return CertificateLength
        .builder()
        .value(10)
        .build();
  }

  @Override
  public CertificateLength createVariant() {
    return CertificateLength
        .builder()
        .value(15)
        .build();
  }
}
