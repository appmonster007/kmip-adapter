package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyFormatType TTLV Serialization")
class KeyFormatTypeTtlvTest extends AbstractTtlvSerializationTestSuite<KeyFormatType> {
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
