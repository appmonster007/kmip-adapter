package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

public class AttributeValueIntervalBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.Interval> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueIntervalBenchmarkSubject() throws Exception {
        AttributeValue.Interval attributeValueInterval = AttributeValue.Interval.of(123);
        initialize(attributeValueInterval, AttributeValue.Interval.class);
    }

    @Override
    public String name() {
        return "AttributeValueInterval";
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
