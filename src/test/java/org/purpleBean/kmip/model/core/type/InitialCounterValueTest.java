package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("InitialCounterValue Domain Tests")
class InitialCounterValueTest extends AbstractKmipDataTypeTestSuite<InitialCounterValue> {

  @Override
  protected Class<InitialCounterValue> type() {
    return InitialCounterValue.class;
  }

  @Override
  protected InitialCounterValue createDefault() {
    return InitialCounterValue.of(1);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}