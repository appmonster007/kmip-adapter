package org.purpleBean.kmip.codec.json.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ClientCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ClientCorrelationValue Json Serialization Tests")
class ClientCorrelationValueJsonTest
    extends AbstractJsonSerializationTestSuite<ClientCorrelationValue> {

  @Override
  public Class<ClientCorrelationValue> type() {
    return ClientCorrelationValue.class;
  }

  @Override
  public ClientCorrelationValue createDefault() {
    return ClientCorrelationValue.of("default-string");
  }

  @Override
  public ClientCorrelationValue createVariant() {
    return ClientCorrelationValue.of("variant-string");
  }
}