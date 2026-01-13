package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Qlength;

public class QlengthBenchmarkSubject extends KmipBenchmarkSubject<Qlength> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public QlengthBenchmarkSubject() throws Exception {
        Qlength qlength = Qlength.builder().value(128).build();
        initialize(qlength, Qlength.class);
    }

    @Override
    public String name() {
        return "Qlength";
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