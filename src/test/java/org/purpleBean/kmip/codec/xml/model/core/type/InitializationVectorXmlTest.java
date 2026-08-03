package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("InitializationVector XML Serialization Tests")
class InitializationVectorXmlTest extends AbstractXmlSerializationTestSuite<InitializationVector> {

  @Override
  public Class<InitializationVector> type() {
    return InitializationVector.class;
  }

  @Override
  public InitializationVector createDefault() {
    return InitializationVector.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public InitializationVector createVariant() {
    return InitializationVector.of(new byte[] {0x04, 0x05, 0x06});
  }
}