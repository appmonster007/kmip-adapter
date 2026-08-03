package org.purplebean.kmip.codec.json.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.HashedPasswordUsername;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("HashedPasswordUsername Json Serialization Tests")
class HashedPasswordUsernameJsonTest
    extends AbstractJsonSerializationTestSuite<HashedPasswordUsername> {

  @Override
  public Class<HashedPasswordUsername> type() {
    return HashedPasswordUsername.class;
  }

  @Override
  public HashedPasswordUsername createDefault() {
    return HashedPasswordUsername.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public HashedPasswordUsername createVariant() {
    return HashedPasswordUsername.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}