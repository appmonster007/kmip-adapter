package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ProtectStopDateBenchmarkSubject extends KmipBenchmarkSubject<ProtectStopDate> {

    public ProtectStopDateBenchmarkSubject() throws Exception {
        ProtectStopDate protectStopDate = ProtectStopDate.builder()
                .value(OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC))
                .build();
        initialize(protectStopDate, ProtectStopDate.class);
    }

    @Override
    public String name() {
        return "ProtectStopDate";
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