package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.Salt;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Salt TTLV Serialization Tests")
class SaltTtlvTest extends AbstractTtlvSerializationTestSuite<Salt> {

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