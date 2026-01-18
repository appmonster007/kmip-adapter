package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;

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

}