package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PaddingMethod TTLV Serialization")
class PaddingMethodTtlvTest extends AbstractTtlvSerializationTestSuite<PaddingMethod> {
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
