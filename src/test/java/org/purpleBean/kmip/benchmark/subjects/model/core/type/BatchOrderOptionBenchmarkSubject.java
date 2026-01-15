package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionBenchmarkSubject extends KmipBenchmarkSubject<BatchOrderOption> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

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