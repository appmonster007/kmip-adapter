package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("Key Domain Tests")
class KeyTest extends AbstractKmipDataTypeTestSuite<Key> {

  @Override
  protected Class<Key> type() {
    return Key.class;
  }

  @Override
  protected Key createDefault() {
    return Key.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}