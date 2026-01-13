package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.BatchCount;

public class BatchCountBenchmarkSubject extends KmipBenchmarkSubject<BatchCount> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public BatchCountBenchmarkSubject() throws Exception {
        BatchCount batchCount = BatchCount.builder().value(5).build();
        initialize(batchCount, BatchCount.class);
    }

    @Override
    public String name() {
        return "BatchCount";
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