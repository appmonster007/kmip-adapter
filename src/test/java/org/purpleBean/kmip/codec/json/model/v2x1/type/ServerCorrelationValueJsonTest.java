package org.purplebean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.ServerCorrelationValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ServerCorrelationValue Json Serialization Tests")
class ServerCorrelationValueJsonTest
    extends AbstractJsonSerializationTestSuite<ServerCorrelationValue> {

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