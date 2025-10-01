package org.purpleBean.kmip.benchmark.subjects.common.enumeration;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.enumeration.NameType;

public class NameTypeBenchmarkSubject extends KmipBenchmarkSubject<NameType> {

    public NameTypeBenchmarkSubject() throws Exception {
        NameType nameType = new NameType(NameType.Standard.UNINTERPRETED_TEXT_STRING);
        initialize(nameType, NameType.class);
    }

    @Override
    public String name() {
        return "NameType";
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
