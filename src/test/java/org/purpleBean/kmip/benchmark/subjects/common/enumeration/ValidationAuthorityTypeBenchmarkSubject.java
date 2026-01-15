package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeBenchmarkSubject extends KmipBenchmarkSubject<ValidationAuthorityType> {

    public ValidationAuthorityTypeBenchmarkSubject() throws Exception {
        ValidationAuthorityType validationAuthorityType = ValidationAuthorityType.Standard.UNSPECIFIED.inst();
        initialize(validationAuthorityType, ValidationAuthorityType.class);
    }

    @Override
    public String name() {
        return "ValidationAuthorityType";
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
