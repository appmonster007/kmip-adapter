package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.KeyValuePresent;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyValuePresent Ttlv Serialization Tests")
class KeyValuePresentTtlvTest extends AbstractTtlvSerializationTestSuite<KeyValuePresent> {

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