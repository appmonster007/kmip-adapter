package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.PSource;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PSource XML Serialization")
class PSourceXmlTest extends AbstractXmlSerializationTestSuite<PSource> {
  @Override
  public Class<PSource> type() {
    return PSource.class;
  }

  @Override
  public PSource createDefault() {
    return PSource.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public PSource createVariant() {
    return PSource.of(new byte[] {0x04, 0x05, 0x06});
  }
}
