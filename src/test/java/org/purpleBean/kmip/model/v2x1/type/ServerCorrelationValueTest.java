package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("ServerCorrelationValue Domain Tests")
class ServerCorrelationValueTest extends AbstractKmipDataTypeTestSuite<ServerCorrelationValue> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ServerCorrelationValue> type() {
    return ServerCorrelationValue.class;
  }

  @Override
  protected ServerCorrelationValue createDefault() {
    return ServerCorrelationValue.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}