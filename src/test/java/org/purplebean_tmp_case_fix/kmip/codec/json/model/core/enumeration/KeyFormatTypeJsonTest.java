package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyFormatType JSON Serialization")
class KeyFormatTypeJsonTest extends AbstractJsonSerializationTestSuite<KeyFormatType> {
  @Override
  public Class<KeyFormatType> type() {
    return KeyFormatType.class;
  }

  @Override
  public KeyFormatType createDefault() {
    return KeyFormatType.Standard.RAW.inst();
  }

  @Override
  public KeyFormatType createVariant() {
    return KeyFormatType.Standard.OPAQUE.inst();
  }
}
