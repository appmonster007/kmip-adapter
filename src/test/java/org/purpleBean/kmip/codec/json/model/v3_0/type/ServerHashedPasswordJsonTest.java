package org.purpleBean.kmip.codec.json.model.v3_0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.type.ServerHashedPassword;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ServerHashedPassword Json Serialization Tests")
class ServerHashedPasswordJsonTest
    extends AbstractJsonSerializationTestSuite<ServerHashedPassword> {

  @Override
  public Class<ServerHashedPassword> type() {
    return ServerHashedPassword.class;
  }

  @Override
  public ServerHashedPassword createDefault() {
    return ServerHashedPassword.of(ByteBuffer.wrap(new byte[] {0x01, 0x02, 0x03}));
  }

  @Override
  public ServerHashedPassword createVariant() {
    return ServerHashedPassword.of(ByteBuffer.wrap(new byte[] {0x04, 0x05, 0x06}));
  }
}