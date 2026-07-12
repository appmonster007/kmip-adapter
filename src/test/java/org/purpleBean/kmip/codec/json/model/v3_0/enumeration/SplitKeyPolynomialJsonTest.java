package org.purpleBean.kmip.codec.json.model.v3_0.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v3_0.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyPolynomial JSON Serialization")
class SplitKeyPolynomialJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyPolynomial> {
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
