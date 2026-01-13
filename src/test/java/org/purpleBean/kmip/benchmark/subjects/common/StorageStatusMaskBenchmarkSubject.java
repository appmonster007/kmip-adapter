package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.StorageStatusMask;

public class StorageStatusMaskBenchmarkSubject extends KmipBenchmarkSubject<StorageStatusMask> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public StorageStatusMaskBenchmarkSubject() throws Exception {
        StorageStatusMask storageStatusMask = StorageStatusMask.builder().value(1).build();
        initialize(storageStatusMask, StorageStatusMask.class);
    }

    @Override
    public String name() {
        return "StorageStatusMask";
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