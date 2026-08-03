package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CounterLength;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CounterLength TTLV Serialization Tests")
class CounterLengthTtlvTest extends AbstractTtlvSerializationTestSuite<CounterLength> {

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