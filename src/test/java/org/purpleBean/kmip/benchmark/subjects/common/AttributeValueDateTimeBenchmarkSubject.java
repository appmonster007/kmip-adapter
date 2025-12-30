package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

import java.time.OffsetDateTime;

public class AttributeValueDateTimeBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.DateTime> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueDateTimeBenchmarkSubject() throws Exception {
        AttributeValue.DateTime attributeValueDateTime = AttributeValue.DateTime.of(OffsetDateTime.now());
        initialize(attributeValueDateTime, AttributeValue.DateTime.class);
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
