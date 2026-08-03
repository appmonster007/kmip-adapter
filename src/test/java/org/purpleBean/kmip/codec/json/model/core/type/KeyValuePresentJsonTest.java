package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.KeyValuePresent;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValuePresent Json Serialization Tests")
class KeyValuePresentJsonTest extends AbstractJsonSerializationTestSuite<KeyValuePresent> {

  @Override
  public Class<KeyValuePresent> type() {
    return KeyValuePresent.class;
  }

  @Override
  public KeyValuePresent createDefault() {
    return KeyValuePresent.of(Boolean.FALSE);
  }

  @Override
  public KeyValuePresent createVariant() {
    return KeyValuePresent.of(Boolean.TRUE);
  }
}