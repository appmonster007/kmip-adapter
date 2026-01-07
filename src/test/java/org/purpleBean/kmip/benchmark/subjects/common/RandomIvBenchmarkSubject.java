package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.RandomIv;

public class RandomIvBenchmarkSubject extends KmipBenchmarkSubject<RandomIv> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public RandomIvBenchmarkSubject() throws Exception {
        RandomIv randomIv = RandomIv.of(true);
        initialize(randomIv, RandomIv.class);
    }

    @Override
    public String name() {
        return "RandomIv";
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