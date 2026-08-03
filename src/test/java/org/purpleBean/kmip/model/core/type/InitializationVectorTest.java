package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("InitializationVector Domain Tests")
class InitializationVectorTest extends AbstractKmipDataTypeTestSuite<InitializationVector> {

  @Override
  protected Class<InitializationVector> type() {
    return InitializationVector.class;
  }

  @Override
  protected InitializationVector createDefault() {
    return InitializationVector.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}