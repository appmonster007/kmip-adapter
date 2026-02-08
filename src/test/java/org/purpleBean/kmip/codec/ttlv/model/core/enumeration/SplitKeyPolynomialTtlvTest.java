package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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
