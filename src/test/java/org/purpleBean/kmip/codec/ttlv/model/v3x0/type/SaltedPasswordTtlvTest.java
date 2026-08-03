package org.purplebean.kmip.codec.ttlv.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.SaltedPassword;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SaltedPassword Ttlv Serialization Tests")
class SaltedPasswordTtlvTest extends AbstractTtlvSerializationTestSuite<SaltedPassword> {

  @Override
  public Class<SaltedPassword> type() {
    return SaltedPassword.class;
  }

  @Override
  public SaltedPassword createDefault() {
    return SaltedPassword.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public SaltedPassword createVariant() {
    return SaltedPassword.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}