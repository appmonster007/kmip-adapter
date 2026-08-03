package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("CertificateRequest Domain Tests")
class CertificateRequestTest extends AbstractKmipDataTypeTestSuite<CertificateRequest> {

  @Override
  protected Class<CertificateRequest> type() {
    return CertificateRequest.class;
  }

  @Override
  protected CertificateRequest createDefault() {
    return CertificateRequest.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}