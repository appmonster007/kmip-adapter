package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.KeyValueLocationValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValueLocationValue JSON Serialization Tests")
class KeyValueLocationValueJsonTest
    extends AbstractJsonSerializationTestSuite<KeyValueLocationValue> {

  @Override
  public Class<KeyValueLocationValue> type() {
    return KeyValueLocationValue.class;
  }

  @Override
  public KeyValueLocationValue createDefault() {
    return KeyValueLocationValue
        .builder()
        .value("test")
        .build();
  }

  @Override
  public KeyValueLocationValue createVariant() {
    return KeyValueLocationValue
        .builder()
        .value("test-2")
        .build();
  }
}