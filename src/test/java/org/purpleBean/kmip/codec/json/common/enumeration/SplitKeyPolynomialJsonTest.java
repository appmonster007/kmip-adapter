package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("SplitKeyPolynomial JSON Serialization")
class SplitKeyPolynomialJsonTest extends AbstractJsonSerializationSuite<SplitKeyPolynomial> {
    @Override
    protected Class<SplitKeyPolynomial> type() {
        return SplitKeyPolynomial.class;
    }

    @Override
    protected SplitKeyPolynomial createDefault() {
        return new SplitKeyPolynomial(SplitKeyPolynomial.Standard.POLYNOMIAL_283);
    }

    @Override
    protected SplitKeyPolynomial createVariant() {
        return new SplitKeyPolynomial(SplitKeyPolynomial.Standard.POLYNOMIAL_285);
    }
}
