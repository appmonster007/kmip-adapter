package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSerialNumber XML Serialization Tests")
class CertificateSerialNumberXmlTest
    extends AbstractXmlSerializationTestSuite<CertificateSerialNumber> {

  @Override
  public Class<CertificateSerialNumber> type() {
    return CertificateSerialNumber.class;
  }

  @Override
  public CertificateSerialNumber createDefault() {
    return CertificateSerialNumber.of("12345".getBytes());
  }

  @Override
  public CertificateSerialNumber createVariant() {
    return CertificateSerialNumber.of("67890".getBytes());
  }
}
