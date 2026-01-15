package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.NameValue;

public class NameValueBenchmarkSubject extends KmipBenchmarkSubject<NameValue> {

    public NameValueBenchmarkSubject() throws Exception {
        NameValue nameValue = NameValue.builder().value("some-name").build();
        initialize(nameValue, NameValue.class);
    }

    @Override
    public String name() {
        return "NameValue";
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
