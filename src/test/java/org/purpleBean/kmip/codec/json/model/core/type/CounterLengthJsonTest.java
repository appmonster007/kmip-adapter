package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CounterLength;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CounterLength JSON Serialization Tests")
class CounterLengthJsonTest extends AbstractJsonSerializationTestSuite<CounterLength> {

  @Override
  public Class<CounterLength> type() {
    return CounterLength.class;
  }

  @Override
  public CounterLength createDefault() {
    return CounterLength.of(128);
  }

  @Override
  public CounterLength createVariant() {
    return CounterLength.of(256);
  }
}