package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Key;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Key JSON Serialization Tests")
class KeyJsonTest extends AbstractJsonSerializationTestSuite<Key> {

  @Override
  public Class<Key> type() {
    return Key.class;
  }

  @Override
  public Key createDefault() {
    return Key.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public Key createVariant() {
    return Key.of(new byte[] {0x04, 0x05, 0x06});
  }
}