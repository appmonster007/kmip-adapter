package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.structure.Attribute;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class AttributeBenchmarkSubject extends KmipBenchmarkSubject<Attribute> {

    public AttributeBenchmarkSubject() throws Exception {
        var fixed = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);
        ActivationDate activationDate = ActivationDate.builder().value(fixed).build();
        Attribute attribute = Attribute.of(activationDate);
        initialize(attribute, Attribute.class);
    }

    @Override
    public String name() {
        return "Attribute";
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
