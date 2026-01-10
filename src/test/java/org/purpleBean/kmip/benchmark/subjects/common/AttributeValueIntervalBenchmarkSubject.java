package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValueInterval;

public class AttributeValueIntervalBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueInterval> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueIntervalBenchmarkSubject() throws Exception {
        AttributeValueInterval attributeValueInterval = AttributeValueInterval.of(123);
        initialize(attributeValueInterval, AttributeValueInterval.class);
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
