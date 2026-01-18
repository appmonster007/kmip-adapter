package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyPolynomial> {

    public SplitKeyPolynomialBenchmarkSubject() throws Exception {
        SplitKeyPolynomial splitKeyPolynomial = SplitKeyPolynomial.Standard.POLYNOMIAL_283.inst();
        initialize(splitKeyPolynomial, SplitKeyPolynomial.class);
    }

    @Override
    public String name() {
        return "SplitKeyPolynomial";
    }

}
