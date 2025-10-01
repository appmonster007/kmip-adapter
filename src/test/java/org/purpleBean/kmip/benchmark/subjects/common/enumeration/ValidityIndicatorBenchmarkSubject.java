package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ValidityIndicator;

public class ValidityIndicatorBenchmarkSubject extends KmipBenchmarkSubject<ValidityIndicator> {

    public ValidityIndicatorBenchmarkSubject() throws Exception {
        ValidityIndicator validityIndicator = new ValidityIndicator(ValidityIndicator.Standard.VALID);
        initialize(validityIndicator, ValidityIndicator.class);
    }

    @Override
    public String name() {
        return "ValidityIndicator";
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
