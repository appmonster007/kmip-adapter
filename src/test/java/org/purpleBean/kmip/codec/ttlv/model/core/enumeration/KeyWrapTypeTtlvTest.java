package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyWrapType TTLV Serialization")
class KeyWrapTypeTtlvTest extends AbstractTtlvSerializationTestSuite<KeyWrapType> {
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
