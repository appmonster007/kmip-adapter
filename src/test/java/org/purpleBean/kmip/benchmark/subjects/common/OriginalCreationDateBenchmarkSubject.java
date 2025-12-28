package org.purpleBean.kmip.benchmark.subjects.common;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OriginalCreationDateBenchmarkSubject extends KmipBenchmarkSubject<OriginalCreationDate> {

    public OriginalCreationDateBenchmarkSubject() throws Exception {
        OriginalCreationDate originalCreationDate = OriginalCreationDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(originalCreationDate, OriginalCreationDate.class);
    }

    @Override
    public String name() {
        return "OriginalCreationDate";
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