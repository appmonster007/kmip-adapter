package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CompromiseDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CompromiseDateBenchmarkSubject extends KmipBenchmarkSubject<CompromiseDate> {

    public CompromiseDateBenchmarkSubject() throws Exception {
        CompromiseDate compromiseDate = CompromiseDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(compromiseDate, CompromiseDate.class);
    }

    @Override
    public String name() {
        return "CompromiseDate";
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