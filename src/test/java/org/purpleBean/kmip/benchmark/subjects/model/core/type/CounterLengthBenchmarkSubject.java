package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CounterLength;

public class CounterLengthBenchmarkSubject extends KmipBenchmarkSubject<CounterLength> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public CounterLengthBenchmarkSubject() throws Exception {
        CounterLength counterLength = CounterLength.of(128);
        initialize(counterLength, CounterLength.class);
    }

    @Override
    public String name() {
        return "CounterLength";
    }

}