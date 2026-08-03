package org.purpleBean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.ClientCorrelationValue;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ClientCorrelationValue Xml Serialization Tests")
class ClientCorrelationValueXmlTest
    extends AbstractXmlSerializationTestSuite<ClientCorrelationValue> {

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