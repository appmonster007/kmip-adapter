package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueDateTime;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueDateTime> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueDateTimeBenchmarkSubject() throws Exception {
        AttributeValueDateTime attributeValueDateTime = AttributeValueDateTime.of(OffsetDateTime.now());
        initialize(attributeValueDateTime, AttributeValueDateTime.class);
    }

    @Override
    public String name() {
        return "AttributeValueDateTime";
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
