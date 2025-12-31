package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.KeyValuePresent;

public class KeyValuePresentBenchmarkSubject extends KmipBenchmarkSubject<KeyValuePresent> {

    public KeyValuePresentBenchmarkSubject() throws Exception {
        KeyValuePresent keyValuePresent = KeyValuePresent.of(Boolean.FALSE);
        initialize(keyValuePresent, KeyValuePresent.class);
    }

    @Override
    public String name() {
        return "KeyValuePresent";
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