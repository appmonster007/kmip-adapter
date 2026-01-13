package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ValidityDate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ValidityDateBenchmarkSubject extends KmipBenchmarkSubject<ValidityDate> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public ValidityDateBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ValidityDate validityDate = ValidityDate.builder().value(fixed).build();
        initialize(validityDate, ValidityDate.class);
    }

    @Override
    public String name() {
        return "ValidityDate";
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