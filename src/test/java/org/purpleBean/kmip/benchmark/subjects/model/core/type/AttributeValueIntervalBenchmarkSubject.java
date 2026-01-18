package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

public class AttributeValueIntervalBenchmarkSubject extends KmipBenchmarkSubject<AttributeValueInterval> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueIntervalBenchmarkSubject() throws Exception {
        AttributeValueInterval attributeValueInterval = AttributeValueInterval.of(123);
        initialize(attributeValueInterval, AttributeValueInterval.class);
    }

    @Override
    public String name() {
        return "AttributeValueInterval";
    }

}
