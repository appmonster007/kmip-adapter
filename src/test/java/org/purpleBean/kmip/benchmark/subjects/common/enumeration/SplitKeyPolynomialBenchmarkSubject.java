package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.SplitKeyPolynomial;

public class SplitKeyPolynomialBenchmarkSubject extends KmipBenchmarkSubject<SplitKeyPolynomial> {

    public SplitKeyPolynomialBenchmarkSubject() throws Exception {
        SplitKeyPolynomial splitKeyPolynomial = new SplitKeyPolynomial(SplitKeyPolynomial.Standard.POLYNOMIAL_283);
        initialize(splitKeyPolynomial, SplitKeyPolynomial.class);
    }

    @Override
    public String name() {
        return "SplitKeyPolynomial";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
