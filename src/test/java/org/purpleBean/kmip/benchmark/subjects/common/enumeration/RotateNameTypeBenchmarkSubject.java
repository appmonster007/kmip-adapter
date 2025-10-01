package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.RotateNameType;

public class RotateNameTypeBenchmarkSubject extends KmipBenchmarkSubject<RotateNameType> {

    public RotateNameTypeBenchmarkSubject() throws Exception {
        RotateNameType rotateNameType = new RotateNameType(RotateNameType.Standard.UNINTERPRETED_TEXT_STRING);
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
