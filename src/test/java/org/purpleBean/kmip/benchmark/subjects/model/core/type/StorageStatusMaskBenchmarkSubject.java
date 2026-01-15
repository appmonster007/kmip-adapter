package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;

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