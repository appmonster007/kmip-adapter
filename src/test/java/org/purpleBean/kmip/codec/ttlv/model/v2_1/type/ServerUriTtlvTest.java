package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ServerUri;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ServerUri Ttlv Serialization Tests")
class ServerUriTtlvTest extends AbstractTtlvSerializationTestSuite<ServerUri> {

  @Override
  public Class<ServerUri> type() {
    return ServerUri.class;
  }

  @Override
  public ServerUri createDefault() {
    return ServerUri.of("default-string");
  }

  @Override
  public ServerUri createVariant() {
    return ServerUri.of("variant-string");
  }
}