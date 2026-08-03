package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateRequest;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateRequest JSON Serialization Tests")
class CertificateRequestJsonTest extends AbstractJsonSerializationTestSuite<CertificateRequest> {

  @Override
  public Class<CertificateRequest> type() {
    return CertificateRequest.class;
  }

  @Override
  public CertificateRequest createDefault() {
    return CertificateRequest.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public CertificateRequest createVariant() {
    return CertificateRequest.of(new byte[] {0x04, 0x05, 0x06});
  }
}