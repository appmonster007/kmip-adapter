package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidityIndicator TTLV Serialization")
class ValidityIndicatorTtlvTest extends AbstractTtlvSerializationTestSuite<ValidityIndicator> {
  @Override
  public Class<ValidityIndicator> type() {
    return ValidityIndicator.class;
  }

  @Override
  public ValidityIndicator createDefault() {
    return ValidityIndicator.Standard.VALID.inst();
  }

  @Override
  public ValidityIndicator createVariant() {
    return ValidityIndicator.Standard.INVALID.inst();
  }
}
