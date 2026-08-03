package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DerivationMethod TTLV Serialization")
class DerivationMethodTtlvTest extends AbstractTtlvSerializationTestSuite<DerivationMethod> {
  @Override
  public Class<DerivationMethod> type() {
    return DerivationMethod.class;
  }

  @Override
  public DerivationMethod createDefault() {
    return DerivationMethod.Standard.PBKDF2.inst();
  }

  @Override
  public DerivationMethod createVariant() {
    return DerivationMethod.Standard.HASH.inst();
  }
}
