package org.purpleBean.kmip.codec.ttlv.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ServerPort;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ServerPort Ttlv Serialization Tests")
class ServerPortTtlvTest extends AbstractTtlvSerializationTestSuite<ServerPort> {

  @Override
  public Class<ServerPort> type() {
    return ServerPort.class;
  }

  @Override
  public ServerPort createDefault() {
    return ServerPort.of(123);
  }

  @Override
  public ServerPort createVariant() {
    return ServerPort.of(456);
  }
}