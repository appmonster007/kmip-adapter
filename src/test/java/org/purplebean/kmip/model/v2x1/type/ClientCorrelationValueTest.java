package org.purplebean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ClientCorrelationValue Domain Tests")
class ClientCorrelationValueTest extends AbstractKmipDataTypeTestSuite<ClientCorrelationValue> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ClientCorrelationValue> type() {
    return ClientCorrelationValue.class;
  }

  @Override
  protected ClientCorrelationValue createDefault() {
    return ClientCorrelationValue.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}