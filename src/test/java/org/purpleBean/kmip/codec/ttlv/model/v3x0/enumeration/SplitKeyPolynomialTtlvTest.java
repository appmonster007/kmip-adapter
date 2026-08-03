package org.purplebean.kmip.codec.ttlv.model.v3x0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.enumeration.SplitKeyPolynomial;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SplitKeyPolynomial TTLV Serialization")
class SplitKeyPolynomialTtlvTest extends AbstractTtlvSerializationTestSuite<SplitKeyPolynomial> {
  @Override
  public Class<SplitKeyPolynomial> type() {
    return SplitKeyPolynomial.class;
  }

  @Override
  public SplitKeyPolynomial createDefault() {
    return SplitKeyPolynomial.Standard.POLYNOMIAL_283.inst();
  }

  @Override
  public SplitKeyPolynomial createVariant() {
    return SplitKeyPolynomial.Standard.POLYNOMIAL_285.inst();
  }
}
