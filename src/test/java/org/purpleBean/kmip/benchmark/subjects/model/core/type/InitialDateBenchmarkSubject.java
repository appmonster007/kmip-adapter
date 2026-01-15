package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.InitialDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class InitialDateBenchmarkSubject extends KmipBenchmarkSubject<InitialDate> {

    public InitialDateBenchmarkSubject() throws Exception {
        InitialDate initialDate = InitialDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(initialDate, InitialDate.class);
    }

    @Override
    public String name() {
        return "InitialDate";
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