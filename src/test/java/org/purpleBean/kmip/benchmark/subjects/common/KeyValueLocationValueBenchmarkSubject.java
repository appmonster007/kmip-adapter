package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyValueLocationValue;

public class KeyValueLocationValueBenchmarkSubject extends KmipBenchmarkSubject<KeyValueLocationValue> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public KeyValueLocationValueBenchmarkSubject() throws Exception {
        KeyValueLocationValue keyValueLocationValue = KeyValueLocationValue.builder().value("test").build();
        initialize(keyValueLocationValue, KeyValueLocationValue.class);
    }

    @Override
    public String name() {
        return "KeyValueLocationValue";
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