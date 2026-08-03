package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.InitialCounterValue;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InitialCounterValue TTLV Serialization Tests")
class InitialCounterValueTtlvTest extends AbstractTtlvSerializationTestSuite<InitialCounterValue> {

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