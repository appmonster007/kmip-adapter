package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.AttributeValue;

public class AttributeValueIntegerBenchmarkSubject extends KmipBenchmarkSubject<AttributeValue.Integer> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public AttributeValueIntegerBenchmarkSubject() throws Exception {
        AttributeValue.Integer attributeValueInteger = AttributeValue.Integer.of(123);
        initialize(attributeValueInteger, AttributeValue.Integer.class);
    }

    @Override
    public String name() {
        return "AttributeValueInteger";
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
