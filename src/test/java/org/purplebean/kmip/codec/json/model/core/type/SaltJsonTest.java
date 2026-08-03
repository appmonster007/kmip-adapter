package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Salt;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Salt JSON Serialization Tests")
class SaltJsonTest extends AbstractJsonSerializationTestSuite<Salt> {

  @Override
  public Class<Salt> type() {
    return Salt.class;
  }

  @Override
  public Salt createDefault() {
    return Salt.of(new byte[] {0x01, 0x02, 0x03});
  }

  @Override
  public Salt createVariant() {
    return Salt.of(new byte[] {0x04, 0x05, 0x06});
  }
}