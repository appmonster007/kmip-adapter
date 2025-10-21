package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.LastChangeDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class LastChangeDateBenchmarkSubject extends KmipBenchmarkSubject<LastChangeDate> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public LastChangeDateBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        LastChangeDate lastChangeDate = LastChangeDate.builder().value(fixed).build();
        initialize(lastChangeDate, LastChangeDate.class);
    }

    @Override
    public String name() {
        return "LastChangeDate";
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
