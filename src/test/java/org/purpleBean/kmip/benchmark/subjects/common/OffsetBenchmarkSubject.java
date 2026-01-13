package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.Offset;

public class OffsetBenchmarkSubject extends KmipBenchmarkSubject<Offset> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public OffsetBenchmarkSubject() throws Exception {
        Offset offset = Offset.builder().value(10).build();
        initialize(offset, Offset.class);
    }

    @Override
    public String name() {
        return "Offset";
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