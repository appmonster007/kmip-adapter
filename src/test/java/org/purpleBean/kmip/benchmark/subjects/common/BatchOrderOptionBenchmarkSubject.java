package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.BatchOrderOption;

public class BatchOrderOptionBenchmarkSubject extends KmipBenchmarkSubject<BatchOrderOption> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public BatchOrderOptionBenchmarkSubject() throws Exception {
        BatchOrderOption batchOrderOption = BatchOrderOption.builder().value(true).build();
        initialize(batchOrderOption, BatchOrderOption.class);
    }

    @Override
    public String name() {
        return "BatchOrderOption";
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