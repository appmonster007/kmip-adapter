package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.LastChangeDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class LastChangeDateBenchmarkSubject extends KmipBenchmarkSubject<LastChangeDate> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

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
