package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("KeyValueLocationValue Domain Tests")
class KeyValueLocationValueTest extends AbstractKmipDataTypeTestSuite<KeyValueLocationValue> {

  @Override
  protected Class<KeyValueLocationValue> type() {
    return KeyValueLocationValue.class;
  }

  @Override
  protected KeyValueLocationValue createDefault() {
    return KeyValueLocationValue
        .builder()
        .value("test")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}