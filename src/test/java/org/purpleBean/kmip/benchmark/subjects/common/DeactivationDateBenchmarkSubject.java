package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DeactivationDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DeactivationDateBenchmarkSubject extends KmipBenchmarkSubject<DeactivationDate> {

    public DeactivationDateBenchmarkSubject() throws Exception {
        DeactivationDate deactivationDate = DeactivationDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(deactivationDate, DeactivationDate.class);
    }

    @Override
    public String name() {
        return "DeactivationDate";
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