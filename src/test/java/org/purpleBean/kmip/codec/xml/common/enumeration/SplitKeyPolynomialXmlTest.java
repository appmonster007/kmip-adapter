package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("SplitKeyPolynomial XML Serialization")
class SplitKeyPolynomialXmlTest extends AbstractXmlSerializationSuite<SplitKeyPolynomial> {
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
