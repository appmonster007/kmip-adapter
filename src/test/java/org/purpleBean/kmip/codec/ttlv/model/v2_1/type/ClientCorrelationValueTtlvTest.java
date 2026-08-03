package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.ClientCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ClientCorrelationValue Ttlv Serialization Tests")
class ClientCorrelationValueTtlvTest
    extends AbstractTtlvSerializationTestSuite<ClientCorrelationValue> {

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