package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ValidationType;

public class ValidationTypeBenchmarkSubject extends KmipBenchmarkSubject<ValidationType> {

    public ValidationTypeBenchmarkSubject() throws Exception {
        ValidationType validationType = new ValidationType(ValidationType.Standard.UNSPECIFIED);
        initialize(validationType, ValidationType.class);
    }

    @Override
    public String name() {
        return "ValidationType";
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
