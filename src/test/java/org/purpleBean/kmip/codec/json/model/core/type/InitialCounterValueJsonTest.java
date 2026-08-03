package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InitialCounterValue JSON Serialization Tests")
class InitialCounterValueJsonTest extends AbstractJsonSerializationTestSuite<InitialCounterValue> {

  @Override
  public Class<InitialCounterValue> type() {
    return InitialCounterValue.class;
  }

  @Override
  public InitialCounterValue createDefault() {
    return InitialCounterValue.of(1);
  }

  @Override
  public InitialCounterValue createVariant() {
    return InitialCounterValue.of(2);
  }
}