package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.TimeStamp;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimeStampBenchmarkSubject extends KmipBenchmarkSubject<TimeStamp> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public TimeStampBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        TimeStamp timeStamp = TimeStamp.builder().value(fixed).build();
        initialize(timeStamp, TimeStamp.class);
    }

    @Override
    public String name() {
        return "TimeStamp";
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