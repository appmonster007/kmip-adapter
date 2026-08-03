package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.KeyWrapType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyWrapType JSON Serialization")
class KeyWrapTypeJsonTest extends AbstractJsonSerializationTestSuite<KeyWrapType> {
  @Override
  public Class<KeyWrapType> type() {
    return KeyWrapType.class;
  }

  @Override
  public KeyWrapType createDefault() {
    return KeyWrapType.Standard.NOT_WRAPPED.inst();
  }

  @Override
  public KeyWrapType createVariant() {
    return KeyWrapType.Standard.AS_REGISTERED.inst();
  }
}
