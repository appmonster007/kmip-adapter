package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


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