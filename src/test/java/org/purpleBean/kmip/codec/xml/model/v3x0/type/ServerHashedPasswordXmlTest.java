package org.purplebean.kmip.codec.xml.model.v3x0.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.ServerHashedPassword;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ServerHashedPassword Xml Serialization Tests")
class ServerHashedPasswordXmlTest extends AbstractXmlSerializationTestSuite<ServerHashedPassword> {

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