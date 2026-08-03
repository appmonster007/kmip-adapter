package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CertificateSerialNumber;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSerialNumber JSON Serialization Tests")
class CertificateSerialNumberJsonTest
    extends AbstractJsonSerializationTestSuite<CertificateSerialNumber> {

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
