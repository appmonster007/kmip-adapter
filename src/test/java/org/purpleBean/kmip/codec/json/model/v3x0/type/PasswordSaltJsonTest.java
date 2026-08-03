package org.purpleBean.kmip.codec.json.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3x0.type.PasswordSalt;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PasswordSalt Json Serialization Tests")
class PasswordSaltJsonTest extends AbstractJsonSerializationTestSuite<PasswordSalt> {

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