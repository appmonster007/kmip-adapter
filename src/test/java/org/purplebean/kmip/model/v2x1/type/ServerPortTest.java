package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ServerPort Domain Tests")
class ServerPortTest extends AbstractKmipDataTypeTestSuite<ServerPort> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ServerPort> type() {
    return ServerPort.class;
  }

  @Override
  protected ServerPort createDefault() {
    return ServerPort.of(123);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}