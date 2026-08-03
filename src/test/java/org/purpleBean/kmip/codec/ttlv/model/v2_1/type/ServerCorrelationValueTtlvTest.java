package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ServerCorrelationValue Ttlv Serialization Tests")
class ServerCorrelationValueTtlvTest
    extends AbstractTtlvSerializationTestSuite<ServerCorrelationValue> {

  @Override
  public Class<ServerCorrelationValue> type() {
    return ServerCorrelationValue.class;
  }

  @Override
  public ServerCorrelationValue createDefault() {
    return ServerCorrelationValue.of("default-string");
  }

  @Override
  public ServerCorrelationValue createVariant() {
    return ServerCorrelationValue.of("variant-string");
  }
}