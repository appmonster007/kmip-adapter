package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CompromiseOccurrenceDateBenchmarkSubject extends KmipBenchmarkSubject<CompromiseOccurrenceDate> {

    public CompromiseOccurrenceDateBenchmarkSubject() throws Exception {
        CompromiseOccurrenceDate compromiseOccurrenceDate = CompromiseOccurrenceDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(compromiseOccurrenceDate, CompromiseOccurrenceDate.class);
    }

    @Override
    public String name() {
        return "CompromiseOccurrenceDate";
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