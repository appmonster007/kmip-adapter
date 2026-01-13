package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AlternativeNameValue;

public class AlternativeNameValueBenchmarkSubject extends KmipBenchmarkSubject<AlternativeNameValue> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AlternativeNameValueBenchmarkSubject() throws Exception {
        AlternativeNameValue alternativeNameValue = AlternativeNameValue.of("some value");
        initialize(alternativeNameValue, AlternativeNameValue.class);
    }

    @Override
    public String name() {
        return "AlternativeNameValue";
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