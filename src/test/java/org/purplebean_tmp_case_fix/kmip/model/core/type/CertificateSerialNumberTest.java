package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CertificateSerialNumber Domain Tests")
class CertificateSerialNumberTest extends AbstractKmipDataTypeTestSuite<CertificateSerialNumber> {

  @Override
  protected Class<CertificateSerialNumber> type() {
    return CertificateSerialNumber.class;
  }

  @Override
  protected CertificateSerialNumber createDefault() {
    return CertificateSerialNumber.of("12345".getBytes());
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}
