package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CryptographicUsageMask;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CryptographicUsageMask XML Serialization Tests")
class CryptographicUsageMaskXmlTest
    extends AbstractXmlSerializationTestSuite<CryptographicUsageMask> {

  @Override
  public Class<CryptographicUsageMask> type() {
    return CryptographicUsageMask.class;
  }

  @Override
  public CryptographicUsageMask createDefault() {
    return CryptographicUsageMask
        .builder()
        .value(10)
        .build();
  }

  @Override
  public CryptographicUsageMask createVariant() {
    return CryptographicUsageMask
        .builder()
        .value(100)
        .build();
  }
}
