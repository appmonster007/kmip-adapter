package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DestroyDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DestroyDateBenchmarkSubject extends KmipBenchmarkSubject<DestroyDate> {

    public DestroyDateBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        DestroyDate destroyDate = DestroyDate.builder().value(fixed).build();
        initialize(destroyDate, DestroyDate.class);
    }

    @Override
    public String name() {
        return "DestroyDate";
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
