package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateValue JSON Serialization Tests")
class CertificateValueJsonTest extends AbstractJsonSerializationTestSuite<CertificateValue> {

  @Override
  public Class<CertificateValue> type() {
    return CertificateValue.class;
  }

  @Override
  public CertificateValue createDefault() {
    return CertificateValue.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public CertificateValue createVariant() {
    return CertificateValue.of(new byte[] {0x04, 0x05, 0x06});
  }
}