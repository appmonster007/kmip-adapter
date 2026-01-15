package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;

public class AlternativeNameTypeBenchmarkSubject extends KmipBenchmarkSubject<AlternativeNameType> {

    public AlternativeNameTypeBenchmarkSubject() throws Exception {
        AlternativeNameType alternativeNameType = AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
        initialize(alternativeNameType, AlternativeNameType.class);
    }

    @Override
    public String name() {
        return "AlternativeNameType";
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
