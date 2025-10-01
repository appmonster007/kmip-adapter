package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ActivationDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ActivationDateBenchmarkSubject extends KmipBenchmarkSubject<ActivationDate> {

    public ActivationDateBenchmarkSubject() throws Exception {
        ActivationDate activationDate = ActivationDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(activationDate, ActivationDate.class);
    }

    @Override
    public String name() {
        return "ActivationDate";
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
