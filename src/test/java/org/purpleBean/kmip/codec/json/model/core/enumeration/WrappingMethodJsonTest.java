package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("WrappingMethod JSON Serialization")
class WrappingMethodJsonTest extends AbstractJsonSerializationTestSuite<WrappingMethod> {
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
