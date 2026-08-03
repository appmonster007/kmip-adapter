package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ServerUri;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ServerUri Json Serialization Tests")
class ServerUriJsonTest extends AbstractJsonSerializationTestSuite<ServerUri> {

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