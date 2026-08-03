package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.WrappingMethod;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("WrappingMethod TTLV Serialization")
class WrappingMethodTtlvTest extends AbstractTtlvSerializationTestSuite<WrappingMethod> {
  @Override
  public Class<WrappingMethod> type() {
    return WrappingMethod.class;
  }

  @Override
  public WrappingMethod createDefault() {
    return WrappingMethod.Standard.ENCRYPT.inst();
  }

  @Override
  public WrappingMethod createVariant() {
    return WrappingMethod.Standard.MAC_SIGN.inst();
  }
}
