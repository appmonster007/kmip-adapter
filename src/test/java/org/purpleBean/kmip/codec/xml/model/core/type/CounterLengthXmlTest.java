package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.CounterLength;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CounterLength XML Serialization Tests")
class CounterLengthXmlTest extends AbstractXmlSerializationTestSuite<CounterLength> {

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