package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CryptographicLength;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;


@DisplayName("CryptographicLength XML Serialization Tests")
class CryptographicLengthXmlTest extends AbstractXmlSerializationTestSuite<CryptographicLength> {

  @Override
  public Class<CryptographicLength> type() {
    return CryptographicLength.class;
  }

  @Override
  public CryptographicLength createDefault() {
    return CryptographicLength.of(256);
  }

  @Override
  public CryptographicLength createVariant() {
    return CryptographicLength.of(512);
  }
}
