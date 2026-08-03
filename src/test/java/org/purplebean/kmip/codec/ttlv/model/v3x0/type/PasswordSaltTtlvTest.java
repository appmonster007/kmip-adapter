package org.purplebean.kmip.codec.ttlv.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.PasswordSalt;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PasswordSalt Ttlv Serialization Tests")
class PasswordSaltTtlvTest extends AbstractTtlvSerializationTestSuite<PasswordSalt> {

  @Override
  public Class<PasswordSalt> type() {
    return PasswordSalt.class;
  }

  @Override
  public PasswordSalt createDefault() {
    return PasswordSalt.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public PasswordSalt createVariant() {
    return PasswordSalt.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}