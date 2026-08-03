package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyMethod JSON Serialization")
class SplitKeyMethodJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyMethod> {
  @Override
  public Class<SplitKeyMethod> type() {
    return SplitKeyMethod.class;
  }

  @Override
  public SplitKeyMethod createDefault() {
    return SplitKeyMethod.Standard.XOR.inst();
  }

  @Override
  public SplitKeyMethod createVariant() {
    return SplitKeyMethod.Standard.POLYNOMIAL_SHARING_GF_216.inst();
  }
}
