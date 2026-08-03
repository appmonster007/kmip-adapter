package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ServerUri;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ServerUri Xml Serialization Tests")
class ServerUriXmlTest extends AbstractXmlSerializationTestSuite<ServerUri> {

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