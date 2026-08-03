package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyValueLocationType TTLV Serialization")
class KeyValueLocationTypeTtlvTest
    extends AbstractTtlvSerializationTestSuite<KeyValueLocationType> {
  @Override
  public Class<KeyValueLocationType> type() {
    return KeyValueLocationType.class;
  }

  @Override
  public KeyValueLocationType createDefault() {
    return KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst();
  }

  @Override
  public KeyValueLocationType createVariant() {
    return KeyValueLocationType.Standard.URI.inst();
  }
}
