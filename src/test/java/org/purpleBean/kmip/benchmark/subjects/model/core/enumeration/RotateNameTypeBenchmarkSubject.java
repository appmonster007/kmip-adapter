package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.RotateNameType;

public class RotateNameTypeBenchmarkSubject extends KmipBenchmarkSubject<RotateNameType> {

    public RotateNameTypeBenchmarkSubject() throws Exception {
        RotateNameType rotateNameType = RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
        initialize(rotateNameType, RotateNameType.class);
    }

    @Override
    public String name() {
        return "RotateNameType";
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
