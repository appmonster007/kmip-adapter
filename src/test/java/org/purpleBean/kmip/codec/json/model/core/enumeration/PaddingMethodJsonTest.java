package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PaddingMethod JSON Serialization")
class PaddingMethodJsonTest extends AbstractJsonSerializationTestSuite<PaddingMethod> {
  @Override
  public Class<PaddingMethod> type() {
    return PaddingMethod.class;
  }

  @Override
  public PaddingMethod createDefault() {
    return PaddingMethod.Standard.NONE.inst();
  }

  @Override
  public PaddingMethod createVariant() {
    return PaddingMethod.Standard.PKCS5.inst();
  }
}
