package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyPolynomial JSON Serialization")
class SplitKeyPolynomialJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyPolynomial> {
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
