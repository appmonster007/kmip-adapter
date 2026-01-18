package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;

public class ValidationAuthorityTypeBenchmarkSubject extends KmipBenchmarkSubject<ValidationAuthorityType> {

    public ValidationAuthorityTypeBenchmarkSubject() throws Exception {
        ValidationAuthorityType validationAuthorityType = ValidationAuthorityType.Standard.UNSPECIFIED.inst();
        initialize(validationAuthorityType, ValidationAuthorityType.class);
    }

    @Override
    public String name() {
        return "ValidationAuthorityType";
    }

}
