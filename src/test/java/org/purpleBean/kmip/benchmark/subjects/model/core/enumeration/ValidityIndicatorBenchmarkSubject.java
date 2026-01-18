package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidityIndicator;

public class ValidityIndicatorBenchmarkSubject extends KmipBenchmarkSubject<ValidityIndicator> {

    public ValidityIndicatorBenchmarkSubject() throws Exception {
        ValidityIndicator validityIndicator = ValidityIndicator.Standard.VALID.inst();
        initialize(validityIndicator, ValidityIndicator.class);
    }

    @Override
    public String name() {
        return "ValidityIndicator";
    }

}
