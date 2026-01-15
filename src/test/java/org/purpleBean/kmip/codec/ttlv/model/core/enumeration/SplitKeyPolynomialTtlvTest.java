package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SplitKeyPolynomial TTLV Serialization")
class SplitKeyPolynomialTtlvTest extends AbstractTtlvSerializationTestSuite<SplitKeyPolynomial> {
    @Override
    protected Class<SplitKeyPolynomial> type() {
        return SplitKeyPolynomial.class;
    }

    @Override
    protected SplitKeyPolynomial createDefault() {
        return SplitKeyPolynomial.Standard.POLYNOMIAL_283.inst();
    }

    @Override
    protected SplitKeyPolynomial createVariant() {
        return SplitKeyPolynomial.Standard.POLYNOMIAL_285.inst();
    }
}
