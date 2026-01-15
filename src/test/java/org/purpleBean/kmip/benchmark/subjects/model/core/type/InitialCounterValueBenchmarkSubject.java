package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;

public class InitialCounterValueBenchmarkSubject extends KmipBenchmarkSubject<InitialCounterValue> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public InitialCounterValueBenchmarkSubject() throws Exception {
        InitialCounterValue initialCounterValue = InitialCounterValue.of(1);
        initialize(initialCounterValue, InitialCounterValue.class);
    }

    @Override
    public String name() {
        return "InitialCounterValue";
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